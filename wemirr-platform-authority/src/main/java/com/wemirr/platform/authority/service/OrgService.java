package com.wemirr.platform.authority.service;


import com.wemirr.framework.db.mybatis.SuperService;
import com.wemirr.platform.authority.domain.entity.baseinfo.Org;

import java.io.Serializable;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * <p>
 * 业务接口
 * 组织
 * </p>
 *
 * @author Levin
 * @since 2019-07-22
 */
public interface OrgService extends SuperService<Org> {


    /**
     * 查询子集
     *
     * @param ids ids
     * @return 查询结果
     */
    List<Org> findChildren(List<Long> ids);

    /**
     * 批量删除以及删除其子节点
     *
     * @param id id
     */
    void remove(Long id);


    /**
     * 添加组织
     * @param org org
     */
    void addOrg(Org org);

    /**
     * 根据 id 查询组织，并转换成Map结构
     *
     * @param ids ids
     * @return 查询结果
     */
    Map<Serializable, Object> findOrgByIds(Set<Serializable> ids);
}
