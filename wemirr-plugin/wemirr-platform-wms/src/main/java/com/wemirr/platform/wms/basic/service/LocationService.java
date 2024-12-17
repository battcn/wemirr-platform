package com.wemirr.platform.wms.basic.service;

import com.wemirr.framework.db.mybatisplus.ext.SuperService;
import com.wemirr.platform.wms.basic.domain.entity.Location;
import com.wemirr.platform.wms.basic.domain.req.LocationSaveReq;

/**
 * <p>
 * 储位表 服务类
 * </p>
 *
 * @author ddCat
 * @since 2024-06-17
 */
public interface LocationService extends SuperService<Location> {


    /**
     * 创建数据
     *
     * @param req req
     */
    void create(LocationSaveReq req);

    /**
     * 修改储位(库位)
     *
     * @param id  储位id
     * @param req 储位信息
     */
    void modify(Long id, LocationSaveReq req);

}
