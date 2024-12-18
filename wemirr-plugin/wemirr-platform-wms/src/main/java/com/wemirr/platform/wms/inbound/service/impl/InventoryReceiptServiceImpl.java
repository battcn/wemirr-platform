package com.wemirr.platform.wms.inbound.service.impl;

import com.baomidou.dynamic.datasource.annotation.DSTransactional;
import com.wemirr.framework.commons.BeanUtilPlus;
import com.wemirr.framework.commons.MapHelper;
import com.wemirr.framework.commons.exception.CheckedException;
import com.wemirr.framework.commons.security.AuthenticationContext;
import com.wemirr.framework.db.mybatisplus.ext.SuperServiceImpl;
import com.wemirr.framework.db.mybatisplus.wrap.Wraps;
import com.wemirr.framework.db.mybatisplus.wrap.query.LbqWrapper;
import com.wemirr.framework.redis.plus.sequence.RedisSequenceHelper;
import com.wemirr.platform.wms.basic.domain.req.ContainerOccupyReleaseReq;
import com.wemirr.platform.wms.basic.service.ContainerService;
import com.wemirr.platform.wms.inbound.domain.dto.ReceiptQtyDTO;
import com.wemirr.platform.wms.inbound.domain.entity.InventoryReceipt;
import com.wemirr.platform.wms.inbound.domain.entity.InventoryReceiptItem;
import com.wemirr.platform.wms.inbound.domain.entity.ReceivingPlan;
import com.wemirr.platform.wms.inbound.domain.enums.*;
import com.wemirr.platform.wms.inbound.domain.req.InventoryReceiptItemSaveReq;
import com.wemirr.platform.wms.inbound.domain.req.InventoryReceiptSaveReq;
import com.wemirr.platform.wms.inbound.domain.req.InventoryReceiptSubmitReq;
import com.wemirr.platform.wms.inbound.domain.resp.InventoryReceiptDetailResp;
import com.wemirr.platform.wms.inbound.domain.resp.InventoryReceiptItemPageResp;
import com.wemirr.platform.wms.inbound.mapper.InventoryReceiptItemMapper;
import com.wemirr.platform.wms.inbound.mapper.InventoryReceiptMapper;
import com.wemirr.platform.wms.inbound.mapper.ReceivingPlanMapper;
import com.wemirr.platform.wms.inbound.service.InventoryReceiptService;
import com.wemirr.platform.wms.stock.domain.req.StockSaveReq;
import com.wemirr.platform.wms.stock.service.StockService;
import lombok.AllArgsConstructor;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.*;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.toList;

/**
 * <p>
 * 入库单 服务类
 * </p>
 *
 * @author ddCat
 * @since 2024-06-27
 */
@Service
@AllArgsConstructor
public class InventoryReceiptServiceImpl extends SuperServiceImpl<InventoryReceiptMapper, InventoryReceipt> implements InventoryReceiptService {

    private final InventoryReceiptItemMapper inventoryReceiptItemMapper;
    private final ContainerService containerService;
    private final ReceivingPlanMapper receivingPlanMapper;
    private final StockService stockService;
    private final RedisSequenceHelper sequenceHelper;
    private final AuthenticationContext authenticationContext;
    private final AuthenticationContext context;

