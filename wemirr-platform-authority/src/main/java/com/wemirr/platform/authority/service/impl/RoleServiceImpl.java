package com.wemirr.platform.authority.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.collection.CollectionUtil;
import com.baomidou.dynamic.datasource.annotation.DSTransactional;
import com.google.common.collect.Lists;
import com.wemirr.framework.commons.exception.CheckedException;
import com.wemirr.framework.commons.security.AuthenticationContext;
import com.wemirr.framework.db.mybatisplus.ext.SuperServiceImpl;
import com.wemirr.framework.db.mybatisplus.wrap.Wraps;
import com.wemirr.platform.authority.domain.baseinfo.entity.Role;
import com.wemirr.platform.authority.domain.baseinfo.entity.RoleOrg;
import com.wemirr.platform.authority.domain.baseinfo.entity.RoleRes;
import com.wemirr.platform.authority.domain.baseinfo.entity.UserRole;
import com.wemirr.platform.authority.domain.baseinfo.req.ResourceQueryReq;
import com.wemirr.platform.authority.domain.baseinfo.req.RoleReq;
import com.wemirr.platform.authority.domain.baseinfo.resp.RolePermissionResp;
import com.wemirr.platform.authority.domain.baseinfo.resp.VueRouter;
import com.wemirr.platform.authority.repository.baseinfo.*;
import com.wemirr.platform.authority.service.RoleService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

import static java.util.stream.Collectors.toList;


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

    private final RoleResMapper roleResMapper;
    private final RoleOrgMapper roleOrgMapper;
    private final UserRoleMapper userRoleMapper;
    private final ResourceMapper resourceMapper;
    private final AuthenticationContext authenticationContext;

    @Override
    public List<Role> list() {
        return baseMapper.list();
    }

    @Override
    @DSTransactional
    public void removeByRoleId(Long roleId) {
        final Role role = Optional.ofNullable(baseMapper.selectById(roleId)).orElseThrow(() -> CheckedException.notFound("角色不存在"));
        if (role.getReadonly()) {
            throw CheckedException.badRequest("内置角色无法删除");
        }
        if (role.getSuperRole()) {
            throw CheckedException.badRequest("超级角色无法删除");
        }
        baseMapper.deleteById(roleId);
        roleOrgMapper.delete(Wraps.<RoleOrg>lbQ().eq(RoleOrg::getRoleId, roleId));
        roleResMapper.delete(Wraps.<RoleRes>lbQ().eq(RoleRes::getRoleId, roleId));
        userRoleMapper.delete(Wraps.<UserRole>lbQ().eq(UserRole::getRoleId, roleId));
    }


    @Override
    public void saveRole(Long userId, RoleReq data) {
        Role role = BeanUtil.toBean(data, Role.class);
        role.setReadonly(false);
        super.save(role);
        saveRoleOrg(role, data.getOrgList());
    }

    @Override
    @DSTransactional
    public void updateRole(Long roleId, Long userId, RoleReq data) {
        Role role = BeanUtil.toBean(data, Role.class);
        if (role.getReadonly() != null && role.getReadonly()) {
            throw CheckedException.badRequest("内置角色无法编辑");
        }
        if (role.getSuperRole() != null && role.getSuperRole()) {
            throw CheckedException.badRequest("超级角色无法编辑");
        }
        role.setId(roleId);
        baseMapper.updateById(role);
        roleOrgMapper.delete(Wraps.<RoleOrg>lbQ().eq(RoleOrg::getRoleId, roleId));
        saveRoleOrg(role, data.getOrgList());
    }

    @Override
    @DSTransactional
    public void saveUserRole(Long roleId, List<Long> userIdList) {
        this.userRoleMapper.delete(Wraps.<UserRole>lbQ().eq(UserRole::getRoleId, roleId));
        final List<UserRole> userRoles = userIdList.stream().map(userId -> UserRole.builder()
                        .roleId(roleId).userId(userId).build())
                .toList();
        this.userRoleMapper.insertBatchSomeColumn(userRoles);
    }

    private void saveRoleOrg(Role role, List<Long> orgList) {
        if (CollectionUtil.isEmpty(orgList)) {
            return;
        }
        // 根据 数据范围类型 和 勾选的组织ID， 重新计算全量的组织ID
        List<RoleOrg> list = orgList.stream().map((orgId) -> RoleOrg.builder().orgId(orgId)
                .roleId(role.getId()).build()).toList();
        for (RoleOrg roleOrg : list) {
            roleOrgMapper.insert(roleOrg);
        }
    }

    @Override
    public RolePermissionResp findRolePermissionById(Long roleId) {
        final List<VueRouter> buttons = resourceMapper.findVisibleResource(ResourceQueryReq.builder()
                .userId(authenticationContext.userId()).build());
        final List<Long> roleRes = Optional.of(this.roleResMapper.selectList(Wraps.<RoleRes>lbQ().eq(RoleRes::getRoleId, roleId)))
                .orElse(Lists.newArrayList()).stream().map(RoleRes::getResId).collect(toList());
        return RolePermissionResp.builder().resIdList(roleRes).buttons(buttons).build();
    }
}
