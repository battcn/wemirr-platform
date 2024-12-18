package com.wemirr.platform.wms.inbound.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.lang.Pair;
import com.baomidou.dynamic.datasource.annotation.DSTransactional;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
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
import com.wemirr.platform.wms.inbound.domain.entity.ReceivingPlan;
import com.wemirr.platform.wms.inbound.domain.entity.ReceivingPlanItem;
import com.wemirr.platform.wms.inbound.domain.enums.*;
import com.wemirr.platform.wms.inbound.domain.req.InventoryReceiptItemSaveReq;
import com.wemirr.platform.wms.inbound.domain.req.InventoryReceiptSaveReq;
import com.wemirr.platform.wms.inbound.domain.req.ReceivingPlanItemReceivingReq;
import com.wemirr.platform.wms.inbound.domain.req.ReceivingPlanSaveReq;
import com.wemirr.platform.wms.inbound.domain.resp.ReceivingPlanDetailResp;
import com.wemirr.platform.wms.inbound.domain.resp.ReceivingPlanItemPageResp;
import com.wemirr.platform.wms.inbound.mapper.InventoryReceiptItemMapper;
import com.wemirr.platform.wms.inbound.mapper.ReceivingPlanItemMapper;
import com.wemirr.platform.wms.inbound.mapper.ReceivingPlanMapper;
import com.wemirr.platform.wms.inbound.service.InventoryReceiptService;
import com.wemirr.platform.wms.inbound.service.ReceivingPlanService;
import com.wemirr.platform.wms.matedata.domain.entity.Material;
import com.wemirr.platform.wms.matedata.mapper.MaterialMapper;
import lombok.AllArgsConstructor;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;

import static java.util.Comparator.comparing;

/**
 * <p>
 * 收货计划 服务类
 * </p>
 *
 * @author ddCat
 * @since 2024-06-24
 */
@Service
@AllArgsConstructor
public class ReceivingPlanServiceImpl extends SuperServiceImpl<ReceivingPlanMapper, ReceivingPlan> implements ReceivingPlanService {

    private final ReceivingPlanItemMapper receivingPlanItemMapper;
    private final MaterialMapper materialMapper;
    private final InventoryReceiptItemMapper itemsReceiptItemMapper;
    private final InventoryReceiptService inventoryReceiptService;
    private final RedisSequenceHelper sequenceHelper;
    private final AuthenticationContext context;
    private final ContainerService containerService;

    @Override
    public IPage<ReceivingPlanItemPageResp> itemPage(Page<ReceivingPlanItem> page, Long planId) {
        LbqWrapper<ReceivingPlanItem> eq = Wraps.<ReceivingPlanItem>lbQ().eq(ReceivingPlanItem::getReceivingPlanId, planId);
        // 这里应该写 XML 查询 并且返回 ReceivingPlanItemPageResp 和  已收货数  未收货数
        Page<ReceivingPlanItem> itemPage = receivingPlanItemMapper.selectPage(page, eq);
        List<ReceiptQtyDTO> receiptQtyList = itemsReceiptItemMapper.selectReceiptQtyByPlanId(planId);
        Map<Long, ReceiptQtyDTO> receiptQtyMap = receiptQtyList.stream().collect(Collectors.toMap(ReceiptQtyDTO::getPlanItemId, Function.identity()));
        return itemPage.convert(item -> {
            ReceivingPlanItemPageResp resp = BeanUtilPlus.toBean(item, ReceivingPlanItemPageResp.class);
            ReceiptQtyDTO receiptQtyDTO = receiptQtyMap.get(item.getId());
            if (receiptQtyDTO != null) {
                resp.setCompleteQty(receiptQtyDTO.getCompleteQty());
                resp.setProgressQty(receiptQtyDTO.getProgressQty());
            }
            return resp;
        });
    }

    @Override
    @DSTransactional(rollbackFor = Exception.class)
    public void create(ReceivingPlanSaveReq req) {
        if (ObjectUtils.isEmpty(req.getItems())) {
            throw CheckedException.badRequest("收货计划明细行不能为空");
        }
        var bean = BeanUtilPlus.toBean(req, ReceivingPlan.class);
        bean.setPlanNum(sequenceHelper.generate(WmsSequence.RECEIVING_PLAN, context.tenantId()));
        // 当前只有草稿状态可编辑收货计划，防止状态被编辑接口篡改
        bean.setStatus(ReceivingStatus.DRAFT);
        this.baseMapper.insert(bean);
        List<ReceivingPlanItem> items = BeanUtilPlus.toBeans(req.getItems(), ReceivingPlanItem.class);
        int planIndex = 0;
        for (ReceivingPlanItem item : items) {
            item.setPlanItemNum(planIndex + 1);
            item.setReceivingPlanId(bean.getId());
            planIndex++;
        }
        receivingPlanItemMapper.insertBatch(items);
    }

