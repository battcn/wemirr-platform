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

import org.aspectj.lang.ProceedingJoinPoint;

/**
 * key生成器
 *
 * @author Levin
 * @since 2021/09/10
 */
public interface RedisKeyGenerator {
    
    /**
     * 获取AOP参数,生成指定缓存Key
     *
     * @param prefix    key前缀
     * @param delimiter 分隔符
     * @return 缓存KEY
     */
    default String generate(String prefix, String delimiter) {
        throw new RuntimeException("请自行实现该接口方法");
    }
    
    /**
     * 获取AOP参数,生成指定缓存Key
     *
     * @param prefix    key前缀
     * @param delimiter 分隔符
     * @param pjp       PJP
     * @return 缓存KEY
     */
    default String generate(String prefix, String delimiter, ProceedingJoinPoint pjp) {
        return prefix;
    }
}