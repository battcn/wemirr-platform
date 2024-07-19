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

import cn.hutool.core.bean.BeanUtil;
import com.wemirr.framework.commons.BeanUtilPlus;
import com.wemirr.framework.commons.exception.CheckedException;
import com.wemirr.framework.commons.security.AuthenticationContext;
import com.wemirr.framework.db.mybatisplus.ext.SuperServiceImpl;
import com.wemirr.framework.db.mybatisplus.wrap.Wraps;
import com.wemirr.platform.authority.domain.common.req.DictItemSaveReq;
import com.wemirr.platform.authority.domain.tenant.entity.TenantDict;
import com.wemirr.platform.authority.domain.tenant.entity.TenantDictItem;
import com.wemirr.platform.authority.repository.tenant.TenantDictItemMapper;
import com.wemirr.platform.authority.repository.tenant.TenantDictMapper;
import com.wemirr.platform.authority.service.TenantDictItemService;
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
public class TenantDictItemServiceImpl extends SuperServiceImpl<TenantDictItemMapper, TenantDictItem> implements TenantDictItemService {

    private final TenantDictMapper tenantDictMapper;
    private final AuthenticationContext context;


    @Override
    public void create(Long dictId, DictItemSaveReq req) {
        final TenantDict dict = Optional.ofNullable(this.tenantDictMapper.selectById(dictId)).orElseThrow(() -> CheckedException.notFound("字典不存在"));
        final long count = this.baseMapper.selectCount(Wraps.<TenantDictItem>lbQ().eq(TenantDictItem::getValue, req.getValue())
                .eq(TenantDictItem::getDictCode, dict.getCode()));
        if (count > 0) {
            throw CheckedException.badRequest("子项编码已存在");
        }
        TenantDictItem item = BeanUtil.toBean(req, TenantDictItem.class);
        item.setDictId(dict.getId());
        item.setDictCode(dict.getCode());
        item.setTenantId(context.tenantId());
        this.baseMapper.insert(item);
    }

    @Override
    public void modify(Long dictId, Long itemId, DictItemSaveReq req) {
        final TenantDict dict = Optional.ofNullable(this.tenantDictMapper.selectById(dictId)).orElseThrow(() -> CheckedException.notFound("字典不存在"));
        final long count = this.baseMapper.selectCount(Wraps.<TenantDictItem>lbQ().ne(TenantDictItem::getId, itemId)
                .eq(TenantDictItem::getValue, req.getValue()).eq(TenantDictItem::getDictCode, dict.getCode()));
        if (count > 0) {
            throw CheckedException.badRequest("编码已存在");
        }
        TenantDictItem item = BeanUtilPlus.toBean(itemId, req, TenantDictItem.class);
        this.baseMapper.updateById(item);
    }
}
