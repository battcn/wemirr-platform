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

package com.wemirr.framework.boot.remote.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * 配置类
 *
 * @author Levin
 */
@Data
@ConfigurationProperties(RemoteProperties.PREFIX)
public class RemoteProperties {
    
    public static final String PREFIX = "extend.boot.remote";
    /**
     * 是否启用远程查询
     */
    private Boolean enabled = true;
    /**
     * 是否启用aop注解方式
     */
    private Boolean aopEnabled = true;
    
    /**
     * 字典类型 和 code 的分隔符
     */
    private String dictSeparator = "###";
    /**
     * 多个字典code 之间的的分隔符
     */
    private String dictItemSeparator = ",";
    
    /**
     * 递归最大深度
     */
    private Integer maxDepth = 3;
    
    /**
     * 本地缓存配置信息
     */
    private GuavaCache guavaCache = new GuavaCache();
    
    @Data
    public static class GuavaCache {
        
        /**Remote
         * 是否启用本地缓存
         * <p>
         * 注意：本地缓存开启后，会存在短暂的数据不一致情况(由guavaCacheRefreshWriteTime决定)， 所以对数据正确性有要求的项目建议禁用，然后在@Remote.method 方法上执行添加redis等缓存！
         */
        private Boolean enabled = false;
        /**
         * guava缓存的 最大数
         */
        private Integer maximumSize = 1000;
        /**
         * guava更新缓存的下一次时间,分钟
         */
        private Integer refreshWriteTime = 2;
        /**
         * guava自动刷新缓存的线程数量
         */
        private Integer refreshThreadPoolSize = 10;
    }
}
