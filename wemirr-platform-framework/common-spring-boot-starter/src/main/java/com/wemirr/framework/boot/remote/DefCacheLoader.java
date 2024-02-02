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

package com.wemirr.framework.boot.remote;

import com.google.common.cache.CacheLoader;
import com.google.common.util.concurrent.ListenableFuture;
import com.google.common.util.concurrent.ListeningExecutorService;
import com.google.common.util.concurrent.MoreExecutors;
import com.google.common.util.concurrent.ThreadFactoryBuilder;
import com.wemirr.framework.boot.remote.properties.RemoteProperties;
import lombok.extern.slf4j.Slf4j;
import org.springframework.lang.NonNull;

import java.io.Serializable;
import java.util.Map;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * 默认的缓存加载器
 *
 * @author Levin
 */
@Slf4j
public class DefCacheLoader extends CacheLoader<CacheLoadKeys, Map<Serializable, Object>> {
    
    /**
     * 侦听执行器服务
     */
    private final ListeningExecutorService backgroundRefreshPools;
    
    public DefCacheLoader(RemoteProperties.GuavaCache guavaCache) {
        ThreadFactory namedThreadFactory = new ThreadFactoryBuilder().setNameFormat("remote-cache-pool-%d").build();
        this.backgroundRefreshPools = MoreExecutors.listeningDecorator(
                new ThreadPoolExecutor(guavaCache.getRefreshThreadPoolSize(), guavaCache.getRefreshThreadPoolSize(),
                        0L, TimeUnit.MILLISECONDS,
                        new LinkedBlockingQueue<>(), namedThreadFactory));
    }
    
    /**
     * 内存缓存不存在时， 调用时触发加载数据
     *
     * @param type 扩展参数
     * @return 加载后的数据
     */
    @Override
    public Map<Serializable, Object> load(@NonNull CacheLoadKeys type) {
        log.info("首次读取缓存: " + type);
        return type.loadMap();
    }
    
    /**
     * 重新载入数据
     *
     * @param key      扩展参数
     * @param oldValue 原来的值
     * @return 重新加载后的数据
     */
    @Override
    public ListenableFuture<Map<Serializable, Object>> reload(@NonNull CacheLoadKeys key, @NonNull Map<Serializable, Object> oldValue) {
        return backgroundRefreshPools.submit(() -> load(key));
    }
}
