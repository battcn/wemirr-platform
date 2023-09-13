package com.wemirr.platform.authority.service;

import com.wemirr.framework.db.mybatis.SuperService;
import com.wemirr.platform.authority.domain.entity.baseinfo.Resource;
import com.wemirr.platform.authority.domain.req.ResourceQueryReq;
import com.wemirr.platform.authority.domain.resp.VueRouter;

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
     * @param resource resource
     * @return 查询结果
     */
    List<VueRouter> findVisibleResource(ResourceQueryReq resource);




    /**
     * 添加资源
     *
     * @param resource 资源
     */
    void addResource(Resource resource);

    /**
     * 修改资源
     *
     * @param resource 资源
     */
    void editResourceById(Resource resource);

    /**
     * 删除资源
     *
     * @param resourceId resourceId
     */
    void delResource(Long resourceId);


}
