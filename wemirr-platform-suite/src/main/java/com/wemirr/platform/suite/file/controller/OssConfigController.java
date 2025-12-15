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
import com.wemirr.platform.suite.file.domain.dto.req.OssConfigSaveReq;
import com.wemirr.platform.suite.file.domain.dto.resp.OssConfigPageResp;
import com.wemirr.platform.suite.file.service.OssConfigService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;


/**
 * OSS 存储配置控制器
 * <p>
 * 管理对象存储服务配置，支持 MinIO、阿里云 OSS、七牛云、AWS S3 等
 *
 * @author Levin
 * @since 2024-12
 */
@Slf4j
@Validated
@RestController
@RequestMapping("/oss/configs")
@RequiredArgsConstructor
@Tag(name = "存储配置", description = "OSS 存储配置管理")
public class OssConfigController {

    private final OssConfigService ossConfigService;


    @PostMapping("/page")
    @Operation(summary = "分页查询", description = "分页查询存储配置列表")
    public IPage<OssConfigPageResp> page(@RequestBody FileStorageSettingPageReq req) {
        return ossConfigService.pageList(req);
    }

    @PostMapping("/create")
    @AccessLog(module = "存储配置", description = "新增存储配置")
    @Operation(summary = "新增配置", description = "新增 OSS 存储配置")
    public void create(@Validated @RequestBody OssConfigSaveReq req) {
        ossConfigService.create(req);
    }

    @PutMapping("/{id}/modify")
    @AccessLog(module = "存储配置", description = "修改存储配置")
    @Operation(summary = "修改配置", description = "修改 OSS 存储配置")
    public void update(@PathVariable Long id, @Validated @RequestBody OssConfigSaveReq req) {
        ossConfigService.modify(id, req);
    }

    @DeleteMapping("/{id}")
    @AccessLog(module = "存储配置", description = "删除存储配置")
    @Operation(summary = "删除配置", description = "删除 OSS 存储配置")
    public void delete(@PathVariable Long id) {
        ossConfigService.delete(id);
    }
}
