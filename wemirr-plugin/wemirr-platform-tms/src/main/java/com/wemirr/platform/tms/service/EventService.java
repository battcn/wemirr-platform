package com.wemirr.platform.tms.service;

import com.wemirr.framework.db.mybatisplus.ext.SuperService;
import com.wemirr.platform.tms.domain.entity.TmsOrderEvent;
import com.wemirr.platform.tms.domain.req.OrderEventSaveReq;
import com.wemirr.platform.tms.domain.resp.OrderEventResp;

import java.util.List;

/**
 * <p>
 * 业务接口
 * 节点信息
 *
 * </p>
 *
 * @author Levin
 * @since 2023-09-09
 */
public interface EventService extends SuperService<TmsOrderEvent> {

    /**
     * 节点列表
     *
     * @param orderId orderId
     * @return 查询结果
     */
    List<OrderEventResp> listEvent(Long orderId);

    /**
     * 创建节点
     *
     * @param req req
     * @return 节点id
     */
    OrderEventResp createEvent(OrderEventSaveReq req);


    /**
     * 取消节点
     *
     * @param id id
     * @return 节点id
     */
    OrderEventResp cancelEvent(Long id);

    /**
     * 修改节点
     *
     * @param id id
     * @param req req
     * @return 节点id
     */
    OrderEventResp updateEvent(Long id, OrderEventSaveReq req);

}
