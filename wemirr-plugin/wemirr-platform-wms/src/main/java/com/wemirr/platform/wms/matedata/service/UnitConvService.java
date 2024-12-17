package com.wemirr.platform.wms.matedata.service;

import com.wemirr.framework.db.mybatisplus.ext.SuperService;
import com.wemirr.platform.wms.matedata.domain.entity.UnitConv;
import com.wemirr.platform.wms.matedata.domain.req.UnitConvSaveReq;

/**
 * <p>
 * 基本计量单位转换 服务类
 * </p>
 *
 * @author ddCat
 * @since 2024-06-24
 */
public interface UnitConvService extends SuperService<UnitConv> {


    /**
     * 添加数据
     *
     * @param req req
     */
    void create(UnitConvSaveReq req);

    /**
     * 修改品牌
     *
     * @param id  品牌ID
     * @param req 品牌信息
     */
    void modify(Long id, UnitConvSaveReq req);


}
