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

import com.wemirr.framework.commons.JacksonUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.gateway.route.RouteDefinition;
import org.springframework.cloud.gateway.route.RouteDefinitionRepository;
import org.springframework.cloud.gateway.support.NotFoundException;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;

import static java.util.stream.Collectors.toList;

/**
 * @author Levin
 */
@Slf4j
@Component
@RequiredArgsConstructor
public class RedisRouteDefinitionRepository implements RouteDefinitionRepository {
    
    /**
     * 存储的的key
     */
    private static final String KEY = "gateway_dynamic_route";
    private final StringRedisTemplate redisTemplate;
    
    public RouteDefinition find(String routeId) {
        final Object object = redisTemplate.opsForHash().get(KEY, routeId);
        if (object == null) {
            return null;
        }
        log.debug("[动态路由信息] - [{}]", object);
        return JacksonUtils.toBean(object.toString(), RouteDefinition.class);
    }
    
    /**
     * 获取路由信息
     *
     * @return Flux
     */
    @Override
    public Flux<RouteDefinition> getRouteDefinitions() {
        final List<RouteDefinition> gatewayRouteEntityList = redisTemplate.opsForHash().keys(KEY).stream()
                .map(routeId -> find(String.valueOf(routeId))).collect(toList());
        return Flux.fromIterable(gatewayRouteEntityList);
    }
    
    /**
     * 新增
     *
     * @param route route
     * @return Mono
     */
    @Override
    public Mono<Void> save(Mono<RouteDefinition> route) {
        return route.flatMap(routeDefinition -> {
            redisTemplate.opsForHash().put(KEY, routeDefinition.getId(), JacksonUtils.toJson(routeDefinition));
            return Mono.empty();
        });
    }
    
    /**
     * 删除
     *
     * @param routeId routeId
     * @return Mono
     */
    @Override
    public Mono<Void> delete(Mono<String> routeId) {
        return routeId.flatMap(id -> {
            if (redisTemplate.opsForHash().hasKey(KEY, id)) {
                redisTemplate.opsForHash().delete(KEY, id);
                return Mono.empty();
            }
            return Mono.defer(() -> Mono.error(new NotFoundException("route definition is not found, routeId:" + routeId)));
        });
    }
}
