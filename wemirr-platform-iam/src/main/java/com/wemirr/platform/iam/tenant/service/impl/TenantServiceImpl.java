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
import com.wemirr.framework.commons.BeanUtilPlus;
import com.wemirr.framework.commons.entity.Entity;
import com.wemirr.framework.commons.exception.CheckedException;
import com.wemirr.framework.commons.security.AuthenticationContext;
import com.wemirr.framework.db.dynamic.DynamicDataSourceHandler;
import com.wemirr.framework.db.dynamic.core.EventAction;
import com.wemirr.framework.db.mybatisplus.ext.SuperServiceImpl;
import com.wemirr.framework.db.mybatisplus.wrap.Wraps;
import com.wemirr.framework.db.properties.DatabaseProperties;
import com.wemirr.framework.db.properties.MultiTenantType;
import com.wemirr.platform.iam.base.domain.entity.AreaEntity;
import com.wemirr.platform.iam.base.domain.entity.SysDict;
import com.wemirr.platform.iam.base.domain.entity.SysDictItem;
import com.wemirr.platform.iam.base.repository.AreaMapper;
import com.wemirr.platform.iam.base.repository.SysDictItemMapper;
import com.wemirr.platform.iam.base.repository.SysDictMapper;
import com.wemirr.platform.iam.system.domain.entity.Org;
import com.wemirr.platform.iam.system.domain.entity.Role;
import com.wemirr.platform.iam.system.domain.entity.User;
import com.wemirr.platform.iam.system.domain.entity.UserRole;
import com.wemirr.platform.iam.system.repository.OrgMapper;
import com.wemirr.platform.iam.system.repository.RoleMapper;
import com.wemirr.platform.iam.system.repository.UserMapper;
import com.wemirr.platform.iam.system.repository.UserRoleMapper;
import com.wemirr.platform.iam.tenant.domain.dto.req.TenantConfigReq;
import com.wemirr.platform.iam.tenant.domain.dto.req.TenantSaveReq;
import com.wemirr.platform.iam.tenant.domain.dto.req.TenantSettingReq;
import com.wemirr.platform.iam.tenant.domain.dto.resp.TenantSettingResp;
import com.wemirr.platform.iam.tenant.domain.entity.Tenant;
import com.wemirr.platform.iam.tenant.domain.entity.TenantDict;
import com.wemirr.platform.iam.tenant.domain.entity.TenantDictItem;
import com.wemirr.platform.iam.tenant.domain.entity.TenantSetting;
import com.wemirr.platform.iam.tenant.repository.TenantDictItemMapper;
import com.wemirr.platform.iam.tenant.repository.TenantDictMapper;
import com.wemirr.platform.iam.tenant.repository.TenantMapper;
import com.wemirr.platform.iam.tenant.repository.TenantSettingMapper;
import com.wemirr.platform.iam.tenant.service.DbSettingService;
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
    private final TenantSettingMapper tenantSettingMapper;
    private final AreaMapper areaMapper;
    private final RoleMapper roleMapper;
    private final UserRoleMapper userRoleMapper;
    private final DbSettingService dbSettingService;
    private final DatabaseProperties properties;
    private final UserMapper userMapper;
    private final OrgMapper orgMapper;
    private final SysDictMapper dictMapper;
    private final SysDictItemMapper dictItemMapper;
    private final TenantDictMapper tenantDictMapper;
    private final TenantDictItemMapper tenantDictItemMapper;

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
//        String tenantCode = RandomUtil.randomNumbers(4);
        Long nameCount = this.baseMapper.selectCount(Tenant::getName, req.getName());
        if (nameCount > 0) {
            throw CheckedException.badRequest("租户名称重复");
        }
        Long codeCount = this.baseMapper.selectCount(Tenant::getCode, req.getCode());
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
//        final Tenant tenant = Optional.ofNullable(this.baseMapper.selectById(tenantId))
//                .orElseThrow(() -> CheckedException.notFound("租户不存在"));
//        if (!tenant.getStatus()) {
//            throw CheckedException.badRequest("租户未启用");
//        }
//        if (StringUtils.equals(tenant.getCode(), properties.getMultiTenant().getSuperTenantCode())) {
//            throw CheckedException.badRequest("超级租户,禁止操作");
//        }
//        TenantConfig tenantConfig = this.tenantConfigMapper.selectOne(TenantConfig::getTenantId, tenantId);
//        if (tenantConfig == null) {
//            tenantConfigMapper.insert(TenantConfig.builder().tenantId(tenantId).datasourceId(req.getDatasourceId()).build());
//        } else {
//            tenantConfigMapper.updateById(TenantConfig.builder().id(tenantConfig.getId()).datasourceId(req.getDatasourceId()).build());
//        }
//        // 先创建
//        dynamicDatasourceService.publishEvent(EventAction.INIT, tenant.getId());
//        if (!req.isLazy()) {
//            initSqlScript(tenantId);
//        }
    }

    @Override
    @DSTransactional(rollbackFor = Exception.class)
    public void initSqlScript(Long id) {
        final Tenant tenant = Optional.ofNullable(this.baseMapper.selectById(id)).orElseThrow(() -> CheckedException.notFound("租户信息不存在"));
        if (!tenant.getStatus()) {
            throw CheckedException.badRequest("租户未启用");
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
//            record.setPassword(passwordEncoder.encode("123456"));
            record.setTenantId(id);
            record.setNickName(tenant.getContactPerson());
            record.setMobile(tenant.getContactPhone());
            record.setStatus(true);
            this.userMapper.insert(record);
            this.userRoleMapper.insert(UserRole.builder().userId(record.getId()).roleId(role.getId()).build());

        } else if (multiTenant.getType() == MultiTenantType.DATASOURCE) {
            DynamicDataSourceHandler dynamicDataSourceHandler = SpringUtil.getBean(DynamicDataSourceHandler.class);
            dynamicDataSourceHandler.initSqlScript(tenant.getCode(), Map.of("tenant_id", tenant.getId() + "", "tenant_name", tenant.getName()));
        }
    }

    @Override
    @DSTransactional(rollbackFor = Exception.class)
    public void refreshTenantDict(Long tenantId) {
        // 查询超管 所有字典数据
        // TODO 默认查询租户ID = 1 的 , 后续改造成配置文件读取的 超管租户ID
        List<SysDict> dictList = dictMapper.selectList(SysDict::getType, 1);
        if (CollUtil.isEmpty(dictList)) {
            log.warn("未查询到有效的数据字典");
            return;
        }
        List<TenantDict> dictTypeList = dictMapper.selectList(SysDict::getType, 1)
                .stream()
                .map(x -> {
                    TenantDict dict = BeanUtil.toBean(x, TenantDict.class);
                    dict.setId(null);
                    dict.setLastModifiedTime(Instant.now());
                    dict.setLastModifiedBy(context.userId());
                    dict.setLastModifiedName(context.nickName());
                    return dict;
                }).toList();
        List<Long> dictIdList = dictList.stream().map(Entity::getId).toList();
        List<TenantDictItem> dictDataList = dictItemMapper.selectList(Wraps.<SysDictItem>lbQ().in(SysDictItem::getDictId, dictIdList))
                .stream()
                .map(x -> {
                    TenantDictItem item = BeanUtil.toBean(x, TenantDictItem.class);
                    item.setId(null);
                    item.setTenantId(tenantId);
                    item.setLastModifiedTime(Instant.now());
                    item.setLastModifiedBy(context.userId());
                    item.setLastModifiedName(context.nickName());
                    return item;
                }).toList();
        // TODO 暂未实现动态数据源刷新 => 如果是动态数据源需要手动切换一下DB
        // 理论上如果是管理员刷新租户字典那么需要给租户的数据给删除然后重新添加
        this.tenantDictMapper.delete(Wraps.<TenantDict>lbQ().eq(TenantDict::getTenantId, tenantId));
        this.tenantDictItemMapper.delete(Wraps.<TenantDictItem>lbQ().eq(TenantDictItem::getTenantId, tenantId));
        // 将新数据写入到租户字典表中
        this.tenantDictMapper.insertBatchSomeColumn(dictTypeList);
        this.tenantDictItemMapper.insertBatchSomeColumn(dictDataList);
    }

    @Override
    public TenantSettingResp settingInfo(Long tenantId) {
        TenantSetting setting = this.tenantSettingMapper.selectOne(TenantSetting::getTenantId, tenantId);
        return BeanUtil.toBean(setting, TenantSettingResp.class);
    }

    @Override
    @DSTransactional(rollbackFor = Exception.class)
    public void saveSetting(Long tenantId, TenantSettingReq req) {
        final Tenant tenant = Optional.ofNullable(this.baseMapper.selectById(tenantId)).orElseThrow(() -> CheckedException.notFound("租户不存在"));
        if (!tenant.getStatus()) {
            throw CheckedException.badRequest("租户未启用");
        }
        if (StringUtils.equals(tenant.getCode(), properties.getMultiTenant().getSuperTenantCode())) {
            throw CheckedException.badRequest("超级租户,禁止操作");
        }
        String siteUrl = req.getSiteUrl();
        if (StrUtil.isNotBlank(siteUrl)) {
            Long count = this.tenantSettingMapper.selectCount(Wraps.<TenantSetting>lbQ()
                    .ne(TenantSetting::getTenantId, tenantId).eq(TenantSetting::getSiteUrl, req.getSiteUrl()));
            if (count != null && count > 0) {
                throw CheckedException.badRequest("该租户站点已存在");
            }
        }
        TenantSetting setting = this.tenantSettingMapper.selectOne(TenantSetting::getTenantId, tenantId);
        var bean = BeanUtil.toBean(req, TenantSetting.class);
        bean.setTenantId(tenantId);
        if (setting == null) {
            this.tenantSettingMapper.insert(bean);
        } else {
            bean.setId(setting.getId());
            this.tenantSettingMapper.updateById(bean);
        }
        dbSettingService.publishEvent(EventAction.INIT, tenantId);
    }
}
