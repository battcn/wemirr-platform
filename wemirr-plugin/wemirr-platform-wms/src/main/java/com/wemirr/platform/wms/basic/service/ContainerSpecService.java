package com.wemirr.platform.wms.basic.service;

import com.wemirr.framework.db.mybatisplus.ext.SuperService;
import com.wemirr.platform.wms.basic.domain.entity.ContainerSpec;
import com.wemirr.platform.wms.basic.domain.req.ContainerSpecSaveReq;

/**
 * <p>
 * 容器规格表 服务类
 * </p>
 *
 * @author ddCat
 * @since 2024-06-17
 */
public interface ContainerSpecService extends SuperService<ContainerSpec> {


    /**
     * 保存容器规格
     *
     * @param req 容器信息
     */
    void create(ContainerSpecSaveReq req);


    /**
     * 保存容器规格
     *
     * @param id  容器规格ID
     * @param req 容器信息
     */
    void modify(Long id, ContainerSpecSaveReq req);
}
