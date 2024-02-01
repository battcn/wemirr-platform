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

import com.alibaba.fastjson2.JSON;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.wemirr.framework.db.mybatisplus.page.PageRequest;
import com.wemirr.framework.db.mybatisplus.wrap.Wraps;
import com.wemirr.platform.tools.domain.entity.DynamicReleaseColumn;
import com.wemirr.platform.tools.domain.req.DynamicReleaseGridColumnReq;
import com.wemirr.platform.tools.domain.resp.DynamicReleaseGridColumnResp;
import com.wemirr.platform.tools.service.DynamicReleaseColumnService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author Levin
 */
@Slf4j
@Validated
@RestController
@RequestMapping("/dynamic_release_grids/{grid_id}/columns")
@RequiredArgsConstructor
@Tag(name = "字段管理", description = "字段管理")
public class DynamicReleaseColumnController {
    
    private final DynamicReleaseColumnService dynamicReleaseColumnService;
    
    @Operation(summary = "分页查询", description = "分页查询")
    @GetMapping
    public IPage<DynamicReleaseGridColumnResp> list(PageRequest pageRequest, @PathVariable("grid_id") Long gridId) {
        Page<DynamicReleaseGridColumnResp> page = new Page<>(pageRequest.getCurrent(), pageRequest.getSize());
        final List<DynamicReleaseGridColumnResp> columns = dynamicReleaseColumnService.listByGridId(gridId);
        page.setRecords(columns);
        return page;
    }
    
    @PutMapping
    public void edit(@PathVariable("grid_id") Long gridId, @RequestBody DynamicReleaseGridColumnReq req) {
        log.info("[请求参数] - {}", JSON.toJSONString(req));
        this.dynamicReleaseColumnService.edit(gridId, req);
        
    }
    
    @DeleteMapping("/{key}")
    public void del(@PathVariable("grid_id") Long gridId, @PathVariable String key) {
        this.dynamicReleaseColumnService.remove(Wraps.<DynamicReleaseColumn>lbQ()
                .eq(DynamicReleaseColumn::getKey, key)
                .eq(DynamicReleaseColumn::getGridId, gridId));
    }
    
}
