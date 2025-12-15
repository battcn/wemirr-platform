package com.wemirr.platform.tms.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.wemirr.framework.db.mybatisplus.ext.SuperService;
import com.wemirr.platform.tms.domain.entity.Fleet;
import com.wemirr.platform.tms.domain.req.FleetPageReq;
import com.wemirr.platform.tms.domain.req.FleetSaveReq;
import com.wemirr.platform.tms.domain.resp.FleetPageResp;

/**
 * @author Levin
 */
public interface FleetService extends SuperService<Fleet> {

    /**
     * 分页查询
     *
     * @param req req
     * @return 查询结果
     */
    IPage<FleetPageResp> pageList(FleetPageReq req);

    /**
     * 创建司机
     *
     * @param req req
     */
    void create(FleetSaveReq req);

    /**
     * 编辑司机信息
     *
     * @param id  id
     * @param req req
     */
    void edit(Long id, FleetSaveReq req);

}
