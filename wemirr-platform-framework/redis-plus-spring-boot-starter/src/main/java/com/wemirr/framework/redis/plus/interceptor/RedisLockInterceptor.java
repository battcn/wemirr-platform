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
import com.wemirr.framework.commons.exception.RedisLockException;
import com.wemirr.framework.redis.plus.RedisKeyGenerator;
import com.wemirr.framework.redis.plus.anontation.RedisLock;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.redisson.RedissonMultiLock;
import org.redisson.RedissonRedLock;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;

import java.lang.reflect.Method;

/**
 * @author Levin
 */
@Slf4j
@Aspect
@RequiredArgsConstructor
public class RedisLockInterceptor {
    
    private final RedissonClient redissonClient;
    private final RedisKeyGenerator redisKeyGenerator;
    
    @SneakyThrows
    @Around("execution(public * *(..)) && @annotation(com.wemirr.framework.redis.plus.anontation.RedisLock)")
    public Object interceptor(ProceedingJoinPoint pjp) {
        MethodSignature signature = (MethodSignature) pjp.getSignature();
        Method method = signature.getMethod();
        RLock rLock = null;
        
        RedisLock lock = method.getAnnotation(RedisLock.class);
        final String defaultKeyPrefix = StrUtil.join(pjp.getTarget().getClass().getName(), lock.delimiter(), method.getName());
        final String prefix = StrUtil.blankToDefault(lock.prefix(), defaultKeyPrefix);
        log.debug("defaultKeyPrefix - {} - prefix - {}", defaultKeyPrefix, prefix);
        
        if (StrUtil.isBlank(prefix)) {
            throw CheckedException.notFound("Lock key prefix cannot be null.");
        }
        
        final String lockKey = redisKeyGenerator.generate(prefix, lock.delimiter(), pjp);
        
        try {
            // 假设上锁成功，但是设置过期时间失效，以后拿到的都是 false
            rLock = getLock(lockKey, lock.lockType());
            final boolean success = rLock.tryLock(lock.waitTime(), lock.expire(), lock.timeUnit());
            
            if (log.isDebugEnabled()) {
                log.debug("Redis lock key is {} and status is {}", lockKey, success);
            }
            
            if (!success) {
                throw new RedisLockException(lock.message());
            }
            
            return pjp.proceed();
        } catch (InterruptedException e) {
            log.error("Redis try lock InterruptedException", e);
            throw new RedisLockException("线程中断" + e.getLocalizedMessage());
        } finally {
            boolean shouldReleaseLock = lock.unlock() && rLock != null && rLock.isHeldByCurrentThread();
            // 判断是否需要自动释放锁
            if (shouldReleaseLock) {
                log.debug("Redisson distributed lock released successfully with key: {}", lockKey);
                rLock.unlock();
            }
        }
    }
    
    /**
     * 获取指定类型锁
     *
     * @param key      key
     * @param lockType lockType
     * @return RLock
     */
    private RLock getLock(String key, RedisLock.LockType lockType) {
        switch (lockType) {
            case REENTRANT_LOCK -> {
                return redissonClient.getLock(key);
            }
            case FAIR_LOCK -> {
                return redissonClient.getFairLock(key);
            }
            case READ_LOCK -> {
                return redissonClient.getReadWriteLock(key).readLock();
            }
            case WRITE_LOCK -> {
                return redissonClient.getReadWriteLock(key).writeLock();
            }
            case RED_LOCK -> {
                return new RedissonRedLock(redissonClient.getLock(key));
            }
            case MULTI_LOCK -> {
                return new RedissonMultiLock(redissonClient.getLock(key));
            }
            default -> {
                log.error("do not support lock type:" + lockType.name());
                throw new RuntimeException("do not support lock type:" + lockType.name());
            }
        }
    }
}