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
import cn.hutool.extra.spring.SpringUtil;
import com.baomidou.dynamic.datasource.annotation.DSTransactional;
import com.wemirr.framework.commons.BeanUtilPlus;
import com.wemirr.framework.commons.exception.CheckedException;
import com.wemirr.framework.db.dynamic.core.DynamicDataSourceEvent;
import com.wemirr.framework.db.dynamic.core.DynamicDataSourceEventPublisher;
import com.wemirr.framework.db.dynamic.core.EventAction;
import com.wemirr.framework.db.dynamic.core.local.DynamicInstanceApplicationEvent;
import com.wemirr.framework.db.mybatisplus.ext.SuperServiceImpl;
import com.wemirr.framework.db.mybatisplus.wrap.Wraps;
import com.wemirr.framework.db.properties.DatabaseProperties;
import com.wemirr.framework.db.properties.MultiTenantType;
import com.wemirr.platform.iam.tenant.domain.dto.req.DbInstanceSaveReq;
import com.wemirr.platform.iam.tenant.domain.dto.resp.DbInstancePageResp;
import com.wemirr.platform.iam.tenant.domain.entity.DbInstance;
import com.wemirr.platform.iam.tenant.repository.DbInstanceMapper;
import com.wemirr.platform.iam.tenant.service.DbInstanceService;
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
public class DbInstanceServiceImpl extends SuperServiceImpl<DbInstanceMapper, DbInstance> implements DbInstanceService {

    private final DatabaseProperties databaseProperties;
    private final ApplicationContext applicationContext;

    @Override
    public List<DbInstancePageResp> selectTenantDynamicDatasource() {
        return this.baseMapper.selectTenantDbById(null);
    }

    @Override
    public void ping(Long id) {
        log.debug("查询结果");
    }

    @PostConstruct
    public void init() {
        if (databaseProperties.getMultiTenant().getType() != MultiTenantType.DATASOURCE) {
            return;
        }
        final List<DbInstancePageResp> dataSourceList = this.baseMapper.selectTenantDbById(null);
        if (CollUtil.isEmpty(dataSourceList)) {
            log.warn("未找到符合条件的数据源...");
            return;
        }
        for (DbInstancePageResp dynamicDatasource : dataSourceList) {
            publishEvent(true, EventAction.ADD, dynamicDatasource);
        }
    }

    @Override
    @DSTransactional
    public void create(DbInstanceSaveReq req) {
        final long count = super.count(Wraps.<DbInstance>lbQ().eq(DbInstance::getName, req.getName()));
        if (count > 0) {
            throw CheckedException.badRequest("连接名称已存在");
        }
        DbInstance bean = BeanUtilPlus.toBean(req, DbInstance.class);
        this.baseMapper.insert(bean);
    }

    @Override
    @DSTransactional
    public void edit(Long id, DbInstanceSaveReq req) {
        final long count = super.count(Wraps.<DbInstance>lbQ()
                .ne(DbInstance::getId, id)
                .eq(DbInstance::getName, req.getName()));
        if (count > 0) {
            throw CheckedException.badRequest("连接名称已存在");
        }
        DbInstance bean = BeanUtilPlus.toBean(id, req, DbInstance.class);
        this.baseMapper.updateById(bean);
    }

    @Override
    @DSTransactional
    public void delete(Long id) {
        Optional.ofNullable(this.baseMapper.selectById(id)).orElseThrow(() -> CheckedException.notFound("数据连接信息不存在"));
        this.baseMapper.deleteById(id);
        final List<DbInstancePageResp> dataSourceList = this.baseMapper.selectTenantDbById(id);
        for (DbInstancePageResp tenantDynamicDatasource : dataSourceList) {
            publishEvent(false, EventAction.DEL, tenantDynamicDatasource);
        }
    }

    @Override
    public void publishEvent(EventAction action, Long tenantId) {
        final DbInstancePageResp dbSetting = this.baseMapper.getTenantDynamicDatasourceByTenantId(tenantId);
        publishEvent(false, action, dbSetting);
    }

    private void publishEvent(boolean init, EventAction action, DbInstancePageResp dbSetting) {
        if (Objects.isNull(dbSetting)) {
            throw CheckedException.notFound("租户未关联数据源信息");
        }
        if (databaseProperties.getMultiTenant().getType() != MultiTenantType.DATASOURCE) {
            throw CheckedException.notFound("系统异常,请配置[动态数据源]模式");
        }
        final DynamicDataSourceEventPublisher eventPublisher = SpringUtil.getBean(DynamicDataSourceEventPublisher.class);
        final DynamicDataSourceEvent event = BeanUtil.toBean(dbSetting, DynamicDataSourceEvent.class);
        event.setAction(action.getType());
        if (init) {
            applicationContext.publishEvent(new DynamicInstanceApplicationEvent(action, event));
        } else {
            eventPublisher.publish(event);
        }
        log.debug("event publish successful - {}", event);
    }
}
