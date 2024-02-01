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

import com.wemirr.platform.tools.domain.DynamicReleaseDragSetting;
import com.wemirr.platform.tools.service.DynamicReleaseGenerateService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Levin
 */
@Slf4j
@RestController
@RequestMapping("/dynamic_release_generates")
@RequiredArgsConstructor
@Tag(name = "代码生成", description = "代码生成")
@Validated
public class DynamicReleaseGenerateController {
    
    private final DynamicReleaseGenerateService dynamicReleaseGenerateService;
    
    @Operation(summary = "添加代码生成")
    @PostMapping
    public void add(@Validated @RequestBody DynamicReleaseDragSetting req) {
        this.dynamicReleaseGenerateService.add(req);
        
    }
    
}
