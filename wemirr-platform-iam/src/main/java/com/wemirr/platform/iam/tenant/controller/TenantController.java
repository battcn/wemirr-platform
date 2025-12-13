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

package com.wemirr.platform.iam.tenant.controller;

import cn.dev33.satoken.annotation.SaCheckPermission;
import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.wemirr.framework.commons.annotation.log.AccessLog;
import com.wemirr.framework.db.mybatisplus.wrap.Wraps;
import com.wemirr.framework.redis.plus.anontation.RedisLock;
import com.wemirr.framework.redis.plus.anontation.RedisParam;
import com.wemirr.platform.iam.tenant.domain.dto.req.TenantDbBindingSaveReq;
import com.wemirr.platform.iam.tenant.domain.dto.req.TenantPageReq;
import com.wemirr.platform.iam.tenant.domain.dto.req.TenantSaveReq;
import com.wemirr.platform.iam.tenant.domain.dto.req.TenantSettingReq;
import com.wemirr.platform.iam.tenant.domain.dto.resp.DbInstancePageResp;
import com.wemirr.platform.iam.tenant.domain.dto.resp.TenantDbBindingResp;
import com.wemirr.platform.iam.tenant.domain.dto.resp.TenantPageResp;
import com.wemirr.platform.iam.tenant.domain.dto.resp.TenantSettingResp;
import com.wemirr.platform.iam.tenant.domain.entity.Tenant;
import com.wemirr.platform.iam.tenant.service.DbInstanceService;
import com.wemirr.platform.iam.tenant.service.TenantService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 租户管理
 *
 * @author Levin
 */
@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/tenants")
@Tag(name = "租户管理", description = "租户管理")
public class TenantController {

    private final TenantService tenantService;
    private final DbInstanceService dynamicDatasourceService;

    @Operation(summary = "查询可用", description = "查询可用数据源")
    @PostMapping("/ids")
    public List<Tenant> list(@RequestBody List<Long> ids) {
        return this.tenantService.list(Wraps.<Tenant>lbQ().in(Tenant::getId, ids));
    }

    @PostMapping("/page")
    @Operation(summary = "租户列表 - [Levin] - [DONE]")
    @SaCheckPermission(value = {"tenant:list"})
    public IPage<TenantPageResp> pageList(@RequestBody TenantPageReq req) {
        return tenantService.page(req.buildPage(), Wraps.<Tenant>lbQ()
                .like(Tenant::getName, req.getName())
                .eq(Tenant::getCode, req.getCode())
                .eq(Tenant::getProvinceId, req.getProvinceId())
                .eq(Tenant::getCityId, req.getCityId())
                .eq(Tenant::getDistrictId, req.getDistrictId())
                .eq(Tenant::getIndustry, req.getIndustry()).eq(Tenant::getStatus, req.getStatus())
                .eq(Tenant::getType, req.getType())).convert(x -> BeanUtil.toBean(x, TenantPageResp.class));
    }

    @Operation(summary = "查询可用", description = "查询可用数据源")
    @GetMapping("/databases/active")
    public List<DbInstancePageResp> queryActive() {
        return this.dynamicDatasourceService.selectTenantDynamicDatasource();
    }

    @PostMapping("/create")
    @AccessLog(module = "租户管理", description = "添加租户")
    @Operation(summary = "添加租户")
    @SaCheckPermission(value = {"tenant:add"})
    public void create(@Validated @RequestBody TenantSaveReq req) {
        tenantService.create(req);
    }

    @PutMapping("/{id}/modify")
    @AccessLog(module = "租户管理", description = "编辑租户")
    @Operation(summary = "编辑租户")
    @SaCheckPermission(value = {"tenant:edit"})
    public void modify(@PathVariable Long id, @Validated @RequestBody TenantSaveReq req) {
        tenantService.modify(id, req);
    }

    @GetMapping("/{id}/setting")
    @AccessLog(module = "租户管理", description = "配置租户")
    @Operation(summary = "配置租户")
    // @SaCheckPermission(value = {"tenant:setting"})
    public TenantSettingResp setting(@PathVariable Long id) {
        return tenantService.settingInfo(id);
    }

    @GetMapping("/{id}/db-ref")
    @Operation(summary = "租户关联DB")
    public TenantDbBindingResp dbRef(@PathVariable Long id) {
        return tenantService.dbRef(id);
    }

    @PostMapping("/{id}/db-binding")
    @Operation(summary = "租户关联DB")
    public void dbBinding(@PathVariable Long id, @RequestBody TenantDbBindingSaveReq req) {
        tenantService.dbBinding(id, req);
    }

    @PutMapping("/{id}/setting")
    @AccessLog(module = "租户管理", description = "配置租户")
    @Operation(summary = "配置租户")
    // @SaCheckPermission(value = {"tenant:setting"})
    public void setting(@PathVariable Long id, @Validated @RequestBody TenantSettingReq req) {
        tenantService.saveSetting(id, req);
    }

    // @PutMapping("/{id}/config")
    // @AccessLog(module = "", description = "配置租户")
    // @Operation(summary = "配置租户")
    // @SaCheckPermission(value = {"tenant:db-config"})
    // public void config(@PathVariable Long id, @Validated @RequestBody TenantConfigReq req) {
    // tenantService.tenantConfig(id, req);
    // }

    @PutMapping("/{id}/init-sql-script")
    @AccessLog(module = "", description = "加载初始数据")
    @Operation(summary = "加载初始数据")
    @RedisLock(prefix = "tenant:init-script")
    public void initSqlScript(@RedisParam(name = "id") @PathVariable Long id) {
        tenantService.initSqlScript(id);
    }

    @DeleteMapping("/{id}")
    @AccessLog(module = "租户管理", description = "删除租户")
    @Operation(summary = "删除租户")
    @SaCheckPermission(value = {"tenant:remove"})
    public void remove(@PathVariable Long id) {
        tenantService.removeById(id);
    }

    @PutMapping("/{id}/refresh-dict")
    @AccessLog(module = "租户管理", description = "字典刷新")
    @Operation(summary = "字典刷新")
    @SaCheckPermission(value = {"tenant:refresh-dict"})
    public void refreshTenantDict(@PathVariable Long id) {
        tenantService.refreshTenantDict(id);
    }

}
