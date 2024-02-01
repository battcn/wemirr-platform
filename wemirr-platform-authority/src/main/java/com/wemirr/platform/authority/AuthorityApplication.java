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

package com.wemirr.platform.authority;

import com.wemirr.framework.boot.log.event.AccessLogListener;
import com.wemirr.framework.security.configuration.server.annotation.EnableOAuth2Server;
import com.wemirr.framework.websocket.redis.EnableRedisWebSocket;
import com.wemirr.platform.authority.service.OptLogService;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Repository;

import java.net.InetAddress;

/**
 * 鉴权服务启动类.
 *
 * @author Levin
 */
@Slf4j
@EnableRedisWebSocket
@EnableCaching
@EnableDiscoveryClient
@SpringBootApplication
@EnableFeignClients(basePackages = "com.wemirr")
@MapperScan(value = "com.wemirr.**.repository", annotationClass = Repository.class)
@EnableOAuth2Server
public class AuthorityApplication {
    
    /**
     * 操作日志监听回调.
     *
     * @param optLogService 操作日志存储服务
     * @return AccessLogListener
     */
    @Bean
    public AccessLogListener accessLogListener(final OptLogService optLogService) {
        return new AccessLogListener(optLogService::listener);
    }
    
    /**
     * 启动类.
     *
     * @param args 启动参数
     */
    @SneakyThrows
    public static void main(final String[] args) {
        final ConfigurableApplicationContext applicationContext = SpringApplication.run(AuthorityApplication.class, args);
        Environment env = applicationContext.getEnvironment();
        final String appName = env.getProperty("spring.application.name");
        String host = InetAddress.getLocalHost().getHostAddress();
        String port = env.getProperty("server.port");
        log.info("""
                
                ----------------------------------------------------------
                \tApplication '{}' is running! Access URLs:
                \tDoc: \thttp://{}:{}/doc.html
                ----------------------------------------------------------""",
                appName, host, port);
    }
    
}
