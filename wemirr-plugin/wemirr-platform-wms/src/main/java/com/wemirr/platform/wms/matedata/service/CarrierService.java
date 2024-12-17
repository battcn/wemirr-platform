package com.wemirr.platform.wms.matedata.service;

import com.wemirr.framework.db.mybatisplus.ext.SuperService;
import com.wemirr.platform.wms.matedata.domain.entity.Carrier;
import com.wemirr.platform.wms.matedata.domain.req.CarrierSaveReq;

/**
 * <p>
 * 承运商 服务类
 * </p>
 *
 * @author ddCat
 * @since 2024-07-14
 */
public interface CarrierService extends SuperService<Carrier> {


    /**
     * 创建承运商
     *
     * @param req req
     */
    void create(CarrierSaveReq req);


    /**
     * 修改承运商
     *
     * @param id  承运商ID
     * @param req 承运商信息
     */
    void modify(Long id, CarrierSaveReq req);


}