    @Override
    @DSTransactional(rollbackFor = Exception.class)
    public void modify(Long id, ReceivingPlanSaveReq req) {
        if (ObjectUtils.isEmpty(req.getItems())) {
            throw CheckedException.badRequest("收货计划明细行不能为空");
        }
        ReceivingPlan plan = Optional.ofNullable(this.baseMapper.selectById(id)).orElseThrow(() -> CheckedException.notFound("收获计划不存在"));
        if (ReceivingStatus.DRAFT != plan.getStatus()) {
            // 当前只有草稿状态可编辑收货计划，防止状态被编辑接口篡改
            throw CheckedException.badRequest("草稿的收货计划才可编辑");
        }
        this.receivingPlanItemMapper.delete(Wraps.<ReceivingPlanItem>lbQ().eq(ReceivingPlanItem::getReceivingPlanId, id));
        ReceivingPlan bean = BeanUtilPlus.toBean(req, ReceivingPlan.class);
        bean.setId(id);
        bean.setPlanNum(plan.getPlanNum());
        bean.setStatus(ReceivingStatus.DRAFT);
        this.baseMapper.updateById(bean);
        List<ReceivingPlanItem> items = BeanUtilPlus.toBeans(req.getItems(), ReceivingPlanItem.class);
        int planIndex = 0;
        for (ReceivingPlanItem item : items) {
            item.setPlanItemNum(planIndex + 1);
            item.setReceivingPlanId(bean.getId());
            planIndex++;
        }
        receivingPlanItemMapper.insertBatch(items);
    }

    @Override
    @DSTransactional(rollbackFor = Exception.class)
    public void removeReceivingPlan(Long id) {
        ReceivingPlan plan = this.baseMapper.selectById(id);
        if (ReceivingStatus.DRAFT != plan.getStatus()) {
            // 草稿才可删除
            throw CheckedException.badRequest("草稿的收货计划才可删除");
        }
        this.receivingPlanItemMapper.delete(Wraps.<ReceivingPlanItem>lbQ().eq(ReceivingPlanItem::getReceivingPlanId, id));
        this.baseMapper.deleteById(id);
    }

    @Override
    public ReceivingPlanDetailResp detail(Long id) {
        ReceivingPlan plan = Optional.ofNullable(this.getById(id)).orElseThrow(() -> CheckedException.notFound("收货计划不存在"));
        ReceivingPlanDetailResp detail = BeanUtil.toBean(plan, ReceivingPlanDetailResp.class);
        List<ReceivingPlanItemPageResp> items = receivingPlanItemMapper.selectList(ReceivingPlanItem::getReceivingPlanId, plan.getId())
                .stream()
                .map(item -> BeanUtil.toBean(item, ReceivingPlanItemPageResp.class))
                .sorted(comparing(ReceivingPlanItemPageResp::getPlanItemNum))
                .toList();
        // 查询物料
        List<Long> materialIdList = items.stream().map(ReceivingPlanItemPageResp::getMaterialId).distinct().toList();
        List<Material> materialList = materialMapper.selectByIds(materialIdList);
        Map<Long, Material> materialMap = materialList.stream().collect(Collectors.toMap(Material::getId, Function.identity()));
        // 查询收货中的数量
        List<Long> planItemIds = items.stream().map(ReceivingPlanItemPageResp::getId).collect(Collectors.toList());
        List<ReceiptQtyDTO> receiptQtyList = itemsReceiptItemMapper.selectReceiptQty(planItemIds);
        Map<Long, ReceiptQtyDTO> receiptQtyMap = receiptQtyList.stream().collect(Collectors.toMap(ReceiptQtyDTO::getPlanItemId, Function.identity()));
        for (ReceivingPlanItemPageResp item : items) {
            Material material = Optional.ofNullable(materialMap.get(item.getMaterialId())).orElseGet(Material::new);
            item.setMaterialCode(material.getCode());
            item.setMaterialName(material.getName());
            ReceiptQtyDTO receiptQtyDTO = Optional.ofNullable(receiptQtyMap.get(item.getId())).orElseGet(ReceiptQtyDTO::new);
            item.setProgressQty(receiptQtyDTO.getProgressQty());
            item.setCompleteQty(receiptQtyDTO.getCompleteQty());
        }
        detail.setItems(items);
        return detail;
    }

    @Override
    public void submit(Long id) {
        ReceivingPlan receivingPlan = this.getById(id);
        if (ReceivingStatus.DRAFT != receivingPlan.getStatus()) {
            throw CheckedException.badRequest("草稿状态才能提交");
        }
        // 状态更新为待执行
        this.update(Wrappers.<ReceivingPlan>lambdaUpdate()
                .eq(ReceivingPlan::getId, id)
                .set(ReceivingPlan::getStatus, ReceivingStatus.WAIT.getStatus()));
        // 占用月台和容器

    }

