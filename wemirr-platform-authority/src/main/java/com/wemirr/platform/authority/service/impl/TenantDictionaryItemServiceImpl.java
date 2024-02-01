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
package com.wemirr.platform.authority.service.impl;

import com.wemirr.framework.commons.exception.CheckedException;
import com.wemirr.framework.db.mybatisplus.ext.SuperServiceImpl;
import com.wemirr.framework.db.mybatisplus.wrap.Wraps;
import com.wemirr.platform.authority.domain.tenant.entity.TenantDictionary;
import com.wemirr.platform.authority.domain.tenant.entity.TenantDictionaryItem;
import com.wemirr.platform.authority.repository.tenant.TenantDictionaryItemMapper;
import com.wemirr.platform.authority.repository.tenant.TenantDictionaryMapper;
import com.wemirr.platform.authority.service.TenantDictionaryItemService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * <p>
 * 业务实现类
 * 字典项
 * </p>
 *
 * @author Levin
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class TenantDictionaryItemServiceImpl extends SuperServiceImpl<TenantDictionaryItemMapper, TenantDictionaryItem> implements TenantDictionaryItemService {
    
    private final TenantDictionaryMapper tenantDictionaryMapper;
    
    @Override
    public void addDictionaryItem(Long dictionaryId, TenantDictionaryItem item) {
        final long count = this.baseMapper.selectCount(Wraps.<TenantDictionaryItem>lbQ()
                .eq(TenantDictionaryItem::getValue, item.getValue())
                .eq(TenantDictionaryItem::getDictionaryId, dictionaryId));
        if (count > 0) {
            throw CheckedException.badRequest("auth.dict.item.exists");
        }
        final TenantDictionary dictionary = Optional.ofNullable(this.tenantDictionaryMapper.selectById(dictionaryId))
                .orElseThrow(() -> CheckedException.notFound("auth.dict.not-exists"));
        item.setDictionaryId(dictionaryId);
        item.setDictionaryCode(dictionary.getCode());
        this.baseMapper.insert(item);
    }
    
    @Override
    public void editDictionaryItem(Long dictionaryId, TenantDictionaryItem item) {
        final long count = this.baseMapper.selectCount(Wraps.<TenantDictionaryItem>lbQ()
                .eq(TenantDictionaryItem::getValue, item.getValue())
                .ne(TenantDictionaryItem::getId, item.getId())
                .eq(TenantDictionaryItem::getDictionaryId, dictionaryId));
        if (count > 0) {
            throw CheckedException.badRequest("auth.dict.item.exists");
        }
        this.baseMapper.updateById(item);
    }
    
}
