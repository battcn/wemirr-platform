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

package com.wemirr.framework.boot.async;

import jakarta.annotation.Nonnull;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.task.TaskDecorator;
import org.springframework.scheduling.annotation.AsyncConfigurer;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import java.util.concurrent.Executor;

/**
 * 异步线程支持
 *
 * @author Levin
 */
@Configuration
@EnableAsync
@EnableConfigurationProperties(AsyncProperties.class)
@RequiredArgsConstructor
public class AsyncConfiguration implements AsyncConfigurer {
    
    private final AsyncProperties properties;
    
    @Override
    public Executor getAsyncExecutor() {
        // 具体可以自己写成 properties 的方式
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        executor.setCorePoolSize(properties.getCorePoolSize());
        executor.setMaxPoolSize(properties.getMaxPoolSize());
        executor.setKeepAliveSeconds(properties.getKeepAliveSeconds());
        executor.setQueueCapacity(properties.getQueueCapacity());
        executor.setThreadNamePrefix(properties.getThreadNamePrefix());
        executor.setTaskDecorator(new RequestAttributesTaskDecorator());
        executor.initialize();
        return executor;
    }
    
    /**
     * 异步线程池的时候 request 上下文复制
     */
    private static class RequestAttributesTaskDecorator implements TaskDecorator {
        
        @Override
        @Nonnull
        public Runnable decorate(@Nonnull Runnable runnable) {
            ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
            return () -> {
                try {
                    // 在异步方法执行前将 RequestAttributes 绑定到当前线程的 ThreadLocal 中
                    RequestContextHolder.setRequestAttributes(attributes);
                    runnable.run();
                } finally {
                    // 在异步方法执行后清除当前线程的 ThreadLocal
                    RequestContextHolder.resetRequestAttributes();
                }
            };
        }
    }
}