    @Override
    public void close(Long id) {
        ReceivingPlan receivingPlan = this.getById(id);
        if (ReceivingStatus.IN_EXECUTION != receivingPlan.getStatus()) {
            throw CheckedException.badRequest("执行中才能进行关闭");
        }
        LbqWrapper<InventoryReceipt> lbqWrapper = Wraps.<InventoryReceipt>lbQ()
                .eq(InventoryReceipt::getPlanId, id).eq(InventoryReceipt::getStatus, InventoryReceiptStatus.DRAFT.getStatus());
        long count = inventoryReceiptService.count(lbqWrapper);
        if (count > 0) {
            throw CheckedException.badRequest("请先确认或关闭关联的入库单");
        }
        this.update(Wrappers.<ReceivingPlan>lambdaUpdate()
                .eq(ReceivingPlan::getId, id)
                .set(ReceivingPlan::getStatus, ReceivingStatus.CLOSE.getStatus()));
    }

    @Override
    public void cancel(Long id) {
        ReceivingPlan receivingPlan = this.getById(id);
        if (ReceivingStatus.WAIT != receivingPlan.getStatus()) {
            throw CheckedException.badRequest("待执行状态下才能进行撤回");
        }
        this.update(Wrappers.<ReceivingPlan>lambdaUpdate()
                .eq(ReceivingPlan::getId, id)
                .set(ReceivingPlan::getStatus, ReceivingStatus.DRAFT.getStatus()));
        // 返还占用的月台和容器
    }

    private Pair<ReceivingPlan, List<ReceivingPlanItem>> getReceivingPlanPari(Long id) {
        var receivingPlan = Optional.ofNullable(this.baseMapper.selectById(id)).orElseThrow(() -> CheckedException.notFound("收获计划不存在"));
        if (ReceivingStatus.DRAFT == receivingPlan.getStatus()) {
            throw CheckedException.badRequest("请先提交收货计划");
        }
        if (ReceivingStatus.COMPLETED == receivingPlan.getStatus()) {
            throw CheckedException.badRequest("收货计划已完成，请勿重复收货");
        }
        if (ReceivingStatus.CLOSE == receivingPlan.getStatus()) {
            throw CheckedException.badRequest("收货计划已取消");
        }
        var planItems = receivingPlanItemMapper.selectList(ReceivingPlanItem::getReceivingPlanId, id);
        return Pair.of(receivingPlan, planItems);
    }

    @Override
    @DSTransactional(rollbackFor = Exception.class)
    public void receiving(Long id, List<ReceivingPlanItemReceivingReq> reqList) {
        if (CollUtil.isEmpty(reqList)) {
            throw CheckedException.badRequest("无可收货的物料");
        }
        Pair<ReceivingPlan, List<ReceivingPlanItem>> pari = getReceivingPlanPari(id);
        var receivingPlan = pari.getKey();
        var planItemMap = MapHelper.toHashMap(pari.getValue(), ReceivingPlanItem::getId, x -> x);
        // 查询收货中的数量
        List<Long> itemIdList = reqList.stream().map(ReceivingPlanItemReceivingReq::getItemId).toList();
        var receiptQtyMap = MapHelper.toHashMap(itemsReceiptItemMapper.selectReceiptQty(itemIdList), ReceiptQtyDTO::getPlanItemId, x -> x);
        // 自定义收货
        var receiptItemList = reqList.stream().map(itemReq -> {
            ReceiptQtyDTO receiptQtyDTO = Optional.ofNullable(receiptQtyMap.get(itemReq.getItemId())).orElseGet(() -> ReceiptQtyDTO.builder()
                    .planItemId(itemReq.getItemId()).completeQty(BigDecimal.ZERO).progressQty(BigDecimal.ZERO).build());
            ReceivingPlanItem planItem = planItemMap.get(itemReq.getItemId());
            BigDecimal canQty = planItem.getQty().subtract(receiptQtyDTO.getProgressQty().add(receiptQtyDTO.getCompleteQty()));
            if (itemReq.getQty().compareTo(canQty) > 0) {
                throw CheckedException.badRequest("收货数量不能大于计划总数量");
            }
            InventoryReceiptItemSaveReq inItem = new InventoryReceiptItemSaveReq();
            inItem.setPlanItemId(planItem.getId());
            inItem.setReceivingQty(itemReq.getQty());
            inItem.setReceivingUnit(planItem.getUnit());
            inItem.setBatchNum(planItem.getBatchNum());
            inItem.setExpiryDate(planItem.getExpiryDate());
            inItem.setProductionDate(planItem.getProductionDate());
            inItem.setMaterialId(planItem.getMaterialId());
            return inItem;
        }).toList();
        if (CollUtil.isEmpty(receiptItemList)) {
            return;
        }
        addInventoryReceipt(receivingPlan, receiptItemList);
    }

