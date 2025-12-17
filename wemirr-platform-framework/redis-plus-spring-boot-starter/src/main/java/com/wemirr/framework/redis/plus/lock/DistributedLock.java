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

package com.wemirr.framework.redis.plus.lock;

import com.wemirr.framework.redis.plus.exception.RedisLockException;
import lombok.extern.slf4j.Slf4j;
import org.redisson.RedissonMultiLock;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;

import java.util.concurrent.Callable;
import java.util.concurrent.TimeUnit;
import java.util.function.Supplier;

/**
 * 分布式锁流式API
 * <p>提供优雅的链式调用方式，支持Virtual Thread</p>
 *
 * <pre>{@code
 * // 1. 简单使用
 * String result = DistributedLock.key("order:123")
 *     .execute(() -> processOrder());
 *
 * // 2. 自定义配置
 * DistributedLock.key("user:456")
 *     .waitTime(5, TimeUnit.SECONDS)
 *     .leaseTime(30, TimeUnit.SECONDS)
 *     .lockType(LockInfo.LockType.FAIR)
 *     .execute(() -> updateUser());
 *
 * // 3. 带结果处理
 * LockResult<Order> result = DistributedLock.key("order:789")
 *     .tryExecute(() -> createOrder());
 * result.onSuccess(order -> log.info("Created: {}", order))
 *       .onFailure(e -> log.error("Failed", e));
 *
 * // 4. 无返回值
 * DistributedLock.key("task:001").run(() -> doTask());
 * }</pre>
 *
 * @author Levin
 */
@Slf4j
public final class DistributedLock {

    private static RedissonClient redissonClient;

    private final String key;
    private long waitTime = LockInfo.DEFAULT_WAIT_TIME;
    private long leaseTime = LockInfo.DEFAULT_LEASE_TIME;
    private TimeUnit timeUnit = TimeUnit.SECONDS;
    private LockInfo.LockType lockType = LockInfo.LockType.REENTRANT;
    private String failMessage = "获取锁失败，请稍后重试";

