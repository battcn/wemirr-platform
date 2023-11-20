package com.wemirr.platform.authority.service;


import com.wemirr.framework.db.mybatisplus.ext.SuperService;
import com.wemirr.platform.authority.domain.baseinfo.entity.Org;

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
     * 批量删除以及删除其子节点
     *
     * @param id id
     */
    void remove(Long id);


    /**
     * 添加组织
     *
     * @param org org
     */
    void addOrg(Org org);

}
