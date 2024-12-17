package com.wemirr.platform.wms.matedata.service;

import com.wemirr.framework.db.mybatisplus.ext.SuperService;
import com.wemirr.platform.wms.matedata.domain.entity.MaterialCategory;
import com.wemirr.platform.wms.matedata.domain.req.MaterialCategorySaveReq;

/**
 * <p>
 * 物料类型 服务类
 * </p>
 *
 * @author ddCat
 * @since 2024-06-18
 */
public interface MaterialCategoryService extends SuperService<MaterialCategory> {
    

    /**
     * 添加物料类型
     *
     * @param req 物料类型信息
     */
    void create(MaterialCategorySaveReq req);

    /**
     * 修改物料类型
     *
     * @param id  物料类型ID
     * @param req 物料类型信息
     */
    void modify(Long id, MaterialCategorySaveReq req);

    /**
     * 级联删除
     *
     * @param id id
     */
    void cascadeDelete(Long id);
}
