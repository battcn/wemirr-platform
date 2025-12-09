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

package com.wemirr.platform.iam.system.controller;

import cn.dev33.satoken.annotation.SaCheckPermission;
import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.wemirr.framework.commons.annotation.log.AccessLog;
import com.wemirr.framework.db.mybatisplus.wrap.Wraps;
import com.wemirr.platform.iam.system.domain.dto.req.RolePageReq;
import com.wemirr.platform.iam.system.domain.dto.req.RoleResSaveReq;
import com.wemirr.platform.iam.system.domain.dto.req.RoleSaveReq;
import com.wemirr.platform.iam.system.domain.dto.req.RoleUserSaveReq;
import com.wemirr.platform.iam.system.domain.dto.resp.RoleDetailResp;
import com.wemirr.platform.iam.system.domain.dto.resp.RolePageResp;
import com.wemirr.platform.iam.system.domain.dto.resp.RolePermissionResp;
import com.wemirr.platform.iam.system.domain.dto.resp.UserRoleResp;
import com.wemirr.platform.iam.system.domain.entity.Role;
import com.wemirr.platform.iam.system.domain.entity.RoleRes;
import com.wemirr.platform.iam.system.service.RoleResService;
import com.wemirr.platform.iam.system.service.RoleService;
import com.wemirr.platform.iam.system.service.UserRoleService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 角色管理
 *
 * @author Levin
 */
@Slf4j
@Validated
@RestController
@RequestMapping("/roles")
@RequiredArgsConstructor
@Tag(name = "角色管理", description = "角色管理")
public class RoleController {

    private final RoleService roleService;
    private final RoleResService roleResService;
    private final UserRoleService userRoleService;

    @PostMapping("/page")
    @Parameters({
            @Parameter(description = "名称", name = "name", in = ParameterIn.QUERY),
    })
    @Operation(summary = "角色列表 - [Levin] - [DONE]")
    @SaCheckPermission(value = {"sys:role:page"})
    public IPage<RolePageResp> pageList(@RequestBody RolePageReq req) {
        return this.roleService.page(req.buildPage(), Wraps.<Role>lbQ().like(Role::getName, req.getName())
                        .eq(Role::getStatus, req.getStatus()).eq(Role::getScopeType, req.getScopeType()))
                .convert(x -> BeanUtil.toBean(x, RolePageResp.class));
    }

    @GetMapping("/{id}/detail")
    @Operation(summary = "角色详情")
    public RoleDetailResp detail(@PathVariable Long id) {
        return roleService.detail(id);
    }

    @PostMapping("/create")
    @AccessLog(module = "角色管理", description = "添加角色")
    @Operation(summary = "添加角色")
    @SaCheckPermission(value = {"sys:role:add"})
    public void create(@Validated @RequestBody RoleSaveReq req) {
        roleService.create(req);
    }

    @PutMapping("/{id}")
    @AccessLog(module = "角色管理", description = "编辑角色")
    @Operation(summary = "编辑角色")
    @SaCheckPermission(value = {"sys:role:edit"})
    public void modify(@PathVariable Long id, @Validated @RequestBody RoleSaveReq req) {
        roleService.modify(id, req);
    }

    @DeleteMapping("/{id}")
    @AccessLog(module = "角色管理", description = "删除角色")
    @Operation(summary = "删除角色")
    @SaCheckPermission(value = {"sys:role:remove"})
    public void remove(@PathVariable Long id) {
        this.roleService.removeByRoleId(id);
    }

    @Operation(summary = "角色关联的用户")
    @GetMapping("/{roleId}/users")
    public UserRoleResp userByRoleId(@PathVariable Long roleId) {
        return userRoleService.findUserByRoleId(roleId);
    }

    @GetMapping("/{role_id}/permissions")
    @Operation(summary = "资源权限", description = "只能看到自身权限")
    public RolePermissionResp permission(@PathVariable("role_id") Long roleId) {
        return this.roleService.findRolePermissionById(roleId);
    }

    @Operation(summary = "角色关联的资源")
    @GetMapping("/{roleId}/role_res")
    public List<RoleRes> resByRoleId(@PathVariable Long roleId) {
        return roleResService.list(Wraps.<RoleRes>lbQ().eq(RoleRes::getRoleId, roleId));
    }

    @Operation(summary = "分配资源")
    @PutMapping("/{roleId}/assign-resources")
    @SaCheckPermission(value = {"sys:role:assign-resource"})
    public void assignResource(@PathVariable Long roleId, @RequestBody RoleResSaveReq req) {
        this.roleResService.assignResource(req);

    }

    @Operation(summary = "分配用户")
    @PutMapping("/{roleId}/assign-users")
    @SaCheckPermission(value = {"sys:role:assign-users"})
    public void assignUser(@PathVariable Long roleId, @RequestBody RoleUserSaveReq req) {
        this.roleService.assignUser(roleId, req.getUserIdList());
    }

}
