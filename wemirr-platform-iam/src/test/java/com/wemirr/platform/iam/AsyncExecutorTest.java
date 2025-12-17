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

package com.wemirr.platform.iam;

import com.wemirr.framework.commons.concurrent.AsyncExecutor;
import com.wemirr.framework.commons.threadlocal.ThreadLocalHolder;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * AsyncExecutor异步执行器测试
 * <p>测试异步执行、并行处理、上下文传递等功能</p>
 *
 * @author Levin
 */
@Slf4j
public class AsyncExecutorTest {

    private static final Long TEST_TENANT_ID = 1001L;
    private static final Long TEST_USER_ID = 12345L;

    @BeforeEach
    void setUp() {
        // 设置测试上下文
        ThreadLocalHolder.setTenantId(TEST_TENANT_ID);
        ThreadLocalHolder.setUserId(TEST_USER_ID);
        log.info("测试上下文已设置: tenantId={}, userId={}", TEST_TENANT_ID, TEST_USER_ID);
    }

    @AfterEach
    void tearDown() {
        // 清理上下文
        ThreadLocalHolder.clear();
        log.info("测试上下文已清理");
    }

    // ==================== 单任务异步执行测试 ====================

    @Test
    void shouldRunAsyncTask() throws Exception {
        AtomicInteger counter = new AtomicInteger(0);

        CompletableFuture<Void> future = AsyncExecutor.runAsync(() -> {
            counter.incrementAndGet();
            log.info("异步任务执行中...");
        });

        future.get(5, TimeUnit.SECONDS);
        assertEquals(1, counter.get(), "异步任务应该执行一次");
    }

    @Test
    void shouldAsyncReturnResult() throws Exception {
        CompletableFuture<String> future = AsyncExecutor.async(() -> {
            log.info("异步计算中...");
            return "Hello, AsyncExecutor!";
        });

        String result = future.get(5, TimeUnit.SECONDS);
        assertEquals("Hello, AsyncExecutor!", result);
    }

    @Test
    void shouldAsyncThenConsumeResult() throws Exception {
        AtomicInteger consumed = new AtomicInteger(0);

        CompletableFuture<Void> future = AsyncExecutor.asyncThen(
                () -> 42,
                value -> {
                    consumed.set(value);
                    log.info("消费结果: {}", value);
                }
        );

        future.get(5, TimeUnit.SECONDS);
        assertEquals(42, consumed.get());
    }

    // ==================== 并行执行测试 ====================

    @Test
    void shouldParallelExecuteMultipleTasks() {
        List<Integer> results = AsyncExecutor.parallel(
                () -> {
                    sleep(100);
                    return 1;
                },
                () -> {
                    sleep(100);
                    return 2;
                },
                () -> {
                    sleep(100);
                    return 3;
                }
        );

        assertEquals(3, results.size());
        assertTrue(results.containsAll(Arrays.asList(1, 2, 3)));
        log.info("并行执行结果: {}", results);
    }

    @Test
    void shouldParallelMapCollection() {
        List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5);

        List<Integer> squared = AsyncExecutor.parallelMap(numbers, n -> {
            log.info("计算 {}^2", n);
            return n * n;
        });

