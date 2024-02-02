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

package com.wemirr.framework.redis.plus;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.io.Serializable;
import java.util.List;

/**
 * @author Levin
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ConfigurationProperties(prefix = "extend.redis")
public class RedisPlusProperties {
    
    private boolean enabled = true;
    private RedisSwitch lock;
    private RedisSwitch limit;
    private RedisCacheSwitch cache;
    
    @Data
    public static class RedisSwitch {
        
        /**
         * 是否启用
         */
        private boolean enabled;
        /**
         * 前缀
         */
        private String prefix = "redis_plus_";
        /**
         * 是否开启 @RedisLimit 的拦截器功能
         */
        private boolean interceptor = true;
    }
    
    @Data
    public static class RedisCacheSwitch {
        
        /**
         * 是否启用
         */
        private boolean enabled;
        /**
         * 前缀
         */
        private String prefix = "redis_plus_cache_";
        
        /**
         * 全局缓存时长,默认24小时
         */
        private long timeout = 60 * 60 * 24;
        
        private List<RedisCacheItem> items;
    }
    
    /**
     * 单项缓存配置信息
     *
     * @author Levin
     */
    @Data
    @EqualsAndHashCode(callSuper = false)
    public static class RedisCacheItem implements Serializable {
        
        /**
         * 单项缓存名称
         */
        private String name;
        
        /**
         * 单项缓存存活时间
         */
        private long timeout;
        
        /**
         * 单项缓存是否开启
         */
        private boolean enabled;
    }
    
}
