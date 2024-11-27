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

package com.wemirr.platform.iam.system.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.dynamic.datasource.annotation.DSTransactional;
import com.baomidou.dynamic.datasource.toolkit.DynamicDataSourceContextHolder;
import com.wemirr.framework.commons.BeanUtilPlus;
import com.wemirr.framework.commons.exception.CheckedException;
import com.wemirr.framework.db.mybatisplus.ext.SuperServiceImpl;
import com.wemirr.framework.db.mybatisplus.wrap.Wraps;
import com.wemirr.framework.db.properties.DatabaseProperties;
import com.wemirr.platform.iam.system.domain.dto.req.ResourceQueryReq;
import com.wemirr.platform.iam.system.domain.dto.req.ResourceSaveReq;
import com.wemirr.platform.iam.system.domain.dto.resp.VueRouter;
import com.wemirr.platform.iam.system.domain.entity.Resource;
import com.wemirr.platform.iam.system.domain.entity.Role;
import com.wemirr.platform.iam.system.domain.entity.RoleRes;
import com.wemirr.platform.iam.system.domain.enums.ResourceType;
import com.wemirr.platform.iam.system.repository.ResourceMapper;
import com.wemirr.platform.iam.system.repository.RoleMapper;
import com.wemirr.platform.iam.system.repository.RoleResMapper;
import com.wemirr.platform.iam.system.repository.UserMapper;
import com.wemirr.platform.iam.system.service.ResourceService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

/**
 * <p>
 * 业务实现类
 * 资源
 * </p>
 *
 * @author Levin
 * @since 2019-07-03
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class ResourceServiceImpl extends SuperServiceImpl<ResourceMapper, Resource> implements ResourceService {

    public static final String DEFAULT_PATH = "/system/development/release/tenant_%s";
    public static final String DEFAULT_COMPONENT = "/system/development/build/standard";

    private static final String SPEL = "/";
    private final DatabaseProperties databaseProperties;
    private final RoleMapper roleMapper;
    private final UserMapper userMapper;
    private final RoleResMapper roleResMapper;

    @Override
    public List<VueRouter> findVisibleResource(ResourceQueryReq req) {
        // 查询租户数据源
        List<Long> resIdList = this.userMapper.selectResByUserId(req.getUserId());
        DynamicDataSourceContextHolder.poll();
        // 解决租户越权行为,菜单数据直接从主库查询,减少数据分发次数
        DynamicDataSourceContextHolder.push(databaseProperties.getMultiTenant().getDefaultDsName());
        List<Resource> list = this.baseMapper.selectList(Wraps.<Resource>lbQ()
                .and(lb -> lb.eq(Resource::getGlobal, true).or(CollUtil.isNotEmpty(resIdList), xx -> xx.in(Resource::getId, resIdList)))
                .eq(Resource::getParentId, req.getParentId()).eq(Resource::getType, req.getType()));
        DynamicDataSourceContextHolder.poll();
        return BeanUtilPlus.toBeans(list, VueRouter.class);
    }

    @Override
    public List<String> selectPermissionByUserId(Long userId) {
        // 查询租户数据源
        List<Long> resIdList = this.userMapper.selectResByUserId(userId);
        DynamicDataSourceContextHolder.poll();
        // 解决租户越权行为,菜单数据直接从主库查询,减少数据分发次数
        DynamicDataSourceContextHolder.push(databaseProperties.getMultiTenant().getDefaultDsName());
        List<Resource> list = this.baseMapper.selectList(Wraps.<Resource>lbQ().select(Resource::getPermission).in(Resource::getId, resIdList));
        DynamicDataSourceContextHolder.poll();
        return list.stream().filter(Objects::nonNull).map(Resource::getPermission).filter(StrUtil::isNotBlank).toList();
    }

    @Override
    @DSTransactional(rollbackFor = Exception.class)
    public void add(ResourceSaveReq req) {
        final Resource resource = BeanUtil.toBean(req, Resource.class);
        if (ResourceType.MENU == resource.getType()) {
            if (!StringUtils.startsWith(resource.getPath(), SPEL)) {
                resource.setPath(SPEL + resource.getPath());
            }
        }
        this.baseMapper.insert(resource);
        final List<Role> roles = this.roleMapper.selectList(Wraps.<Role>lbQ().eq(Role::getSuperRole, true)
                .eq(Role::getStatus, true));
        if (CollUtil.isEmpty(roles)) {
            return;
        }
        // 给管理员角色挂载权限
        final List<RoleRes> roleResList = roles.stream()
                .map(role -> RoleRes.builder().roleId(role.getId()).resId(resource.getId()).build())
                .toList();
        roleResMapper.insertBatchSomeColumn(roleResList);
    }

    @Override
    public void modify(Long id, ResourceSaveReq req) {
        final Resource resource = BeanUtilPlus.toBean(id, req, Resource.class);
        this.baseMapper.updateById(resource);
    }

    @Override
    @DSTransactional(rollbackFor = Exception.class)
    public void delete(Long id) {
        final Long count = this.baseMapper.selectCount(Resource::getParentId, id);
        if (count != null && count > 0) {
            throw CheckedException.badRequest("当前节点存在子节点,请先移除子节点");
        }
        // 删除菜单和按钮
        this.baseMapper.deleteById(id);
        // 删除对应的资源权限
        this.roleResMapper.delete(Wraps.<RoleRes>lbQ().eq(RoleRes::getResId, id));
    }
}
