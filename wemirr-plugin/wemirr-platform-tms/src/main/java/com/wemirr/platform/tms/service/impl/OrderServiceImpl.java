package com.wemirr.platform.tms.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.collection.CollUtil;
import com.baomidou.dynamic.datasource.annotation.DSTransactional;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.wemirr.framework.commons.BeanUtilPlus;
import com.wemirr.framework.commons.exception.CheckedException;
import com.wemirr.framework.commons.security.AuthenticationContext;
import com.wemirr.framework.db.mybatisplus.ext.SuperServiceImpl;
import com.wemirr.framework.db.mybatisplus.wrap.Wraps;
import com.wemirr.framework.db.mybatisplus.wrap.query.LbqWrapper;
import com.wemirr.framework.redis.plus.sequence.RedisSequenceHelper;
import com.wemirr.platform.tms.domain.entity.TmsOrder;
import com.wemirr.platform.tms.domain.entity.TmsOrderAddress;
import com.wemirr.platform.tms.domain.entity.TmsOrderFile;
import com.wemirr.platform.tms.domain.entity.TmsOrderSku;
import com.wemirr.platform.tms.domain.enums.OrderStatus;
import com.wemirr.platform.tms.domain.enums.TmsSequence;
import com.wemirr.platform.tms.domain.req.OrderFileSaveReq;
import com.wemirr.platform.tms.domain.req.OrderPageReq;
import com.wemirr.platform.tms.domain.req.OrderSaveReq;
import com.wemirr.platform.tms.domain.resp.OrderAddressResp;
import com.wemirr.platform.tms.domain.resp.OrderDetailResp;
import com.wemirr.platform.tms.domain.resp.OrderPageResp;
import com.wemirr.platform.tms.domain.resp.OrderSkuResp;
import com.wemirr.platform.tms.mapper.OrderMapper;
import com.wemirr.platform.tms.mapper.TmsOrderAddressMapper;
import com.wemirr.platform.tms.mapper.TmsOrderFileMapper;
import com.wemirr.platform.tms.mapper.TmsOrderSkuMapper;
import com.wemirr.platform.tms.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * 订单
 *
 * @author wangyi
 */
@Service
@RequiredArgsConstructor
public class OrderServiceImpl extends SuperServiceImpl<OrderMapper, TmsOrder> implements OrderService {

    private final RedisSequenceHelper sequenceHelper;
    private final AuthenticationContext authenticationContext;
    private final TmsOrderAddressMapper orderAddressMapper;
    private final TmsOrderSkuMapper orderSkuMapper;
    private final TmsOrderFileMapper orderFileMapper;

    @Override
    public IPage<OrderPageResp> pageList(OrderPageReq req) {
        final LbqWrapper<TmsOrder> wrapper = Wraps.<TmsOrder>lbQ().eq(TmsOrder::getProjectId, req.getProjectId())
                .eq(TmsOrder::getSourceType, req.getSourceType())
                .eq(TmsOrder::getOrderNo, req.getOrderNo()).eq(TmsOrder::getCustomNo, req.getCustomNo())
                .eq(TmsOrder::getTransportType, req.getTransportType()).in(TmsOrder::getOrderStatus, req.getOrderStatus())
                .eq(TmsOrder::getHasEpod, req.getHasEpod());
        return this.baseMapper.selectPage(req.buildPage(), wrapper).convert(x -> BeanUtil.toBean(x, OrderPageResp.class));
    }

    @Override
    public OrderDetailResp detail(Long id) {
        final TmsOrder order = Optional.ofNullable(this.baseMapper.selectById(id)).orElseThrow(() -> CheckedException.notFound("订单不存在"));
        final OrderDetailResp bean = BeanUtil.toBean(order, OrderDetailResp.class);
        final List<TmsOrderSku> skus = this.orderSkuMapper.selectList(Wraps.<TmsOrderSku>lbQ().eq(TmsOrderSku::getOrderId, id));
        bean.setSkus(BeanUtilPlus.toBeans(skus, OrderSkuResp.class));
        final TmsOrderAddress senderInfo = this.orderAddressMapper.selectOne(Wraps.<TmsOrderAddress>lbQ().eq(TmsOrderAddress::getOrderId, id).eq(TmsOrderAddress::getType, 0));
        bean.setSenderInfo(BeanUtil.toBean(senderInfo, OrderAddressResp.class));
        final TmsOrderAddress receiverInfo = this.orderAddressMapper.selectOne(Wraps.<TmsOrderAddress>lbQ().eq(TmsOrderAddress::getOrderId, id).eq(TmsOrderAddress::getType, 1));
        bean.setReceiverInfo(BeanUtil.toBean(receiverInfo, OrderAddressResp.class));
        return bean;
    }

