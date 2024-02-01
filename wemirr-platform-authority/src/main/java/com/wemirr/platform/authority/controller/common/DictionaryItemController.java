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
import com.wemirr.framework.db.mybatisplus.wrap.Wraps;
import com.wemirr.platform.authority.domain.common.entity.DictionaryItem;
import com.wemirr.platform.authority.domain.common.req.DictionaryItemPageReq;
import com.wemirr.platform.authority.domain.common.req.DictionaryItemReq;
import com.wemirr.platform.authority.service.DictionaryItemService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import static com.wemirr.platform.authority.domain.common.converts.DictionaryConverts.DICTIONARY_ITEM_DTO_2_ITEM_PO_CONVERTS;

/**
 * 字典项
 *
 * @author Levin
 */
@Slf4j
@Validated
@RestController
@RequiredArgsConstructor
@Tag(name = "字典类型", description = "字典类型")
@RequestMapping("/dictionaries/{dictionary_id}/items")
public class DictionaryItemController {
    
    private final DictionaryItemService dictionaryItemService;
    
    @GetMapping
    @Operation(summary = "查询字典子项", description = "查询字典子项 - [DONE] - [Levin]")
    @Parameters({
            @Parameter(name = "dictionary_id", description = "字典ID", in = ParameterIn.PATH),
            @Parameter(name = "label", description = "名称", in = ParameterIn.QUERY)
    })
    public Page<DictionaryItem> query(@PathVariable("dictionary_id") Long dictionaryId,
                                      DictionaryItemPageReq req) {
        return this.dictionaryItemService.page(req.buildPage(), Wraps.<DictionaryItem>lbQ()
                .eq(DictionaryItem::getDictionaryId, dictionaryId)
                .like(DictionaryItem::getLabel, req.getLabel()).eq(DictionaryItem::getStatus, req.getStatus()));
    }
    
    @PostMapping
    @Operation(summary = "添加字典子项", description = "添加字典子项 - [DONE] - [Levin]")
    @Parameter(name = "dictionary_id", description = "字典ID", in = ParameterIn.PATH)
    public void save(@PathVariable("dictionary_id") Long dictionaryId, @Validated @RequestBody DictionaryItemReq req) {
        this.dictionaryItemService.addDictionaryItem(dictionaryId, DICTIONARY_ITEM_DTO_2_ITEM_PO_CONVERTS.convert(req));
        
    }
    
    @PutMapping("/{id}")
    @Operation(summary = "编辑字典子项 - [DONE] - [Levin]", description = "编辑字典子项 - [DONE] - [Levin]")
    @Parameter(name = "id", description = "子项ID", in = ParameterIn.PATH)
    public void edit(@PathVariable("dictionary_id") Long dictionaryId, @PathVariable Long id, @Validated @RequestBody DictionaryItemReq req) {
        final DictionaryItem dictionaryItem = DICTIONARY_ITEM_DTO_2_ITEM_PO_CONVERTS.convert(req);
        dictionaryItem.setId(id);
        this.dictionaryItemService.editDictionaryItem(dictionaryId, dictionaryItem);
        
    }
    
    @DeleteMapping("/{id}")
    @Operation(summary = "删除字典子项 - [DONE] - [Levin]", description = "删除字典子项 - [DONE] - [Levin]")
    @Parameter(name = "id", description = "子项ID", in = ParameterIn.PATH)
    public void del(@PathVariable Long id, @PathVariable("dictionary_id") String dictionaryId) {
        this.dictionaryItemService.removeById(id);
        
    }
    
}
