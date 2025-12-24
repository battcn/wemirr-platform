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

package com.wemirr.platform.iam.tenant.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.lang.Pair;
import com.baomidou.dynamic.datasource.annotation.DSTransactional;
import com.wemirr.framework.boot.remote.dict.DictLoadService;
import com.wemirr.framework.commons.BeanUtilPlus;
import com.wemirr.framework.commons.entity.Dict;
import com.wemirr.framework.commons.exception.CheckedException;
import com.wemirr.framework.commons.security.AuthenticationContext;
import com.wemirr.framework.db.mybatisplus.ext.SuperServiceImpl;
import com.wemirr.framework.db.mybatisplus.wrap.Wraps;
import com.wemirr.framework.db.mybatisplus.wrap.query.LbqWrapper;
import com.wemirr.framework.db.utils.TenantHelper;
import com.wemirr.platform.iam.base.domain.entity.SysDict;
import com.wemirr.platform.iam.base.repository.SysDictMapper;
import com.wemirr.platform.iam.tenant.domain.dto.req.TenantDictSaveReq;
import com.wemirr.platform.iam.tenant.domain.entity.TenantDict;
import com.wemirr.platform.iam.tenant.repository.TenantDictMapper;
import com.wemirr.platform.iam.tenant.service.TenantDictService;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.groupingBy;

/**
 * 租户业务字典
 *
 * @author Levin
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class TenantDictServiceImpl extends SuperServiceImpl<TenantDictMapper, TenantDict> implements TenantDictService {

    private final AuthenticationContext context;
    private final SysDictMapper dictMapper;
    private final DictLoadService dictLoadService;

//    @PostConstruct
    public void init() {
        refresh();
    }

    @Override
    public void create(TenantDictSaveReq req) {
        if (req == null) {
            throw CheckedException.notFound("字典内容不能为空");
        }
        final long count = this.baseMapper.selectCount(TenantDict::getParentId, req.getParentId(), TenantDict::getCode, req.getCode());
        if (count > 0) {
            throw CheckedException.badRequest("字典类型编码重复");
        }
        var bean = BeanUtil.toBean(req, TenantDict.class);
        bean.setStatus(true);
        bean.setTenantId(context.tenantId());
        if (req.getParentId() == 0L) {
            bean.setFullCodePath(req.getCode());
        } else {
            var parentDict = Optional.ofNullable(this.baseMapper.selectById(req.getParentId())).orElseThrow(() -> CheckedException.notFound("上级字典不存在"));
            bean.setParentCode(parentDict.getCode());
        }
        this.baseMapper.insert(bean);
    }

    @Override
    @DSTransactional(rollbackFor = Exception.class)
    public void modify(Long id, TenantDictSaveReq req) {
        Optional.ofNullable(this.baseMapper.selectById(id)).orElseThrow(() -> CheckedException.notFound("字典不存在"));
        final Long count = this.baseMapper.selectCount(Wraps.<TenantDict>lbQ().ne(TenantDict::getId, id).eq(TenantDict::getCode, req.getCode()));
        if (count != 0 && count > 0) {
            throw CheckedException.badRequest("字典类型编码重复");
        }
        TenantDict bean = BeanUtilPlus.toBean(id, req, TenantDict.class);
        this.baseMapper.updateById(bean);
        this.dictLoadService.refreshCache(getPairMap());
    }

    @Override
    @DSTransactional(rollbackFor = Exception.class)
    public void deleteById(Long id) {
        final TenantDict dict = Optional.ofNullable(this.baseMapper.selectById(id)).orElseThrow(() -> CheckedException.notFound("字典不存在"));
        LbqWrapper<TenantDict> wrapper = Wraps.<TenantDict>lbQ().eq(TenantDict::getId, dict.getId());
        if (dict.getParentId() == null || dict.getParentId() == 0L) {
            this.baseMapper.delete(wrapper.or().eq(TenantDict::getParentId, dict.getId()));
        } else {
            this.baseMapper.delete(wrapper);
        }
    }

    @Override
    public void refresh() {
        List<TenantDict> list = this.baseMapper.selectList(TenantDict::getStatus, true);
        if (CollUtil.isEmpty(list)) {
            return;
        }
        List<String> codeList = list.stream().map(TenantDict::getCode).distinct().toList();
        // TODO 优化逻辑
//        this.dictLoadService.refreshCache(getPairMap(codeList));
    }

    private Map<String, List<Pair<String, String>>> getPairMap() {
        return this.baseMapper.selectList(Wraps.<TenantDict>lbQ().ne(TenantDict::getParentId, 0L))
                .stream()
                .collect(groupingBy(
                        TenantDict::getParentCode,
                        Collectors.mapping(item -> Pair.of(item.getCode(), item.getName()), Collectors.toList())));
    }

    @Override
    public List<Dict<String>> findItemByCode(String code) {
        Map<Object, Object> map = this.dictLoadService.findByIds(code);
        if (CollUtil.isEmpty(map)) {
            return List.of();
        }
        List<Dict<String>> dictList = new ArrayList<>();
        for (Map.Entry<Object, Object> entry : map.entrySet()) {
            dictList.add(new Dict<>(entry.getKey() + "", entry.getValue() + ""));
        }
        return dictList;
    }

    @Override
    public void incrSyncTenantDict(Long tenantId) {
        if (TenantHelper.isSuperTenant()) {
            log.warn("超级租户,跳过同步");
            return;
        }
        // 查询超管 所有字典数据
        List<SysDict> dictList = TenantHelper.executeWithMaster(() -> dictMapper.selectList(SysDict::getType, 1));
        if (CollUtil.isEmpty(dictList)) {
            log.warn("未查询到有效的数据字典");
            return;
        }
        List<TenantDict> dictTypeList = dictList.stream().map(x -> {
            TenantDict dict = BeanUtil.toBean(x, TenantDict.class);
            dict.setId(null);
            dict.setReadonly(true);
            dict.setTenantId(tenantId);
            dict.setCreateTime(Instant.now());
            dict.setCreateBy(context.userId());
            dict.setCreateName(context.nickName());
            dict.setLastModifyTime(Instant.now());
            dict.setLastModifyBy(context.userId());
            dict.setLastModifyName(context.nickName());
            return dict;
        }).toList();
        // TODO 需要优化重构
    }

}
