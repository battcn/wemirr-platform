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
import cn.hutool.core.collection.CollectionUtil;
import com.baomidou.dynamic.datasource.annotation.DSTransactional;
import com.wemirr.framework.commons.BeanUtilPlus;
import com.wemirr.framework.commons.exception.CheckedException;
import com.wemirr.framework.db.mybatisplus.ext.SuperServiceImpl;
import com.wemirr.framework.db.mybatisplus.wrap.Wraps;
import com.wemirr.framework.redis.plus.sequence.RedisSequenceHelper;
import com.wemirr.platform.iam.system.domain.dto.resp.RolePermissionResp;
import com.wemirr.platform.iam.system.domain.entity.Resource;
import com.wemirr.platform.iam.system.domain.enums.ResourceType;
import com.wemirr.platform.iam.system.repository.ResourceMapper;
import com.wemirr.platform.iam.tenant.domain.dto.req.PlanDefPermissionReq;
import com.wemirr.platform.iam.tenant.domain.dto.req.PlanDefinitionSaveReq;
import com.wemirr.platform.iam.tenant.domain.dto.resp.PlanDefinitionDetailResp;
import com.wemirr.platform.iam.tenant.domain.entity.PlanDefinition;
import com.wemirr.platform.iam.tenant.domain.entity.PlanDefinitionRes;
import com.wemirr.platform.iam.tenant.domain.entity.PlanSubscription;
import com.wemirr.platform.iam.tenant.domain.enums.TenantSequence;
import com.wemirr.platform.iam.tenant.repository.PlanDefResMapper;
import com.wemirr.platform.iam.tenant.repository.PlanDefinitionMapper;
import com.wemirr.platform.iam.tenant.repository.PlanSubscriptionMapper;
import com.wemirr.platform.iam.tenant.service.ProductDefinitionService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;

import static java.util.stream.Collectors.toList;

/**
 * @author Levin
 */
@Service
@RequiredArgsConstructor
public class ProductDefinitionServiceImpl extends SuperServiceImpl<PlanDefinitionMapper, PlanDefinition> implements ProductDefinitionService {

    private final PlanDefResMapper planDefResMapper;
    private final PlanSubscriptionMapper planSubscriptionMapper;
    private final ResourceMapper resourceMapper;
    private final RedisSequenceHelper sequenceHelper;

    @Override
    public void create(PlanDefinitionSaveReq req) {
        final long count = count(Wraps.<PlanDefinition>lbQ().eq(PlanDefinition::getName, req.getName()));
        if (count > 0) {
            throw CheckedException.badRequest("该套餐名称已存在");
        }
        var bean = BeanUtil.toBean(req, PlanDefinition.class);
        String code = sequenceHelper.generate(TenantSequence.PRODUCT_DEFINITION_NO);
        bean.setCode(code);
        this.baseMapper.insert(bean);
        refHandler(bean.getId(), req.getItemIdList());
    }

    @Override
    @DSTransactional(rollbackFor = Exception.class)
    public void modify(Long id, PlanDefinitionSaveReq req) {
        final PlanDefinition definition = Optional.ofNullable(this.baseMapper.selectById(id))
                .orElseThrow(() -> CheckedException.notFound("套餐信息不存在"));
        final long count = count(Wraps.<PlanDefinition>lbQ().ne(PlanDefinition::getId, definition.getId()).eq(PlanDefinition::getName, req.getName()));
        if (count > 0) {
            throw CheckedException.badRequest("该套餐名称已存在");
        }
        this.baseMapper.updateById(PlanDefinition.builder().id(id).name(req.getName()).logo(req.getLogo()).description(req.getDescription()).build());
        refHandler(id, req.getItemIdList());
    }

    public void refHandler(Long planId, Set<Long> itemIdList) {
        // 删除角色和资源的关联
        this.planDefResMapper.delete(Wraps.<PlanDefinitionRes>lbQ().eq(PlanDefinitionRes::getPlanId, planId));
        if (CollUtil.isEmpty(itemIdList)) {
            return;
        }
        final List<PlanDefinitionRes> resList = itemIdList.stream().filter(Objects::nonNull)
                .map(resId -> PlanDefinitionRes.builder().resId(resId).planId(planId).build()).collect(toList());
        this.planDefResMapper.insertBatch(resList);
    }

    @Override
    @DSTransactional(rollbackFor = Exception.class)
    public void permissions(Long planId, PlanDefPermissionReq req) {
        // 删除角色和资源的关联
        planDefResMapper.delete(Wraps.<PlanDefinitionRes>lbQ().eq(PlanDefinitionRes::getPlanId, planId));
        final Set<Long> resIdList = req.getResIdList();
        if (CollectionUtil.isEmpty(resIdList)) {
            return;
        }
        final List<PlanDefinitionRes> resList = resIdList.stream().filter(Objects::nonNull)
                .map(resId -> PlanDefinitionRes.builder().resId(resId).planId(planId).build())
                .collect(toList());
        planDefResMapper.insertBatch(resList);
    }

    @Override
    public RolePermissionResp findPermissions(Long id) {
        final List<Resource> resourceList = resourceMapper.selectList();
        if (CollUtil.isEmpty(resourceList)) {
            return null;
        }
        List<Long> resIdList = this.planDefResMapper.selectList(PlanDefinitionRes::getPlanId, id)
                .stream().map(PlanDefinitionRes::getResId).distinct().toList();
        List<Long> buttonIdList = resourceList.stream()
                .filter(x -> resIdList.contains(x.getId()))
                .filter(x -> x.getType() == ResourceType.BUTTON)
                .map(Resource::getId)
                .toList();
        List<Long> menuIdList = resourceList.stream()
                .filter(x -> resIdList.contains(x.getId()))
                .filter(x -> x.getType() != ResourceType.BUTTON)
                .map(Resource::getId)
                .toList();
        return RolePermissionResp.builder().menuIdList(menuIdList).buttonIdList(buttonIdList).build();
    }

    @Override
    @DSTransactional(rollbackFor = Exception.class)
    public void delete(Long id) {
        PlanDefinition definition = Optional.ofNullable(this.baseMapper.selectById(id))
                .orElseThrow(() -> CheckedException.notFound("套餐不存在,删除失败"));
        if (definition.getStatus() != null && definition.getStatus()) {
            throw CheckedException.notFound("套餐已启用,删除失败");
        }
        Long count = this.planSubscriptionMapper.selectCount(PlanSubscription::getPlanId, id);
        if (count != null && count > 0) {
            throw CheckedException.badRequest("套餐已被订阅,删除失败");
        }
    }

    @Override
    public PlanDefinitionDetailResp detail(Long id) {
        var entity = Optional.ofNullable(this.baseMapper.selectById(id)).orElseThrow(() -> CheckedException.notFound("套餐不存在"));
        var bean = BeanUtilPlus.toBean(entity, PlanDefinitionDetailResp.class);
        List<PlanDefinitionRes> resList = this.planDefResMapper.selectList(PlanDefinitionRes::getPlanId, id);
        if (CollUtil.isEmpty(resList)) {
            return bean;
        }
        List<Long> itemIdList = resList.stream().map(PlanDefinitionRes::getResId).distinct().toList();
        bean.setItemIdList(itemIdList);
        return bean;
    }

}
