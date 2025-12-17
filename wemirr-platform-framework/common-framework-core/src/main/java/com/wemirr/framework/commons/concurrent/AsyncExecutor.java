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

package com.wemirr.framework.commons.concurrent;

import com.alibaba.ttl.TtlCallable;
import com.alibaba.ttl.TtlRunnable;
import com.wemirr.framework.commons.threadlocal.ThreadLocalHolder;
import lombok.extern.slf4j.Slf4j;

import java.util.*;
import java.util.concurrent.*;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Collectors;

/**
 * 异步执行器（支持上下文传递）
 * <p>基于JDK21 Virtual Thread实现，提供便捷的并发执行方法，自动传递ThreadLocalHolder中的上下文数据</p>
 *
 * <h3>功能特性</h3>
 * <ul>
 *   <li>使用Virtual Thread（虚拟线程）实现高并发</li>
 *   <li>自动传递ThreadLocalHolder中的上下文（租户ID、用户ID等）</li>
 *   <li>支持并行、串行、超时等多种执行模式</li>
 *   <li>配合TTL实现线程池场景下的上下文传递</li>
 * </ul>
 *
 * <h3>使用示例</h3>
 * <pre>{@code
 * // 异步执行单个任务
 * CompletableFuture<User> future = AsyncExecutor.async(() -> userService.findById(1L));
 *
 * // 并行执行多个任务
 * List<User> users = AsyncExecutor.parallel(
 *     () -> userService.findById(1L),
 *     () -> userService.findById(2L),
 *     () -> userService.findById(3L)
 * );
 *
 * // 批量并行映射
 * List<Order> orders = AsyncExecutor.parallelMap(orderIds, id -> orderService.findById(id));
 *
 * // 限制并发数
 * List<Data> data = AsyncExecutor.parallelMap(items, item -> process(item), 5);
 *
 * // 带超时的并行执行
 * List<Result> results = AsyncExecutor.parallelWithTimeout(tasks, 5, TimeUnit.SECONDS);
 *
 * // 使用Virtual Thread执行
 * AsyncExecutor.runVirtual(() -> heavyTask());
 * }</pre>
 *
 * @author Levin
 * @since 1.0.0
 * @see ThreadLocalHolder
 * @see java.util.concurrent.Executors#newVirtualThreadPerTaskExecutor()
 */
@Slf4j
public final class AsyncExecutor {

    /**
     * 默认执行器（使用Virtual Thread，每个任务一个虚拟线程）
     */
    private static final ExecutorService DEFAULT_EXECUTOR = Executors.newVirtualThreadPerTaskExecutor();

    /**
     * 有界执行器（限制最大并发数为CPU核心数*2）
     */
    @SuppressWarnings("unused")
    private static final ExecutorService BOUNDED_EXECUTOR = Executors.newFixedThreadPool(
            Runtime.getRuntime().availableProcessors() * 2,
            Thread.ofVirtual().name("async-bounded-", 0).factory()
    );

    /**
     * 私有构造函数，防止实例化
     */
    private AsyncExecutor() {
        throw new UnsupportedOperationException("Utility class cannot be instantiated");
    }

    // ==================== 异步执行（单任务） ====================

    /**
     * 异步执行任务（无返回值）
     *
     * @param task 要执行的任务
     * @return CompletableFuture
     */
    public static CompletableFuture<Void> runAsync(Runnable task) {
        return CompletableFuture.runAsync(wrapRunnable(task), DEFAULT_EXECUTOR);
    }

    /**
     * 异步执行任务（有返回值）
     *
     * @param supplier 要执行的任务
     * @param <T>      返回值类型
     * @return CompletableFuture
     */
    public static <T> CompletableFuture<T> async(Supplier<T> supplier) {
        return CompletableFuture.supplyAsync(wrapSupplier(supplier), DEFAULT_EXECUTOR);
    }

    /**
     * 异步执行任务并处理结果
     *
     * @param supplier 要执行的任务
     * @param consumer 结果处理器
     * @param <T>      返回值类型
     * @return CompletableFuture
     */
    public static <T> CompletableFuture<Void> asyncThen(Supplier<T> supplier, Consumer<T> consumer) {
        return async(supplier).thenAccept(consumer);
    }

