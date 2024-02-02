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
 * 自动生成 token 请求内部服务(对动态数据源支持不一定好)
 *
 * @author Levin
 */
@SuppressWarnings("ALL")
@Data
@ConfigurationProperties(prefix = AutoRefreshTokenProperties.TOKEN_PREFIX)
public class AutoRefreshTokenProperties {
    
    public static final String TOKEN_PREFIX = "extend.feign.plugin.token";
    public static final String X_AUTO_TOKEN = "X-Auto-Token=true";
    public static final String X_AUTO_TOKEN_KEYWORD = "X-Auto-Token";
    public static final String AUTHORIZATION = "Authorization";
    
    private boolean enabled;
    private String includeTokenHeader = X_AUTO_TOKEN_KEYWORD;
    private String serverTokenHeader = AUTHORIZATION;
    private OAuth oAuth;
    private String uri;
    
    /**
     * true 走feign 否则普通 http 请求
     */
    private boolean loadBalance;
    private Cache cache = new Cache();
    
    @Data
    public static class OAuth {
        
        private String clientId = "wemirr";
        private String clientSecret = "wemirr";
        private String username;
        private String password;
        private String tenantCode;
        private String grantType = "password";
        private String loginType = "password";
        private String scope = "server";
    }
    
    @Data
    public static class Cache {
        
        /**
         * 过期时间-秒
         * 默认 1小时
         */
        private long expire = 60 * 60;
        private int initialCapacity = 30;
        private long maximumSize = 100;
        
    }
    
}