    @Override
    @Transactional
    public void saveOrUpdateInventoryReceipt(Long id, InventoryReceiptSaveReq req) {
        if (ObjectUtils.isEmpty(req.getItems())) {
            throw CheckedException.badRequest("明细行不能为空");
        }
        InventoryReceipt oldInventoryReceipt = new InventoryReceipt();
        if (id != null) {
            oldInventoryReceipt = this.getById(id);
            if (InventoryReceiptStatus.DRAFT != oldInventoryReceipt.getStatus()) {
                // 草稿才可以编辑
                throw CheckedException.badRequest("草稿才可编辑");
            }
            LbqWrapper<InventoryReceiptItem> lbqWrapper = Wraps.<InventoryReceiptItem>lbQ().eq(InventoryReceiptItem::getReceiptId, id);
            inventoryReceiptItemMapper.delete(lbqWrapper);
        }
        InventoryReceipt inventoryReceipt = InventoryReceipt.builder()
                .planId(req.getPlanId())
                .supplierId(req.getSupplierId())
                .warehouseId(req.getWarehouseId())
                .remark(req.getRemark())
                .planNum(req.getPlanNum())
                .build();
        if (id != null) {
            inventoryReceipt.setId(oldInventoryReceipt.getId());
            inventoryReceipt.setStatus(oldInventoryReceipt.getStatus());
            inventoryReceipt.setReceiptNum(oldInventoryReceipt.getReceiptNum());
            inventoryReceipt.setPlanId(oldInventoryReceipt.getPlanId());
            inventoryReceipt.setPlanNum(oldInventoryReceipt.getPlanNum());
        } else {
            inventoryReceipt.setStatus(InventoryReceiptStatus.DRAFT);
            inventoryReceipt.setReceiptNum(sequenceHelper.generate(WmsSequence.INVENTORY_RECEIPT, authenticationContext.tenantId()));
        }
        this.saveOrUpdate(inventoryReceipt);
        id = inventoryReceipt.getId();
        // 删除明细行项旧数据
        inventoryReceiptItemMapper.delete(Wraps.<InventoryReceiptItem>lbQ().eq(InventoryReceiptItem::getReceiptId, id));
        List<InventoryReceiptItem> items = new ArrayList<>();
        for (InventoryReceiptItemSaveReq item : req.getItems()) {
            // TODO 计量单位转换待完成
            BigDecimal qty = item.getReceivingQty();
            String unit = item.getReceivingUnit();
            InventoryReceiptItem inventoryReceiptItem = InventoryReceiptItem.builder()
                    .receiptId(id)
                    .planItemId(item.getPlanItemId())
                    .materialId(item.getMaterialId())
                    .locationId(item.getLocationId())
                    .batchNum(item.getBatchNum())
                    .receivingQty(item.getReceivingQty())
                    .receivingUnit(item.getReceivingUnit())
                    .qty(qty)
                    .unit(unit)
                    .remark(item.getRemark())
                    .expiryDate(item.getExpiryDate())
                    .productionDate(item.getProductionDate())
                    .build();
            items.add(inventoryReceiptItem);
        }
        inventoryReceiptItemMapper.insertBatch(items);
    }

    @Override
    @DSTransactional(rollbackFor = Exception.class)
    public void removeInventoryReceipt(Long id) {
        InventoryReceipt inventoryReceipt = this.getById(id);
        if (InventoryReceiptStatus.DRAFT != inventoryReceipt.getStatus()) {
            throw CheckedException.badRequest("草稿的入库单才可删除");
        }
        LbqWrapper<InventoryReceiptItem> lbqWrapper = Wraps.<InventoryReceiptItem>lbQ().eq(InventoryReceiptItem::getReceiptId, id);
        this.inventoryReceiptItemMapper.delete(lbqWrapper);
        this.removeById(id);
        // 返还数量
        List<InventoryReceiptItem> inventoryReceiptItems = this.inventoryReceiptItemMapper.selectList(lbqWrapper);
        Map<Long, InventoryReceiptItem> inventoryReceiptItemMap = inventoryReceiptItems.stream()
                .collect(Collectors.toMap(InventoryReceiptItem::getPlanItemId, inventoryReceiptItem -> inventoryReceiptItem));
        // 判断收货计划是否全部完成
        List<ReceiptQtyDTO> receiptQtys = inventoryReceiptItemMapper.selectReceiptQtyByPlanId(inventoryReceipt.getPlanId());
        boolean complete = true;
        for (ReceiptQtyDTO receiptQty : receiptQtys) {
            InventoryReceiptItem inventoryReceiptItem = inventoryReceiptItemMap.get(receiptQty.getPlanItemId());
            if (inventoryReceiptItem == null) {
                complete = false;
                continue;
            }
            // 该明细行 收货进行中的数量 不等于 本次入库单的数量
            if (inventoryReceiptItem.getQty().compareTo(receiptQty.getProgressQty().add(receiptQty.getCompleteQty())) != 0) {
                complete = false;
            }
        }
        if (complete) {
            ReceivingPlan receivingPlan = receivingPlanMapper.selectById(inventoryReceipt.getPlanId());
            receivingPlan.setStatus(ReceivingStatus.WAIT);
            receivingPlanMapper.updateById(receivingPlan);
        }
    }