    @Override
    @DSTransactional(rollbackFor = Exception.class)
    public void createOrder(OrderSaveReq req) {
        final TmsOrder order = BeanUtil.toBean(req, TmsOrder.class);
        order.setOrderStatus(0);
        order.setOrderNo(sequenceHelper.generate(TmsSequence.ORDER_NO, authenticationContext.tenantId()));
        this.baseMapper.insert(order);
        final TmsOrderAddress senderInfo = req.buildOrderAddress(order.getId(), 0, req.getSenderInfo());
        final TmsOrderAddress receiverInfo = req.buildOrderAddress(order.getId(), 1, req.getReceiverInfo());
        this.orderAddressMapper.insertBatchSomeColumn(List.of(senderInfo, receiverInfo));
        Optional.ofNullable(req.buildOrderSkus(order.getId())).ifPresent(this.orderAddressMapper::insertBatchSomeColumn);
        log.debug("添加货品信息成功");
        List<OrderFileSaveReq> files = req.getFiles();
        if (CollUtil.isNotEmpty(files)) {
            final List<TmsOrderFile> fileList = files.stream().map(file -> {
                final TmsOrderFile orderFile = BeanUtil.toBean(file, TmsOrderFile.class);
                orderFile.setId(null);
                orderFile.setOrderId(order.getId());
                orderFile.setSourceId(order.getSourceType());
                orderFile.setLocked(false);
                return orderFile;
            }).collect(Collectors.toList());
            this.orderFileMapper.insertBatchSomeColumn(fileList);
        }
    }

    @Override
    public void cancelOrder(Long id) {
        Optional.ofNullable(this.baseMapper.selectById(id)).orElseThrow(() -> CheckedException.notFound("取消失败,订单不存在"));
        this.baseMapper.updateById(TmsOrder.builder().orderStatus(OrderStatus.CANCELLED.getStatus()).build());
    }

    @Override
    @DSTransactional(rollbackFor = Exception.class)
    public void updateOrder(Long id, OrderSaveReq req) {
        Optional.ofNullable(this.baseMapper.selectById(id)).orElseThrow(() -> CheckedException.notFound("订单不存在"));
        final TmsOrder order = BeanUtil.toBean(req, TmsOrder.class);
        order.setId(id);
        this.baseMapper.updateById(order);
        //删除旧记录
        this.orderAddressMapper.delete(Wraps.<TmsOrderAddress>lbQ().eq(TmsOrderAddress::getOrderId, id));
        final TmsOrderAddress senderInfo = req.buildOrderAddress(order.getId(), 0, req.getSenderInfo());
        final TmsOrderAddress receiverInfo = req.buildOrderAddress(order.getId(), 1, req.getReceiverInfo());
        this.orderAddressMapper.insertBatchSomeColumn(List.of(senderInfo, receiverInfo));
        //删除旧记录
        this.orderSkuMapper.delete(Wraps.<TmsOrderSku>lbQ().eq(TmsOrderSku::getOrderId, id));
        final List<TmsOrderSku> skus = req.buildOrderSkus(order.getId());
        if (CollUtil.isNotEmpty(skus)) {
            this.orderSkuMapper.insertBatchSomeColumn(skus);
        }
        //删除旧记录
        this.orderFileMapper.delete(Wraps.<TmsOrderFile>lbQ().eq(TmsOrderFile::getOrderId, id));
        List<OrderFileSaveReq> files = req.getFiles();
        if (CollUtil.isNotEmpty(files)) {
            final List<TmsOrderFile> fileList = files.stream().map(file -> {
                TmsOrderFile orderFile = BeanUtil.toBean(file, TmsOrderFile.class);
                orderFile.setId(null);
                orderFile.setOrderId(order.getId());
                orderFile.setSourceId(order.getSourceType());
                orderFile.setLocked(false);
                return orderFile;
            }).collect(Collectors.toList());
            this.orderFileMapper.insertBatchSomeColumn(fileList);
        }
    }

}
