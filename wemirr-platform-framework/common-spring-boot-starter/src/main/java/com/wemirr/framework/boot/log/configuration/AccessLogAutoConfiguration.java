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

package com.wemirr.framework.boot.log.configuration;

import com.wemirr.framework.boot.log.LogProperties;
import com.wemirr.framework.boot.log.event.AccessLogListener;
import com.wemirr.framework.boot.log.feign.AccessLogFeign;
import org.springframework.boot.autoconfigure.condition.ConditionalOnExpression;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.scheduling.annotation.EnableAsync;

/**
 * 日志自动配置
 * <p>
 * 启动条件：
 * 1，存在web环境
 *
 * @author Levin
 */
@EnableAsync
@Configuration
@EnableConfigurationProperties(LogProperties.class)
@ConditionalOnProperty(prefix = LogProperties.PREFIX, name = "enabled", havingValue = "true", matchIfMissing = true)
public class AccessLogAutoConfiguration {
    
    @Bean
    @ConditionalOnMissingBean
    public AccessLogAspect accessLogAspect() {
        return new AccessLogAspect();
    }
    
    @Bean
    @Order
    @ConditionalOnExpression("'${extend.boot.log.strategy}'.equalsIgnoreCase('feign')")
    public AccessLogListener accessLogListener(AccessLogFeign feign) {
        return new AccessLogListener(feign::listener);
    }
    
}
