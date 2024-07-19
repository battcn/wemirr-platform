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
import com.wemirr.framework.db.mybatisplus.ext.SuperServiceImpl;
import com.wemirr.framework.db.mybatisplus.wrap.Wraps;
import com.wemirr.platform.authority.domain.common.entity.SysDict;
import com.wemirr.platform.authority.domain.common.entity.SysDictItem;
import com.wemirr.platform.authority.domain.common.req.DictItemSaveReq;
import com.wemirr.platform.authority.repository.common.SysDictItemMapper;
import com.wemirr.platform.authority.repository.common.SysDictMapper;
import com.wemirr.platform.authority.service.DictItemService;
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
 * @since 2019-07-02
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class DictItemServiceImpl extends SuperServiceImpl<SysDictItemMapper, SysDictItem> implements DictItemService {

    private final SysDictMapper sysDictMapper;

    @Override
    public void create(Long dictId, DictItemSaveReq req) {
        final SysDict dict = Optional.ofNullable(this.sysDictMapper.selectById(dictId)).orElseThrow(() -> CheckedException.notFound("字典不存在"));
        final long count = this.baseMapper.selectCount(Wraps.<SysDictItem>lbQ().eq(SysDictItem::getValue, req.getValue())
                .eq(SysDictItem::getDictCode, dict.getCode()));
        if (count > 0) {
            throw CheckedException.badRequest("子项编码已存在");
        }
        SysDictItem item = BeanUtil.toBean(req, SysDictItem.class);
        item.setDictId(dict.getId());
        item.setDictCode(dict.getCode());
        this.baseMapper.insert(item);
    }

    @Override
    public void modify(Long dictId, Long itemId, DictItemSaveReq req) {
        final SysDict dict = Optional.ofNullable(this.sysDictMapper.selectById(dictId)).orElseThrow(() -> CheckedException.notFound("字典不存在"));
        final long count = this.baseMapper.selectCount(Wraps.<SysDictItem>lbQ().ne(SysDictItem::getId, itemId)
                .eq(SysDictItem::getValue, req.getValue()).eq(SysDictItem::getDictCode, dict.getCode()));
        if (count > 0) {
            throw CheckedException.badRequest("编码已存在");
        }
        SysDictItem item = BeanUtilPlus.toBean(itemId, req, SysDictItem.class);
        this.baseMapper.updateById(item);
    }
}