        assertEquals(Arrays.asList(1, 4, 9, 16, 25), squared);
        log.info("并行映射结果: {}", squared);
    }

    @Test
    void shouldParallelMapWithConcurrencyLimit() {
        List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);
        AtomicInteger concurrent = new AtomicInteger(0);
        AtomicInteger maxConcurrent = new AtomicInteger(0);

        List<Integer> results = AsyncExecutor.parallelMap(numbers, n -> {
            int current = concurrent.incrementAndGet();
            maxConcurrent.updateAndGet(max -> Math.max(max, current));
            try {
                sleep(50);
                return n * 2;
            } finally {
                concurrent.decrementAndGet();
            }
        }, 3); // 限制并发数为3

        assertEquals(10, results.size());
        log.info("最大并发数: {}, 限制: 3", maxConcurrent.get());
        assertTrue(maxConcurrent.get() <= 3, "并发数不应超过限制");
    }

    // ==================== 上下文传递测试 ====================

    @Test
    void shouldPropagateThreadLocalContext() throws Exception {
        CompletableFuture<String> future = AsyncExecutor.async(() -> {
            Long tenantId = ThreadLocalHolder.getTenantId();
            Long userId = ThreadLocalHolder.getUserId();
            log.info("异步线程获取上下文: tenantId={}, userId={}", tenantId, userId);
            return tenantId + ":" + userId;
        });

        String result = future.get(5, TimeUnit.SECONDS);
        assertEquals(TEST_TENANT_ID + ":" + TEST_USER_ID, result, "上下文应该正确传递");
    }

    @Test
    void shouldPropagateContextInParallelTasks() {
        List<String> results = AsyncExecutor.parallel(
                () -> "Thread1:" + ThreadLocalHolder.getTenantId(),
                () -> "Thread2:" + ThreadLocalHolder.getTenantId(),
                () -> "Thread3:" + ThreadLocalHolder.getTenantId()
        );

        results.forEach(r -> {
            assertTrue(r.contains(String.valueOf(TEST_TENANT_ID)), "每个线程都应获取到正确的tenantId");
            log.info("上下文传递结果: {}", r);
        });
    }

    // ==================== Virtual Thread测试 ====================

    @Test
    void shouldRunWithVirtualThread() throws Exception {
        AtomicInteger executed = new AtomicInteger(0);

        AsyncExecutor.runVirtual(() -> {
            executed.set(1);
            log.info("Virtual Thread执行, 线程: {}", Thread.currentThread());
        });

        // 等待执行完成
        sleep(500);
        assertEquals(1, executed.get());
    }

    @Test
    void shouldCallVirtualReturnResult() throws Exception {
        CompletableFuture<Integer> future = AsyncExecutor.callVirtual(() -> {
            log.info("Virtual Thread计算, 线程: {}", Thread.currentThread());
            return 100;
        });

        Integer result = future.get(5, TimeUnit.SECONDS);
        assertEquals(100, result);
    }

    // ==================== 批量执行测试 ====================

    @Test
    void shouldRunAllAsyncFireAndForget() throws Exception {
        AtomicInteger counter = new AtomicInteger(0);

        AsyncExecutor.runAllAsync(Arrays.asList(
                () -> counter.incrementAndGet(),
                () -> counter.incrementAndGet(),
                () -> counter.incrementAndGet()
        ));

        // 等待执行完成
        sleep(500);
        assertEquals(3, counter.get());
    }

    @Test
    void shouldRunAllAndWait() {
        AtomicInteger counter = new AtomicInteger(0);

        AsyncExecutor.runAllAndWait(Arrays.asList(
                () -> {
                    sleep(100);
                    counter.incrementAndGet();
                },
                () -> {
                    sleep(100);
                    counter.incrementAndGet();
                }
        ));

        assertEquals(2, counter.get(), "所有任务应该完成");
    }

    @Test
    void shouldRunAllWithTimeout() {
        AtomicInteger completed = new AtomicInteger(0);

        boolean success = AsyncExecutor.runAllAndWait(Arrays.asList(
                () -> {
                    sleep(50);
                    completed.incrementAndGet();
                },
                () -> {
                    sleep(50);
                    completed.incrementAndGet();
                }
        ), 5, TimeUnit.SECONDS);

        assertTrue(success, "应该在超时前完成");
        assertEquals(2, completed.get());
    }

    // ==================== 任务编排测试 ====================

    @Test
    void shouldSerialExecuteTasks() throws Exception {
        StringBuilder order = new StringBuilder();

        CompletableFuture<String> future = AsyncExecutor.serial(
                () -> {
                    order.append("A");
                    return "A";
                },
                () -> {
                    order.append("B");
                    return "B";
                },
                () -> {
                    order.append("C");
                    return "C";
                }
        );

        String result = future.get(5, TimeUnit.SECONDS);
        assertEquals("C", result, "应该返回最后一个任务的结果");
        assertEquals("ABC", order.toString(), "任务应该按顺序执行");
    }

    @Test
    void shouldAnyOfReturnFirstCompleted() throws Exception {
        CompletableFuture<String> future = AsyncExecutor.anyOf(
                () -> {
                    sleep(500);
                    return "slow";
                },
                () -> {
                    sleep(50);
                    return "fast";
                },
                () -> {
                    sleep(300);
                    return "medium";
                }
        );

        String result = future.get(5, TimeUnit.SECONDS);
        assertEquals("fast", result, "应该返回最快完成的结果");
    }

    // ==================== 包装器测试 ====================

    @Test
    void shouldWrapRunnablePreserveContext() throws Exception {
        Runnable wrapped = AsyncExecutor.wrapRunnable(() -> {
            Long tenantId = ThreadLocalHolder.getTenantId();
            assertEquals(TEST_TENANT_ID, tenantId);
            log.info("包装的Runnable获取上下文: {}", tenantId);
        });

        // 在新线程中执行
        Thread thread = new Thread(wrapped);
        thread.start();
        thread.join(5000);
    }

    @Test
    void shouldWrapSupplierPreserveContext() {
        var wrapped = AsyncExecutor.wrapSupplier(() -> ThreadLocalHolder.getTenantId());

        // 在新线程中验证
        CompletableFuture.supplyAsync(wrapped).thenAccept(tenantId -> {
            assertEquals(TEST_TENANT_ID, tenantId);
            log.info("包装的Supplier获取上下文: {}", tenantId);
        }).join();
    }

    // ==================== 辅助方法 ====================

    private void sleep(long millis) {
        try {
            Thread.sleep(millis);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}
