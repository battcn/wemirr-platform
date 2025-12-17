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

import java.util.concurrent.TimeUnit;

/**
 * 锁信息（使用JDK21 Record特性，不可变且线程安全）
 *
 * @param key       锁的key
 * @param waitTime  等待时间
 * @param leaseTime 租约时间（-1表示启用看门狗自动续期）
 * @param timeUnit  时间单位
 * @param lockType  锁类型
 * @author Levin
 */
public record LockInfo(
        String key,
        long waitTime,
        long leaseTime,
        TimeUnit timeUnit,
        LockType lockType
) {
    /**
     * 默认等待时间（秒）
     */
    public static final long DEFAULT_WAIT_TIME = 3L;

    /**
     * 默认租约时间（-1表示启用看门狗）
     */
    public static final long DEFAULT_LEASE_TIME = -1L;

    /**
     * 创建默认配置的锁信息
     */
    public static LockInfo of(String key) {
        return new LockInfo(key, DEFAULT_WAIT_TIME, DEFAULT_LEASE_TIME, TimeUnit.SECONDS, LockType.REENTRANT);
    }

    /**
     * 创建指定等待时间的锁信息
     */
    public static LockInfo of(String key, long waitTime) {
        return new LockInfo(key, waitTime, DEFAULT_LEASE_TIME, TimeUnit.SECONDS, LockType.REENTRANT);
    }

    /**
     * 创建完整配置的锁信息
     */
    public static LockInfo of(String key, long waitTime, long leaseTime, TimeUnit timeUnit) {
        return new LockInfo(key, waitTime, leaseTime, timeUnit, LockType.REENTRANT);
    }

    /**
     * 锁类型枚举
     */
    public enum LockType {
        /**
         * 可重入锁（默认）
         */
        REENTRANT,
        /**
         * 公平锁
         */
        FAIR,
        /**
         * 读锁
         */
        READ,
        /**
         * 写锁
         */
        WRITE,
        /**
         * 联锁（多把锁）
         */
        MULTI
    }
}
