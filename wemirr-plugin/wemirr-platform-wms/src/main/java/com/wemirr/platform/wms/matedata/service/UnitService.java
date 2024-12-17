package com.wemirr.platform.wms.matedata.service;

import com.wemirr.framework.db.mybatisplus.ext.SuperService;
import com.wemirr.platform.wms.matedata.domain.entity.Unit;
import com.wemirr.platform.wms.matedata.domain.req.UnitSaveReq;

/**
 * <p>
 * 基本计量单位 服务类
 * </p>
 *
 * @author ddCat
 * @since 2024-06-24
 */
public interface UnitService extends SuperService<Unit> {


    /**
     * 添加计量单位
     *
     * @param req req
     */
    void create(UnitSaveReq req);

    /**
     * 修改计量单位
     *
     * @param id  ID
     * @param req 信息
     */
    void modify(Long id, UnitSaveReq req);


}
