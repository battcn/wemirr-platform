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

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.wemirr.framework.commons.annotation.log.AccessLog;
import com.wemirr.framework.commons.entity.KeyVal;
import com.wemirr.framework.db.mybatisplus.wrap.Wraps;
import com.wemirr.platform.authority.domain.common.entity.Dictionary;
import com.wemirr.platform.authority.domain.common.req.DictionaryPageReq;
import com.wemirr.platform.authority.domain.common.req.DictionarySaveReq;
import com.wemirr.platform.authority.service.DictionaryItemService;
import com.wemirr.platform.authority.service.DictionaryService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.wemirr.platform.authority.domain.common.converts.DictionaryConverts.DICTIONARY_DTO_2_PO_CONVERTS;

/**
 * 字典类型
 *
 * @author Levin
 */
@Slf4j
@Validated
@RestController
@RequestMapping("/dictionaries")
@Tag(name = "字典类型", description = "字典类型")
@RequiredArgsConstructor
public class DictionaryController {
    
    private final DictionaryService dictionaryService;
    private final DictionaryItemService dictionaryItemService;
    
    @GetMapping
    @AccessLog(description = "字典查询")
    @Operation(summary = "查询字典 - [DONE] - [Levin]", description = "查询字典 - [DONE] - [Levin]")
    @Parameter(name = "name", description = "名称", in = ParameterIn.QUERY)
    @PreAuthorize("hasAuthority('sys:dict:page')")
    public IPage<Dictionary> query(DictionaryPageReq req) {
        return this.dictionaryService.page(req.buildPage(),
                Wraps.<Dictionary>lbQ().eq(Dictionary::getStatus, req.getStatus()).like(Dictionary::getCode, req.getCode())
                        .like(Dictionary::getName, req.getName()));
    }
    
    @PostMapping
    @AccessLog(description = "字典新增")
    @Operation(summary = "新增字典 - [DONE] - [Levin]", description = "新增字典 - [DONE] - [Levin]")
    @PreAuthorize("hasAuthority('sys:dict:add')")
    public void save(@Validated @RequestBody DictionarySaveReq req) {
        this.dictionaryService.addDictionary(DICTIONARY_DTO_2_PO_CONVERTS.convert(req));
    }
    
    @PutMapping("/{id}")
    @AccessLog(description = "字典编辑")
    @Operation(summary = "编辑字典 - [DONE] - [Levin]", description = "编辑字典 - [DONE] - [Levin]")
    @PreAuthorize("hasAuthority('sys:dict:edit')")
    public void edit(@PathVariable Long id, @Validated @RequestBody DictionarySaveReq req) {
        this.dictionaryService.editDictionary(DICTIONARY_DTO_2_PO_CONVERTS.convert(req, id));
    }
    
    @DeleteMapping("/{id}")
    @AccessLog(description = "删除指定字典项")
    @Operation(summary = "删除字典 - [DONE] - [Levin]", description = "删除字典 - [DONE] - [Levin]")
    @PreAuthorize("hasAuthority('sys:dict:remove')")
    public void del(@PathVariable Long id) {
        this.dictionaryService.deleteById(id);
    }
    
    @GetMapping("/{code}/refresh")
    @AccessLog(description = "刷新字典")
    @Operation(summary = "刷新字典 - [DONE] - [Levin]", description = "刷新字典 - [DONE] - [Levin]")
    public void refresh(@PathVariable("code") String code) {
        this.dictionaryService.refresh(code);
    }
    
    @GetMapping("/{code}/list")
    @Operation(summary = "查询字典子项 - [DONE] - [Levin]", description = "查询字典子项,缓存10分钟,每隔 5 分钟刷新一次,为了性能利用本地JVM缓存,字典过大可以采用远程缓存")
    @Parameter(name = "code", description = "编码", in = ParameterIn.PATH)
    public List<KeyVal> list(@PathVariable("code") String code) {
        return dictionaryService.findItemByCode(code);
    }
}
