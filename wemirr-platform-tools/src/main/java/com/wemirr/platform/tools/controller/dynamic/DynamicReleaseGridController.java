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
package com.wemirr.platform.tools.controller.dynamic;

import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.wemirr.framework.db.mybatisplus.page.PageRequest;
import com.wemirr.framework.db.mybatisplus.wrap.Wraps;
import com.wemirr.platform.tools.domain.entity.DynamicReleaseGrid;
import com.wemirr.platform.tools.domain.req.DynamicReleaseGridReq;
import com.wemirr.platform.tools.service.DynamicReleaseGridService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

/**
 * @author Levin
 */
@Slf4j
@Validated
@RestController
@RequestMapping("/dynamic_release_grids")
@RequiredArgsConstructor
@Tag(name = "数据源管理", description = "数据源管理")
public class DynamicReleaseGridController {
    
    private final DynamicReleaseGridService dynamicReleaseGridService;
    
    @Operation(summary = "分页查询", description = "分页查询")
    @GetMapping
    public Page<DynamicReleaseGrid> page(PageRequest pageRequest, String model, String tableName) {
        final Page<DynamicReleaseGrid> page = dynamicReleaseGridService.page(pageRequest.buildPage(),
                Wraps.<DynamicReleaseGrid>lbQ()
                        .like(DynamicReleaseGrid::getTableName, tableName)
                        .like(DynamicReleaseGrid::getModel, model));
        return page;
    }
    
    @Operation(summary = "添加数据源")
    @PostMapping
    public void add(@Validated @RequestBody DynamicReleaseGridReq req) {
        dynamicReleaseGridService.save(BeanUtil.toBean(req, DynamicReleaseGrid.class));
        
    }
    
    @Operation(summary = "编辑数据源")
    @PutMapping("/{id}")
    public void edit(@PathVariable Long id, @Validated @RequestBody DynamicReleaseGridReq req) {
        final DynamicReleaseGrid request = BeanUtil.toBean(req, DynamicReleaseGrid.class);
        request.setId(id);
        dynamicReleaseGridService.updateById(request);
        
    }
    
    @Operation(summary = "删除数据源")
    @DeleteMapping("/{id}")
    public void remove(@PathVariable Long id) {
        dynamicReleaseGridService.removeById(id);
        
    }
}
