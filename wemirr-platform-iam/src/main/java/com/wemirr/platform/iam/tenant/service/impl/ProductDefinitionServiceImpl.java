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
import cn.hutool.core.collection.CollectionUtil;
import com.baomidou.dynamic.datasource.annotation.DSTransactional;
import com.wemirr.framework.commons.BeanUtilPlus;
import com.wemirr.framework.commons.exception.CheckedException;
import com.wemirr.framework.db.mybatisplus.ext.SuperServiceImpl;
import com.wemirr.framework.db.mybatisplus.wrap.Wraps;
import com.wemirr.platform.iam.system.domain.dto.resp.RolePermissionResp;
import com.wemirr.platform.iam.system.domain.dto.resp.VisibleResourceResp;
import com.wemirr.platform.iam.system.repository.ResourceMapper;
import com.wemirr.platform.iam.tenant.domain.dto.req.ProductDefPermissionReq;
import com.wemirr.platform.iam.tenant.domain.dto.req.ProductDefinitionSaveReq;
import com.wemirr.platform.iam.tenant.domain.entity.ProductDefinition;
import com.wemirr.platform.iam.tenant.domain.entity.ProductDefinitionRes;
import com.wemirr.platform.iam.tenant.domain.entity.ProductSubscription;
import com.wemirr.platform.iam.tenant.repository.ProductDefResMapper;
import com.wemirr.platform.iam.tenant.repository.ProductDefinitionMapper;
import com.wemirr.platform.iam.tenant.repository.ProductSubscriptionMapper;
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
public class ProductDefinitionServiceImpl extends SuperServiceImpl<ProductDefinitionMapper, ProductDefinition> implements ProductDefinitionService {

    private final ProductDefResMapper productDefResMapper;
    private final ProductSubscriptionMapper productSubscriptionMapper;
    private final ResourceMapper resourceMapper;

    @Override
    public void create(ProductDefinitionSaveReq req) {
        final long count = count(Wraps.<ProductDefinition>lbQ().eq(ProductDefinition::getName, req.getName()));
        if (count > 0) {
            throw CheckedException.badRequest("该产品名称已存在");
        }
        this.baseMapper.insert(BeanUtil.toBean(req, ProductDefinition.class));
    }

    @Override
    @DSTransactional(rollbackFor = Exception.class)
    public void modify(Long id, ProductDefinitionSaveReq req) {
        final ProductDefinition definition = Optional.ofNullable(this.baseMapper.selectById(id))
                .orElseThrow(() -> CheckedException.notFound("产品信息不存在"));
        final long count = count(Wraps.<ProductDefinition>lbQ().ne(ProductDefinition::getId, definition.getId()).eq(ProductDefinition::getName, req.getName()));
        if (count > 0) {
            throw CheckedException.badRequest("该产品名称已存在");
        }
        this.baseMapper.updateById(ProductDefinition.builder()
                .id(id).name(req.getName()).logo(req.getLogo()).description(req.getDescription())
                .build());
    }

    @Override
    @DSTransactional(rollbackFor = Exception.class)
    public void permissions(Long productId, ProductDefPermissionReq req) {
        // 删除角色和资源的关联
        productDefResMapper.delete(Wraps.<ProductDefinitionRes>lbQ().eq(ProductDefinitionRes::getProductId, productId));
        final Set<Long> resIdList = req.getResIdList();
        if (CollectionUtil.isEmpty(resIdList)) {
            return;
        }
        final List<ProductDefinitionRes> resList = resIdList.stream().filter(Objects::nonNull)
                .map(resId -> ProductDefinitionRes.builder().resId(resId).productId(productId).build())
                .collect(toList());
        productDefResMapper.insertBatch(resList);
    }

    @Override
    public RolePermissionResp findPermissions(Long id) {
        final List<VisibleResourceResp> buttons = BeanUtilPlus.toBeans(resourceMapper.selectList(), VisibleResourceResp.class);
        final List<Long> roleRes = Optional.of(this.productDefResMapper.selectList(ProductDefinitionRes::getProductId, id))
                .orElseGet(List::of).stream().map(ProductDefinitionRes::getResId).toList();
        return RolePermissionResp.builder().menuIdList(roleRes)
                .buttonIdList(buttons.stream().map(VisibleResourceResp::getId).collect(toList()))
                .build();
    }

    @Override
    @DSTransactional(rollbackFor = Exception.class)
    public void delete(Long id) {
        ProductDefinition definition = this.baseMapper.selectById(id);
        if (definition == null) {
            throw CheckedException.notFound("产品不存在,删除失败");
        }
        if (definition.getStatus() != null && definition.getStatus()) {
            throw CheckedException.notFound("产品已启用,删除失败");
        }
        Long count = this.productSubscriptionMapper.selectCount(ProductSubscription::getProductId, id);
        if (count != null && count > 0) {
            throw CheckedException.badRequest("产品已被订阅,删除失败");
        }

    }

}