    @Override
    @DSTransactional(rollbackFor = Exception.class)
    public void submit(Long id, InventoryReceiptSubmitReq req) {
        InventoryReceipt receipt = Optional.ofNullable(this.getById(id)).orElseThrow(() -> CheckedException.notFound("入库单不存在"));
        if (InventoryReceiptStatus.DRAFT != receipt.getStatus()) {
            throw CheckedException.badRequest("草稿才可提交");
        }
        long notConfigLocation = req.getItems().stream()
                .map(InventoryReceiptSubmitReq.InventoryReceiptItemSubmitReq::getLocationId)
                .filter(Objects::isNull).count();
        if (notConfigLocation > 0) {
            throw CheckedException.badRequest("请先配置储位");
        }

        List<InventoryReceiptItem> itemList = req.getItems().stream().map(x -> InventoryReceiptItem.builder()
                .id(x.getId()).locationId(x.getLocationId()).build()).collect(toList());
        this.baseMapper.updateById(InventoryReceipt.builder().id(id).status(InventoryReceiptStatus.COMPLETED).build());
        this.inventoryReceiptItemMapper.updateBatch(itemList);
        List<InventoryReceiptItem> receiptItemList = inventoryReceiptItemMapper.selectList(InventoryReceiptItem::getReceiptId, id);
        Map<Long, InventoryReceiptItem> itemMap = MapHelper.toHashMap(receiptItemList, InventoryReceiptItem::getPlanItemId, x -> x);
        // 判断收货计划是否全部完成
        List<ReceiptQtyDTO> receiptQtyList = inventoryReceiptItemMapper.selectReceiptQtyByPlanId(receipt.getPlanId());
        boolean complete = receiptQtyList.stream()
                .allMatch(receiptQty -> {
                    InventoryReceiptItem inventoryReceiptItem = itemMap.get(receiptQty.getPlanItemId());
                    return inventoryReceiptItem != null &&
                            // 该明细行 收货进行中的数量 等于 本次入库单的数量 则认为完成
                            inventoryReceiptItem.getQty().compareTo(receiptQty.getProgressQty()) == 0;
                });
        if (complete) {
            ReceivingPlan receivingPlan = receivingPlanMapper.selectById(receipt.getPlanId());
            // 释放容器
            ContainerOccupyReleaseReq releaseReq = ContainerOccupyReleaseReq.builder()
                    .containerId(receivingPlan.getContainerId()).docId(receivingPlan.getId())
                    .status(ContainerStatus.RELEASE).occupationTaskType(ContainerTaskType.RECEIVING_PLAN).build();
            containerService.occupyOrRelease(List.of(releaseReq));
            receivingPlanMapper.updateById(ReceivingPlan.builder().id(receivingPlan.getId()).status(ReceivingStatus.COMPLETED).build());
        }
        // 上架库存操作
        List<StockSaveReq> stocks = receiptItemList.stream().map(item -> StockSaveReq.builder()
                .warehouseId(receipt.getWarehouseId()).docId(receipt.getId()).docItemId(item.getId())
                .locationId(item.getLocationId())
                .materialId(item.getMaterialId())
                .batchNum(item.getBatchNum())
                .productionDate(item.getProductionDate())
                .expiryDate(item.getExpiryDate())
                .qty(item.getQty()).unit(item.getUnit())
                .level(StockLevelStatus.GOODS)
                .changeType(StockChangeType.IN)
                .remarks(item.getRemark())
                .build()).collect(toList());
        stockService.saveStocks(stocks);
    }

    @Override
    public InventoryReceiptDetailResp detail(Long id) {
        InventoryReceipt receipt = this.baseMapper.selectById(id);
        if (receipt == null) {
            throw CheckedException.notFound("入库单不存在");
        }
        InventoryReceiptDetailResp result = BeanUtilPlus.toBean(receipt, InventoryReceiptDetailResp.class);
        List<InventoryReceiptItem> receiptItemList = this.inventoryReceiptItemMapper.selectList(InventoryReceiptItem::getReceiptId, id);
        List<InventoryReceiptItemPageResp> items = BeanUtilPlus.toBeans(receiptItemList, InventoryReceiptItemPageResp.class);
        result.setItems(items);
        return result;
    }


}
