/*
 * Copyright (c) 2023 WEMIRR-PLATFORM Authors. All Rights Reserved.
 *
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.wemirr.platform.authority.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.collection.CollectionUtil;
import com.baomidou.dynamic.datasource.annotation.DSTransactional;
import com.wemirr.framework.commons.exception.CheckedException;
import com.wemirr.framework.commons.security.AuthenticationContext;
import com.wemirr.framework.commons.security.DataResourceType;
import com.wemirr.framework.db.mybatisplus.ext.SuperServiceImpl;
import com.wemirr.framework.db.mybatisplus.wrap.Wraps;
import com.wemirr.platform.authority.domain.baseinfo.entity.DataPermissionResource;
import com.wemirr.platform.authority.domain.baseinfo.entity.Role;
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
    private final DataPermissionResourceMapper dataPermissionResourceMapper;
    private final UserRoleMapper userRoleMapper;
    private final ResourceMapper resourceMapper;
    private final AuthenticationContext context;

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
        dataPermissionResourceMapper.delete(Wraps.<DataPermissionResource>lbQ()
                .eq(DataPermissionResource::getOwnerId, roleId)
                .eq(DataPermissionResource::getOwnerType, DataResourceType.ROLE));
        roleResMapper.delete(Wraps.<RoleRes>lbQ().eq(RoleRes::getRoleId, roleId));
        userRoleMapper.delete(Wraps.<UserRole>lbQ().eq(UserRole::getRoleId, roleId));
    }

    @Override
    public void saveRole(Long userId, RoleReq req) {
        Role role = BeanUtil.toBean(req, Role.class);
        role.setReadonly(false);
        super.save(role);
        saveRoleOrgDataPermission(role.getId(), req.getOrgList());
    }

    @Override
    @DSTransactional(rollbackFor = Exception.class)
    public void updateRole(Long roleId, Long userId, RoleReq req) {
        Role role = BeanUtil.toBean(req, Role.class);
        if (role.getReadonly() != null && role.getReadonly()) {
            throw CheckedException.badRequest("内置角色无法编辑");
        }
        if (role.getSuperRole() != null && role.getSuperRole()) {
            throw CheckedException.badRequest("超级角色无法编辑");
        }
        role.setId(roleId);
        this.baseMapper.updateById(role);
        saveRoleOrgDataPermission(role.getId(), req.getOrgList());
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

    private void saveRoleOrgDataPermission(Long roleId, List<Long> orgList) {
        dataPermissionResourceMapper.delete(Wraps.<DataPermissionResource>lbQ()
                .eq(DataPermissionResource::getOwnerId, roleId)
                .eq(DataPermissionResource::getOwnerType, DataResourceType.ROLE)
                .eq(DataPermissionResource::getDataType, DataResourceType.ORG));
        if (CollectionUtil.isEmpty(orgList)) {
            return;
        }
        // 根据 数据范围类型 和 勾选的组织ID， 重新计算全量的组织ID
        List<DataPermissionResource> list = orgList.stream()
                .map(orgId -> DataPermissionResource.builder().dataId(orgId)
                        .ownerId(roleId).build())
                .collect(toList());
        dataPermissionResourceMapper.insertBatchSomeColumn(list);
    }

    @Override
    public RolePermissionResp findRolePermissionById(Long roleId) {
        final List<VueRouter> buttons = resourceMapper.findVisibleResource(ResourceQueryReq.builder()
                .userId(context.userId()).build());
        final List<Long> roleRes = Optional.of(this.roleResMapper.selectList(RoleRes::getRoleId, roleId))
                .orElseGet(List::of).stream().map(RoleRes::getResId).toList();
        return RolePermissionResp.builder().resIdList(roleRes).buttons(buttons).build();
    }
}
