package com.wemirr.platform.tms.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.wemirr.framework.db.mybatisplus.ext.SuperService;
import com.wemirr.platform.tms.domain.entity.Truck;
import com.wemirr.platform.tms.domain.req.TruckApprovalReq;
import com.wemirr.platform.tms.domain.req.TruckPageReq;
import com.wemirr.platform.tms.domain.req.TruckSaveReq;
import com.wemirr.platform.tms.domain.resp.TruckPageResp;

/**
 * @author Levin
 */
public interface TruckService extends SuperService<Truck> {


    /**
     * 车辆列表
     *
     * @param req req
     * @return 查询结果
     */
    IPage<TruckPageResp> pageList(TruckPageReq req);

    /**
     * 创建车辆
     *
     * @param req req
     */
    void create(TruckSaveReq req);

    /**
     * 编辑车辆信息
     *
     * @param id  id
     * @param req req
     */
    void edit(Long id, TruckSaveReq req);

    /**
     * 审批
     *
     * @param id  id
     * @param req 审批信息
     */
    void approval(Long id, TruckApprovalReq req);


    /**
     * 启用/停用
     *
     * @param id      id
     * @param enabled 启用/停用状态
     */
    void enabled(Long id, Boolean enabled);
}
