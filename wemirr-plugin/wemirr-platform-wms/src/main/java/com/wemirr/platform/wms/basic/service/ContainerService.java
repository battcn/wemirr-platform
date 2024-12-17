package com.wemirr.platform.wms.basic.service;

import com.wemirr.framework.db.mybatisplus.ext.SuperService;
import com.wemirr.platform.wms.basic.domain.entity.Container;
import com.wemirr.platform.wms.basic.domain.req.ContainerOccupyReleaseReq;
import com.wemirr.platform.wms.basic.domain.req.ContainerSaveReq;

import java.util.List;

/**
 * <p>
 * 容器表 服务类
 * </p>
 *
 * @author ddCat
 * @since 2024-06-17
 */
public interface ContainerService extends SuperService<Container> {

    /**
     * 添加容器
     *
     * @param req req
     */
    void create(ContainerSaveReq req);

    /**
     * 修改容器
     *
     * @param id  容器ID
     * @param req 容器信息
     */
    void modify(Long id, ContainerSaveReq req);

    /**
     * 占用或释放容器（生成容器日志）
     *
     * @param reqs 请求参数
     */
    void occupyOrRelease(List<ContainerOccupyReleaseReq> reqs);


}