    // ==================== 并行执行（多任务） ====================

    /**
     * 并行执行多个任务，等待所有完成并返回结果列表
     *
     * @param suppliers 任务列表
     * @param <T>       返回值类型
     * @return 结果列表（顺序与任务顺序一致）
     */
    @SafeVarargs
    public static <T> List<T> parallel(Supplier<T>... suppliers) {
        List<CompletableFuture<T>> futures = new ArrayList<>();
        for (Supplier<T> supplier : suppliers) {
            futures.add(async(supplier));
        }
        return futures.stream()
                .map(CompletableFuture::join)
                .collect(Collectors.toList());
    }

    /**
     * 并行执行任务列表
     */
    public static <T> List<T> parallel(List<Supplier<T>> suppliers) {
        return suppliers.stream()
                .map(AsyncExecutor::async)
                .toList()
                .stream()
                .map(CompletableFuture::join)
                .collect(Collectors.toList());
    }

    /**
     * 并行映射处理
     */
    public static <T, R> List<R> parallelMap(Collection<T> items, Function<T, R> mapper) {
        return items.stream()
                .map(item -> async(() -> mapper.apply(item)))
                .toList()
                .stream()
                .map(CompletableFuture::join)
                .collect(Collectors.toList());
    }

    /**
     * 并行执行（限制并发数）
     */
    public static <T, R> List<R> parallelMap(Collection<T> items, Function<T, R> mapper, int parallelism) {
        Semaphore semaphore = new Semaphore(parallelism);
        return items.stream()
                .map(item -> async(() -> {
                    try {
                        semaphore.acquire();
                        return mapper.apply(item);
                    } catch (InterruptedException e) {
                        Thread.currentThread().interrupt();
                        throw new RuntimeException(e);
                    } finally {
                        semaphore.release();
                    }
                }))
                .toList()
                .stream()
                .map(CompletableFuture::join)
                .collect(Collectors.toList());
    }

    /**
     * 并行执行（带超时）
     */
    public static <T> List<T> parallelWithTimeout(List<Supplier<T>> suppliers, long timeout, TimeUnit unit) {
        List<CompletableFuture<T>> futures = suppliers.stream()
                .map(AsyncExecutor::async)
                .toList();

        CompletableFuture<Void> allOf = CompletableFuture.allOf(futures.toArray(new CompletableFuture[0]));
        try {
            allOf.get(timeout, unit);
        } catch (TimeoutException e) {
            log.warn("Parallel execution timeout after {} {}", timeout, unit);
            futures.forEach(f -> f.cancel(true));
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            throw new RuntimeException(e);
        } catch (ExecutionException e) {
            throw new RuntimeException(e.getCause());
        }

        return futures.stream()
                .filter(f -> f.isDone() && !f.isCancelled())
                .map(f -> {
                    try {
                        return f.getNow(null);
                    } catch (Exception e) {
                        return null;
                    }
                })
                .filter(Objects::nonNull)
                .collect(Collectors.toList());
    }

    // ==================== Virtual Thread 支持 ====================

    /**
     * 使用Virtual Thread执行任务
     */
    public static void runVirtual(Runnable task) {
        Thread.ofVirtual()
                .name("virtual-task")
                .start(wrapRunnable(task));
    }

    /**
     * 使用Virtual Thread执行任务并返回结果
     */
    public static <T> CompletableFuture<T> callVirtual(Callable<T> callable) {
        CompletableFuture<T> future = new CompletableFuture<>();
        Thread.ofVirtual()
                .name("virtual-callable")
                .start(() -> {
                    try {
                        future.complete(wrapCallable(callable).call());
                    } catch (Exception e) {
                        future.completeExceptionally(e);
                    }
                });
        return future;
    }

    // ==================== 批量执行 ====================

    /**
     * 批量异步执行（Fire and Forget）
     */
    public static void runAllAsync(Collection<Runnable> tasks) {
        tasks.forEach(AsyncExecutor::runAsync);
    }

    /**
     * 批量执行并等待所有完成
     */
    public static void runAllAndWait(Collection<Runnable> tasks) {
        List<CompletableFuture<Void>> futures = tasks.stream()
                .map(AsyncExecutor::runAsync)
                .toList();
        CompletableFuture.allOf(futures.toArray(new CompletableFuture[0])).join();
    }

