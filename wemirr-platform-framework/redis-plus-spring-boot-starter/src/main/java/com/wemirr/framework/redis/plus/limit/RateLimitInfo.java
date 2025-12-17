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

package com.wemirr.framework.redis.plus.limit;

import org.redisson.api.RateType;

import java.util.concurrent.TimeUnit;

/**
 * 限流信息（使用JDK21 Record特性）
 *
 * @param key       限流key
 * @param permits   时间窗口内允许的最大请求数
 * @param timeout   时间窗口大小
 * @param timeUnit  时间单位
 * @param rateType  限流类型（总体/单机）
 * @param retryTime 重试等待时间（0表示不重试）
 * @author Levin
 */
public record RateLimitInfo(
        String key,
        long permits,
        long timeout,
        TimeUnit timeUnit,
        RateType rateType,
        long retryTime
) {
    /**
     * 默认每秒50个请求
     */
    public static final long DEFAULT_PERMITS = 50L;

    /**
     * 默认时间窗口1秒
     */
    public static final long DEFAULT_TIMEOUT = 1L;

    /**
     * 创建默认限流配置
     */
    public static RateLimitInfo of(String key) {
        return new RateLimitInfo(key, DEFAULT_PERMITS, DEFAULT_TIMEOUT, TimeUnit.SECONDS, RateType.OVERALL, 0);
    }

    /**
     * 创建指定QPS限流配置
     */
    public static RateLimitInfo ofQps(String key, long qps) {
        return new RateLimitInfo(key, qps, 1L, TimeUnit.SECONDS, RateType.OVERALL, 0);
    }

    /**
     * 创建每分钟限流配置
     */
    public static RateLimitInfo perMinute(String key, long permits) {
        return new RateLimitInfo(key, permits, 1L, TimeUnit.MINUTES, RateType.OVERALL, 0);
    }

    /**
     * 创建每小时限流配置
     */
    public static RateLimitInfo perHour(String key, long permits) {
        return new RateLimitInfo(key, permits, 1L, TimeUnit.HOURS, RateType.OVERALL, 0);
    }

    /**
     * 创建每天限流配置
     */
    public static RateLimitInfo perDay(String key, long permits) {
        return new RateLimitInfo(key, permits, 1L, TimeUnit.DAYS, RateType.OVERALL, 0);
    }

    /**
     * 创建单机限流配置
     */
    public RateLimitInfo perClient() {
        return new RateLimitInfo(key, permits, timeout, timeUnit, RateType.PER_CLIENT, retryTime);
    }

    /**
     * 设置重试时间
     */
    public RateLimitInfo withRetry(long retryTime) {
        return new RateLimitInfo(key, permits, timeout, timeUnit, rateType, retryTime);
    }
}