    private DistributedLock(String key) {
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
     * 创建分布式锁构建器
     *
     * @param key 锁的key
     * @return 构建器实例
     */
    public static DistributedLock key(String key) {
        if (redissonClient == null) {
            throw new IllegalStateException("DistributedLock not initialized. Please ensure RedissonClient is configured.");
        }
        return new DistributedLock(key);
    }

    /**
     * 使用LockInfo创建
     */
    public static DistributedLock of(LockInfo lockInfo) {
        return key(lockInfo.key())
                .waitTime(lockInfo.waitTime(), lockInfo.timeUnit())
                .leaseTime(lockInfo.leaseTime(), lockInfo.timeUnit())
                .lockType(lockInfo.lockType());
    }

    // ==================== 配置方法 ====================

    /**
     * 设置等待时间
     */
    public DistributedLock waitTime(long waitTime) {
        this.waitTime = waitTime;
        return this;
    }

    /**
     * 设置等待时间和单位
     */
    public DistributedLock waitTime(long waitTime, TimeUnit timeUnit) {
        this.waitTime = waitTime;
        this.timeUnit = timeUnit;
        return this;
    }

    /**
     * 设置租约时间（-1表示启用看门狗自动续期）
     */
    public DistributedLock leaseTime(long leaseTime) {
        this.leaseTime = leaseTime;
        return this;
    }

    /**
     * 设置租约时间和单位
     */
    public DistributedLock leaseTime(long leaseTime, TimeUnit timeUnit) {
        this.leaseTime = leaseTime;
        this.timeUnit = timeUnit;
        return this;
    }

    /**
     * 设置锁类型
     */
    public DistributedLock lockType(LockInfo.LockType lockType) {
        this.lockType = lockType;
        return this;
    }

    /**
     * 设置失败提示消息
     */
    public DistributedLock failMessage(String message) {
        this.failMessage = message;
        return this;
    }

    /**
     * 使用公平锁
     */
    public DistributedLock fair() {
        this.lockType = LockInfo.LockType.FAIR;
        return this;
    }

    /**
     * 使用读锁
     */
    public DistributedLock readLock() {
        this.lockType = LockInfo.LockType.READ;
        return this;
    }

    /**
     * 使用写锁
     */
    public DistributedLock writeLock() {
        this.lockType = LockInfo.LockType.WRITE;
        return this;
    }

    // ==================== 执行方法 ====================

    /**
     * 执行带返回值的任务（获取锁失败抛出异常）
     *
     * @param supplier 任务
     * @param <T>      返回类型
     * @return 执行结果
     */
    public <T> T execute(Supplier<T> supplier) {
        RLock lock = getLock();
        boolean locked = false;
        try {
            locked = lock.tryLock(waitTime, leaseTime, timeUnit);
            if (!locked) {
                throw new RedisLockException(failMessage);
            }
            log.debug("Acquired lock: {}", key);
            return supplier.get();
        } catch (InterruptedException e) {
            // 正确处理中断：恢复中断状态
            Thread.currentThread().interrupt();
            throw new RedisLockException("线程被中断", e);
        } finally {
            unlockSafely(lock, locked);
        }
    }

    /**
     * 执行带返回值的任务（可能抛出检查异常）
     */
    public <T> T executeWithException(Callable<T> callable) throws Exception {
        RLock lock = getLock();
        boolean locked = false;
        try {
            locked = lock.tryLock(waitTime, leaseTime, timeUnit);
            if (!locked) {
                throw new RedisLockException(failMessage);
            }
            log.debug("Acquired lock: {}", key);
            return callable.call();
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            throw new RedisLockException("线程被中断", e);
        } finally {
            unlockSafely(lock, locked);
        }
    }

    /**
     * 执行无返回值的任务
     *
     * @param runnable 任务
     */
    public void run(Runnable runnable) {
        execute(() -> {
            runnable.run();
            return null;
        });
    }

    /**
     * 尝试执行任务，返回结果包装（不抛出异常）
     *
     * @param supplier 任务
     * @param <T>      返回类型
     * @return 执行结果包装
     */
    public <T> LockResult<T> tryExecute(Supplier<T> supplier) {
        RLock lock = getLock();
        boolean locked = false;
        try {
            locked = lock.tryLock(waitTime, leaseTime, timeUnit);
            if (!locked) {
                return LockResult.failure(new RedisLockException(failMessage));
            }
            log.debug("Acquired lock: {}", key);
            return LockResult.success(supplier.get());
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            return LockResult.failure(new RedisLockException("线程被中断", e));
        } catch (Exception e) {
            return LockResult.failure(e);
        } finally {
            unlockSafely(lock, locked);
        }
    }

    /**
     * 尝试执行无返回值任务
     */
    public LockResult<Void> tryRun(Runnable runnable) {
        return tryExecute(() -> {
            runnable.run();
            return null;
        });
    }

    /**
     * 检查是否能获取锁（不执行任务）
     */
    public boolean isAvailable() {
        RLock lock = getLock();
        try {
            boolean locked = lock.tryLock(0, 0, timeUnit);
            if (locked) {
                lock.unlock();
            }
            return locked;
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            return false;
        }
    }

    // ==================== 私有方法 ====================

    /**
     * 获取锁实例
     */
    private RLock getLock() {
        return switch (lockType) {
            case REENTRANT -> redissonClient.getLock(key);
            case FAIR -> redissonClient.getFairLock(key);
            case READ -> redissonClient.getReadWriteLock(key).readLock();
            case WRITE -> redissonClient.getReadWriteLock(key).writeLock();
            case MULTI -> new RedissonMultiLock(redissonClient.getLock(key));
        };
    }

    /**
     * 安全释放锁
     */
    private void unlockSafely(RLock lock, boolean wasLocked) {
        if (wasLocked && lock != null && lock.isHeldByCurrentThread()) {
            try {
                lock.unlock();
                log.debug("Released lock: {}", key);
            } catch (Exception e) {
                log.warn("Failed to release lock: {}", key, e);
            }
        }
    }
}
