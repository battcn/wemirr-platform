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

package com.wemirr.framework.redis.plus.interceptor;

import com.wemirr.framework.redis.plus.anontation.RedisLimit;
import com.wemirr.framework.redis.plus.exception.RedisLimitException;
import com.wemirr.framework.redis.plus.utils.RedisAopUtils;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.redisson.api.RRateLimiter;
import org.redisson.api.RateIntervalUnit;
import org.redisson.api.RateType;
import org.redisson.api.RedissonClient;
import org.springframework.core.annotation.AnnotationUtils;

import java.lang.reflect.Method;
import java.time.Duration;
import java.util.concurrent.TimeUnit;

/**
 * 分布式限流AOP拦截器
 * <p>基于Redisson令牌桶算法实现接口限流，防止接口被恶意调用</p>
 *
 * <h3>使用方式</h3>
 * <pre>{@code
 * @RedisLimit(prefix = "api:user:login", limit = 5, timeout = 60)
 * public Result login(LoginRequest request) {
 *     // 每分钟最多允许5次调用
 * }
 * }</pre>
 *
 * @author Levin
 * @since 1.0.0
 * @see RedisLimit
 */
@Slf4j
@Aspect
public record RedisLimitInterceptor(RedissonClient redissonClient) {

    /**
     * 切点：拦截所有标注了@RedisLimit注解的方法
     */
    @Pointcut("@annotation(com.wemirr.framework.redis.plus.anontation.RedisLimit)")
    public void redissonRateAspectPointcut() {
    }

    /**
     * 环绕通知：执行限流检查
     *
     * @param point 连接点
     * @return 方法执行结果
     * @throws Throwable 方法执行异常或限流异常
     */
    @Around("redissonRateAspectPointcut()")
    public Object doRedissonRateAround(ProceedingJoinPoint point) throws Throwable {
        MethodSignature signature = (MethodSignature) point.getSignature();
        Method method = signature.getMethod();
        RedisLimit rateLimiter = AnnotationUtils.getAnnotation(method, RedisLimit.class);
        if (rateLimiter == null) {
            return point.proceed();
        }

        String key = RedisAopUtils.parse(rateLimiter.prefix(), rateLimiter.useArgs(), method, point.getArgs(), point);
        boolean acquired = tryAcquire(key, rateLimiter.limit(), rateLimiter.timeout(),
                rateLimiter.unit(), rateLimiter.type(), rateLimiter.retryTime());

        if (acquired) {
            log.debug("Rate limiter acquired token for key: {}", key);
            return point.proceed();
        }

        log.warn("Rate limiter rejected request for key: {}", key);
        throw new RedisLimitException(rateLimiter.message());
    }

    /**
     * 尝试获取令牌
     */
    @SuppressWarnings("unused")
    private boolean tryAcquire(String key, long permits, long timeout, TimeUnit timeUnit,
                               RateType rateType, long retryTime) {
        RRateLimiter rateLimiter = redissonClient.getRateLimiter(key);
        Duration interval = convertToDuration(timeout, timeUnit);
        rateLimiter.trySetRate(rateType, permits,interval.getSeconds(), RateIntervalUnit.SECONDS);
        // retryTime参数保留以兼容@RedisLimit注解，后续版本可考虑实现重试逻辑
        return rateLimiter.tryAcquire(1);
    }

    /**
     * 转换为Duration
     */
    private Duration convertToDuration(long timeout, TimeUnit timeUnit) {
        return switch (timeUnit) {
            case MINUTES -> Duration.ofMinutes(timeout);
            case HOURS -> Duration.ofHours(timeout);
            case DAYS -> Duration.ofDays(timeout);
            default -> Duration.ofSeconds(timeout);
        };
    }
}
