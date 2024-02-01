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

import lombok.RequiredArgsConstructor;
import org.redisson.api.RRateLimiter;
import org.redisson.api.RateIntervalUnit;
import org.redisson.api.RateType;
import org.redisson.api.RedissonClient;

import java.util.concurrent.TimeUnit;

/**
 * @author Levin
 */
@RequiredArgsConstructor
public class RedisLimitHelper {
    
    private final RedissonClient redissonClient;
    
    public boolean tryAcquire(String rateKey, long permits, long timeout, RateType type) {
        return tryAcquire(rateKey, permits, timeout, TimeUnit.SECONDS, type, 0);
    }
    
    /**
     * 直接使用redisson限流器
     * 参考:https://github.com/redisson/redisson/wiki
     * 限流器redisson3.7.1版本
     * 本处默认配置都是秒，可以手动修改
     * 重启服务，可以手动清除一下redis库
     *
     * @param rateKey：自定义key
     * @param permits:允许最大限流数
     * @param timeout:时间间隔单位数
     * @param timeUnit:时间间隔类型，默认秒(SECONDS,MINUTES,HOURS,DAYS)
     * @param type:限流类型，默认秒(OVERALL                           总机限流，PER_CLIENT 单机限流)
     * @param retryTimes:重试次数，默认0不重试
     */
    public boolean tryAcquire(String rateKey, long permits, long timeout, TimeUnit timeUnit, RateType type, long retryTimes) {
        RRateLimiter rateLimiter = redissonClient.getRateLimiter(rateKey);
        switch (timeUnit) {
            // 间隔一秒，尝试一次，失败就不重试，直接拒绝访问，0换成正数，就会间隔重试
            case MINUTES:
                // 1分钟permits个token
                rateLimiter.trySetRate(type, permits, timeout, RateIntervalUnit.MINUTES);
                // 间隔一分钟，尝试一次，失败就不重试，直接拒绝访问，0换成正数，就会间隔重试
                return rateLimiter.tryAcquire(1, retryTimes, TimeUnit.MINUTES);
            case HOURS:
                // 1小时permits个token
                rateLimiter.trySetRate(type, permits, timeout, RateIntervalUnit.HOURS);
                // 间隔一小时，尝试一次，失败就不重试，直接拒绝访问，0换成正数，就会间隔重试
                return rateLimiter.tryAcquire(1, retryTimes, TimeUnit.HOURS);
            case DAYS:
                // 1天permits个token
                rateLimiter.trySetRate(type, permits, timeout, RateIntervalUnit.DAYS);
                // 间隔一天，尝试一次，失败就不重试，直接拒绝访问，0换成正数，就会间隔重试
                return rateLimiter.tryAcquire(1, retryTimes, TimeUnit.DAYS);
            default:
                // "SECONDS": 1秒permits个token
                rateLimiter.trySetRate(type, permits, timeout, RateIntervalUnit.SECONDS);
                // 间隔一秒，尝试一次，失败就不重试，直接拒绝访问，0换成正数，就会间隔重试
                return rateLimiter.tryAcquire(1, retryTimes, TimeUnit.SECONDS);
        }
    }
    
}
