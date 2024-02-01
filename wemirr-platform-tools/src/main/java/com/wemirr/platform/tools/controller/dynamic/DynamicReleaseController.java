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

import cn.hutool.core.map.MapUtil;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.wemirr.framework.db.mybatisplus.page.PageRequest;
import com.wemirr.platform.tools.domain.resp.DynamicReleaseCurdOptionResp;
import com.wemirr.platform.tools.service.DynamicReleaseService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * @author Levin
 */
@Slf4j
@Validated
@RestController
@RequestMapping("/dynamic_releases/{model}")
@RequiredArgsConstructor
@Tag(name = "动态发布", description = "动态发布")
public class DynamicReleaseController {
    
    @Qualifier("mySqlDynamicReleaseServiceImpl")
    private final DynamicReleaseService<Long> dynamicReleaseService;
    
    @GetMapping("/pages")
    public IPage<?> pages(@PathVariable String model, PageRequest request, @RequestParam Map<String, Object> params) {
        return this.dynamicReleaseService.pages(model, request, params);
    }
    
    @GetMapping("/curd_options")
    public DynamicReleaseCurdOptionResp curdOptions(@PathVariable String model) {
        final DynamicReleaseCurdOptionResp curdOptions = dynamicReleaseService.curdOptions(model);
        return curdOptions;
    }
    
    @PostMapping
    public void add(@PathVariable String model, @RequestBody Map<String, Object> map) {
        this.dynamicReleaseService.save(model, map);
    }
    
    @PutMapping("/{id}")
    public void edit(@PathVariable String model, @PathVariable Long id, @RequestBody Map<String, Object> map) {
        if (MapUtil.isNotEmpty(map)) {
            // 删除主键ID
            map.remove("id");
            this.dynamicReleaseService.updateById(model, id, map);
        }
    }
    
    @DeleteMapping("/{id}")
    public void del(@PathVariable String model, @PathVariable Long id) {
        this.dynamicReleaseService.deleteById(model, id);
    }
    
}