    /**
     * 批量执行并等待所有完成（带超时）
     */
    public static boolean runAllAndWait(Collection<Runnable> tasks, long timeout, TimeUnit unit) {
        List<CompletableFuture<Void>> futures = tasks.stream()
                .map(AsyncExecutor::runAsync)
                .toList();
        try {
            CompletableFuture.allOf(futures.toArray(new CompletableFuture[0]))
                    .get(timeout, unit);
            return true;
        } catch (TimeoutException e) {
            log.warn("Batch execution timeout");
            return false;
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            return false;
        } catch (ExecutionException e) {
            throw new RuntimeException(e.getCause());
        }
    }

    // ==================== 任务编排 ====================

    /**
     * 串行执行（按顺序）
     */
    @SafeVarargs
    public static <T> CompletableFuture<T> serial(Supplier<T>... suppliers) {
        CompletableFuture<T> future = async(suppliers[0]);
        for (int i = 1; i < suppliers.length; i++) {
            final int index = i;
            future = future.thenCompose(r -> async(suppliers[index]));
        }
        return future;
    }

    /**
     * 任意一个完成即返回
     */
    @SafeVarargs
    public static <T> CompletableFuture<T> anyOf(Supplier<T>... suppliers) {
        @SuppressWarnings("unchecked")
        CompletableFuture<T>[] futures = new CompletableFuture[suppliers.length];
        for (int i = 0; i < suppliers.length; i++) {
            futures[i] = async(suppliers[i]);
        }
        return CompletableFuture.anyOf(futures).thenApply(r -> (T) r);
    }

    // ==================== 上下文包装 ====================

    /**
     * 包装Runnable（传递上下文）
     */
    public static Runnable wrapRunnable(Runnable runnable) {
        Map<String, Object> context = ThreadLocalHolder.snapshot();
        Runnable wrapped = () -> {
            Map<String, Object> previous = ThreadLocalHolder.snapshot();
            try {
                ThreadLocalHolder.restore(context);
                runnable.run();
            } finally {
                ThreadLocalHolder.restore(previous);
            }
        };
        // 同时使用TTL包装，支持线程池场景
        return TtlRunnable.get(wrapped);
    }

    /**
     * 包装Supplier（传递上下文）
     */
    public static <T> Supplier<T> wrapSupplier(Supplier<T> supplier) {
        Map<String, Object> context = ThreadLocalHolder.snapshot();
        return () -> {
            Map<String, Object> previous = ThreadLocalHolder.snapshot();
            try {
                ThreadLocalHolder.restore(context);
                return supplier.get();
            } finally {
                ThreadLocalHolder.restore(previous);
            }
        };
    }

    /**
     * 包装Callable（传递上下文）
     */
    public static <T> Callable<T> wrapCallable(Callable<T> callable) {
        Map<String, Object> context = ThreadLocalHolder.snapshot();
        Callable<T> wrapped = () -> {
            Map<String, Object> previous = ThreadLocalHolder.snapshot();
            try {
                ThreadLocalHolder.restore(context);
                return callable.call();
            } finally {
                ThreadLocalHolder.restore(previous);
            }
        };
        return TtlCallable.get(wrapped);
    }

    /**
     * 包装Consumer（传递上下文）
     */
    public static <T> Consumer<T> wrapConsumer(Consumer<T> consumer) {
        Map<String, Object> context = ThreadLocalHolder.snapshot();
        return t -> {
            Map<String, Object> previous = ThreadLocalHolder.snapshot();
            try {
                ThreadLocalHolder.restore(context);
                consumer.accept(t);
            } finally {
                ThreadLocalHolder.restore(previous);
            }
        };
    }

    /**
     * 包装Function（传递上下文）
     */
    public static <T, R> Function<T, R> wrapFunction(Function<T, R> function) {
        Map<String, Object> context = ThreadLocalHolder.snapshot();
        return t -> {
            Map<String, Object> previous = ThreadLocalHolder.snapshot();
            try {
                ThreadLocalHolder.restore(context);
                return function.apply(t);
            } finally {
                ThreadLocalHolder.restore(previous);
            }
        };
    }
}
