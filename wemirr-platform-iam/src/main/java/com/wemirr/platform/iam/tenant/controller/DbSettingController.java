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
import com.wemirr.platform.iam.tenant.domain.dto.req.DbSettingSaveReq;
import com.wemirr.platform.iam.tenant.domain.entity.DbSetting;
import com.wemirr.platform.iam.tenant.service.DbSettingService;
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
@RequestMapping("/db-setting")
@RequiredArgsConstructor
@Tag(name = "数据源管理", description = "数据源管理")
@Validated
public class DbSettingController {
    
    private final DbSettingService dbSettingService;
    
    @Operation(summary = "分页查询", description = "分页查询")
    @GetMapping("/page")
    public Page<DbSetting> page(PageRequest pageRequest, String dbType, Boolean locked) {
        return dbSettingService.page(pageRequest.buildPage(),
                Wraps.<DbSetting>lbQ().eq(DbSetting::getDbType, dbType).eq(DbSetting::getLocked, locked));
    }
    
    @Operation(summary = "查询可用", description = "查询可用数据源")
    @GetMapping("/active")
    public List<DbSetting> queryActive() {
        return this.dbSettingService.list(Wraps.<DbSetting>lbQ().eq(DbSetting::getLocked, false));
    }
    
    @Operation(summary = "Ping数据库")
    @GetMapping("/{id}/ping")
    public void ping(@PathVariable Long id) {
        this.dbSettingService.ping(id);
        
    }
    
    @Operation(summary = "添加数据源")
    @PostMapping("/create")
    public void create(@Validated @RequestBody DbSettingSaveReq req) {
        dbSettingService.created(req);
        
    }
    
    @Operation(summary = "编辑数据源")
    @PutMapping("/{id}/modify")
    public void modify(@PathVariable Long id, @Validated @RequestBody DbSettingSaveReq req) {
        dbSettingService.edit(id, req);
        
    }
    
    @Operation(summary = "删除数据源")
    @DeleteMapping("/{id}")
    public void remove(@PathVariable Long id) {
        dbSettingService.delete(id);
        
    }
}
