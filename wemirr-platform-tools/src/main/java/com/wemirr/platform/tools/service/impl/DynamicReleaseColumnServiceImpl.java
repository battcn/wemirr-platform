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
package com.wemirr.platform.tools.service.impl;

import cn.hutool.core.collection.CollectionUtil;
import com.baomidou.dynamic.datasource.annotation.DSTransactional;
import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.wemirr.framework.commons.exception.CheckedException;
import com.wemirr.framework.db.mybatisplus.ext.SuperServiceImpl;
import com.wemirr.framework.db.mybatisplus.wrap.Wraps;
import com.wemirr.platform.tools.domain.entity.DynamicReleaseColumn;
import com.wemirr.platform.tools.domain.entity.DynamicReleaseGrid;
import com.wemirr.platform.tools.domain.req.DynamicReleaseGridColumnReq;
import com.wemirr.platform.tools.domain.resp.DynamicReleaseGridColumnResp;
import com.wemirr.platform.tools.repository.DynamicReleaseColumnMapper;
import com.wemirr.platform.tools.repository.DynamicReleaseGridMapper;
import com.wemirr.platform.tools.service.DynamicReleaseColumnService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;

import static com.wemirr.platform.tools.domain.converts.DynamicReleaseGridColumnConverts.DYNAMIC_RELEASE_GRID_COLUMN_PO_2_RESP;
import static com.wemirr.platform.tools.domain.converts.DynamicReleaseGridColumnConverts.DYNAMIC_RELEASE_GRID_COLUMN_REQ_2_PO;

/**
 * @author Levin
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class DynamicReleaseColumnServiceImpl extends SuperServiceImpl<DynamicReleaseColumnMapper, DynamicReleaseColumn> implements DynamicReleaseColumnService {
    
    private final DynamicReleaseGridMapper dynamicReleaseGridMapper;
    
    @Override
    public void edit(Long gridId, DynamicReleaseGridColumnReq req) {
        final Wrapper<DynamicReleaseColumn> wrapper = Wraps.<DynamicReleaseColumn>lbQ()
                .eq(DynamicReleaseColumn::getKey, req.getKey())
                .eq(DynamicReleaseColumn::getGridId, gridId);
        DynamicReleaseColumn column = DYNAMIC_RELEASE_GRID_COLUMN_REQ_2_PO.convert(req);
        final Long count = this.baseMapper.selectCount(wrapper);
        if (count == null || count == 0) {
            column.setGridId(gridId);
            this.baseMapper.insert(column);
        } else {
            this.baseMapper.update(column, wrapper);
        }
    }
    
    @Override
    @DSTransactional
    public List<DynamicReleaseGridColumnResp> listByGridId(Long gridId) {
        final DynamicReleaseGrid grid = Optional.ofNullable(this.dynamicReleaseGridMapper.selectById(gridId))
                .orElseThrow(() -> CheckedException.notFound("模型不存在"));
        final List<DynamicReleaseColumn> releaseColumns = baseMapper.selectList(Wraps.<DynamicReleaseColumn>lbQ()
                .eq(DynamicReleaseColumn::getGridId, gridId));
        final List<String> tableColumns = baseMapper.findFieldByTableName(grid.getTableName());
        if (CollectionUtil.isEmpty(releaseColumns)) {
            return tableColumns.stream().map(column -> DynamicReleaseGridColumnResp.builder()
                    .key(column).build()).collect(Collectors.toList());
        }
        // 所有的字段
        final Map<String, DynamicReleaseColumn> map = releaseColumns.stream()
                .collect(Collectors.toMap(DynamicReleaseColumn::getKey, Function.identity()));
        return tableColumns.stream().map(column -> {
            DynamicReleaseColumn releaseColumn = map.get(column);
            if (Objects.isNull(releaseColumn)) {
                // 字段从数据库中删除了
                // 删除多的字段
                return DynamicReleaseGridColumnResp.builder().key(column).build();
            }
            return DYNAMIC_RELEASE_GRID_COLUMN_PO_2_RESP.convert(releaseColumn);
        }).collect(Collectors.toList());
    }
}
