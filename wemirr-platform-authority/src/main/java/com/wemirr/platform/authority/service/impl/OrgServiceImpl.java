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
import cn.hutool.core.util.StrUtil;
import com.wemirr.framework.commons.entity.Entity;
import com.wemirr.framework.commons.exception.CheckedException;
import com.wemirr.framework.db.mybatisplus.ext.SuperServiceImpl;
import com.wemirr.framework.db.mybatisplus.wrap.Wraps;
import com.wemirr.platform.authority.domain.baseinfo.entity.Org;
import com.wemirr.platform.authority.domain.baseinfo.req.OrgSaveReq;
import com.wemirr.platform.authority.repository.baseinfo.OrgMapper;
import com.wemirr.platform.authority.service.OrgService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

import static java.util.stream.Collectors.toList;

/**
 * <p>
 * 业务实现类
 * 组织
 * </p>
 *
 * @author Levin
 * @since 2019-07-22
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class OrgServiceImpl extends SuperServiceImpl<OrgMapper, Org> implements OrgService {
    
    @Override
    public void remove(Long id) {
        final Long count = this.baseMapper.selectCount(Org::getParentId, id);
        if (count != null && count > 0) {
            throw CheckedException.badRequest("当前组织下还存在子节点,请先移除子节点");
        }
        this.baseMapper.deleteById(id);
    }
    
    @Override
    public void addOrg(OrgSaveReq req) {
        final Org bean = BeanUtil.toBean(req, Org.class);
        bean.setTreePath(buildNewTreePath(req.getParentId()));
        this.baseMapper.insert(bean);
    }
    
    @Override
    public List<Long> getFullTreeIdPath(Long id) {
        if (id == null) {
            return null;
        }
        final Org org = this.baseMapper.selectById(id);
        if (org == null) {
            return null;
        }
        List<Long> treePath = org.getTreePath();
        treePath.add(org.getId());
        final List<Long> list = this.baseMapper.selectList(Wraps.<Org>lbQ()
                .likeRight(Org::getTreePath, StrUtil.join(StrUtil.COMMA, treePath)))
                .stream()
                .map(Entity::getId)
                .distinct()
                .collect(toList());
        list.add(org.getId());
        return list;
    }
    
    private List<Long> buildNewTreePath(Long id) {
        final Org org = Optional.ofNullable(this.baseMapper.selectById(id)).orElseThrow(() -> CheckedException.notFound("父节点不存在"));
        final List<Long> treePath = org.getTreePath();
        treePath.add(org.getId());
        return treePath;
    }
    
}
