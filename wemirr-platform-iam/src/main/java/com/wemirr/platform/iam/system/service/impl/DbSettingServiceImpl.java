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
import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.extra.spring.SpringUtil;
import com.alibaba.fastjson2.JSON;
import com.baomidou.dynamic.datasource.annotation.DSTransactional;
import com.wemirr.framework.commons.BeanUtilPlus;
import com.wemirr.framework.commons.exception.CheckedException;
import com.wemirr.framework.db.dynamic.core.DynamicDatasourceEvent;
import com.wemirr.framework.db.dynamic.core.DynamicDatasourceEventPublish;
import com.wemirr.framework.db.dynamic.core.EventAction;
import com.wemirr.framework.db.mybatisplus.ext.SuperServiceImpl;
import com.wemirr.framework.db.mybatisplus.wrap.Wraps;
import com.wemirr.framework.db.properties.DatabaseProperties;
import com.wemirr.framework.db.properties.MultiTenantType;
import com.wemirr.platform.iam.tenant.domain.dto.req.DbSettingSaveReq;
import com.wemirr.platform.iam.tenant.domain.dto.resp.DbSettingPageResp;
import com.wemirr.platform.iam.tenant.domain.entity.DbSetting;
import com.wemirr.platform.iam.tenant.repository.DbSettingMapper;
import com.wemirr.platform.iam.tenant.service.DbSettingService;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

/**
 * @author Levin
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class DbSettingServiceImpl extends SuperServiceImpl<DbSettingMapper, DbSetting> implements DbSettingService {

    private final DatabaseProperties databaseProperties;
    private final ApplicationContext applicationContext;

    @Override
    public List<DbSettingPageResp> selectTenantDynamicDatasource() {
        return this.baseMapper.selectTenantDbById(null);
    }

    @Override
    public void ping(Long id) {
        log.debug("查询结果 - {}", JSON.toJSONString(""));
    }

    @PostConstruct
    public void init() {
        final List<DbSettingPageResp> dataSourceList = this.baseMapper.selectTenantDbById(null);
        if (CollectionUtil.isEmpty(dataSourceList)) {
            log.warn("未找到符合条件的数据源...");
            return;
        }
        if (databaseProperties.getMultiTenant().getType() != MultiTenantType.DATASOURCE) {
            return;
        }
        for (DbSettingPageResp dynamicDatasource : dataSourceList) {
            publishEvent(true, EventAction.ADD, dynamicDatasource);
        }
    }

    @Override
    @DSTransactional
    public void created(DbSettingSaveReq req) {
        final long count = super.count(Wraps.<DbSetting>lbQ().eq(DbSetting::getName, req.getName()));
        if (count > 0) {
            throw CheckedException.badRequest("连接名称已存在");
        }
        DbSetting bean = BeanUtilPlus.toBean(req, DbSetting.class);
        this.baseMapper.insert(bean);
    }

    @Override
    @DSTransactional
    public void edit(Long id, DbSettingSaveReq req) {
        final long count = super.count(Wraps.<DbSetting>lbQ()
                .ne(DbSetting::getId, id)
                .eq(DbSetting::getName, req.getName()));
        if (count > 0) {
            throw CheckedException.badRequest("连接名称已存在");
        }
        DbSetting bean = BeanUtilPlus.toBean(id, req, DbSetting.class);
        this.baseMapper.updateById(bean);
    }

    @Override
    @DSTransactional
    public void delete(Long id) {
        Optional.ofNullable(this.baseMapper.selectById(id)).orElseThrow(() -> CheckedException.notFound("数据连接信息不存在"));
        this.baseMapper.deleteById(id);
        final List<DbSettingPageResp> dataSourceList = this.baseMapper.selectTenantDbById(id);
        for (DbSettingPageResp tenantDynamicDatasource : dataSourceList) {
            publishEvent(false, EventAction.DEL, tenantDynamicDatasource);
        }
    }

    @Override
    public void publishEvent(EventAction action, Long tenantId) {
        final DbSettingPageResp dbSetting = this.baseMapper.getTenantDynamicDatasourceByTenantId(tenantId);
        publishEvent(false, action, dbSetting);
    }

    private void publishEvent(boolean init, EventAction action, DbSettingPageResp dbSetting) {
        if (Objects.isNull(dbSetting)) {
            throw CheckedException.notFound("租户未关联数据源信息");
        }
        if (databaseProperties.getMultiTenant().getType() != MultiTenantType.DATASOURCE) {
            throw CheckedException.notFound("系统异常,请配置[动态数据源]模式");
        }
        final DynamicDatasourceEventPublish eventPublisher = SpringUtil.getBean(DynamicDatasourceEventPublish.class);
        final DynamicDatasourceEvent event = BeanUtil.toBean(dbSetting, DynamicDatasourceEvent.class);
        event.setAction(action.getType());
        if (init) {
            applicationContext.publishEvent(new com.wemirr.framework.db.dynamic.core.local.DynamicDatasourceEvent(action, event));
        } else {
            eventPublisher.publish(event);
        }
        log.debug("event publish successful - {}", event);
    }
}
