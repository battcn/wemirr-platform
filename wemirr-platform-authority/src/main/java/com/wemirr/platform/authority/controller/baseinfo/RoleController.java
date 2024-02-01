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
package com.wemirr.platform.authority.controller.baseinfo;

import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.wemirr.framework.commons.BeanUtilPlus;
import com.wemirr.framework.commons.annotation.log.AccessLog;
import com.wemirr.framework.commons.security.AuthenticationContext;
import com.wemirr.framework.db.mybatisplus.wrap.Wraps;
import com.wemirr.platform.authority.domain.baseinfo.entity.Role;
import com.wemirr.platform.authority.domain.baseinfo.entity.RoleRes;
import com.wemirr.platform.authority.domain.baseinfo.req.RolePageReq;
import com.wemirr.platform.authority.domain.baseinfo.req.RoleReq;
import com.wemirr.platform.authority.domain.baseinfo.req.RoleResSaveReq;
import com.wemirr.platform.authority.domain.baseinfo.req.RoleUserReq;
import com.wemirr.platform.authority.domain.baseinfo.resp.*;
import com.wemirr.platform.authority.service.RoleResService;
import com.wemirr.platform.authority.service.RoleService;
import com.wemirr.platform.authority.service.UserRoleService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.prepost.PreAuthorize;
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
    
    private final AuthenticationContext authenticationContext;
    private final RoleService roleService;
    private final RoleResService roleResService;
    private final UserRoleService userRoleService;
    
    @GetMapping("/query_all")
    @Operation(summary = "角色列表 - [Levin] - [DONE]")
    public List<Role> query() {
        return this.roleService.list();
    }
    
    @GetMapping
    @Parameters({
            @Parameter(description = "名称", name = "name", in = ParameterIn.QUERY),
    })
    @Operation(summary = "角色列表 - [Levin] - [DONE]")
    @PreAuthorize("hasAuthority('sys:role:page')")
    public IPage<RolePageResp> pageList(RolePageReq req) {
        return this.roleService.page(req.buildPage(), Wraps.<Role>lbQ().like(Role::getName, req.getName())
                .eq(Role::getLocked, req.getLocked()).eq(Role::getScopeType, req.getScopeType()))
                .convert(x -> BeanUtil.toBean(x, RolePageResp.class));
    }
    
    @GetMapping("/{id}/detail")
    @Operation(summary = "角色详情")
    public RoleDetailResp details(@PathVariable Long id) {
        Role role = roleService.getById(id);
        RoleDetailResp detail = BeanUtilPlus.toBean(role, RoleDetailResp.class);
        final RoleResResp authority = this.roleResService.findAuthorityIdByRoleId(id);
        detail.setAuthority(authority);
        return detail;
    }
    
    @PostMapping
    @AccessLog(description = "添加角色")
    @Operation(summary = "添加角色")
    @PreAuthorize("hasAuthority('sys:role:add')")
    public void add(@Validated @RequestBody RoleReq data) {
        roleService.saveRole(authenticationContext.userId(), data);
    }
    
    @PutMapping("/{id}")
    @AccessLog(description = "编辑角色")
    @Operation(summary = "编辑角色")
    @PreAuthorize("hasAuthority('sys:role:edit')")
    public void edit(@PathVariable Long id, @Validated @RequestBody RoleReq data) {
        roleService.updateRole(id, authenticationContext.userId(), data);
    }
    
    @DeleteMapping("/{id}")
    @AccessLog(description = "删除角色")
    @Operation(summary = "删除角色")
    @PreAuthorize("hasAuthority('sys:role:remove')")
    public void del(@PathVariable Long id) {
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
    
    @Operation(summary = "角色分配操作资源")
    @PostMapping("/{roleId}/authority")
    @PreAuthorize("hasAuthority('sys:role:distribution:res')")
    public void distributionAuthority(@PathVariable Long roleId, @RequestBody RoleResSaveReq dto) {
        this.roleResService.saveRoleAuthority(dto);
        
    }
    
    @Operation(summary = "角色分配用户")
    @PostMapping("/{roleId}/users")
    @PreAuthorize("hasAuthority('sys:role:distribution:user')")
    public void distributionUser(@PathVariable Long roleId, @RequestBody RoleUserReq dto) {
        this.roleService.saveUserRole(roleId, dto.getUserIdList());
    }
    
}
