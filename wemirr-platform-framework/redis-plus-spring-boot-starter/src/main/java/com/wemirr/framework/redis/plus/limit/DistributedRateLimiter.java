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

import com.wemirr.framework.redis.plus.exception.RedisLimitException;
import lombok.extern.slf4j.Slf4j;
import org.redisson.api.RRateLimiter;
import org.redisson.api.RateIntervalUnit;
import org.redisson.api.RateType;
import org.redisson.api.RedissonClient;

import java.time.Duration;
import java.util.concurrent.TimeUnit;
import java.util.function.Supplier;

/**
 * 分布式限流器流式API
 * <p>基于Redisson令牌桶算法实现</p>
 *
 * <pre>{@code
 * // 1. 简单限流（每秒50个请求）
 * String result = DistributedRateLimiter.key("api:user:list")
 *     .execute(() -> userService.list());
 *
 * // 2. 自定义QPS
 * DistributedRateLimiter.key("api:order:create")
 *     .permits(100)           // 每秒100个请求
 *     .execute(() -> orderService.create(order));
 *
 * // 3. 每分钟限流
 * DistributedRateLimiter.key("api:sms:send")
 *     .permits(10)
 *     .perMinute()
 *     .execute(() -> smsService.send(phone));
 *
 * // 4. 单机限流
 * DistributedRateLimiter.key("api:upload")
 *     .permits(5)
 *     .perClient()
 *     .execute(() -> fileService.upload(file));
 *
 * // 5. 尝试执行（不抛异常）
 * boolean success = DistributedRateLimiter.key("api:login")
 *     .tryAcquire();
 * }</pre>
 *
 * @author Levin
 */
@Slf4j
public final class DistributedRateLimiter {

    private static RedissonClient redissonClient;

    private final String key;
    private long permits = RateLimitInfo.DEFAULT_PERMITS;
    private long timeout = RateLimitInfo.DEFAULT_TIMEOUT;
    private TimeUnit timeUnit = TimeUnit.SECONDS;
    private RateType rateType = RateType.OVERALL;
    private long retryTime = 0;
    private String failMessage = "请求过于频繁，请稍后重试";

    private DistributedRateLimiter(String key) {
        this.key = key;
    }

    /**
     * 初始化RedissonClient（由Spring自动配置调用）
     */
    public static void initialize(RedissonClient client) {
        redissonClient = client;
    }

    // ==================== 静态工厂方法 ====================

    /**
     * 创建限流器
     *
     * @param key 限流key
     * @return 限流器实例
     */
    public static DistributedRateLimiter key(String key) {
        if (redissonClient == null) {
            throw new IllegalStateException("DistributedRateLimiter not initialized. Please ensure RedissonClient is configured.");
        }
        return new DistributedRateLimiter(key);
    }

    /**
     * 使用RateLimitInfo创建
     */
    public static DistributedRateLimiter of(RateLimitInfo info) {
        return key(info.key())
                .permits(info.permits())
                .timeout(info.timeout())
                .timeUnit(info.timeUnit())
                .rateType(info.rateType())
                .retryTime(info.retryTime());
    }

    // ==================== 配置方法 ====================

    /**
     * 设置时间窗口内允许的请求数
     */
    public DistributedRateLimiter permits(long permits) {
        this.permits = permits;
        return this;
    }

    /**
     * 设置时间窗口大小
     */
    public DistributedRateLimiter timeout(long timeout) {
        this.timeout = timeout;
        return this;
    }

    /**
     * 设置时间单位
     */
    public DistributedRateLimiter timeUnit(TimeUnit timeUnit) {
        this.timeUnit = timeUnit;
        return this;
    }

    /**
     * 设置限流类型
     */
    public DistributedRateLimiter rateType(RateType rateType) {
        this.rateType = rateType;
        return this;
    }

    /**
     * 设置重试时间
     */
    public DistributedRateLimiter retryTime(long retryTime) {
        this.retryTime = retryTime;
        return this;
    }

