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
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.wemirr.framework.commons.entity.Result;
import com.wemirr.framework.db.mybatisplus.page.PageRequest;
import com.wemirr.framework.db.mybatisplus.wrap.Wraps;
import com.wemirr.framework.security.domain.UserInfoDetails;
import com.wemirr.framework.security.utils.SecurityUtils;
import com.wemirr.platform.authority.domain.common.req.DictionaryItemReq;
import com.wemirr.platform.authority.domain.tenant.entity.TenantDictionaryItem;
import com.wemirr.platform.authority.domain.tenant.resp.TenantDictionaryItemResp;
import com.wemirr.platform.authority.service.TenantDictionaryItemService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

/**
 * 字典项
 *
 * @author Levin
 */
@Slf4j
@Validated
@RestController
@RequiredArgsConstructor
@Tag(name = "业务字典", description = "业务字典")
@RequestMapping("/tenant_dictionaries/{dictionary_id}/items")
public class TenantDictionaryItemController {
    
    private final TenantDictionaryItemService tenantDictionaryItemService;
    
    @GetMapping
    @Operation(description = "查询字典子项 - [DONE] - [Levin]")
    @Parameters({
            @Parameter(name = "dictionary_id", description = "字典ID", in = ParameterIn.PATH),
            @Parameter(name = "label", description = "名称", in = ParameterIn.QUERY)
    })
    public Result<Page<TenantDictionaryItem>> query(@PathVariable("dictionary_id") Long dictionaryId, String label, Boolean status, PageRequest params) {
        final Page<TenantDictionaryItem> itemPage = this.tenantDictionaryItemService.page(params.buildPage(), Wraps.<TenantDictionaryItem>lbQ()
                .like(TenantDictionaryItem::getLabel, label).eq(TenantDictionaryItem::getStatus, status)
                .eq(TenantDictionaryItem::getDictionaryId, dictionaryId));
        return Result.success(itemPage);
    }
    
    @PostMapping
    @Operation(description = "添加字典子项 - [DONE] - [Levin]")
    @Parameter(name = "dictionary_id", description = "字典ID", in = ParameterIn.PATH)
    public void save(@PathVariable("dictionary_id") Long dictionaryId, @Validated @RequestBody DictionaryItemReq dto) {
        this.tenantDictionaryItemService.addDictionaryItem(dictionaryId, BeanUtil.toBean(dto, TenantDictionaryItem.class));
        
    }
    
    @PutMapping("/{id}")
    @Operation(description = "编辑字典子项 - [DONE] - [Levin]")
    @Parameter(name = "id", description = "子项ID", in = ParameterIn.PATH)
    public void edit(@PathVariable("dictionary_id") Long dictionaryId, @PathVariable Long id, @Validated @RequestBody DictionaryItemReq dto) {
        final TenantDictionaryItem tenantDictionaryItem = BeanUtil.toBean(dto, TenantDictionaryItem.class);
        tenantDictionaryItem.setId(id);
        this.tenantDictionaryItemService.editDictionaryItem(dictionaryId, tenantDictionaryItem);
        
    }
    
    @DeleteMapping("/{id}")
    @Operation(description = "删除字典子项 - [DONE] - [Levin]")
    @Parameter(name = "id", description = "子项ID", in = ParameterIn.PATH)
    public void del(@PathVariable Long id) {
        this.tenantDictionaryItemService.removeById(id);
        
    }
    
    @GetMapping("/{dictionary_code}")
    @Operation(description = "字典子项获取 - [DONE] - [Levin]")
    @Parameter(name = "dictionary_code", description = "子项编码", in = ParameterIn.PATH)
    public List<TenantDictionaryItemResp> dictionaryItem(@PathVariable("dictionary_id") Long dictionaryId, @PathVariable("dictionary_code") String dictionaryCode) {
        UserInfoDetails userInfoDetails = SecurityUtils.getAuthInfo();
        List<TenantDictionaryItem> list = this.tenantDictionaryItemService.list(Wraps.<TenantDictionaryItem>lbQ()
                .eq(TenantDictionaryItem::getDictionaryId, dictionaryId)
                .eq(TenantDictionaryItem::getTenantId, userInfoDetails.getTenantId())
                .eq(TenantDictionaryItem::getStatus, true)
                .eq(TenantDictionaryItem::getDictionaryCode, dictionaryCode));
        return list.stream()
                .map(item -> TenantDictionaryItemResp.builder().value(item.getValue()).label(item.getLabel()).build())
                .collect(Collectors.toList());
    }
    
}
