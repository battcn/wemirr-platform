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

package com.wemirr.platform.authority.controller.common;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.wemirr.framework.commons.security.AuthenticationContext;
import com.wemirr.framework.db.dynamic.annotation.TenantDS;
import com.wemirr.framework.db.mybatisplus.page.PageRequest;
import com.wemirr.framework.db.mybatisplus.wrap.Wraps;
import com.wemirr.platform.authority.domain.common.entity.SiteMessage;
import com.wemirr.platform.authority.service.SiteMessageService;
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
@Tag(name = "站内消息")
@RequiredArgsConstructor
@TenantDS
@RequestMapping("/site_messages")
public class SiteMessageController {
    
    private final AuthenticationContext authenticationContext;
    private final SiteMessageService siteMessageService;
    
    @GetMapping("/page")
    public Page<SiteMessage> pageList(String title, String level, Boolean mark, PageRequest request) {
        return siteMessageService.page(request.buildPage(), Wraps.<SiteMessage>lbQ()
                .like(SiteMessage::getTitle, title).eq(SiteMessage::getLevel, level)
                .eq(SiteMessage::getMark, mark).eq(SiteMessage::getReceiveId, authenticationContext.userId()));
    }
    
    @PatchMapping("/{id}/mark")
    public void mark(@PathVariable("id") Long id) {
        this.siteMessageService.updateById(SiteMessage.builder().mark(true).id(id).build());
    }
    
    @DeleteMapping("/{id}")
    public void del(@PathVariable("id") Long id) {
        this.siteMessageService.removeById(id);
    }
    
    @DeleteMapping("/batch_remove")
    public void batchDel(@RequestBody List<Long> ids) {
        this.siteMessageService.removeByIds(ids);
    }
    
}
