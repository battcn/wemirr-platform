package com.wemirr.platform.authority.service;

import com.wemirr.framework.boot.service.SuperService;
import com.wemirr.framework.database.mybatis.auth.DataScope;
import com.wemirr.platform.authority.domain.dto.RoleDTO;
import com.wemirr.platform.authority.domain.entity.Role;
import com.wemirr.platform.authority.domain.vo.RolePermissionResp;

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
     * @param scope scope
     * @return 查询结果
     */
    List<Role> list(DataScope scope);

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
     * @param data   data
     * @param userId 用户id
     */
    void saveRole(Long userId, RoleDTO data);

    /**
     * 修改角色
     *
     * @param role   role
     * @param userId userId
     * @param roleId 角色ID
     */
    void updateRole(Long roleId, Long userId, RoleDTO role);


    /**
     * 给角色分配用户
     *
     * @param roleId     roleId
     * @param userIdList userIdList
     */
    void saveUserRole(Long roleId, List<Long> userIdList);


    RolePermissionResp findRolePermissionById(Long roleId);
}
