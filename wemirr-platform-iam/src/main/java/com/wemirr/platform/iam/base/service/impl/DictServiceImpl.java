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

package com.wemirr.platform.iam.base.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.lang.Pair;
import com.baomidou.dynamic.datasource.annotation.DSTransactional;
import com.wemirr.framework.boot.remote.dict.DictLoadService;
import com.wemirr.framework.commons.BeanUtilPlus;
import com.wemirr.framework.commons.entity.Dict;
import com.wemirr.framework.commons.exception.CheckedException;
import com.wemirr.framework.db.mybatisplus.ext.SuperServiceImpl;
import com.wemirr.framework.db.mybatisplus.wrap.Wraps;
import com.wemirr.framework.db.mybatisplus.wrap.query.LbqWrapper;
import com.wemirr.platform.iam.base.domain.dto.req.DictSaveReq;
import com.wemirr.platform.iam.base.domain.entity.SysDict;
import com.wemirr.platform.iam.base.repository.I18nLocaleMessageMapper;
import com.wemirr.platform.iam.base.repository.SysDictMapper;
import com.wemirr.platform.iam.base.service.DictService;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.groupingBy;

/**
 * 系统字典服务层
 *
 * @author Levin
 * @see DictLoadService 可以理解为字典缓存（同时 @Remote 注解使用）
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class DictServiceImpl extends SuperServiceImpl<SysDictMapper, SysDict> implements DictService {

    private final DictLoadService dictLoadService;
    private final I18nLocaleMessageMapper i18nLocaleMessageMapper;

    @PostConstruct
    public void init() {
        refresh();
    }

    @Override
    public void create(DictSaveReq req) {
        if (req == null) {
            throw CheckedException.notFound("字典内容不能为空");
        }
        final long count = this.baseMapper.selectCount(SysDict::getParentId, req.getParentId(), SysDict::getCode, req.getCode());
        if (count > 0) {
            throw CheckedException.badRequest("字典类型编码重复");
        }
        var bean = BeanUtil.toBean(req, SysDict.class);
        bean.setStatus(true);
        if (req.getParentId() == 0L) {
            bean.setFullCodePath(req.getCode());
        } else {
            var parentDict = Optional.ofNullable(this.baseMapper.selectById(req.getParentId())).orElseThrow(() -> CheckedException.notFound("上级字典不存在"));
            bean.setParentCode(parentDict.getCode());
            bean.setType(parentDict.getType());
        }
        this.baseMapper.insert(bean);
    }

    @Override
    @DSTransactional(rollbackFor = Exception.class)
    public void modify(Long id, DictSaveReq req) {
        final SysDict old = Optional.ofNullable(this.baseMapper.selectById(id)).orElseThrow(() -> CheckedException.notFound("字典不存在"));
        if (old.getType() == 0) {
            throw CheckedException.notFound("平台字典数据无法修改");
        }
        final Long count = this.baseMapper.selectCount(Wraps.<SysDict>lbQ().ne(SysDict::getId, id)
                .eq(SysDict::getParentId, req.getParentId()).eq(SysDict::getCode, req.getCode()));
        if (count != 0 && count > 0) {
            throw CheckedException.badRequest("字典类型编码重复");
        }
        SysDict bean = BeanUtilPlus.toBean(id, req, SysDict.class);
        this.baseMapper.updateById(bean);
//        this.dictLoadService.refreshCache(getPairMap(List.of(req.getCode())));
    }

    @Override
    @DSTransactional(rollbackFor = Exception.class)
    public void deleteById(Long id) {
        final SysDict dict = Optional.ofNullable(this.baseMapper.selectById(id)).orElseThrow(() -> CheckedException.notFound("字典不存在"));
        if (dict.getType() == 0) {
            throw CheckedException.notFound("内置数据无法删除");
        }
        LbqWrapper<SysDict> wrapper = Wraps.<SysDict>lbQ().eq(SysDict::getId, dict.getId());
        if (dict.getParentId() == null || dict.getParentId() == 0L) {
            this.baseMapper.delete(wrapper.or().eq(SysDict::getParentId, dict.getId()));
        } else {
            this.baseMapper.delete(wrapper);
        }
    }

    @Override
    public void refresh() {
        List<SysDict> list = this.baseMapper.selectList();
        if (CollUtil.isEmpty(list)) {
            return;
        }
        this.dictLoadService.refreshCache(getPairMap(list));
    }

    /**
     * 构建字典缓存数据
     * parentId = 0 或 null 的是父字典（字典类型），其余是子字典（字典项）
     * 按父字典的 code 分组，子字典转换为 Pair<子code, 子name>
     */
    private Map<String, List<Pair<String, String>>> getPairMap(List<SysDict> list) {
        // 父字典 Map: id -> code
        Map<Long, String> parentCodeMap = list.stream()
                .filter(dict -> dict.getParentId() == null || dict.getParentId() == 0L)
                .collect(Collectors.toMap(SysDict::getId, SysDict::getCode));
        // 子字典按 parentId 分组，转换为 Map<parentCode, List<Pair<code, name>>>
        return list.stream()
                .filter(dict -> dict.getParentId() != null && dict.getParentId() != 0L)
                .filter(dict -> parentCodeMap.containsKey(dict.getParentId()))
                .collect(groupingBy(
                        dict -> parentCodeMap.get(dict.getParentId()),
                        Collectors.mapping(item -> Pair.of(item.getCode(), item.getName()), Collectors.toList())));
    }

    @Override
    public List<Dict<String>> findItemByCode(String code) {
        Map<Object, Object> map = this.dictLoadService.findByIds(code);
        if (CollUtil.isEmpty(map)) {
            return null;
        }
        List<Dict<String>> dictList = new ArrayList<>();
        for (Map.Entry<Object, Object> entry : map.entrySet()) {
            dictList.add(new Dict<>(entry.getKey() + "", entry.getValue() + ""));
        }
        return dictList;
    }

}
