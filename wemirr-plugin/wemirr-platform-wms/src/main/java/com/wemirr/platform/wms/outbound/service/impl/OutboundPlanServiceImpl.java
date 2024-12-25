package com.wemirr.platform.wms.outbound.service.impl;

import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.wemirr.framework.commons.BeanUtilPlus;
import com.wemirr.framework.commons.exception.CheckedException;
import com.wemirr.framework.commons.security.AuthenticationContext;
import com.wemirr.framework.db.mybatisplus.ext.SuperServiceImpl;
import com.wemirr.framework.db.mybatisplus.wrap.Wraps;
import com.wemirr.framework.db.mybatisplus.wrap.query.LbqWrapper;
import com.wemirr.framework.redis.plus.sequence.RedisSequenceHelper;
import com.wemirr.platform.wms.inbound.domain.enums.WmsSequence;
import com.wemirr.platform.wms.matedata.domain.entity.Material;
import com.wemirr.platform.wms.matedata.mapper.MaterialMapper;
import com.wemirr.platform.wms.outbound.domain.entity.OutboundPlan;
import com.wemirr.platform.wms.outbound.domain.entity.OutboundPlanItem;
import com.wemirr.platform.wms.outbound.domain.entity.OutboundPlanItemStock;
import com.wemirr.platform.wms.outbound.domain.enums.OutboundStatus;
import com.wemirr.platform.wms.outbound.domain.req.OutboundPlanSaveReq;
import com.wemirr.platform.wms.outbound.domain.resp.OutboundPlanDetailResp;
import com.wemirr.platform.wms.outbound.domain.resp.OutboundPlanItemPageResp;
import com.wemirr.platform.wms.outbound.domain.resp.OutboundPlanItemStockPageResp;
import com.wemirr.platform.wms.outbound.mapper.OutboundPlanItemMapper;
import com.wemirr.platform.wms.outbound.mapper.OutboundPlanItemStockMapper;
import com.wemirr.platform.wms.outbound.mapper.OutboundPlanMapper;
import com.wemirr.platform.wms.outbound.service.OutboundPlanService;
import com.wemirr.platform.wms.stock.domain.entity.Stock;
import com.wemirr.platform.wms.stock.mapper.StockMapper;
import lombok.AllArgsConstructor;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * <p>
 * 出库单 服务类
 * </p>
 *
 * @author ddCat
 * @since 2024-07-20
 */
@Service
@AllArgsConstructor
public class OutboundPlanServiceImpl extends SuperServiceImpl<OutboundPlanMapper, OutboundPlan> implements OutboundPlanService {

    private final OutboundPlanItemMapper outboundPlanItemMapper;
    private final MaterialMapper materialMapper;
    private final RedisSequenceHelper sequenceHelper;
    private final AuthenticationContext authenticationContext;
    private final StockMapper stockMapper;
    private final OutboundPlanItemStockMapper outlinePlanItemStockMapper;

    @Override
    public IPage<OutboundPlanItemPageResp> itemPage(Page<OutboundPlanItem> page, Long planId) {
        LbqWrapper<OutboundPlanItem> eq = Wraps.<OutboundPlanItem>lbQ().eq(OutboundPlanItem::getPlanId, planId);
        // 这里应该写 XML 查询 并且返回 OutboundPlanItemPageResp 和  已出库数  未出库数
        Page<OutboundPlanItem> receivingPlanItemPage = outboundPlanItemMapper.selectPage(page, eq);
        return receivingPlanItemPage.convert(item -> {
            return BeanUtilPlus.toBean(item, OutboundPlanItemPageResp.class);
        });
    }

