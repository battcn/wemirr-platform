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
 * @author levin
 */
@Data
@ConfigurationProperties(prefix = MockProperties.MOCK_PREFIX)
public class MockProperties {
    
    public static final String MOCK_PREFIX = "extend.feign.plugin.mock";
    
    private boolean enabled;
    /**
     * 如果每个服务的 mock server 地址不一样，使用该配置,key:服务名  value: ip地址 ,例
     */
    private Map<String, Server> serverMap = new ConcurrentHashMap<>();
    
    @Data
    public static class Server {
        
        private String serverUrl;
        private boolean oldServer = false;
        private String dataField = "data";
    }
}