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

import cn.hutool.core.util.StrUtil;
import com.wemirr.framework.commons.exception.CheckedException;
import com.wemirr.framework.redis.plus.RedisKeyGenerator;
import com.wemirr.framework.redis.plus.anontation.RedisLock;
import com.wemirr.framework.redis.plus.exception.RedisLockException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.redisson.RedissonMultiLock;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;

import java.lang.reflect.Method;

/**
 * 分布式锁注解拦截器
 * <p>优化中断处理，支持Virtual Thread</p>
 *
 * @author Levin
 */
@Slf4j
@Aspect
@RequiredArgsConstructor
public class RedisLockInterceptor {

    private final RedissonClient redissonClient;
    private final RedisKeyGenerator redisKeyGenerator;

    @Around("execution(public * *(..)) && @annotation(com.wemirr.framework.redis.plus.anontation.RedisLock)")
    public Object interceptor(ProceedingJoinPoint pjp) throws Throwable {
        MethodSignature signature = (MethodSignature) pjp.getSignature();
        Method method = signature.getMethod();
        RedisLock lock = method.getAnnotation(RedisLock.class);

        // 生成锁Key
        String lockKey = generateLockKey(pjp, method, lock);
        RLock rLock = getLock(lockKey, lock.lockType());
        boolean locked = false;

        try {
            locked = rLock.tryLock(lock.waitTime(), lock.expire(), lock.timeUnit());

            if (log.isDebugEnabled()) {
                log.debug("Redis lock key: {}, acquired: {}", lockKey, locked);
            }

            if (!locked) {
                throw new RedisLockException(lock.message());
            }

            return pjp.proceed();
        } catch (InterruptedException e) {
            // 正确处理中断：恢复中断状态，这对Virtual Thread尤为重要
            Thread.currentThread().interrupt();
            throw new RedisLockException("线程被中断", e);
        } finally {
            unlockSafely(rLock, locked, lock.unlock(), lockKey);
        }
    }

    /**
     * 生成锁Key
     */
    private String generateLockKey(ProceedingJoinPoint pjp, Method method, RedisLock lock) {
        String defaultKeyPrefix = StrUtil.join(lock.delimiter(),
                pjp.getTarget().getClass().getName(), method.getName());
        String prefix = StrUtil.blankToDefault(lock.prefix(), defaultKeyPrefix);

        if (StrUtil.isBlank(prefix)) {
            throw CheckedException.notFound("Lock key prefix cannot be null.");
        }

        return redisKeyGenerator.generate(prefix, lock.delimiter(), pjp);
    }

    /**
     * 安全释放锁
     */
    private void unlockSafely(RLock lock, boolean wasLocked, boolean shouldUnlock, String lockKey) {
        if (!shouldUnlock || !wasLocked || lock == null) {
            return;
        }
        try {
            if (lock.isHeldByCurrentThread()) {
                lock.unlock();
                log.debug("Released lock: {}", lockKey);
            }
        } catch (Exception e) {
            log.warn("Failed to release lock: {}", lockKey, e);
        }
    }

    /**
     * 获取指定类型锁（使用JDK21 switch表达式）
     */
    private RLock getLock(String key, RedisLock.LockType lockType) {
        return switch (lockType) {
            case REENTRANT_LOCK -> redissonClient.getLock(key);
            case FAIR_LOCK -> redissonClient.getFairLock(key);
            case READ_LOCK -> redissonClient.getReadWriteLock(key).readLock();
            case WRITE_LOCK -> redissonClient.getReadWriteLock(key).writeLock();
            case MULTI_LOCK -> new RedissonMultiLock(redissonClient.getLock(key));
        };
    }
}