    @Override
    public OutboundPlanDetailResp detail(Long id) {
        OutboundPlan plan = Optional.ofNullable(this.getById(id)).orElseThrow(() -> CheckedException.notFound("出库单不存在"));
        OutboundPlanDetailResp detail = BeanUtil.toBean(plan, OutboundPlanDetailResp.class);
        List<OutboundPlanItemPageResp> items = outboundPlanItemMapper
                .selectList(Wraps.<OutboundPlanItem>lbQ().eq(OutboundPlanItem::getPlanId, plan.getId()))
                .stream().map(item -> BeanUtil.toBean(item, OutboundPlanItemPageResp.class))
                .sorted(Comparator.comparing(OutboundPlanItemPageResp::getPlanItemNum)).toList();
        // 查询物料
        Map<Long, Material> materialMap = materialMapper.selectByIds(items.stream()
                        .map(OutboundPlanItemPageResp::getMaterialId).distinct().toList())
                .stream().collect(Collectors.toMap(Material::getId, Function.identity()));
        items.forEach(item -> {
            // 赋值物料
            Material material = materialMap.get(item.getMaterialId());
            item.setMaterialCode(material.getCode());
            item.setMaterialName(material.getName());
        });
        // 查询发货单明细行关联的关联关系
        List<OutboundPlanItemStock> outboundPlanItemStocks = this.outlinePlanItemStockMapper.selectList(
                Wraps.<OutboundPlanItemStock>lbQ().in(OutboundPlanItemStock::getPlanItemId, items.stream().map(OutboundPlanItemPageResp::getId).toList()));
        if (ObjectUtils.isNotEmpty(outboundPlanItemStocks)) {
            Map<Long, List<OutboundPlanItemStock>> outboundPlanItemStocksMap = outboundPlanItemStocks.stream().collect(Collectors.groupingBy(OutboundPlanItemStock::getPlanItemId));
            // 查询库存
            List<Stock> stockList = stockMapper.selectByIds(
                    outboundPlanItemStocks.stream().map(OutboundPlanItemStock::getStockId).distinct().toList());
            Map<Long, Stock> stockMap = stockList.stream().collect(Collectors.toMap(Stock::getId, Function.identity()));
            items.forEach(item -> {
                // 赋值物料
                Material material = materialMap.get(item.getMaterialId());
                // 赋值发货单明细行的库存
                List<OutboundPlanItemStock> outboundPlanItemStockList = outboundPlanItemStocksMap.get(item.getId());
                if (ObjectUtils.isNotEmpty(outboundPlanItemStockList)) {
                    List<OutboundPlanItemStockPageResp> plantItemStockPageResps = outboundPlanItemStockList.stream().map(s -> BeanUtil.toBean(s, OutboundPlanItemStockPageResp.class)).toList();
                    plantItemStockPageResps.forEach(s -> {
                        Stock stock = stockMap.get(s.getStockId());
                        if (ObjectUtils.isNotEmpty(stock)) {
                            s.setMaterialCode(material.getCode());
                            s.setMaterialName(material.getName());
                            s.setBatchNum(stock.getBatchNum());
                            s.setExpiryDate(stock.getExpiryDate());
                            s.setProductionDate(stock.getProductionDate());
                            s.setLevel(stock.getLevel());
                            s.setUnitPrice(stock.getUnitPrice());
                            s.setAttribute(stock.getAttribute());
                        }
                    });
                    item.setStocks(plantItemStockPageResps);
                }
            });
        }
        detail.setItems(items);
        return detail;
    }

    @Override
    @Transactional
    public void confirm(Long id) {
        OutboundPlan outboundPlan = this.getById(id);
        if (OutboundStatus.PACKING != outboundPlan.getStatus()) {
            throw CheckedException.badRequest("配货中才能进行完成");
        }
        this.update(Wrappers.<OutboundPlan>lambdaUpdate()
                .eq(OutboundPlan::getId, id)
                .set(OutboundPlan::getStatus, OutboundStatus.COMPLETE.getStatus()));
        // TODO 扣除库存余额，产生库存流水

    }

