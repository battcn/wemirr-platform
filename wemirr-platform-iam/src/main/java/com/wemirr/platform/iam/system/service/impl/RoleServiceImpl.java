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

package com.wemirr.platform.iam.system.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.collection.CollectionUtil;
import com.baomidou.dynamic.datasource.annotation.DSTransactional;
import com.wemirr.framework.commons.BeanUtilPlus;
import com.wemirr.framework.commons.exception.CheckedException;
import com.wemirr.framework.commons.security.DataResourceType;
import com.wemirr.framework.commons.security.DataScopeType;
import com.wemirr.framework.db.mybatisplus.ext.SuperServiceImpl;
import com.wemirr.framework.db.mybatisplus.wrap.Wraps;
import com.wemirr.platform.iam.system.domain.dto.req.RoleSaveReq;
import com.wemirr.platform.iam.system.domain.dto.resp.RoleDetailResp;
import com.wemirr.platform.iam.system.domain.dto.resp.RolePermissionResp;
import com.wemirr.platform.iam.system.domain.entity.*;
import com.wemirr.platform.iam.system.domain.enums.ResourceType;
import com.wemirr.platform.iam.system.repository.*;
import com.wemirr.platform.iam.system.service.RoleService;
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
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class RoleServiceImpl extends SuperServiceImpl<RoleMapper, Role> implements RoleService {

    private final RoleResMapper roleResMapper;
    private final DataPermissionRefMapper dataPermissionRefMapper;
    private final UserRoleMapper userRoleMapper;
    private final ResourceMapper resourceMapper;

    @Override
    public List<Role> list() {
        return baseMapper.list();
    }


    @Override
    public RoleDetailResp detail(Long id) {
        var role = Optional.ofNullable(this.baseMapper.selectById(id)).orElseThrow(() -> CheckedException.notFound("角色不存在"));
        var bean = BeanUtilPlus.toBean(role, RoleDetailResp.class);
        if (bean.getScopeType() != DataScopeType.CUSTOMIZE) {
            return bean;
        }
        var dataList = this.dataPermissionRefMapper.selectList(Wraps.<DataPermissionRef>lbQ()
                .eq(DataPermissionRef::getOwnerId, id)
                .eq(DataPermissionRef::getOwnerType, DataResourceType.ROLE)
                .eq(DataPermissionRef::getDataType, DataResourceType.ORG));
        if (CollUtil.isEmpty(dataList)) {
            return bean;
        }
        bean.setOrgIdList(dataList.stream().map(DataPermissionRef::getDataId).filter(java.util.Objects::nonNull).distinct().toList());
        return bean;
    }

    @Override
    @DSTransactional(rollbackFor = Exception.class)
    public void removeByRoleId(Long roleId) {
        final Role role = Optional.ofNullable(baseMapper.selectById(roleId)).orElseThrow(() -> CheckedException.notFound("角色不存在"));
        if (role.getReadonly()) {
            throw CheckedException.badRequest("内置角色无法删除");
        }
        if (role.getSuperRole()) {
            throw CheckedException.badRequest("超级角色无法删除");
        }
        baseMapper.deleteById(roleId);
        dataPermissionRefMapper.delete(Wraps.<DataPermissionRef>lbQ()
                .eq(DataPermissionRef::getOwnerId, roleId)
                .eq(DataPermissionRef::getOwnerType, DataResourceType.ROLE));
        roleResMapper.delete(Wraps.<RoleRes>lbQ().eq(RoleRes::getRoleId, roleId));
        userRoleMapper.delete(Wraps.<UserRole>lbQ().eq(UserRole::getRoleId, roleId));
    }

    @Override
    @DSTransactional(rollbackFor = Exception.class)
    public void create(RoleSaveReq req) {
        Role role = BeanUtil.toBean(req, Role.class);
        role.setReadonly(false);
        super.save(role);
    }

    @Override
    @DSTransactional(rollbackFor = Exception.class)
    public void modify(Long roleId, RoleSaveReq req) {
        Role role = Optional.ofNullable(this.baseMapper.selectById(roleId)).orElseThrow(() -> CheckedException.notFound("角色不存在"));
        if (role.getReadonly() != null && role.getReadonly()) {
            throw CheckedException.badRequest("内置角色无法编辑");
        }
        if (role.getSuperRole() != null && role.getSuperRole()) {
            throw CheckedException.badRequest("超级角色无法编辑");
        }
        final long count = this.baseMapper.selectCount(Wraps.<Role>lbQ().ne(Role::getId, roleId).eq(Role::getCode, req.getCode()));
        if (count > 0) {
            throw CheckedException.badRequest("角色编码已存在");
        }
        var bean = BeanUtilPlus.toBean(roleId, req, Role.class);
        this.baseMapper.updateById(bean);
    }

    @Override
    @DSTransactional(rollbackFor = Exception.class)
    public void assignUser(Long roleId, List<Long> userIdList) {
        this.userRoleMapper.delete(Wraps.<UserRole>lbQ().eq(UserRole::getRoleId, roleId));
        if (CollUtil.isEmpty(userIdList)) {
            return;
        }
        final List<UserRole> userRoles = userIdList.stream()
                .map(userId -> UserRole.builder().roleId(roleId).userId(userId).build())
                .collect(toList());
        this.userRoleMapper.insertBatchSomeColumn(userRoles);
    }

    private void addDataPermission(Long roleId, List<Long> orgList) {
        dataPermissionRefMapper.delete(Wraps.<DataPermissionRef>lbQ()
                .eq(DataPermissionRef::getOwnerId, roleId)
                .eq(DataPermissionRef::getOwnerType, DataResourceType.ROLE)
                .eq(DataPermissionRef::getDataType, DataResourceType.ORG));
        if (CollectionUtil.isEmpty(orgList)) {
            return;
        }
        // 根据 数据范围类型 和 勾选的组织ID， 重新计算全量的组织ID
        List<DataPermissionRef> list = orgList.stream().map(orgId -> DataPermissionRef.builder()
                .ownerId(roleId)
                .ownerType(DataResourceType.ROLE)
                .dataType(DataResourceType.ORG)
                .dataId(orgId)
                .build()).collect(toList());
        dataPermissionRefMapper.insertBatchSomeColumn(list);
    }

    @Override
    public RolePermissionResp findRolePermissionById(Long roleId) {
        final List<Resource> resourceList = resourceMapper.selectList();
        if (CollUtil.isEmpty(resourceList)) {
            return null;
        }
        List<Long> resIdList = this.roleResMapper.selectList(RoleRes::getRoleId, roleId)
                .stream().map(RoleRes::getResId).distinct().toList();
        List<Long> buttonIdList = resourceList.stream()
                .filter(x -> x.getType() == ResourceType.BUTTON)
                .filter(x -> resIdList.contains(x.getId()))
                .map(Resource::getId)
                .toList();
        List<Long> menuIdList = resourceList.stream()
                .filter(x -> x.getType() != ResourceType.BUTTON)
                .filter(x -> resIdList.contains(x.getId()))
                .map(Resource::getId)
                .toList();
        return RolePermissionResp.builder().menuIdList(menuIdList).buttonIdList(buttonIdList).build();
    }

}
