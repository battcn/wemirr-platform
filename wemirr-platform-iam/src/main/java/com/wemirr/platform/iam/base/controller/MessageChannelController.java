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

package com.wemirr.platform.iam.base.controller;

import com.wemirr.framework.commons.annotation.log.AccessLog;
import com.wemirr.platform.iam.base.domain.dto.req.MessageChannelSaveReq;
import com.wemirr.platform.iam.base.domain.dto.resp.MessageChannelDetailResp;
import com.wemirr.platform.iam.base.service.MessageChannelService;
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
@Tag(name = "消息通道")
@RequiredArgsConstructor
@RequestMapping("/message-channel")
public class MessageChannelController {
    
    private final MessageChannelService messageChannelService;
    
    @PostMapping("/setting")
    @AccessLog(module = "消息通道", description = "渠道设置")
    @Operation(summary = "渠道设置")
    public void setting(@Validated @RequestBody MessageChannelSaveReq req) {
        messageChannelService.setting(req);
    }
    
    @GetMapping("/detail")
    @Operation(summary = "渠道详情")
    public MessageChannelDetailResp detail(String type) {
        return messageChannelService.detail(type);
    }
    
}
