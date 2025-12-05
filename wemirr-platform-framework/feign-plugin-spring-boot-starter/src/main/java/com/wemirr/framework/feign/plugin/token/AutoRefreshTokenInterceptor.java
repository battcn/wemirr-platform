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

package com.wemirr.framework.feign.plugin.token;

import cn.hutool.core.util.StrUtil;
import com.alibaba.fastjson2.JSONObject;
import com.google.common.cache.Cache;
import com.wemirr.framework.commons.exception.CheckedException;
import feign.RequestInterceptor;
import feign.RequestTemplate;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;

import java.net.URI;
import java.util.Collection;
import java.util.Map;

/**
 * @author Levin
 */
@Slf4j
@RequiredArgsConstructor
public class AutoRefreshTokenInterceptor implements RequestInterceptor {

    private final DiscoveryClient discoveryClient;
    private final AutoRefreshTokenProperties properties;
    private final Cache<String, String> tokenCache;

    @SneakyThrows
    @Override
    public void apply(RequestTemplate template) {
        final Map<String, Collection<String>> headers = template.headers();
        if (!headers.containsKey(properties.getIncludeTokenHeader())) {
            return;
        }
        final String tokenHeader = properties.getServerTokenHeader();
        if (template.headers().containsKey(tokenHeader)) {
            log.debug("当前上下文中已包含token,跳过自动获取流程...");
            return;
        }
        template.header(tokenHeader, tokenCache.get(tokenHeader, this::loadCache));
    }

    @SneakyThrows
    private String loadCache() {
        final AutoRefreshTokenProperties.Login auth = properties.getLogin();
        // 设置访问参数
        JSONObject params = new JSONObject();
        params.put("clientId", auth.getClientId());
        params.put("clientSecret", auth.getClientSecret());
        params.put("username", auth.getUsername());
        params.put("password", auth.getPassword());
        params.put("tenantCode", auth.getTenantCode());
        params.put("loginType", auth.getLoginType());
        String url = properties.getUri();
        if (properties.isLoadBalance()) {
            // 没找到好方案,只能用这种笨办法了
            final URI uri = new URI(properties.getUri());
            final String serviceId = uri.getHost();
            final ServiceInstance instance = discoveryClient.getInstances(serviceId).getFirst();
            final String hostAndPort = instance.getHost() + ":" + instance.getPort();
            url = StrUtil.replace(url, serviceId, hostAndPort);
        }
        throw CheckedException.badRequest("需要重构");
//        final String response = HttpUtil.createPost(url).body(params.toJSONString()).basicAuth(auth.getClientId(), auth.getClientSecret()).execute().body();
//        log.info("自动获取Token响应结果 - {}", response);
//        final String accessToken = (String) JSONPath.eval(response, "data.accessToken");
//        if (StrUtil.isBlank(accessToken)) {
//            throw CheckedException.badRequest("未获取到有效的 Token 数据");
//        }
//        return "Bearer " + accessToken;
    }
}
