package com.wemirr.platform.tms.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.wemirr.framework.db.mybatisplus.ext.SuperService;
import com.wemirr.platform.tms.domain.entity.Driver;
import com.wemirr.platform.tms.domain.req.DriverApprovalReq;
import com.wemirr.platform.tms.domain.req.DriverPageReq;
import com.wemirr.platform.tms.domain.req.DriverSaveReq;
import com.wemirr.platform.tms.domain.resp.DriverPageResp;

/**
 * @author Levin
 */
public interface DriverService extends SuperService<Driver> {

    /**
     * 司机列表
     *
     * @param req req
     * @return 查询结果
     */
    IPage<DriverPageResp> pageList(DriverPageReq req);

    /**
     * 创建司机
     *
     * @param req req
     */
    void create(DriverSaveReq req);

    /**
     * 编辑司机信息
     *
     * @param id  id
     * @param req req
     */
    void edit(Long id, DriverSaveReq req);

    /**
     * 审批
     *
     * @param id  id
     * @param req 审批信息
     */
    void approval(Long id, DriverApprovalReq req);


    /**
     * 启用/停用
     *
     * @param id      id
     * @param enabled 启用/停用状态
     */
    void enabled(Long id, Boolean enabled);

}
