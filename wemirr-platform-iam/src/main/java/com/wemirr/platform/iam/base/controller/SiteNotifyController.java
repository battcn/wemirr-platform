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
import cn.dev33.satoken.annotation.SaMode;
import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.wemirr.framework.commons.annotation.log.AccessLog;
import com.wemirr.framework.db.dynamic.annotation.TenantDS;
import com.wemirr.framework.db.mybatisplus.wrap.Wraps;
import com.wemirr.platform.iam.base.domain.dto.req.SiteMessagePageReq;
import com.wemirr.platform.iam.base.domain.dto.req.SiteMessageSaveReq;
import com.wemirr.platform.iam.base.domain.dto.resp.CommonDataResp;
import com.wemirr.platform.iam.base.domain.dto.resp.SiteMessageResp;
import com.wemirr.platform.iam.base.domain.entity.SiteNotify;
import com.wemirr.platform.iam.system.domain.enums.ReceiverType;
import com.wemirr.platform.iam.base.service.SiteNotifyService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.wemirr.platform.iam.base.domain.converts.SiteNotifyConverts.SITE_NOTIFY_2_VO_CONVERTS;

/**
 * @author Levin
 */
@TenantDS
@RequestMapping("/site_notifies")
@RestController
@RequiredArgsConstructor
@Tag(name = "站内消息")
public class SiteNotifyController {
    
    private final SiteNotifyService siteNotifyService;
    
    @GetMapping("/page")
    @Operation(summary = "分页查询")
    @SaCheckPermission(value = {"sys:site_notify:page"}, mode = SaMode.OR)
    public IPage<SiteMessageResp> publishList(SiteMessagePageReq req) {
        return siteNotifyService.page(req.buildPage(), Wraps.<SiteNotify>lbQ()
                .eq(SiteNotify::getTitle, req.getTitle()).eq(SiteNotify::getLevel, req.getLevel())
                .eq(SiteNotify::getType, ReceiverType.of(req.getType())))
                .convert(SITE_NOTIFY_2_VO_CONVERTS::convert);
    }
    
    @GetMapping("/{type}/receivers")
    @Operation(summary = "接收人列表")
    public List<CommonDataResp> query(@PathVariable ReceiverType type, String search) {
        return this.siteNotifyService.queryReceiverByType(type, search);
    }
    
    @PostMapping
    @AccessLog(description = "添加通知")
    @Operation(summary = "添加通知")
    @SaCheckPermission(value = {"sys:site_notify:add"}, mode = SaMode.OR)
    public void add(@Validated @RequestBody SiteMessageSaveReq req) {
        final SiteNotify bean = BeanUtil.toBean(req, SiteNotify.class);
        bean.setReceiver(StringUtils.join(req.getReceiver(), ","));
        siteNotifyService.save(bean);
    }
    
    @PutMapping("/{id}")
    @AccessLog(description = "编辑通知")
    @Operation(summary = "编辑通知")
    @SaCheckPermission(value = {"sys:site_notify:edit"}, mode = SaMode.OR)
    public void edit(@PathVariable Long id, @Validated @RequestBody SiteMessageSaveReq req) {
        final SiteNotify bean = BeanUtil.toBean(req, SiteNotify.class);
        bean.setReceiver(StringUtils.join(req.getReceiver(), ","));
        bean.setId(id);
        siteNotifyService.updateById(bean);
    }
    
    @DeleteMapping("/{id}")
    @AccessLog(description = "删除通知")
    @Operation(summary = "删除通知")
    @SaCheckPermission(value = {"sys:site_notify:remove"}, mode = SaMode.OR)
    public void del(@PathVariable Long id) {
        siteNotifyService.removeById(id);
    }
    
    @PatchMapping("/{id}/publish")
    @AccessLog(description = "发布通知")
    @Operation(summary = "发布通知")
    @SaCheckPermission(value = {"sys:site_notify:publish"}, mode = SaMode.OR)
    public void publish(@PathVariable Long id) {
        siteNotifyService.publish(id);
    }
    
}
