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

package com.wemirr.framework.security.configuration;

import com.google.common.collect.Lists;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.ArrayList;
import java.util.List;

import static com.wemirr.framework.security.configuration.SecurityExtProperties.PLUGIN_PREFIX;

/**
 * 扩展安全配置
 *
 * @author Levin
 */
@Data
@ConfigurationProperties(prefix = PLUGIN_PREFIX)
public class SecurityExtProperties {

    public static final String PLUGIN_PREFIX = "extend.security";

    /**
     * 默认的过滤地址
     */
    private List<String> defaultIgnoreUrls = List.of("/captcha", "/sms_captcha", "/message/**",
            "/login", "/error", "/oauth2/**", "/warm-flow-ui/**", "/warm-flow/**",  "/flow/**",
            "/favicon.ico", "/css/**", "/webjars/**",
            "/swagger-ui.html", "/doc.html", "/v3/api-docs/**");

    /**
     * 是否启用 oauth2 协议（不启用也不影响正常登录）
     */
    private boolean enabledOauth2;

    /**
     * 忽略资源
     */
    private Ignore ignore = new Ignore();

    /**
     * 服务端配置
     */
    private Server server = new Server();

    /**
     * 客户端配置
     */
    private Client client = new Client();
    private InnerService innerService = new InnerService();

    @Data
    public static class Server {


        /**
         * 用户信息KEY
         * %s = token
         */
        private String tokenInfoKey = "tokenInfo";

    }

    @Data
    public static class Client {

    }

    @Data
    public static class Ignore {

        private List<String> resourceUrls = new ArrayList<>();
    }

    @Data
    public static class InnerService {

        /**
         * 白名单
         */
        private List<String> whiteLists = Lists.newArrayList();

    }

}
