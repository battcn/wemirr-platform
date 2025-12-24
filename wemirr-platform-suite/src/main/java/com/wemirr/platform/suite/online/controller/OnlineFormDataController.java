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

package com.wemirr.platform.suite.online.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.wemirr.platform.suite.online.domain.req.OnlineFormDataSaveReq;
import com.wemirr.platform.suite.online.domain.req.OnlineFormDesignerPageReq;
import com.wemirr.platform.suite.online.service.OnlineFormDataService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * @author Levin
 */
@Slf4j
@Validated
@RestController
@RequestMapping("/online-form-data")
@RequiredArgsConstructor
@Tag(name = "表单数据", description = "表单数据")
public class OnlineFormDataController {

    private final OnlineFormDataService onlineFormDataService;

    @Operation(summary = "分页查询", description = "分页查询")
    @PostMapping("/page")
    public IPage<Map<String, Object>> pageList(@RequestBody OnlineFormDesignerPageReq req) {
        return onlineFormDataService.pageList(req);
    }

    @PostMapping("/create")
    @Operation(summary = "添加数据 - [Levin] - [DONE]")
    public void create(@Validated @RequestBody OnlineFormDataSaveReq req) {
        this.onlineFormDataService.create(req);
    }

    @PutMapping("/{id}/modify")
    @Operation(summary = "修改数据 - [Levin] - [DONE]")
    public void modify(@PathVariable Long id, @Validated @RequestBody OnlineFormDataSaveReq req) {
        this.onlineFormDataService.modify(id, req);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "删除数据 - [Levin] - [DONE]")
    public void delete(@PathVariable Long id) {
        this.onlineFormDataService.removeById(id);
    }
}
