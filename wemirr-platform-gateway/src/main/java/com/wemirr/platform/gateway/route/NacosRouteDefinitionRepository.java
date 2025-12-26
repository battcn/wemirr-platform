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

package com.wemirr.platform.gateway.route;

import com.alibaba.cloud.nacos.NacosConfigManager;
import com.alibaba.nacos.api.config.listener.Listener;
import com.alibaba.nacos.api.exception.NacosException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.google.common.collect.Lists;
import com.wemirr.framework.commons.JacksonUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.cloud.gateway.event.RefreshRoutesEvent;
import org.springframework.cloud.gateway.route.RouteDefinition;
import org.springframework.cloud.gateway.route.RouteDefinitionRepository;
import org.springframework.context.ApplicationEventPublisher;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executor;

/**
 * Nacos 路由数据源
 *
 * @author Levin
 * <p>
 */
@Slf4j
public class NacosRouteDefinitionRepository implements RouteDefinitionRepository {

    private static final String SCG_DATA_ID = "scg-routes";
    private static final String SCG_GROUP_ID = "SCG_GATEWAY";

    private final ApplicationEventPublisher publisher;
    private final NacosConfigManager nacosConfigManager;

    public NacosRouteDefinitionRepository(ApplicationEventPublisher publisher, NacosConfigManager nacosConfigManager) {
        this.publisher = publisher;
        this.nacosConfigManager = nacosConfigManager;
        addListener();
    }

    @Override
    public Flux<RouteDefinition> getRouteDefinitions() {
        try {
            String content = this.nacosConfigManager.getConfigService().getConfig(SCG_DATA_ID, SCG_GROUP_ID, 5000);
            List<RouteDefinition> routeDefinitions = getListByStr(content);
            return Flux.fromIterable(routeDefinitions);
        } catch (NacosException e) {
            log.error("getRouteDefinitions by nacos error", e);
        }
        return Flux.fromIterable(Lists.newArrayList());
    }

    /**
     * 添加Nacos监听
     */
    private void addListener() {
        try {
            this.nacosConfigManager.getConfigService().addListener(SCG_DATA_ID, SCG_GROUP_ID, new Listener() {

                @Override
                public Executor getExecutor() {
                    return null;
                }

                @Override
                public void receiveConfigInfo(String configInfo) {
                    publisher.publishEvent(new RefreshRoutesEvent(this));
                }
            });
        } catch (NacosException e) {
            log.error("nacos-addListener-error", e);
        }
    }

    @Override
    public Mono<Void> save(Mono<RouteDefinition> route) {
        return null;
    }

    @Override
    public Mono<Void> delete(Mono<String> routeId) {
        return null;
    }

    private List<RouteDefinition> getListByStr(String content) {
        if (StringUtils.isNotBlank(content)) {
            return JacksonUtils.readValue(content, new TypeReference<>() {
            });
        }
        return new ArrayList<>(0);
    }
}