    @Override
    public void allReceiving(Long id) {
        Pair<ReceivingPlan, List<ReceivingPlanItem>> pari = getReceivingPlanPari(id);
        var receivingPlan = pari.getKey();
        var planItems = pari.getValue();
        var planItemMap = MapHelper.toHashMap(planItems, ReceivingPlanItem::getId, x -> x);
        // 查询收货中的数量
        List<Long> itemIdList = pari.getValue().stream().map(ReceivingPlanItem::getId).toList();
        var receiptQtyMap = MapHelper.toHashMap(itemsReceiptItemMapper.selectReceiptQty(itemIdList), ReceiptQtyDTO::getPlanItemId, x -> x);
        // 自定义收货
        var receiptItemList = planItems.stream().map(item -> {
            ReceiptQtyDTO receiptQtyDTO = receiptQtyMap.get(item.getId());
            if (receiptQtyDTO == null) {
                return null;
            }
            ReceivingPlanItem planItem = planItemMap.get(item.getId());
            BigDecimal canQty = planItem.getQty().subtract(receiptQtyDTO.getProgressQty().add(receiptQtyDTO.getCompleteQty()));
            if (canQty.compareTo(BigDecimal.ZERO) <= 0) {
                return null;
            }
            InventoryReceiptItemSaveReq inItem = new InventoryReceiptItemSaveReq();
            inItem.setPlanItemId(planItem.getId());
            inItem.setReceivingQty(canQty);
            inItem.setReceivingUnit(planItem.getUnit());
            inItem.setBatchNum(planItem.getBatchNum());
            inItem.setExpiryDate(planItem.getExpiryDate());
            inItem.setProductionDate(planItem.getProductionDate());
            inItem.setMaterialId(planItem.getMaterialId());
            return inItem;
        }).filter(Objects::nonNull).toList();
        if (CollUtil.isEmpty(receiptItemList)) {
            return;
        }
        addInventoryReceipt(receivingPlan, receiptItemList);
    }

    @Override
    public void bindingDock(Long id, Long dockId) {
        ReceivingPlan receivingPlan = Optional.ofNullable(this.baseMapper.selectById(id)).orElseThrow(() ->
                CheckedException.notFound("收获计划不存在"));
        if (ReceivingStatus.WAIT != receivingPlan.getStatus()) {
            throw CheckedException.badRequest("待执行才能修改收货计划");
        }
        receivingPlan.setDockId(dockId);
        this.baseMapper.updateById(receivingPlan);
    }

    @Override
    public void bindingContainer(Long id, Long containerId) {
        ReceivingPlan receivingPlan = Optional.ofNullable(this.baseMapper.selectById(id)).orElseThrow(() ->
                CheckedException.notFound("收获计划不存在"));
        if (ReceivingStatus.WAIT != receivingPlan.getStatus()) {
            throw CheckedException.badRequest("待执行才能修改收货计划");
        }
        receivingPlan.setContainerId(containerId);
        this.baseMapper.updateById(receivingPlan);
    }

    private void addInventoryReceipt(ReceivingPlan receivingPlan, List<InventoryReceiptItemSaveReq> receiptItemList) {
        InventoryReceiptSaveReq inviteReceiptSaveReq = new InventoryReceiptSaveReq();
        inviteReceiptSaveReq.setPlanId(receivingPlan.getId());
        inviteReceiptSaveReq.setPlanNum(receivingPlan.getPlanNum());
        inviteReceiptSaveReq.setWarehouseId(receivingPlan.getWarehouseId());
        inviteReceiptSaveReq.setSupplierId(receivingPlan.getSupplierId());
        inviteReceiptSaveReq.setRemark(receivingPlan.getRemark());
        inviteReceiptSaveReq.setItems(receiptItemList);
        inventoryReceiptService.saveOrUpdateInventoryReceipt(null, inviteReceiptSaveReq);
        // 更新该收货单状态
        if (ReceivingStatus.WAIT != receivingPlan.getStatus()) {
            return;
        }
        this.baseMapper.updateById(ReceivingPlan.builder().id(receivingPlan.getId()).status(ReceivingStatus.IN_EXECUTION).build());
        // 占用容器
        ContainerOccupyReleaseReq releaseReq = ContainerOccupyReleaseReq.builder()
                .containerId(receivingPlan.getContainerId())
                .docId(receivingPlan.getId())
                .status(ContainerStatus.OCCUPY)
                .occupationTaskType(ContainerTaskType.RECEIVING_PLAN)
                .build();
        containerService.occupyOrRelease(List.of(releaseReq));
    }
}
