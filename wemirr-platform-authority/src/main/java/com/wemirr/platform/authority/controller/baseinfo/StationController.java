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
package com.wemirr.platform.authority.controller.baseinfo;

import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.wemirr.framework.commons.BeanUtilPlus;
import com.wemirr.framework.commons.annotation.log.AccessLog;
import com.wemirr.platform.authority.domain.baseinfo.entity.Station;
import com.wemirr.platform.authority.domain.baseinfo.req.StationPageReq;
import com.wemirr.platform.authority.domain.baseinfo.req.StationSaveReq;
import com.wemirr.platform.authority.service.StationService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

/**
 * 岗位管理
 *
 * @author Levin
 */
@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/stations")
@Tag(name = "岗位管理", description = "岗位管理")
public class StationController {
    
    private final StationService stationService;
    
    @GetMapping
    @Operation(summary = "岗位列表 - [Levin] - [DONE]")
    public IPage<Station> query(StationPageReq req) {
        return stationService.pageList(req);
    }
    
    @PostMapping
    @AccessLog(description = "添加岗位")
    @Operation(summary = "添加岗位")
    @PreAuthorize("hasAuthority('sys:station:add')")
    public void add(@Validated @RequestBody StationSaveReq dto) {
        stationService.save(BeanUtil.toBean(dto, Station.class));
    }
    
    @PutMapping("/{id}")
    @AccessLog(description = "编辑岗位")
    @Operation(summary = "编辑岗位")
    @PreAuthorize("hasAuthority('sys:station:edit')")
    public void edit(@PathVariable Long id, @Validated @RequestBody StationSaveReq dto) {
        stationService.updateById(BeanUtilPlus.toBean(id, dto, Station.class));
    }
    
    @DeleteMapping("/{id}")
    @AccessLog(description = "删除岗位")
    @Operation(summary = "删除岗位")
    @PreAuthorize("hasAuthority('sys:station:remove')")
    public void del(@PathVariable Long id) {
        stationService.removeById(id);
    }
    
}
