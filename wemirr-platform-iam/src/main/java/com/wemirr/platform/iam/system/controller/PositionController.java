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

package com.wemirr.platform.iam.system.controller;

import cn.dev33.satoken.annotation.SaCheckPermission;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.wemirr.framework.commons.BeanUtilPlus;
import com.wemirr.framework.commons.annotation.log.AccessLog;
import com.wemirr.framework.db.mybatisplus.wrap.Wraps;
import com.wemirr.platform.iam.system.domain.dto.req.PositionPageReq;
import com.wemirr.platform.iam.system.domain.dto.req.PositionSaveReq;
import com.wemirr.platform.iam.system.domain.dto.resp.PositionPageResp;
import com.wemirr.platform.iam.system.domain.entity.Position;
import com.wemirr.platform.iam.system.service.SysPositionService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 岗位管理
 *
 * @author Levin
 */
@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/positions")
@Tag(name = "岗位管理", description = "岗位管理")
public class PositionController {
    
    private final SysPositionService sysPositionService;
    
    @GetMapping("/list")
    @Operation(summary = "岗位列表 - [Levin] - [DONE]")
    public List<PositionPageResp> list(Long orgId) {
        List<Position> list = sysPositionService.list(Wraps.<Position>lbQ().eq(Position::getOrgId, orgId)
                .orderByAsc(Position::getSequence));
        return BeanUtilPlus.toBeans(list, PositionPageResp.class);
    }
    
    @GetMapping("/page")
    @Operation(summary = "分页查询 - [Levin] - [DONE]")
    @SaCheckPermission(value = {"sys:position:page"})
    public IPage<PositionPageResp> pageList(PositionPageReq req) {
        return sysPositionService.pageList(req);
    }
    
    @PostMapping("/create")
    @AccessLog(module = "岗位管理", description = "添加岗位")
    @Operation(summary = "添加岗位")
    @SaCheckPermission(value = {"sys:position:add"})
    public void create(@Validated @RequestBody PositionSaveReq req) {
        sysPositionService.create(req);
    }
    
    @PutMapping("/{id}/modify")
    @AccessLog(module = "岗位管理", description = "编辑岗位")
    @Operation(summary = "编辑岗位")
    @SaCheckPermission(value = {"sys:position:edit"})
    public void modify(@PathVariable Long id, @Validated @RequestBody PositionSaveReq req) {
        sysPositionService.modify(id, req);
    }
    
    @DeleteMapping("/{id}")
    @AccessLog(module = "岗位管理", description = "删除岗位")
    @Operation(summary = "删除岗位")
    @SaCheckPermission(value = {"sys:position:remove"})
    public void remove(@PathVariable Long id) {
        sysPositionService.removeById(id);
    }
    
}
