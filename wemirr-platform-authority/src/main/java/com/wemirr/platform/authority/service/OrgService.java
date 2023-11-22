package com.wemirr.platform.authority.service;


import com.wemirr.framework.db.mybatisplus.ext.SuperService;
import com.wemirr.platform.authority.domain.baseinfo.entity.Org;
import com.wemirr.platform.authority.domain.baseinfo.req.OrgSaveReq;

import java.util.List;

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
     * @param req req
     */
    void addOrg(OrgSaveReq req);

    /**
     * 获取当前机构本级及子级的ID
     *
     * @param id id
     * @return 本级及子级ID
     */
    List<Long> getFullTreeIdPath(Long id);
}
