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
import com.wemirr.framework.commons.BeanUtilPlus;
import com.wemirr.framework.commons.MapHelper;
import com.wemirr.framework.commons.annotation.log.AccessLog;
import com.wemirr.framework.commons.entity.Entity;
import com.wemirr.framework.db.mybatisplus.datascope.service.DataScopeService;
import com.wemirr.framework.excel.annotation.ResponseExcel;
import com.wemirr.platform.iam.system.domain.dto.req.UserPageReq;
import com.wemirr.platform.iam.system.domain.dto.req.UserSaveReq;
import com.wemirr.platform.iam.system.domain.dto.req.UserUpdateReq;
import com.wemirr.platform.iam.system.domain.dto.resp.UserPageResp;
import com.wemirr.platform.iam.system.domain.entity.User;
import com.wemirr.platform.iam.system.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * 用户管理
 *
 * @author Levin
 */
@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/users")
@Tag(name = "用户管理", description = "用户管理")
public class UserController {
    
    private final UserService userService;
    private final DataScopeService dataScopeService;
    
    @PostMapping("/page")
    @Operation(summary = "用户列表 - [Levin] - [DONE]")
    @SaCheckPermission(value = {"sys:user:page"})
    public IPage<UserPageResp> pageList(@RequestBody UserPageReq req) {
        return this.userService.pageList(req);
    }
    
    @PutMapping("/{id}/reset-password")
    @SaCheckPermission(value = {"sys:user:reset"})
    @Operation(summary = "重置密码", description = "重置密码,并且将随机生成的密码通过邮箱/短信的形式发送")
    public void resetPassword(@PathVariable Long id) {
        this.userService.resetPassword(id);
    }
    
    @PostMapping("/export")
    @Operation(summary = "用户列表 - [Levin] - [DONE]")
    @SaCheckPermission(value = {"sys:user:export"})
    @ResponseExcel(fileName = "用户列表")
    public List<UserPageResp> exportList(@RequestBody UserPageReq req) {
        // 因为导出要全部数据
        req.setCurrent(1);
        req.setSize(-1);
        return this.userService.pageList(req).getRecords();
    }
    
    @PostMapping("/create")
    @AccessLog(module = "用户管理", description = "添加用户")
    @Operation(summary = "添加用户")
    @SaCheckPermission(value = {"sys:user:add"})
    public void create(@Validated @RequestBody UserSaveReq req) {
        this.userService.create(req);
    }

    @PutMapping("/{id}")
    @AccessLog(module = "用户管理", description = "编辑用户")
    @Operation(summary = "编辑用户")
    @SaCheckPermission(value = {"sys:user:edit"})
    public void modify(@PathVariable Long id, @Validated(Entity.Update.class) @RequestBody UserUpdateReq req) {
        this.userService.modify(id, req);
    }

    @DeleteMapping("/{id}")
    @AccessLog(module = "用户管理", description = "删除用户")
    @Operation(summary = "删除用户")
    @SaCheckPermission(value = {"sys:user:remove"})
    public void del(@PathVariable Long id) {
        this.userService.delete(id);
    }
    
    @PostMapping("/ids")
    @Operation(summary = "ID批量查询")
    public List<UserPageResp> idList(@RequestBody Set<Long> ids) {
        return BeanUtilPlus.toBeans(this.userService.listByIds(ids), UserPageResp.class);
    }
    
    @PostMapping("/batch_ids")
    @Operation(summary = "ID批量查询")
    public Map<Long, UserPageResp> batchIds(@RequestBody Set<Long> ids) {
        final List<User> users = this.userService.listByIds(ids);
        return MapHelper.toHashMap(users, Entity::getId, x -> BeanUtil.toBean(x, UserPageResp.class));
    }

    @GetMapping("/{id}")
    @Operation(summary = "用户信息查询")
    public UserPageResp getUser(@PathVariable Long id) {
        final User user = this.userService.getById(id);
        return BeanUtil.toBean(user, UserPageResp.class);
    }
    
    @GetMapping("/{id}/data_permission")
    @Operation(summary = "获取数据权限")
    public void dataPermission(@PathVariable Long id) {
        this.dataScopeService.getDataScopeById(id);
    }
    
}
