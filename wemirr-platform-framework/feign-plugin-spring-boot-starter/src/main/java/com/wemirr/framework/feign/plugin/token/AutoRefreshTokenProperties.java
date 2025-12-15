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

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * 自动刷新 Token 配置
 * <p>
 * 用于 Feign 内部服务调用时自动获取和刷新认证 Token
 *
 * @author Levin
 */
@Data
@ConfigurationProperties(prefix = AutoRefreshTokenProperties.TOKEN_PREFIX)
public class AutoRefreshTokenProperties {

    public static final String TOKEN_PREFIX = "extend.feign.plugin.token";
    public static final String X_AUTO_TOKEN = "X-Auto-Token=true";
    public static final String X_AUTO_TOKEN_KEYWORD = "X-Auto-Token";
    public static final String AUTHORIZATION = "Authorization";

    /**
     * 是否启用自动 Token 刷新
     */
    private boolean enabled;

    /**
     * 请求头标识（用于判断是否需要自动添加 Token）
     */
    private String includeTokenHeader = X_AUTO_TOKEN_KEYWORD;

    /**
     * Token 请求头名称
     */
    private String serverTokenHeader = AUTHORIZATION;

    /**
     * 登录配置
     */
    private Login login;

    /**
     * 认证服务 URI
     */
    private String uri;

    /**
     * 是否使用负载均衡（true: Feign 调用, false: 普通 HTTP）
     */
    private boolean loadBalance;

    /**
     * Token 缓存配置
     */
    private Cache cache = new Cache();

    /**
     * 登录凭证配置
     */
    @Data
    public static class Login {
        private String username;
        private String password;
        private String clientId = "wemirr";
        private String clientSecret = "wemirr";
        private String tenantCode;
        private String loginType = "password";
    }

    /**
     * Token 缓存配置
     */
    @Data
    public static class Cache {
        /**
         * 过期时间（秒），默认 1 小时
         */
        private long expire = 3600L;
        /**
         * 初始容量
         */
        private int initialCapacity = 30;
        /**
         * 最大容量
         */
        private long maximumSize = 100;
    }
}
