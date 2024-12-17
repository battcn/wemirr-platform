package com.wemirr.platform.wms.basic.service;

import com.wemirr.framework.db.mybatisplus.ext.SuperService;
import com.wemirr.platform.wms.basic.domain.entity.Warehouse;
import com.wemirr.platform.wms.basic.domain.req.WarehouseSaveReq;

/**
 * <p>
 * 仓库表 服务类
 * </p>
 *
 * @author ddCat
 * @since 2024-06-17
 */
public interface WarehouseService extends SuperService<Warehouse> {

    /**
     * 新增仓库
     *
     * @param req req
     */
    void create(WarehouseSaveReq req);

    /**
     * 修改仓库信息
     *
     * @param id  id
     * @param req req
     */
    void modify(Long id, WarehouseSaveReq req);

}
