package com.wemirr.platform.authority.service;

import com.wemirr.framework.db.mybatisplus.ext.SuperService;
import com.wemirr.platform.authority.domain.baseinfo.entity.Resource;
import com.wemirr.platform.authority.domain.baseinfo.req.ResourceQueryReq;
import com.wemirr.platform.authority.domain.baseinfo.req.ResourceSaveReq;
import com.wemirr.platform.authority.domain.baseinfo.resp.VueRouter;

import java.util.List;

/**
 * <p>
 * 业务接口
 * 资源
 * </p>
 *
 * @author Levin
 * @since 2020-07-03
 */
public interface ResourceService extends SuperService<Resource> {

    /**
     * 查询 拥有的资源
     *
     * @param req req
     * @return 查询结果
     */
    List<VueRouter> findVisibleResource(ResourceQueryReq req);


    /**
     * 添加资源
     *
     * @param req 资源
     */
    void add(ResourceSaveReq req);

    /**
     * 修改资源
     *
     * @param id  id
     * @param req 资源
     */
    void edit(Long id, ResourceSaveReq req);

    /**
     * 删除资源
     *
     * @param resourceId resourceId
     */
    void delete(Long resourceId);


}
