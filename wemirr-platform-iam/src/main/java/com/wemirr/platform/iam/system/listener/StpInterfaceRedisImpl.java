package com.wemirr.platform.iam.system.listener;

import cn.dev33.satoken.SaManager;
import cn.dev33.satoken.stp.StpInterface;
import cn.hutool.core.collection.CollUtil;
import com.wemirr.platform.iam.system.domain.entity.Role;
import com.wemirr.platform.iam.system.repository.ResourceMapper;
import com.wemirr.platform.iam.system.repository.RoleMapper;
import com.wemirr.platform.iam.system.repository.UserMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author Levin
 */
@Slf4j
@Primary
@Component
@RequiredArgsConstructor
@SuppressWarnings("unchecked")
public class StpInterfaceRedisImpl implements StpInterface {

    private final UserMapper userMapper;
    private final ResourceMapper resourceMapper;
    private final RoleMapper roleMapper;

    private String buildCacheKey(String tokenValue) {
        return String.format("USER_INFO_KEY:%s", tokenValue);
    }

    /**
     * 返回一个账号所拥有的权限码集合
     *
     * @param loginId   账号id
     * @param loginType 账号类型
     * @return 返回一个账号所拥有的权限码集合
     */
    @Override

    public List<String> getPermissionList(Object loginId, String loginType) {
        // 1.遍历角色列表，查询拥有的权限码
        List<String> permissionList = (List<String>) SaManager.getSaTokenDao().getObject("wp-token:loginId-find-permission:" + loginId);
        if (CollUtil.isEmpty(permissionList)) {
            // 从数据库查询这个角色 id 所拥有的权限列表
            permissionList = this.resourceMapper.selectPermissionByUserId(Long.parseLong(loginId.toString()));
            // 查好后，set 到缓存中
            SaManager.getSaTokenDao().setObject("wp-token:loginId-find-permission:" + loginId, permissionList, 60 * 60 * 24 * 30);
        }
        // 2. 返回权限码集合
        return permissionList;
    }

    /**
     * 返回一个账号所拥有的角色标识集合
     *
     * @param loginId   账号id
     * @param loginType 账号类型
     * @return 返回一个账号所拥有的角色标识集合
     */
    @Override
    public List<String> getRoleList(Object loginId, String loginType) {
        List<String> roleList = (List<String>) SaManager.getSaTokenDao().getObject("wp-token:loginId-find-role:" + loginId);
        if (roleList == null) {
            // 从数据库查询这个账号id拥有的角色列表，
            roleList = this.roleMapper.findRoleByUserId(Long.parseLong(loginId.toString())).stream().map(Role::getCode).distinct().toList();
            // 查好后，set 到缓存中
            SaManager.getSaTokenDao().setObject("wp-token:loginId-find-role:" + loginId, roleList, 60 * 60 * 24 * 30);
        }
        return roleList;
    }
}
