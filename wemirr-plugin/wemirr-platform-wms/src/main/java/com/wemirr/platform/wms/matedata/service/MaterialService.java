package com.wemirr.platform.wms.matedata.service;

import com.wemirr.framework.db.mybatisplus.ext.SuperService;
import com.wemirr.platform.wms.matedata.domain.entity.Material;
import com.wemirr.platform.wms.matedata.domain.req.MaterialSaveReq;

/**
 * <p>
 * 物料 服务类
 * </p>
 *
 * @author ddCat
 * @since 2024-06-18
 */
public interface MaterialService extends SuperService<Material> {

    /**
     * 保存物料
     *
     * @param req 物料信息
     */
    void create(MaterialSaveReq req);

    /**
     * 修改物料
     *
     * @param id  物料ID
     * @param req 物料信息
     */
    void modify(Long id, MaterialSaveReq req);
}
