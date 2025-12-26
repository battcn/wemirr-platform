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
import cn.hutool.core.util.StrUtil;
import cn.hutool.extra.spring.SpringUtil;
import com.baomidou.dynamic.datasource.annotation.DSTransactional;
import com.google.common.collect.Maps;
import com.wemirr.framework.commons.BeanUtilPlus;
import com.wemirr.framework.commons.JacksonUtils;
import com.wemirr.framework.commons.entity.Entity;
import com.wemirr.framework.commons.exception.CheckedException;
import com.wemirr.framework.commons.security.AuthenticationContext;
import com.wemirr.framework.db.dynamic.DynamicDataSourceHandler;
import com.wemirr.framework.db.dynamic.core.DynamicDataSourceEvent;
import com.wemirr.framework.db.dynamic.core.EventAction;
import com.wemirr.framework.db.mybatisplus.ext.SuperServiceImpl;
import com.wemirr.framework.db.mybatisplus.wrap.Wraps;
import com.wemirr.framework.db.properties.DatabaseProperties;
import com.wemirr.framework.db.properties.MultiTenantType;
import com.wemirr.framework.db.utils.TenantHelper;
import com.wemirr.framework.security.utils.PasswordEncoderHelper;
import com.wemirr.platform.iam.base.domain.entity.AreaEntity;
import com.wemirr.platform.iam.base.domain.entity.SysDict;
import com.wemirr.platform.iam.base.repository.AreaMapper;
import com.wemirr.platform.iam.base.repository.SysDictMapper;
import com.wemirr.platform.iam.system.domain.entity.*;
import com.wemirr.platform.iam.system.repository.*;
import com.wemirr.platform.iam.tenant.domain.dto.req.TenantConfigReq;
import com.wemirr.platform.iam.tenant.domain.dto.req.TenantDbBindingSaveReq;
import com.wemirr.platform.iam.tenant.domain.dto.req.TenantSaveReq;
import com.wemirr.platform.iam.tenant.domain.dto.req.TenantSettingReq;
import com.wemirr.platform.iam.tenant.domain.dto.resp.DbInstancePageResp;
import com.wemirr.platform.iam.tenant.domain.dto.resp.TenantDbBindingResp;
import com.wemirr.platform.iam.tenant.domain.dto.resp.TenantSettingResp;
import com.wemirr.platform.iam.tenant.domain.entity.Tenant;
import com.wemirr.platform.iam.tenant.domain.entity.TenantDbBinding;
import com.wemirr.platform.iam.tenant.domain.entity.TenantDict;
import com.wemirr.platform.iam.tenant.repository.DbInstanceMapper;
import com.wemirr.platform.iam.tenant.repository.TenantDbBindingMapper;
import com.wemirr.platform.iam.tenant.repository.TenantDictMapper;
import com.wemirr.platform.iam.tenant.repository.TenantMapper;
import com.wemirr.platform.iam.tenant.service.DbInstanceService;
import com.wemirr.platform.iam.tenant.service.TenantService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.List;
import java.util.Map;
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

    private final AuthenticationContext context;
    private final TenantDbBindingMapper tenantDbBindingMapper;
    private final AreaMapper areaMapper;
    private final RoleMapper roleMapper;
    private final RoleResMapper roleResMapper;
    private final DbInstanceMapper dbInstanceMapper;
    private final UserRoleMapper userRoleMapper;
    private final DbInstanceService dbInstanceService;
    private final DatabaseProperties properties;
    private final UserMapper userMapper;
    private final OrgMapper orgMapper;
    private final SysDictMapper dictMapper;
    private final TenantDictMapper tenantDictMapper;

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
    public void create(TenantSaveReq req) {
        // 随机生成租户编码
        long nameCount = this.baseMapper.selectCount(Tenant::getName, req.getName());
        if (nameCount > 0) {
            throw CheckedException.badRequest("租户名称重复");
        }
        long codeCount = this.baseMapper.selectCount(Tenant::getCode, req.getCode());
        if (codeCount > 0) {
            throw CheckedException.badRequest("租户编码重复");
        }
        Tenant tenant = BeanUtil.toBean(req, Tenant.class);
        tenant.setProvinceName(getNameById(tenant.getProvinceId()));
        tenant.setCityName(getNameById(tenant.getCityId()));
        tenant.setDistrictName(getNameById(tenant.getDistrictId()));
        this.baseMapper.insert(tenant);
    }

    @Override
    @DSTransactional(rollbackFor = Exception.class)
    public void modify(Long id, TenantSaveReq req) {
        final Tenant tenant = Optional.ofNullable(this.baseMapper.selectById(id))
                .orElseThrow(() -> CheckedException.notFound("租户不存在"));
        Long nameCount = this.baseMapper.selectCount(Wraps.<Tenant>lbQ().eq(Tenant::getName, req.getName()).ne(Tenant::getId, id));
        if (nameCount > 0) {
            throw CheckedException.badRequest("租户名称重复");
        }
        Long codeCount = this.baseMapper.selectCount(Wraps.<Tenant>lbQ().eq(Tenant::getCode, req.getCode()).ne(Tenant::getId, id));
        if (codeCount > 0) {
            throw CheckedException.badRequest("租户编码重复");
        }
        Tenant bean = BeanUtilPlus.toBean(id, req, Tenant.class);
        bean.setProvinceName(getNameById(tenant.getProvinceId()));
        bean.setCityName(getNameById(tenant.getCityId()));
        bean.setDistrictName(getNameById(tenant.getDistrictId()));
        this.baseMapper.updateById(bean);
    }

    @Override
    @DSTransactional(rollbackFor = Exception.class)
    public void tenantConfig(Long tenantId, TenantConfigReq req) {
    }

    @Override
    @DSTransactional(rollbackFor = Exception.class)
    public void initSqlScript(Long id) {
        var tenant = Optional.ofNullable(this.baseMapper.selectById(id)).orElseThrow(() -> CheckedException.notFound("租户信息不存在"));
        if (!tenant.getStatus()) {
            throw CheckedException.badRequest("租户未启用");
        }
        final DatabaseProperties.MultiTenant multiTenant = properties.getMultiTenant();
        if (isSuperTenant(tenant, multiTenant)) {
            throw CheckedException.badRequest("超级租户,禁止操作");
        }
        if (multiTenant.getType() == MultiTenantType.COLUMN) {
            initColumnTypeTenant(tenant);
        } else if (multiTenant.getType() == MultiTenantType.DATASOURCE) {
            initDatasourceTypeTenant(tenant);
        }
    }

    private boolean isSuperTenant(Tenant tenant, DatabaseProperties.MultiTenant multiTenant) {
        return StringUtils.equals(tenant.getCode(), multiTenant.getSuperTenantCode());
    }

    private Role selectTenantAdminRole() {
        return Optional.ofNullable(roleMapper.selectOne(Wraps.<Role>lbQ()
                        .eq(Role::getCode, "TENANT-ADMIN")))
                .orElseThrow(() -> CheckedException.notFound("内置租户管理员角色不存在"));
    }

    private void clearTenantData(Tenant tenant, List<User> users) {
        final List<Long> userIdList = users.stream().map(User::getId).distinct().collect(Collectors.toList());
        log.warn("开始清除租户 - {} 的系统数据,危险动作", tenant.getName());
        if (CollUtil.isNotEmpty(userIdList)) {
            // 等于0全表会删。
            this.userRoleMapper.delete(Wraps.<UserRole>lbQ().in(UserRole::getUserId, userIdList));
        }
        TenantHelper.runWithIgnoreStrategy(() -> {
            this.userMapper.delete(User::getTenantId, tenant.getId());
            this.roleMapper.delete(Role::getTenantId, tenant.getId());
            this.orgMapper.delete(Org::getTenantId, tenant.getId());
        });

    }


    private void initColumnTypeTenant(Tenant tenant) {
        final Role role = selectTenantAdminRole();
        var users = TenantHelper.withIgnoreStrategy(() -> this.userMapper.selectList(User::getTenantId, tenant.getId()));
        if (CollUtil.isNotEmpty(users)) {
            clearTenantData(tenant, users);
        }
        initializeTenantData(tenant, role);
    }

    private void initDatasourceTypeTenant(Tenant tenant) {
        DynamicDataSourceHandler dynamicDataSourceHandler = SpringUtil.getBean(DynamicDataSourceHandler.class);
        Map<String, Object> variables = Maps.newHashMap();
        variables.put("tenant_id", tenant.getId());
        variables.put("tenant_name", tenant.getName());
        final DbInstancePageResp dbInstance = this.dbInstanceMapper.getTenantDynamicDatasourceByTenantId(tenant.getId());
        log.debug("dbInstance => {}", JacksonUtils.toJson(dbInstance));
        final DynamicDataSourceEvent event = BeanUtil.toBean(dbInstance, DynamicDataSourceEvent.class);
        dynamicDataSourceHandler.initSqlScript(event, variables);
        final Role role = selectTenantAdminRole();
        List<RoleRes> list = this.roleResMapper.selectList(RoleRes::getRoleId, role.getId());
        TenantHelper.executeWithTenantDb(tenant.getCode(), () -> {
            var users = TenantHelper.withIgnoreStrategy(() -> this.userMapper.selectList(User::getTenantId, tenant.getId()));
            if (CollUtil.isNotEmpty(users)) {
                clearTenantData(tenant, users);
            }
            initializeTenantData(tenant, role);
            if (CollUtil.isNotEmpty(list)) {
                this.roleResMapper.insertBatchSomeColumn(list);
            }
            return null;
        });
    }


    private void initializeTenantData(Tenant tenant, Role role) {
        log.warn("开始初始化租户 - {} 的系统数据,危险动作", tenant.getName());
        Org org = new Org();
        org.setLabel(tenant.getName());
        org.setTenantId(tenant.getId());
        org.setStatus(true);
        org.setDescription("不可删除不可修改");
        org.setParentId(0L);
        org.setSequence(0);
        this.orgMapper.insert(org);

        User user = new User();
        String contactPhone = tenant.getContactPhone();
        user.setUsername(contactPhone);
        user.setEmail(tenant.getEmail());
        user.setAvatar(tenant.getLogo());
        user.setPassword(PasswordEncoderHelper.encode("123456"));
        user.setTenantId(tenant.getId());
        user.setNickName(tenant.getContactPerson());
        user.setMobile(contactPhone);
        user.setStatus(true);
        this.userMapper.insert(user);
        this.userRoleMapper.insert(UserRole.builder().userId(user.getId()).roleId(role.getId()).build());
    }

    void validTenant(Long tenantId) {
        final Tenant tenant = Optional.ofNullable(this.baseMapper.selectById(tenantId)).orElseThrow(() -> CheckedException.notFound("租户不存在"));
        if (!tenant.getStatus()) {
            throw CheckedException.badRequest("租户未启用");
        }
        if (StringUtils.equals(tenant.getCode(), properties.getMultiTenant().getSuperTenantCode())) {
            throw CheckedException.badRequest("超级租户,禁止操作");
        }
    }

    @Override
    @DSTransactional(rollbackFor = Exception.class)
    public void refreshTenantDict(Long tenantId) {
        validTenant(tenantId);
        // 查询超管 所有字典数据
        List<SysDict> dictList = TenantHelper.executeWithMaster(() -> dictMapper.selectList(SysDict::getType, 1));
        if (CollUtil.isEmpty(dictList)) {
            log.warn("未查询到有效的数据字典");
            return;
        }
        List<TenantDict> dictTypeList = dictList.stream().map(x -> {
            TenantDict dict = BeanUtil.toBean(x, TenantDict.class);
            dict.setId(null);
            dict.setLastModifyTime(Instant.now());
            dict.setLastModifyBy(context.userId());
            dict.setLastModifyName(context.nickName());
            return dict;
        }).toList();
        List<Long> dictIdList = dictList.stream().map(Entity::getId).toList();
        // TODO 重构优化逻辑
    }

    @Override
    public TenantSettingResp settingInfo(Long tenantId) {
        TenantDbBinding setting = this.tenantDbBindingMapper.selectOne(TenantDbBinding::getTenantId, tenantId);
        return BeanUtil.toBean(setting, TenantSettingResp.class);
    }

    @Override
    @DSTransactional(rollbackFor = Exception.class)
    public void saveSetting(Long tenantId, TenantSettingReq req) {
        validTenant(tenantId);
        String siteUrl = req.getSiteUrl();
        if (StrUtil.isNotBlank(siteUrl)) {
//            Long count = this.tenantSettingMapper.selectCount(Wraps.<TenantDb>lbQ()
//                    .ne(TenantDb::getTenantId, tenantId).eq(TenantDb::getSiteUrl, req.getSiteUrl()));
//            if (count != null && count > 0) {
//                throw CheckedException.badRequest("该租户站点已存在");
//            }
        }
        TenantDbBinding setting = this.tenantDbBindingMapper.selectOne(TenantDbBinding::getTenantId, tenantId);
        var bean = BeanUtil.toBean(req, TenantDbBinding.class);
        bean.setTenantId(tenantId);
        if (setting == null) {
            this.tenantDbBindingMapper.insert(bean);
        } else {
            bean.setId(setting.getId());
            this.tenantDbBindingMapper.updateById(bean);
        }
        dbInstanceService.publishEvent(EventAction.INIT, tenantId);
    }

    @Override
    public TenantDbBindingResp dbRef(Long id) {
        var bean = this.tenantDbBindingMapper.selectOne(TenantDbBinding::getTenantId, id);
        return BeanUtil.toBean(bean, TenantDbBindingResp.class);
    }

    @Override
    public void dbBinding(Long id, TenantDbBindingSaveReq req) {
        var entity = this.tenantDbBindingMapper.selectOne(TenantDbBinding::getTenantId, id);
        if (entity == null) {
            var bean = BeanUtilPlus.toBean(req, TenantDbBinding.class);
            this.tenantDbBindingMapper.insert(bean);
        } else {
            var bean = BeanUtilPlus.toBean(req, TenantDbBinding.class);
            bean.setId(entity.getId());
            this.tenantDbBindingMapper.updateById(bean);
        }
    }
}
