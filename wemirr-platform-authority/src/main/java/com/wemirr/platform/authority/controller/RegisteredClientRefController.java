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

package com.wemirr.platform.authority.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.wemirr.framework.commons.annotation.log.AccessLog;
import com.wemirr.framework.db.mybatisplus.wrap.Wraps;
import com.wemirr.platform.authority.domain.baseinfo.entity.RegisteredClientRef;
import com.wemirr.platform.authority.domain.baseinfo.req.RegisteredClientRefReq;
import com.wemirr.platform.authority.domain.baseinfo.resp.RegisteredClientRefResp;
import com.wemirr.platform.authority.service.RegisteredClientRefService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import static com.wemirr.platform.authority.domain.baseinfo.converts.RegisteredClientRefConverts.REGISTERED_CLIENT_REF_2_RESP_CONVERTS;

/**
 * 应用管理
 *
 * @author Levin
 */
@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/registered_client_refs")
@Tag(name = "终端管理", description = "终端管理")
public class RegisteredClientRefController {
    
    private final RegisteredClientRefService registeredClientRefService;
    
    @GetMapping
    @Parameters({
            @Parameter(description = "clientId", name = "clientId", in = ParameterIn.QUERY),
            @Parameter(description = "clientName", name = "clientName", in = ParameterIn.QUERY)
    })
    @Operation(summary = "应用列表 - [Levin] - [DONE]")
    public IPage<RegisteredClientRefResp> query(@Parameter(description = "当前页") @RequestParam(required = false, defaultValue = "1") Integer current,
                                                @Parameter(description = "条数") @RequestParam(required = false, defaultValue = "20") Integer size,
                                                String clientId, String clientName) {
        return this.registeredClientRefService.page(new Page<>(current, size),
                Wraps.<RegisteredClientRef>lbQ().like(RegisteredClientRef::getClientId, clientId)
                        .like(RegisteredClientRef::getClientName, clientName))
                .convert(REGISTERED_CLIENT_REF_2_RESP_CONVERTS::convert);
    }
    
    @PostMapping
    @AccessLog(description = "保存应用")
    @Operation(summary = "保存应用")
    public void save(@Validated @RequestBody RegisteredClientRefReq dto) {
        this.registeredClientRefService.registeredClient(dto);
    }
    
    @DeleteMapping("/{id}")
    @AccessLog(description = "删除应用")
    @Operation(summary = "删除应用")
    public void del(@PathVariable String id) {
        this.registeredClientRefService.deleteById(id);
    }
    
}
