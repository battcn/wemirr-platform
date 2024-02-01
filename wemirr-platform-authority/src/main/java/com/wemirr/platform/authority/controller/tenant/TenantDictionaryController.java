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
package com.wemirr.platform.authority.controller.tenant;

import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.wemirr.framework.commons.annotation.log.AccessLog;
import com.wemirr.framework.db.mybatisplus.page.PageRequest;
import com.wemirr.framework.db.mybatisplus.wrap.Wraps;
import com.wemirr.framework.security.domain.UserInfoDetails;
import com.wemirr.framework.security.utils.SecurityUtils;
import com.wemirr.platform.authority.domain.tenant.entity.TenantDictionary;
import com.wemirr.platform.authority.domain.tenant.entity.TenantDictionaryItem;
import com.wemirr.platform.authority.domain.tenant.req.TenantDictionaryReq;
import com.wemirr.platform.authority.service.TenantDictionaryItemService;
import com.wemirr.platform.authority.service.TenantDictionaryService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 字典类型
 *
 * @author Levin
 */
@Slf4j
@Validated
@RestController
@RequestMapping("/tenant_dictionaries")
@Tag(name = "业务字典", description = "业务字典")
@RequiredArgsConstructor
public class TenantDictionaryController {
    
    private final TenantDictionaryService tenantDictionaryService;
    private final TenantDictionaryItemService tenantDictionaryItemService;
    
    @GetMapping
    @AccessLog(description = "字典查询")
    @Operation(description = "查询字典 - [DONE] - [Levin]")
    @Parameter(name = "name", description = "名称", in = ParameterIn.QUERY)
    public IPage<TenantDictionary> query(PageRequest pageRequest, String name, String code, Boolean status) {
        // 获取租户ID
        UserInfoDetails userInfoDetails = SecurityUtils.getAuthInfo();
        return this.tenantDictionaryService.page(pageRequest.buildPage(),
                Wraps.<TenantDictionary>lbQ().eq(TenantDictionary::getStatus, status).eq(TenantDictionary::getTenantId, userInfoDetails.getTenantId())
                        .like(TenantDictionary::getCode, code).like(TenantDictionary::getName, name)
                        .orderByDesc(TenantDictionary::getId));
    }
    
    @PostMapping
    @AccessLog(description = "字典新增")
    @Operation(description = "新增字典 - [DONE] - [Levin]")
    public void save(@Validated @RequestBody TenantDictionaryReq dto) {
        this.tenantDictionaryService.addDictionary(BeanUtil.toBean(dto, TenantDictionary.class));
    }
    
    @PutMapping("/{id}")
    @AccessLog(description = "字典编辑")
    @Operation(description = "编辑字典 - [DONE] - [Levin]")
    public void edit(@PathVariable Long id, @Validated @RequestBody TenantDictionaryReq dto) {
        final TenantDictionary bean = BeanUtil.toBean(dto, TenantDictionary.class);
        bean.setId(id);
        this.tenantDictionaryService.editDictionary(bean);
    }
    
    @DeleteMapping("/{id}")
    @AccessLog(description = "删除指定字典项")
    @Operation(description = "删除字典 - [DONE] - [Levin]")
    public void del(@PathVariable Long id) {
        this.tenantDictionaryService.deleteById(id);
    }
    
    @GetMapping("/{dictionary_code}/list")
    @Operation(description = "查询字典子项 - [DONE] - [Levin]")
    @Parameter(name = "dictionary_code", description = "编码", in = ParameterIn.PATH)
    public List<TenantDictionaryItem> list(@PathVariable("dictionary_code") String dictionaryCode) {
        return this.tenantDictionaryItemService.list(Wraps.<TenantDictionaryItem>lbQ()
                .eq(TenantDictionaryItem::getStatus, true)
                .eq(TenantDictionaryItem::getDictionaryCode, dictionaryCode));
    }
}
