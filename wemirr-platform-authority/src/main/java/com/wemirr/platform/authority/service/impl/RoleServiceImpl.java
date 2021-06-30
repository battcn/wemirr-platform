package com.wemirr.platform.authority.service.impl;

import cn.hutool.core.collection.CollectionUtil;
import com.wemirr.framework.boot.service.impl.SuperServiceImpl;
import com.wemirr.framework.boot.utils.BeanPlusUtil;
import com.wemirr.framework.commons.exception.CheckedException;
import com.wemirr.framework.database.mybatis.auth.DataScope;
import com.wemirr.framework.database.mybatis.conditions.Wraps;
import com.wemirr.platform.authority.domain.dto.RoleDTO;
import com.wemirr.platform.authority.domain.entity.Role;
import com.wemirr.platform.authority.domain.entity.RoleOrg;
import com.wemirr.platform.authority.domain.entity.RoleRes;
import com.wemirr.platform.authority.domain.entity.UserRole;
import com.wemirr.platform.authority.repository.RoleMapper;
import com.wemirr.platform.authority.service.RoleOrgService;
import com.wemirr.platform.authority.service.RoleResService;
import com.wemirr.platform.authority.service.RoleService;
import com.wemirr.platform.authority.service.UserRoleService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


/**
 * <p>
 * 业务实现类
 * 角色
 * </p>
 *
 * @author Levin
 * @since 2019-07-03
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class RoleServiceImpl extends SuperServiceImpl<RoleMapper, Role> implements RoleService {

    private final RoleOrgService roleOrgService;
    private final RoleResService roleResService;
    private final UserRoleService userRoleService;


    @Override
    public List<Role> list(DataScope scope) {
        return baseMapper.list(scope);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void removeByRoleId(Long roleId) {
        final Role role = Optional.ofNullable(baseMapper.selectById(roleId)).orElseThrow(() -> CheckedException.notFound("角色不存在"));
        if (role.getReadonly()) {
            throw CheckedException.badRequest("内置角色无法删除");
        }
        if (role.getSuperRole()) {
            throw CheckedException.badRequest("超级角色无法删除");
        }
        baseMapper.deleteById(roleId);
        roleOrgService.remove(Wraps.<RoleOrg>lbQ().eq(RoleOrg::getRoleId, roleId));
        roleResService.remove(Wraps.<RoleRes>lbQ().eq(RoleRes::getRoleId, roleId));
        userRoleService.remove(Wraps.<UserRole>lbQ().eq(UserRole::getRoleId, roleId));
    }


    @Override
    public void saveRole(Long userId, RoleDTO data) {
        Role role = BeanPlusUtil.toBean(data, Role.class);
        role.setReadonly(false);
        super.save(role);
        saveRoleOrg(role, data.getOrgList());
    }

    @Override
    public void updateRole(Long roleId, Long userId, RoleDTO data) {
        Role role = BeanPlusUtil.toBean(data, Role.class);
        if (role.getReadonly()) {
            throw CheckedException.badRequest("内置角色无法编辑");
        }
        if (role.getSuperRole()) {
            throw CheckedException.badRequest("超级角色无法编辑");
        }
        role.setId(roleId);
        baseMapper.updateById(role);

        roleOrgService.remove(Wraps.<RoleOrg>lbQ().eq(RoleOrg::getRoleId, roleId));
        saveRoleOrg(role, data.getOrgList());
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void saveUserRole(Long roleId, List<Long> userIdList) {
        this.userRoleService.remove(Wraps.<UserRole>lbQ().eq(UserRole::getRoleId, roleId));
        final List<UserRole> userRoles = userIdList.stream().map(userId -> UserRole.builder()
                .roleId(roleId).userId(userId).build())
                .collect(Collectors.toList());
        this.userRoleService.saveBatch(userRoles);
    }

    private void saveRoleOrg(Role role, List<Long> orgList) {
        // 根据 数据范围类型 和 勾选的组织ID， 重新计算全量的组织ID
        if (CollectionUtil.isNotEmpty(orgList)) {
            List<RoleOrg> list = orgList.stream().map((orgId) -> RoleOrg.builder().orgId(orgId).roleId(role.getId()).build()
            ).collect(Collectors.toList());
            roleOrgService.saveBatch(list);
        }
    }
}
