package com.wemirr.platform.tms.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.wemirr.framework.db.mybatisplus.ext.SuperService;
import com.wemirr.platform.tms.domain.entity.TmsOrder;
import com.wemirr.platform.tms.domain.req.OrderPageReq;
import com.wemirr.platform.tms.domain.req.OrderSaveReq;
import com.wemirr.platform.tms.domain.resp.OrderDetailResp;
import com.wemirr.platform.tms.domain.resp.OrderPageResp;

/**
 * <p>
 * 业务接口
 * 订单信息
 *
 * </p>
 *
 * @author Levin
 * @since 2023-09-02
 */
public interface OrderService extends SuperService<TmsOrder> {

    /**
     * 订单列表
     *
     * @param req req
     * @return 查询结果
     */
    IPage<OrderPageResp> pageList(OrderPageReq req);

    /**
     * 查询订单
     *
     * @param id id
     * @return 订单信息
     */
    OrderDetailResp detail(Long id);

    /**
     * 创建订单
     *
     * @param req req
     * @return 订单单号、id
     */
    void createOrder(OrderSaveReq req);


    /**
     * 取消订单
     *
     * @param id id
     */
    void cancelOrder(Long id);

    /**
     * 修改订单
     *
     * @param id  id
     * @param req req
     */
    void updateOrder(Long id, OrderSaveReq req);


}
