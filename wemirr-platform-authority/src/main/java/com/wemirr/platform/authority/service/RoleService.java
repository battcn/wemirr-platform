package com.wemirr.platform.authority.service;

import com.wemirr.framework.db.mybatisplus.ext.SuperService;
import com.wemirr.platform.authority.domain.baseinfo.entity.Role;
import com.wemirr.platform.authority.domain.baseinfo.req.RoleReq;
import com.wemirr.platform.authority.domain.baseinfo.resp.RolePermissionResp;

import java.util.List;

/**
 * <p>
 * 业务接口
 * 角色
 * </p>
 *
 * @author Levin
 * @since 2019-07-03
 */
public interface RoleService extends SuperService<Role> {


    /**
     * 根据 scope 查询角色
     *
     * @return 查询结果
     */
    List<Role> list();

    /**
     * 根据角色ID移除
     *
     * @param roleId roleId
     */
    void removeByRoleId(Long roleId);


    /**
     * 1，保存角色
     * 2，保存 与组织的关系
     *
     * @param req    req
     * @param userId 用户id
     */
    void saveRole(Long userId, RoleReq req);

    /**
     * 修改角色
     *
     * @param req    req
     * @param userId userId
     * @param roleId 角色ID
     */
    void updateRole(Long roleId, Long userId, RoleReq req);


    /**
     * 给角色分配用户
     *
     * @param roleId     roleId
     * @param userIdList userIdList
     */
    void saveUserRole(Long roleId, List<Long> userIdList);


    /**
     * 根据角色ID查询资源码
     *
     * @param roleId 角色ID
     * @return 查询结果
     */
    RolePermissionResp findRolePermissionById(Long roleId);
}
