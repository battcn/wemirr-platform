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

import com.wemirr.framework.commons.entity.Result;
import com.wemirr.platform.gateway.configuration.rule.BlacklistHelper;
import com.wemirr.platform.gateway.rest.domain.BlacklistRule;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author Levin
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("/gateway/rules/blacklist")
public class BlacklistController {
    
    private final BlacklistHelper blacklistHelper;
    
    @GetMapping
    public Result<List<BlacklistRule>> query() {
        return Result.success(blacklistHelper.query());
    }
    
    @PostMapping
    public Result<Void> saveOrUpdate(@RequestBody BlacklistRule rule) {
        blacklistHelper.saveOrUpdate(rule);
        return Result.success();
    }
    
    @DeleteMapping("/{id}")
    public Result<Void> delete(@PathVariable String id) {
        blacklistHelper.delete(id);
        return Result.success();
    }
    
}
