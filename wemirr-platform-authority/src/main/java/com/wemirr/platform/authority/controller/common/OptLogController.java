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

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.wemirr.framework.boot.log.AccessLogInfo;
import com.wemirr.framework.db.dynamic.annotation.TenantDS;
import com.wemirr.framework.db.mybatisplus.wrap.Wraps;
import com.wemirr.platform.authority.domain.common.entity.OptLog;
import com.wemirr.platform.authority.domain.common.req.OptLogPageReq;
import com.wemirr.platform.authority.service.OptLogService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.time.Instant;
import java.time.temporal.ChronoUnit;

/**
 * 操作日志
 *
 * @author Levin
 */
@Slf4j
@Validated
@RestController
@RequestMapping("/opt_logs")
@Tag(name = "操作日志", description = "操作日志")
@TenantDS
@RequiredArgsConstructor
public class OptLogController {
    
    private final OptLogService optLogService;
    
    @GetMapping
    @Operation(summary = "查询日志 - [DONE] - [Levin]", description = "查询日志 - [DONE] - [Levin]")
    @PreAuthorize("hasAuthority('log:opt:page')")
    public Page<OptLog> page(OptLogPageReq req) {
        return this.optLogService.page(req.buildPage(), Wraps.<OptLog>lbQ()
                .eq(OptLog::getHttpMethod, req.getHttpMethod())
                .eq(OptLog::getStatus, req.getStatus())
                .eq(OptLog::getPlatform, req.getPlatform()));
    }
    
    @DeleteMapping("/{day}")
    @Parameters({
            @Parameter(name = "day", description = "天数", in = ParameterIn.PATH),
    })
    @Operation(summary = "查询日志 - [DONE] - [Levin]", description = "查询日志 - [DONE] - [Levin]")
    public void batchDelete(@PathVariable Integer day) {
        this.optLogService.remove(Wraps.<OptLog>lbQ().le(OptLog::getStartTime, Instant.now().plus(-day, ChronoUnit.DAYS)));
    }
    
    @PostMapping("/listener")
    @Operation(summary = "监听日志 - [DONE] - [Levin]", description = "监听日志 - [DONE] - [Levin]")
    public void listener(@RequestBody AccessLogInfo info) {
        this.optLogService.listener(info);
    }
    
}
