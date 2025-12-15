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

package com.wemirr.framework.feign.plugin.mock;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Mock 服务配置
 * <p>
 * 用于开发环境模拟远程服务调用，支持按服务名配置不同的 Mock 地址
 *
 * @author Levin
 */
@Data
@ConfigurationProperties(prefix = MockProperties.MOCK_PREFIX)
public class MockProperties {

    public static final String MOCK_PREFIX = "extend.feign.plugin.mock";

    /**
     * 是否启用 Mock 模式
     */
    private boolean enabled;

    /**
     * 服务 Mock 映射配置
     * <p>
     * key: 服务名, value: Mock 服务器配置
     */
    private Map<String, Server> serverMap = new ConcurrentHashMap<>();

    /**
     * Mock 服务器配置
     */
    @Data
    public static class Server {
        /**
         * Mock 服务器 URL
         */
        private String serverUrl;
        /**
         * 是否使用旧版服务格式
         */
        private boolean oldServer = false;
        /**
         * 响应数据字段名
         */
        private String dataField = "data";
    }
}