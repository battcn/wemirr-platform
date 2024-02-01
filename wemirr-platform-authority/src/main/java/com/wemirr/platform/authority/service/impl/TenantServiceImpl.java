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

import cn.hutool.core.collection.CollUtil;
import cn.hutool.extra.spring.SpringUtil;
import com.baomidou.dynamic.datasource.annotation.DSTransactional;
import com.wemirr.framework.commons.exception.CheckedException;
import com.wemirr.framework.db.dynamic.TenantDynamicDataSourceHandler;
import com.wemirr.framework.db.dynamic.core.EventAction;
import com.wemirr.framework.db.mybatisplus.ext.SuperServiceImpl;
import com.wemirr.framework.db.mybatisplus.wrap.Wraps;
import com.wemirr.framework.db.properties.DatabaseProperties;
import com.wemirr.framework.db.properties.MultiTenantType;
import com.wemirr.platform.authority.domain.baseinfo.entity.Org;
import com.wemirr.platform.authority.domain.baseinfo.entity.Role;
import com.wemirr.platform.authority.domain.baseinfo.entity.User;
import com.wemirr.platform.authority.domain.baseinfo.entity.UserRole;
import com.wemirr.platform.authority.domain.common.entity.AreaEntity;
import com.wemirr.platform.authority.domain.tenant.entity.Tenant;
import com.wemirr.platform.authority.domain.tenant.entity.TenantConfig;
import com.wemirr.platform.authority.domain.tenant.req.TenantConfigReq;
import com.wemirr.platform.authority.repository.baseinfo.OrgMapper;
import com.wemirr.platform.authority.repository.baseinfo.RoleMapper;
import com.wemirr.platform.authority.repository.baseinfo.UserMapper;
import com.wemirr.platform.authority.repository.baseinfo.UserRoleMapper;
import com.wemirr.platform.authority.repository.common.AreaMapper;
import com.wemirr.platform.authority.repository.tenant.TenantConfigMapper;
import com.wemirr.platform.authority.repository.tenant.TenantMapper;
import com.wemirr.platform.authority.service.TenantDatasourceService;
import com.wemirr.platform.authority.service.TenantService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * @author Levin
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class TenantServiceImpl extends SuperServiceImpl<TenantMapper, Tenant> implements TenantService {
    
    private final AreaMapper areaMapper;
    private final RoleMapper roleMapper;
    private final UserRoleMapper userRoleMapper;
    private final TenantConfigMapper tenantConfigMapper;
    private final TenantDatasourceService dynamicDatasourceService;
    private final DatabaseProperties properties;
    private final UserMapper userMapper;
    private final OrgMapper orgMapper;
    private final PasswordEncoder passwordEncoder;
    
    @Override
    public void saveOrUpdateTenant(Tenant tenant) {
        tenant.setProvinceName(getNameById(tenant.getProvinceId()));
        tenant.setCityName(getNameById(tenant.getCityId()));
        tenant.setDistrictName(getNameById(tenant.getDistrictId()));
        if (tenant.getId() != null) {
            baseMapper.updateById(tenant);
            return;
        }
        baseMapper.insert(tenant);
    }
    
    private String getNameById(Long id) {
        if (Objects.isNull(id)) {
            return null;
        }
        final AreaEntity areaEntity = areaMapper.selectById(id);
        if (Objects.isNull(areaEntity)) {
            return null;
        }
        return areaEntity.getName();
    }
    
    @Override
    @DSTransactional(rollbackFor = Exception.class)
    public void tenantConfig(Long tenantId, TenantConfigReq req) {
        final Tenant tenant = Optional.ofNullable(this.baseMapper.selectById(tenantId))
                .orElseThrow(() -> CheckedException.notFound("租户不存在"));
        if (tenant.getLocked()) {
            throw CheckedException.badRequest("租户已被禁用");
        }
        if (StringUtils.equals(tenant.getCode(), properties.getMultiTenant().getSuperTenantCode())) {
            throw CheckedException.badRequest("超级租户,禁止操作");
        }
        TenantConfig tenantConfig = this.tenantConfigMapper.selectOne(TenantConfig::getTenantId, tenantId);
        if (tenantConfig == null) {
            tenantConfig = TenantConfig.builder().tenantId(tenantId).datasourceId(req.getDatasourceId()).build();
            tenantConfigMapper.insert(tenantConfig);
        } else {
            tenantConfig = TenantConfig.builder().id(tenantConfig.getId()).tenantId(tenantId).datasourceId(req.getDatasourceId()).build();
            tenantConfigMapper.updateById(tenantConfig);
        }
        // 先创建
        dynamicDatasourceService.publishEvent(EventAction.INIT, tenantConfig.getTenantId());
        if (!req.isLazy()) {
            initSqlScript(tenantId);
        }
    }
    
    @Override
    @DSTransactional(rollbackFor = Exception.class)
    public void initSqlScript(Long id) {
        final Tenant tenant = Optional.ofNullable(this.baseMapper.selectById(id)).orElseThrow(() -> CheckedException.notFound("租户信息不存在"));
        if (tenant.getLocked()) {
            throw CheckedException.badRequest("租户已被禁用");
        }
        final DatabaseProperties.MultiTenant multiTenant = properties.getMultiTenant();
        if (StringUtils.equals(tenant.getCode(), multiTenant.getSuperTenantCode())) {
            throw CheckedException.badRequest("超级租户,禁止操作");
        }
        if (multiTenant.getType() == MultiTenantType.COLUMN) {
            final Role role = Optional.ofNullable(roleMapper.selectOne(Wraps.<Role>lbQ()
                    .eq(Role::getCode, "TENANT-ADMIN"))).orElseThrow(() -> CheckedException.notFound("内置租户管理员角色不存在"));
            final List<User> users = this.userMapper.selectByTenantId(tenant.getId());
            if (CollUtil.isNotEmpty(users)) {
                final List<Long> userIdList = users.stream().map(User::getId).distinct().collect(Collectors.toList());
                log.warn("开始清除租户 - {} 的系统数据,危险动作", tenant.getName());
                if (CollUtil.isNotEmpty(userIdList)) {
                    // 等于0全表会删。
                    this.userRoleMapper.delete(Wraps.<UserRole>lbQ().in(UserRole::getUserId, userIdList));
                }
                this.userMapper.deleteByTenantId(tenant.getId());
                this.roleMapper.deleteByTenantId(tenant.getId());
                this.orgMapper.deleteByTenantId(tenant.getId());
                log.warn("开始初始化租户 - {} 的系统数据,危险动作", tenant.getName());
            }
            Org org = new Org();
            org.setLabel(tenant.getName());
            org.setTenantId(tenant.getId());
            org.setStatus(true);
            org.setDescription("不可删除不可修改");
            org.setParentId(0L);
            org.setSequence(0);
            this.orgMapper.insert(org);
            User record = new User();
            record.setUsername("admin");
            record.setPassword(passwordEncoder.encode("123456"));
            record.setTenantId(id);
            record.setNickName(tenant.getContactPerson());
            record.setMobile(tenant.getContactPhone());
            record.setStatus(true);
            this.userMapper.insert(record);
            this.userRoleMapper.insert(UserRole.builder().userId(record.getId()).roleId(role.getId()).build());
            
        } else if (multiTenant.getType() == MultiTenantType.DATASOURCE) {
            TenantDynamicDataSourceHandler tenantDynamicDataSourceHandler = SpringUtil.getBean(TenantDynamicDataSourceHandler.class);
            tenantDynamicDataSourceHandler.initSqlScript(tenant.getId(), tenant.getCode());
        }
    }
}
