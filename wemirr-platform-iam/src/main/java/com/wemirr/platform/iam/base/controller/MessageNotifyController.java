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
import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.wemirr.framework.commons.annotation.log.AccessLog;
import com.wemirr.framework.commons.security.AuthenticationContext;
import com.wemirr.framework.db.mybatisplus.wrap.Wraps;
import com.wemirr.platform.iam.base.domain.dto.req.MessageNotifyPublishReq;
import com.wemirr.platform.iam.base.domain.dto.resp.MessageNotifyPageResp;
import com.wemirr.platform.iam.base.domain.entity.MessageNotify;
import com.wemirr.platform.iam.base.service.MessageNotifyService;
import com.wemirr.platform.iam.system.domain.dto.req.MessageNotifyPageReq;
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
@Tag(name = "消息通知")
@RequiredArgsConstructor
@RequestMapping("/message-notify")
public class MessageNotifyController {
    
    private final AuthenticationContext context;
    private final MessageNotifyService messageNotifyService;
    
    @GetMapping("/page")
    @Operation(summary = "消息列表 - [全部]")
    @SaCheckPermission(value = {"message:list"})
    public IPage<MessageNotifyPageResp> pageList(MessageNotifyPageReq req) {
        return messageNotifyService.page(req.buildPage(), Wraps.<MessageNotify>lbQ()
                .eq(MessageNotify::getType, req.getType())
                .eq(MessageNotify::getUserId, req.getUserId())
                .and(StrUtil.isNotBlank(req.getKeyword()),
                        lb -> lb.likeRight(MessageNotify::getTitle, req.getKeyword())
                                .or().likeRight(MessageNotify::getContent, req.getKeyword())))
                .convert(x -> BeanUtil.toBean(x, MessageNotifyPageResp.class));
    }
    
    @PostMapping("/publish")
    @AccessLog(module = "消息通知", description = "发布消息通知")
    @Operation(summary = "消息通知")
    @SaCheckPermission(value = {"message:publish"})
    public void publish(@Validated @RequestBody MessageNotifyPublishReq req) {
        messageNotifyService.publish(req);
    }
    
    @GetMapping("/subscribe-list")
    @Operation(summary = "消息列表 - [订阅]")
    @SaCheckPermission(value = {"message:subscribe-list"})
    public IPage<MessageNotifyPageResp> subscribe(MessageNotifyPageReq req) {
        req.setUserId(context.userId());
        return pageList(req);
    }
    
    @DeleteMapping("/{id}")
    @Operation(summary = "删除消息")
    public void remove(@PathVariable("id") Long id) {
        this.messageNotifyService.removeById(id);
    }
    
    @DeleteMapping("/batch_remove")
    @Operation(summary = "批量删除")
    public void batchDel(@RequestBody List<Long> ids) {
        this.messageNotifyService.removeByIds(ids);
    }
    
}
