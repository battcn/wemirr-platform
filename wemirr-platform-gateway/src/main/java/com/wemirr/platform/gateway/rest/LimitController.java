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

package com.wemirr.platform.gateway.rest;

import cn.hutool.core.util.IdUtil;
import com.wemirr.framework.commons.entity.Result;
import com.wemirr.platform.gateway.configuration.rule.LimitHelper;
import com.wemirr.platform.gateway.rest.domain.LimitRule;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Levin
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("/gateway/rules/limits")
public class LimitController {
    
    private final LimitHelper limitHelper;
    
    @GetMapping
    public Result<Map<String, Object>> query() {
        Map<String, Object> data = new HashMap<>(5);
        final List<LimitRule> limitRules = limitHelper.query();
        data.put("total", limitRules.size());
        data.put("records", limitRules);
        data.put("current", 1);
        data.put("size", 20);
        data.put("pages", 1);
        return Result.success(data);
    }
    
    @PostMapping
    public Result<Void> add(@RequestBody LimitRule rule) {
        String uuid = IdUtil.fastSimpleUUID();
        rule.setId(uuid);
        limitHelper.saveOrUpdate(rule);
        return Result.success();
    }
    
    @PutMapping("/{id}")
    public Result<Void> edit(@PathVariable String id, @RequestBody LimitRule rule) {
        limitHelper.saveOrUpdate(rule);
        return Result.success();
    }
    
    @DeleteMapping("/{id}")
    public Result<Void> delete(@PathVariable String id) {
        limitHelper.delete(id);
        return Result.success();
    }
    
}
