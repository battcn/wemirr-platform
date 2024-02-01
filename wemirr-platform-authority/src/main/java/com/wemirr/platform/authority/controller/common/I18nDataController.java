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

package com.wemirr.platform.authority.controller.common;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.wemirr.framework.commons.annotation.log.AccessLog;
import com.wemirr.platform.authority.domain.common.req.I18nDataSaveReq;
import com.wemirr.platform.authority.domain.common.req.I18nPageReq;
import com.wemirr.platform.authority.domain.common.resp.I18nDataPageResp;
import com.wemirr.platform.authority.service.I18nDataService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

/**
 * @author Levin
 */
@Slf4j
@Validated
@RestController
@RequestMapping("/i18n")
@Tag(name = "I18N国际化", description = "I18N国际化维护")
@RequiredArgsConstructor
public class I18nDataController {
    
    private final I18nDataService i18nDataService;
    
    @GetMapping
    @AccessLog(description = "国际化查询")
    @Operation(summary = "查询国际化 - [DONE] - [Levin]", description = "查询国际化 - [DONE] - [Levin]")
    @PreAuthorize("hasAuthority('sys:i18n:page')")
    public IPage<I18nDataPageResp> page(I18nPageReq req) {
        return this.i18nDataService.pageList(req);
    }
    
    @PostMapping
    @AccessLog(description = "国际化新增")
    @Operation(summary = "新增国际化 - [DONE] - [Levin]", description = "新增国际化 - [DONE] - [Levin]")
    @PreAuthorize("hasAuthority('sys:i18n:add')")
    public void save(@Validated @RequestBody I18nDataSaveReq req) {
        this.i18nDataService.add(req);
    }
    
    @PutMapping("/{id}")
    @AccessLog(description = "国际化编辑")
    @Operation(summary = "编辑国际化 - [DONE] - [Levin]", description = "编辑国际化 - [DONE] - [Levin]")
    @PreAuthorize("hasAuthority('sys:i18n:edit')")
    public void edit(@PathVariable Long id, @Validated @RequestBody I18nDataSaveReq req) {
        this.i18nDataService.edit(id, req);
    }
    
    @DeleteMapping("/{id}")
    @AccessLog(description = "删除指定国际化项")
    @Operation(summary = "删除国际化 - [DONE] - [Levin]", description = "删除国际化 - [DONE] - [Levin]")
    @PreAuthorize("hasAuthority('sys:i18n:remove')")
    public void del(@PathVariable Long id) {
        this.i18nDataService.removeById(id);
    }
    
}