    /**
     * 设置失败消息
     */
    public DistributedRateLimiter failMessage(String message) {
        this.failMessage = message;
        return this;
    }

    /**
     * 每秒限流（QPS）
     */
    public DistributedRateLimiter perSecond() {
        this.timeout = 1;
        this.timeUnit = TimeUnit.SECONDS;
        return this;
    }

    /**
     * 每分钟限流
     */
    public DistributedRateLimiter perMinute() {
        this.timeout = 1;
        this.timeUnit = TimeUnit.MINUTES;
        return this;
    }

    /**
     * 每小时限流
     */
    public DistributedRateLimiter perHour() {
        this.timeout = 1;
        this.timeUnit = TimeUnit.HOURS;
        return this;
    }

    /**
     * 每天限流
     */
    public DistributedRateLimiter perDay() {
        this.timeout = 1;
        this.timeUnit = TimeUnit.DAYS;
        return this;
    }

    /**
     * 单机限流模式
     */
    public DistributedRateLimiter perClient() {
        this.rateType = RateType.PER_CLIENT;
        return this;
    }

    /**
     * 总体限流模式（默认）
     */
    public DistributedRateLimiter overall() {
        this.rateType = RateType.OVERALL;
        return this;
    }

    // ==================== 执行方法 ====================

    /**
     * 尝试获取令牌
     *
     * @return 是否成功获取
     */
    public boolean tryAcquire() {
        return tryAcquire(1);
    }

    /**
     * 尝试获取指定数量的令牌
     *
     * @param count 令牌数量
     * @return 是否成功获取
     */
    public boolean tryAcquire(int count) {
        RRateLimiter rateLimiter = getRateLimiter();
        boolean acquired = rateLimiter.tryAcquire(count);
        if (acquired) {
            log.debug("Rate limiter acquired {} permits for key: {}", count, key);
        } else {
            log.debug("Rate limiter rejected for key: {}", key);
        }
        return acquired;
    }

    /**
     * 执行任务（获取令牌失败抛出异常）
     *
     * @param supplier 任务
     * @param <T>      返回类型
     * @return 执行结果
     */
    public <T> T execute(Supplier<T> supplier) {
        if (!tryAcquire()) {
            throw new RedisLimitException(failMessage);
        }
        return supplier.get();
    }

    /**
     * 执行无返回值任务
     *
     * @param runnable 任务
     */
    public void run(Runnable runnable) {
        if (!tryAcquire()) {
            throw new RedisLimitException(failMessage);
        }
        runnable.run();
    }

    /**
     * 尝试执行任务（不抛异常）
     *
     * @param supplier 任务
     * @param <T>      返回类型
     * @return 执行结果，失败返回null
     */
    public <T> T tryExecute(Supplier<T> supplier) {
        if (!tryAcquire()) {
            return null;
        }
        return supplier.get();
    }

    /**
     * 尝试执行任务（带默认值）
     *
     * @param supplier     任务
     * @param defaultValue 失败时的默认值
     * @param <T>          返回类型
     * @return 执行结果或默认值
     */
    public <T> T tryExecuteOrDefault(Supplier<T> supplier, T defaultValue) {
        if (!tryAcquire()) {
            return defaultValue;
        }
        return supplier.get();
    }

    /**
     * 获取当前可用令牌数
     */
    public long availablePermits() {
        return getRateLimiter().availablePermits();
    }

    // ==================== 私有方法 ====================

    /**
     * 获取或创建限流器
     */
    private RRateLimiter getRateLimiter() {
        RRateLimiter rateLimiter = redissonClient.getRateLimiter(key);
        Duration interval = convertToDuration(timeout, timeUnit);
        rateLimiter.trySetRate(rateType, permits,interval.getSeconds() ,RateIntervalUnit.SECONDS);
        return rateLimiter;
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
