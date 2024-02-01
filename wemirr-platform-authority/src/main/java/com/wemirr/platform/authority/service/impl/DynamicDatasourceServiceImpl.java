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

import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.extra.spring.SpringUtil;
import com.alibaba.fastjson2.JSON;
import com.baomidou.dynamic.datasource.annotation.DSTransactional;
import com.wemirr.framework.commons.BeanUtilPlus;
import com.wemirr.framework.commons.exception.CheckedException;
import com.wemirr.framework.db.dynamic.core.DynamicDatasourceEventPublish;
import com.wemirr.framework.db.dynamic.core.EventAction;
import com.wemirr.framework.db.dynamic.core.TenantDynamicDatasource;
import com.wemirr.framework.db.dynamic.core.local.DynamicDatasourceEvent;
import com.wemirr.framework.db.mybatisplus.ext.SuperServiceImpl;
import com.wemirr.framework.db.mybatisplus.wrap.Wraps;
import com.wemirr.framework.db.properties.DatabaseProperties;
import com.wemirr.framework.db.properties.MultiTenantType;
import com.wemirr.platform.authority.domain.baseinfo.req.DynamicDatasourceReq;
import com.wemirr.platform.authority.domain.tenant.entity.DynamicDatasource;
import com.wemirr.platform.authority.domain.tenant.resp.TenantDatasourceResp;
import com.wemirr.platform.authority.repository.common.DynamicDatasourceMapper;
import com.wemirr.platform.authority.repository.tenant.TenantConfigMapper;
import com.wemirr.platform.authority.service.TenantDatasourceService;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

import static com.wemirr.platform.authority.domain.tenant.converts.TenantDatasourceConverts.TENANT_DYNAMIC_DATASOURCE_VO_2_TENANT_DYNAMIC_DATASOURCE_CONVERTS;

/**
 * @author Levin
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class DynamicDatasourceServiceImpl extends SuperServiceImpl<DynamicDatasourceMapper, DynamicDatasource> implements TenantDatasourceService {
    
    private final TenantConfigMapper tenantConfigMapper;
    private final DatabaseProperties databaseProperties;
    private final ApplicationContext applicationContext;
    
    @Override
    public List<TenantDatasourceResp> selectTenantDynamicDatasource() {
        return this.tenantConfigMapper.selectTenantDbById(null);
    }
    
    @Override
    public void ping(Long id) {
        log.debug("查询结果 - {}", JSON.toJSONString(""));
    }
    
    @PostConstruct
    public void init() {
        final List<TenantDatasourceResp> dataSourceList = this.tenantConfigMapper.selectTenantDbById(null);
        if (CollectionUtil.isEmpty(dataSourceList)) {
            log.warn("未找到符合条件的数据源...");
            return;
        }
        if (databaseProperties.getMultiTenant().getType() != MultiTenantType.DATASOURCE) {
            return;
        }
        for (TenantDatasourceResp dynamicDatasource : dataSourceList) {
            publishEvent(true, EventAction.ADD, dynamicDatasource);
        }
    }
    
    @Override
    @DSTransactional
    public void created(DynamicDatasourceReq req) {
        final long count = super.count(Wraps.<DynamicDatasource>lbQ().eq(DynamicDatasource::getName, req.getName()));
        if (count > 0) {
            throw CheckedException.badRequest("连接名称已存在");
        }
        DynamicDatasource bean = BeanUtilPlus.toBean(req, DynamicDatasource.class);
        this.baseMapper.insert(bean);
    }
    
    @Override
    @DSTransactional
    public void edit(Long id, DynamicDatasourceReq req) {
        final long count = super.count(Wraps.<DynamicDatasource>lbQ()
                .ne(DynamicDatasource::getId, id)
                .eq(DynamicDatasource::getName, req.getName()));
        if (count > 0) {
            throw CheckedException.badRequest("连接名称已存在");
        }
        DynamicDatasource bean = BeanUtilPlus.toBean(id, req, DynamicDatasource.class);
        this.baseMapper.updateById(bean);
    }
    
    @Override
    @DSTransactional
    public void delete(Long id) {
        Optional.ofNullable(this.baseMapper.selectById(id)).orElseThrow(() -> CheckedException.notFound("数据连接信息不存在"));
        this.baseMapper.deleteById(id);
        final List<TenantDatasourceResp> dataSourceList = this.tenantConfigMapper.selectTenantDbById(id);
        for (TenantDatasourceResp tenantDynamicDatasource : dataSourceList) {
            publishEvent(false, EventAction.DEL, tenantDynamicDatasource);
        }
    }
    
    @Override
    public void publishEvent(EventAction action, Long tenantId) {
        final TenantDatasourceResp dynamicDatasource = this.tenantConfigMapper.getTenantDynamicDatasourceByTenantId(tenantId);
        publishEvent(false, action, dynamicDatasource);
    }
    
    private void publishEvent(boolean init, EventAction action, TenantDatasourceResp dynamicDatasource) {
        if (Objects.isNull(dynamicDatasource)) {
            throw CheckedException.notFound("租户未关联数据源信息");
        }
        if (databaseProperties.getMultiTenant().getType() != MultiTenantType.DATASOURCE) {
            throw CheckedException.notFound("未满足数据源隔离策略");
        }
        final DynamicDatasourceEventPublish eventPublisher = SpringUtil.getBean(DynamicDatasourceEventPublish.class);
        final TenantDynamicDatasource datasource = TENANT_DYNAMIC_DATASOURCE_VO_2_TENANT_DYNAMIC_DATASOURCE_CONVERTS.convert(dynamicDatasource);
        datasource.setAction(action.getType());
        if (init) {
            applicationContext.publishEvent(new DynamicDatasourceEvent(action, datasource));
        } else {
            eventPublisher.publish(datasource);
        }
        log.debug("event publish successful - {}", datasource);
    }
}
