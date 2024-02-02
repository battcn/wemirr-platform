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

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @author Levin
 */
@Data
@ConfigurationProperties(AsyncProperties.PREFIX)
public class AsyncProperties {
    
    public static final String PREFIX = "extend.boot.async";
    
    /**
     * 核心线程数 并非越大越好
     * 默认 10 个
     */
    private int corePoolSize = 10;
    
    /**
     * 最大线程数 并非越大越好，当线程数过大的时候上下文切换也会耗费时间
     * 默认 50 个
     */
    private int maxPoolSize = 50;
    
    /**
     * 设置 ThreadPoolExecutor 的心跳时间
     */
    private int keepAliveSeconds = 60;
    
    /**
     * 为了防止线程溢出，设置队列长度
     * 默认最大 10000 个任务
     */
    private int queueCapacity = 10000;
    
    private String threadNamePrefix = "wemirr-async-thread-";
    
}
