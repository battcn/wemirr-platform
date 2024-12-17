package com.wemirr.platform.wms.basic.service;

import com.wemirr.framework.db.mybatisplus.ext.SuperService;
import com.wemirr.platform.wms.basic.domain.entity.Dock;
import com.wemirr.platform.wms.basic.domain.req.DockSaveReq;

/**
 * <p>
 * 月台管理表 服务类
 * </p>
 *
 * @author ddCat
 * @since 2024-06-17
 */
public interface DockService extends SuperService<Dock> {

    /**
     * 创建
     *
     * @param req req
     */
    void create(DockSaveReq req);


    /**
     * 修改数据
     *
     * @param id  id
     * @param req req
     */
    void modify(Long id, DockSaveReq req);


}