    @Transactional
    @Override
    public void saveOrUpdateOutboundPlan(Long id, OutboundPlanSaveReq req) {
        if (ObjectUtils.isEmpty(req.getItems())) {
            throw CheckedException.badRequest("出库单明细行不能为空");
        }
        OutboundPlan oldOutboundPlan = new OutboundPlan();
        if (id != null) {
            oldOutboundPlan = this.getById(id);
            if (OutboundStatus.DRAFT != oldOutboundPlan.getStatus()) {
                // 草稿才可以编辑
                throw CheckedException.badRequest("草稿的出库单才可编辑");
            }
            this.outboundPlanItemMapper.removeByPlanId(id);
        }
        OutboundPlan outboundPlan = BeanUtilPlus.toBean(req, OutboundPlan.class);
        if (id != null) {
            outboundPlan.setId(id);
            outboundPlan.setPlanNum(oldOutboundPlan.getPlanNum());
        } else {
            outboundPlan.setPlanNum(sequenceHelper.generate(WmsSequence.RECEIVING_PLAN, authenticationContext.tenantId()));
        }
        // 当前只有草稿状态可编辑出库单，防止状态被编辑接口篡改
        outboundPlan.setStatus(OutboundStatus.DRAFT);
        this.saveOrUpdate(outboundPlan);
        List<OutboundPlanItem> items = BeanUtilPlus.toBeans(req.getItems(), OutboundPlanItem.class);
        int planIndex = 0;
        for (OutboundPlanItem item : items) {
            item.setPlanItemNum(planIndex + 1);
            item.setPlanId(outboundPlan.getId());
            planIndex++;
        }
        outboundPlanItemMapper.insertBatch(items);
    }

    @Transactional
    @Override
    public void removeOutboundPlan(Long id) {
        OutboundPlan outboundPlan = this.getById(id);
        if (OutboundStatus.DRAFT != outboundPlan.getStatus()) {
            // 草稿才可删除
            throw CheckedException.badRequest("草稿的出库单才可删除");
        }
        List<OutboundPlanItem> outboundPlanItems = this.outboundPlanItemMapper.selectList(Wraps.<OutboundPlanItem>lbQ().eq(OutboundPlanItem::getPlanId, id));
        List<Long> planItemIds = outboundPlanItems.stream().map(OutboundPlanItem::getId).toList();
        this.outlinePlanItemStockMapper.delete(Wraps.<OutboundPlanItemStock>lbQ().in(OutboundPlanItemStock::getPlanItemId, planItemIds));
        this.outboundPlanItemMapper.deleteByIds(planItemIds);
        this.removeById(id);
    }


    @Override
    public void submit(Long id) {
        OutboundPlan outboundPlan = this.getById(id);
        if (OutboundStatus.DRAFT != outboundPlan.getStatus()) {
            throw CheckedException.badRequest("草稿状态才能提交");
        }
        // 状态更新为待执行
        this.update(Wrappers.<OutboundPlan>lambdaUpdate()
                .eq(OutboundPlan::getId, id)
                .set(OutboundPlan::getStatus, OutboundStatus.PACKING.getStatus()));
    }

    @Override
    public void close(Long id) {
        OutboundPlan outboundPlan = this.getById(id);
        if (OutboundStatus.PACKING != outboundPlan.getStatus()) {
            throw CheckedException.badRequest("配货中才能进行关闭");
        }
        this.update(Wrappers.<OutboundPlan>lambdaUpdate()
                .eq(OutboundPlan::getId, id)
                .set(OutboundPlan::getStatus, OutboundStatus.CLOSE.getStatus()));
    }

    @Override
    public void cancel(Long id) {
        OutboundPlan outboundPlan = this.getById(id);
        if (OutboundStatus.PACKING != outboundPlan.getStatus()) {
            throw CheckedException.badRequest("配货中状态下才能进行撤回");
        }
        this.update(Wrappers.<OutboundPlan>lambdaUpdate()
                .eq(OutboundPlan::getId, id)
                .set(OutboundPlan::getStatus, OutboundStatus.DRAFT.getStatus()));
    }

}
