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

import cn.dev33.satoken.annotation.SaCheckPermission;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.wemirr.framework.commons.annotation.log.AccessLog;
import com.wemirr.platform.iam.base.domain.dto.req.MessageTemplatePageReq;
import com.wemirr.platform.iam.base.domain.dto.req.MessageTemplateSaveReq;
import com.wemirr.platform.iam.base.domain.dto.resp.MessageTemplateDetailResp;
import com.wemirr.platform.iam.base.domain.dto.resp.MessageTemplatePageResp;
import com.wemirr.platform.iam.base.service.MessageTemplateService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

/**
 * 登录日志
 *
 * @author Levin
 */
@Slf4j
@Validated
@RestController
@RequiredArgsConstructor
@RequestMapping("/message-template")
@Tag(name = "消息模板", description = "消息模板")
public class MessageTemplateController {
    
    private final MessageTemplateService messageTemplateService;
    
    @GetMapping("/page")
    @Operation(summary = "分页查询")
    @SaCheckPermission(value = {"message:template:page"})
    public IPage<MessageTemplatePageResp> pageList(MessageTemplatePageReq req) {
        return messageTemplateService.pageList(req);
    }
    
    @GetMapping("/{id}/detail")
    @Operation(summary = "模板详情")
    public MessageTemplateDetailResp detail(@PathVariable Long id) {
        return messageTemplateService.detail(id);
    }
    
    @PostMapping("/create")
    @AccessLog(module = "消息模板", description = "添加模板")
    @Operation(summary = "添加模板")
    @SaCheckPermission(value = {"message:template:add"})
    public void create(@Validated @RequestBody MessageTemplateSaveReq req) {
        messageTemplateService.create(req);
    }
    
    @PutMapping("/{id}/modify")
    @AccessLog(module = "消息模板", description = "编辑模板")
    @Operation(summary = "编辑模板")
    @SaCheckPermission(value = {"message:template:edit"})
    public void modify(@PathVariable Long id, @Validated @RequestBody MessageTemplateSaveReq req) {
        messageTemplateService.modify(id, req);
    }
    
    @DeleteMapping("/{id}")
    @AccessLog(module = "消息模板", description = "删除模板")
    @Operation(summary = "删除模板")
    @SaCheckPermission(value = {"message:template:remove"})
    public void remove(@PathVariable Long id) {
        messageTemplateService.removeById(id);
    }
    
}
