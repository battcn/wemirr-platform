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

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.wemirr.framework.db.mybatisplus.page.PageRequest;
import com.wemirr.framework.db.mybatisplus.wrap.Wraps;
import com.wemirr.platform.iam.tenant.domain.dto.req.DbInstanceSaveReq;
import com.wemirr.platform.iam.tenant.domain.entity.DbInstance;
import com.wemirr.platform.iam.tenant.service.DbInstanceService;
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
@Validated
@RestController
@RequestMapping("/db-instances")
@RequiredArgsConstructor
@Tag(name = "数据源管理", description = "数据源管理")
public class DbInstanceController {

    private final DbInstanceService dbInstanceService;

    @GetMapping("/page")
    @Operation(summary = "分页查询", description = "分页查询")
    public Page<DbInstance> page(PageRequest pageRequest, String dbType, Boolean status) {
        return dbInstanceService.page(pageRequest.buildPage(),
                Wraps.<DbInstance>lbQ().eq(DbInstance::getDbType, dbType).eq(DbInstance::getStatus, status));
    }

    @GetMapping("/active")
    @Operation(summary = "查询可用", description = "查询可用数据源")
    public List<DbInstance> queryActive() {
        return this.dbInstanceService.list(Wraps.<DbInstance>lbQ().eq(DbInstance::getStatus, true));
    }

    @GetMapping("/{id}/ping")
    @Operation(summary = "Ping数据库")
    public void ping(@PathVariable Long id) {
        this.dbInstanceService.ping(id);

    }

    @PostMapping("/create")
    @Operation(summary = "添加数据源")
    public void create(@Validated @RequestBody DbInstanceSaveReq req) {
        dbInstanceService.create(req);

    }

    @PutMapping("/{id}/modify")
    @Operation(summary = "编辑数据源")
    public void modify(@PathVariable Long id, @Validated @RequestBody DbInstanceSaveReq req) {
        dbInstanceService.edit(id, req);

    }

    @DeleteMapping("/{id}")
    @Operation(summary = "删除数据源")
    public void remove(@PathVariable Long id) {
        dbInstanceService.delete(id);

    }
}
