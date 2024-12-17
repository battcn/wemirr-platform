package com.wemirr.platform.wms.basic.service;

import com.wemirr.framework.db.mybatisplus.ext.SuperService;
import com.wemirr.platform.wms.basic.domain.entity.LocationSpec;
import com.wemirr.platform.wms.basic.domain.req.LocationSpecSaveReq;

/**
 * <p>
 * 储位规格表 服务类
 * </p>
 *
 * @author ddCat
 * @since 2024-06-17
 */
public interface LocationSpecService extends SuperService<LocationSpec> {


    /**
     * 添加储位规格
     *
     * @param req 储位规格信息
     */
    void create(LocationSpecSaveReq req);

    /**
     * 修改储位规格
     *
     * @param id  储位规格ID
     * @param req 储位规格信息
     */
    void modify(Long id, LocationSpecSaveReq req);


}
