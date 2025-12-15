package com.wemirr.platform.tms.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.wemirr.framework.db.mybatisplus.ext.SuperService;
import com.wemirr.platform.tms.domain.entity.Peccancy;
import com.wemirr.platform.tms.domain.req.PeccancyPageReq;
import com.wemirr.platform.tms.domain.req.PeccancySaveReq;
import com.wemirr.platform.tms.domain.resp.PeccancyPageResp;

/**
 * @author Levin
 */
public interface PeccancyService extends SuperService<Peccancy> {



    /**
     * 分页查询
     *
     * @param req req
     * @return 查询结果
     */
    IPage<PeccancyPageResp> pageList(PeccancyPageReq req);

    /**
     * 创建车辆违章信息
     *
     * @param req req
     */
    void create(PeccancySaveReq req);

    /**
     * 编辑车辆违章信息
     *
     * @param id  id
     * @param req req
     */
    void edit(Long id, PeccancySaveReq req);

    
}
