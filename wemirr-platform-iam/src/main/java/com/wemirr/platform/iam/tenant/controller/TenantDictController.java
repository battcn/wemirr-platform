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
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.wemirr.framework.commons.BeanUtilPlus;
import com.wemirr.framework.commons.annotation.log.AccessLog;
import com.wemirr.framework.commons.entity.Dict;
import com.wemirr.framework.commons.security.AuthenticationContext;
import com.wemirr.framework.db.mybatisplus.wrap.Wraps;
import com.wemirr.platform.iam.tenant.domain.dto.req.TenantDictSaveReq;
import com.wemirr.platform.iam.tenant.domain.dto.resp.TenantDictResp;
import com.wemirr.platform.iam.tenant.domain.entity.TenantDict;
import com.wemirr.platform.iam.tenant.service.TenantDictService;
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
 * 字典类型
 *
 * @author Levin
 */
@Slf4j
@Validated
@RestController
@RequestMapping("/tenant-dict")
@Tag(name = "业务字典", description = "业务字典")
@RequiredArgsConstructor
public class TenantDictController {
    
    private final AuthenticationContext context;
    private final TenantDictService tenantDictService;
    
    @GetMapping("/list")
    @Operation(summary = "字典列表 - [DONE] - [Levin]", description = "查询字典列表 - [DONE] - [Levin]")
    @SaCheckPermission(value = {"tenant:dict:list"})
    public List<TenantDictResp> list() {
        var list = this.tenantDictService.list(Wraps.<TenantDict>lbQ().eq(TenantDict::getParentId, 0L).orderByAsc(TenantDict::getSequence));
        return BeanUtilPlus.toBeans(list, TenantDictResp.class);
    }

    @GetMapping("/items")
    @Operation(summary = "查询字典子项", description = "查询字典子项 - [DONE] - [Levin]")
    @Parameters({
            @Parameter(name = "current", description = "当前页", in = ParameterIn.QUERY),
            @Parameter(name = "size", description = "每页记录", in = ParameterIn.QUERY),
            @Parameter(name = "ancestorCode", description = "祖节点", in = ParameterIn.QUERY),
            @Parameter(name = "status", description = "状态", in = ParameterIn.QUERY),
            @Parameter(name = "keyword", description = "关键字搜索", in = ParameterIn.QUERY),
    })
    public IPage<TenantDictResp> pageList(Integer current, Integer size, Long parentId, Boolean status, String keyword) {
        return this.tenantDictService.page(new Page<>(current, size), Wraps.<TenantDict>lbQ().eq(TenantDict::getParentId, parentId)
                        .eq(TenantDict::getStatus, status).like(TenantDict::getCode, keyword))
                .convert(x -> BeanUtilPlus.toBean(x, TenantDictResp.class));
    }


    @PostMapping("/refresh")
    @AccessLog(module = "租户字典", description = "刷新字典")
    @Operation(summary = "刷新字典 - [DONE] - [Levin]", description = "刷新字典缓存数据 - [DONE] - [Levin]")
    @SaCheckPermission(value = {"tenant:dict:refresh"})
    public void refresh() {
        this.tenantDictService.refresh();
    }
    
    @PostMapping("/incr-sync")
    @AccessLog(module = "租户字典", description = "同步字典")
    @Operation(summary = "同步字典 - [DONE] - [Levin]", description = "同步平台字典到租户字典库中 - [DONE] - [Levin]")
    // @SaCheckPermission(value = {"tenant:dict:sync-dict"})
    public void incrSyncTenantDict() {
        this.tenantDictService.incrSyncTenantDict(context.tenantId());
    }
    
    @PostMapping("/create")
    @AccessLog(module = "租户字典", description = "字典新增")
    @Operation(summary = "新增字典 - [DONE] - [Levin]", description = "新增字典 - [DONE] - [Levin]")
    @SaCheckPermission(value = {"tenant:dict:add"})
    public void create(@Validated @RequestBody TenantDictSaveReq req) {
        this.tenantDictService.create(req);
    }
    
    @PutMapping("/{id}/modify")
    @AccessLog(module = "租户字典", description = "字典编辑")
    @Operation(summary = "编辑字典 - [DONE] - [Levin]", description = "编辑字典 - [DONE] - [Levin]")
    @SaCheckPermission(value = {"tenant:dict:edit"})
    public void modify(@PathVariable Long id, @Validated @RequestBody TenantDictSaveReq req) {
        this.tenantDictService.modify(id, req);
    }
    
    @DeleteMapping("/{id}")
    @AccessLog(module = "租户字典", description = "删除指定字典项")
    @Operation(summary = "删除字典 - [DONE] - [Levin]", description = "删除字典 - [DONE] - [Levin]")
    @SaCheckPermission(value = {"tenant:dict:remove"})
    public void remove(@PathVariable Long id) {
        this.tenantDictService.deleteById(id);
    }
    
    @GetMapping("/{code}/list")
    @Operation(summary = "查询字典子项 - [DONE] - [Levin]", description = "查询字典子项")
    @Parameter(name = "code", description = "编码", in = ParameterIn.PATH)
    public List<Dict<String>> list(@PathVariable("code") String code) {
        return tenantDictService.findItemByCode(code);
    }
}
