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

package com.wemirr.platform.suite.file.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.wemirr.framework.commons.annotation.log.AccessLog;
import com.wemirr.platform.suite.file.domain.dto.req.FileStorageSettingPageReq;
import com.wemirr.platform.suite.file.domain.dto.req.FileStorageSettingSaveReq;
import com.wemirr.platform.suite.file.domain.dto.resp.FileStorageSettingPageResp;
import com.wemirr.platform.suite.file.service.FileStorageSettingService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;

/**
 * @author xiao1
 * @since 2024-12
 */
@Slf4j
@Validated
@RestController
@RequestMapping("/file-storage/setting")
@RequiredArgsConstructor
@Tag(name = "存储配置", description = "存储配置管理")
public class FileStorageSettingController {
    
    private final FileStorageSettingService fileStorageSettingService;
    @GetMapping("/page")
    @Operation(summary = "分页查询")
    public IPage<FileStorageSettingPageResp> pageList(FileStorageSettingPageReq req) {
        return fileStorageSettingService.pageList(req);
    }
    /**
     * 删除配置
     */
    @PostMapping
    @AccessLog(module = "存储配置", description = "添加存储配置")
    @Operation(summary = "新增存储配置", description = "新增存储配置")
    public void add(@RequestBody FileStorageSettingSaveReq req) {
        fileStorageSettingService.create(req);
    }
    
    @PutMapping("/{id}/modify")
    @AccessLog(module = "存储配置", description = "编辑存储配置")
    @Operation(summary = "编辑存储配置", description = "编辑存储配置")
    public void edit(@PathVariable Long id, @Validated @RequestBody FileStorageSettingSaveReq req) {
        fileStorageSettingService.modify(id, req);
    }
    
    @DeleteMapping("/{id}")
    @AccessLog(module = "存储配置", description = "删除存储配置")
    @Operation(summary = "删除存储配置", description = "删除存储配置")
    public void delete(@PathVariable Long id) {
        fileStorageSettingService.delete(id);
    }
}
