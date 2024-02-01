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

package com.wemirr.platform.authority.controller.tenant;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.wemirr.framework.db.mybatisplus.page.PageRequest;
import com.wemirr.framework.db.mybatisplus.wrap.Wraps;
import com.wemirr.platform.authority.domain.baseinfo.req.DynamicDatasourceReq;
import com.wemirr.platform.authority.domain.tenant.entity.DynamicDatasource;
import com.wemirr.platform.authority.service.TenantDatasourceService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author Levin
 */
@Slf4j
@RestController
@RequestMapping("/databases")
@RequiredArgsConstructor
@Tag(name = "数据源管理", description = "数据源管理")
@Validated
public class DynamicDatasourceController {
    
    private final TenantDatasourceService tenantDatasourceService;
    
    @Operation(summary = "分页查询", description = "分页查询")
    @GetMapping
    public Page<DynamicDatasource> page(PageRequest pageRequest, String dbType, Boolean locked) {
        return tenantDatasourceService.page(pageRequest.buildPage(),
                Wraps.<DynamicDatasource>lbQ().eq(DynamicDatasource::getDbType, dbType).eq(DynamicDatasource::getLocked, locked));
    }
    
    @Operation(summary = "查询可用", description = "查询可用数据源")
    @GetMapping("/active")
    public List<DynamicDatasource> queryActive() {
        return this.tenantDatasourceService.list(Wraps.<DynamicDatasource>lbQ().eq(DynamicDatasource::getLocked, false));
    }
    
    @Operation(summary = "Ping数据库")
    @GetMapping("/{id}/ping")
    public void ping(@PathVariable Long id) {
        this.tenantDatasourceService.ping(id);
        
    }
    
    @Operation(summary = "添加数据源")
    @PostMapping
    public void add(@Validated @RequestBody DynamicDatasourceReq req) {
        tenantDatasourceService.created(req);
        
    }
    
    @Operation(summary = "编辑数据源")
    @PutMapping("/{id}")
    public void edit(@PathVariable Long id, @Validated @RequestBody DynamicDatasourceReq req) {
        tenantDatasourceService.edit(id, req);
        
    }
    
    @Operation(summary = "删除数据源")
    @DeleteMapping("/{id}")
    public void remove(@PathVariable Long id) {
        tenantDatasourceService.delete(id);
        
    }
}
