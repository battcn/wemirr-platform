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
import com.wemirr.framework.db.mybatisplus.wrap.Wraps;
import com.wemirr.platform.suite.online.dialect.FastCrudDialect;
import com.wemirr.platform.suite.online.domain.entity.OnlineModel;
import com.wemirr.platform.suite.online.domain.req.OnlineFormDesignSaveReq;
import com.wemirr.platform.suite.online.domain.req.OnlineFormDesignerPageReq;
import com.wemirr.platform.suite.online.domain.req.OnlineFormDesignerSaveReq;
import com.wemirr.platform.suite.online.domain.resp.OnlineFormDesignerDetailResp;
import com.wemirr.platform.suite.online.domain.resp.OnlineFormDesignerPageResp;
import com.wemirr.platform.suite.online.service.OnlineModelService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Levin
 */
@Slf4j
@Validated
@RestController
@RequestMapping("/online-model")
@RequiredArgsConstructor
@Tag(name = "表单设计", description = "表单设计")
public class OnlineModelController {

    private final OnlineModelService onlineModelService;

    @Operation(summary = "分页查询", description = "分页查询")
    @PostMapping("/page")
    public IPage<OnlineFormDesignerPageResp> pageList(@RequestBody OnlineFormDesignerPageReq req) {
        return onlineModelService.pageList(req);
    }

    @PostMapping("/create")
    @Operation(summary = "新增表单 - [Levin] - [DONE]")
    public void create(@Validated @RequestBody OnlineFormDesignerSaveReq req) {
        this.onlineModelService.create(req);
    }

    @GetMapping("/{id}/detail")
    @Operation(summary = "查看详情 - [Levin] - [DONE]")
    public OnlineFormDesignerDetailResp detail(@PathVariable Long id) {
        return this.onlineModelService.detail(id);
    }

    @PostMapping("/{id}/form-design")
    @Operation(summary = "表单设计 - [Levin] - [DONE]")
    public void formDesign(@PathVariable Long id, @Validated @RequestBody OnlineFormDesignSaveReq req) {
        this.onlineModelService.formDesign(id, req);
    }

    @PutMapping("/{id}/modify")
    @Operation(summary = "修改表单 - [Levin] - [DONE]")
    public void modify(@PathVariable Long id, @Validated @RequestBody OnlineFormDesignerSaveReq req) {
        this.onlineModelService.modify(id, req);
    }

    @GetMapping("/fast-crud")
    @Operation(summary = "fast-crud模板 - [Levin] - [DONE]")
    public Map<String, Object> fastCrud(@RequestParam String definitionKey) {
        OnlineModel model = this.onlineModelService.getOne(Wraps.<OnlineModel>lbQ().eq(OnlineModel::getDefinitionKey, definitionKey));
        if (model == null) {
            return new HashMap<>() {

                {
                    put("columns", null);
                }
            };
        }
        Map<String, Object> fastCrud = FastCrudDialect.toFastCrud(model.getFormSchemas());
        return new HashMap<>() {

            {
                put("columns", fastCrud);
            }
        };
    }

    @GetMapping("/detail")
    @Operation(summary = "模型详情- [Levin] - [DONE]", description = "根据参数获取详情")
    public OnlineFormDesignerDetailResp detail(@RequestParam String definitionKey) {
        return this.onlineModelService.detail(definitionKey);
    }
}
