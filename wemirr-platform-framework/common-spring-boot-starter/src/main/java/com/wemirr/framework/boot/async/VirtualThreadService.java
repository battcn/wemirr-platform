package com.wemirr.framework.boot.async;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.Duration;
import java.util.List;
import java.util.concurrent.*;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * 虚拟线程工具类 提供了多种异步任务执行方式，包括基本异步任务、并行处理集合、批量处理、超时控制、重试机制和性能测试等功能。
 * 应用场景：
 * <p>- 轻量级任务：计算密集型、I/O 密集型、网络请求密集型等
 * <p>- Web 服务：处理大量并发 HTTP 请求
 * <p>- 数据处理：并行处理大量数据
 * <p>- I/O 密集型任务：文件操作、数据库查询、网络请求
 * <p>- 微服务架构：服务间异步通信
 *
 * @author YanCh
 * Created on: 2025-12-05 14:40
 **/
public class VirtualThreadService {
    // 单例虚拟线程执行器
    private final ExecutorService VIRTUAL_EXECUTOR;
    private static final Logger log = LoggerFactory.getLogger(VirtualThreadService.class);

    public VirtualThreadService(ExecutorService virtualExecutor) {
        this.VIRTUAL_EXECUTOR = virtualExecutor;
    }

    /**
     * 执行单个异步任务
     *
     * @param task 要执行的任务
     * @return CompletableFuture
     */
    public CompletableFuture<Void> runAsync(Runnable task) {
        return CompletableFuture.runAsync(task, VIRTUAL_EXECUTOR);
    }

    /**
     * 执行单个异步任务并返回结果
     *
     * @param <T>      返回类型
     * @param supplier 要执行的任务
     * @return CompletableFuture
     */
    public <T> CompletableFuture<T> supplyAsync(Supplier<T> supplier) {
        return CompletableFuture.supplyAsync(supplier, VIRTUAL_EXECUTOR);
    }

    /**
     * 并行执行多个任务
     *
     * @param tasks 任务列表
     * @return 所有任务的CompletableFuture
     */
    public CompletableFuture<Void> runAllAsync(List<Runnable> tasks) {
        List<CompletableFuture<Void>> futures = tasks.stream()
                .map(this::runAsync)
                .toList();

        return CompletableFuture.allOf(futures.toArray(new CompletableFuture[0]));
    }

    /**
     * 并行执行多个任务并收集结果
     *
     * @param <T>       返回类型
     * @param suppliers 任务列表
     * @return 所有任务结果的列表
     */
    public <T> CompletableFuture<List<T>>
    supplyAllAsync(List<Supplier<T>> suppliers) {
        List<CompletableFuture<T>> futures = suppliers.stream()
                .map(this::supplyAsync)
                .toList();

        return CompletableFuture.allOf(futures.toArray(new CompletableFuture[0]))
                .thenApply(v -> futures.stream()
                        .map(CompletableFuture::join)
                        .collect(Collectors.toList()));
    }

    /**
     * 并行处理集合数据
     *
     * @param <T>       输入类型
     * @param <R>       输出类型
     * @param items     要处理的数据集合
     * @param processor 处理函数
     * @return 处理结果列表
     */
    public <T, R> CompletableFuture<List<R>> processCollectionAsync(
            List<T> items, Function<T, R> processor) {

        List<CompletableFuture<R>> futures = items.stream()
                .map(item -> supplyAsync(() -> processor.apply(item)))
                .toList();

        return CompletableFuture.allOf(futures.toArray(new CompletableFuture[0]))
                .thenApply(v -> futures.stream()
                        .map(CompletableFuture::join)
                        .collect(Collectors.toList()));
    }

    /**
     * 带超时的任务执行
     *
     * @param <T>      返回类型
     * @param supplier 要执行的任务
     * @param timeout  超时时间
     * @return 任务结果
     * @throws TimeoutException     超时异常
     * @throws ExecutionException   执行异常
     * @throws InterruptedException 中断异常
     */
    public <T> T executeWithTimeout(Supplier<T> supplier, Duration timeout)
            throws TimeoutException, ExecutionException, InterruptedException {

        CompletableFuture<T> future = supplyAsync(supplier);
        return future.get(timeout.toMillis(), TimeUnit.MILLISECONDS);
    }

    /**
     * 批量执行任务，控制并发数量
     *
     * @param <T>       输入类型
     * @param <R>       输出类型
     * @param items     要处理的数据
     * @param processor 处理函数
     * @param batchSize 批次大小
     * @return 处理结果列表
     */
    public <T, R> CompletableFuture<List<R>> processBatchAsync(
            List<T> items, Function<T, R> processor, int batchSize) {

        List<List<T>> batches = partitionList(items, batchSize);
        List<CompletableFuture<List<R>>> batchFutures = batches.stream()
                .map(batch -> processCollectionAsync(batch, processor))
                .toList();

        return CompletableFuture.allOf(batchFutures.toArray(new CompletableFuture[0]))
                .thenApply(v -> batchFutures.stream()
                        .flatMap(future -> future.join().stream())
                        .collect(Collectors.toList()));
    }

    /**
     * 创建重试任务
     *
     * @param <T>        返回类型
     * @param supplier   要执行的任务
     * @param maxRetries 最大重试次数
     * @param retryDelay 重试间隔
     * @return 任务结果
     */
    public <T> CompletableFuture<T> retryAsync(Supplier<T> supplier, int maxRetries, Duration retryDelay) {

        return supplyAsync(() -> {
            Exception lastException = null;

            for (int i = 0; i <= maxRetries; i++) {
                try {
                    return supplier.get();
                } catch (Exception e) {
                    lastException = e;
                    if (i < maxRetries) {
                        try {
                            Thread.sleep(retryDelay.toMillis());
                        } catch (InterruptedException ie) {
                            Thread.currentThread().interrupt();
                            throw new RuntimeException("重试被中断", ie);
                        }
                    }
                }
            }
            throw new RuntimeException("重试失败，已达到最大重试次数", lastException);
        });
    }

    /**
     * 执行定时任务（一次性）
     *
     * @param task  要执行的任务
     * @param delay 延迟时间
     * @return CompletableFuture
     */
    public CompletableFuture<Void> scheduleOnce(Runnable task, Duration delay) {
        return runAsync(() -> {
            try {
                Thread.sleep(delay.toMillis());
                task.run();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                throw new RuntimeException("定时任务被中断", e);
            }
        });
    }

    /**
     * 性能测试工具
     *
     * @param task     要测试的任务
     * @param taskName 任务名称
     * @return 执行时间（毫秒）
     */
    public long benchmark(Runnable task, String taskName) {
        long startTime = System.currentTimeMillis();

        try {
            runAsync(task).get();
        } catch (Exception e) {
            throw new RuntimeException("基准测试执行失败", e);
        }

        long duration = System.currentTimeMillis() - startTime;
        log.info("任务 [{}] 执行耗时: {} ms%n", taskName, duration);
        return duration;
    }

    /**
     * 创建固定大小的虚拟线程池（用于特殊场景）
     *
     * @param name 线程池名称
     * @return ExecutorService
     */
    public ExecutorService createNamedVirtualThreadExecutor(String name) {
        ThreadFactory factory = Thread.ofVirtual().name(name + "-", 0).factory();
        return Executors.newThreadPerTaskExecutor(factory);
    }

    /**
     * 获取当前线程信息
     *
     * @return 线程信息字符串
     */
    public String getCurrentThreadInfo() {
        Thread currentThread = Thread.currentThread();
        return String.format("Thread[%s] - Virtual: %s, Daemon: %s",
                currentThread.getName(),
                currentThread.isVirtual(),
                currentThread.isDaemon());
    }

    /**
     * 工具方法：分割列表
     */
    private static <T> List<List<T>> partitionList(List<T> list, int batchSize) {
        return IntStream.range(0, (list.size() + batchSize - 1) / batchSize)
                .mapToObj(i -> list.subList(
                        i * batchSize,
                        Math.min((i + 1) * batchSize, list.size())
                ))
                .collect(Collectors.toList());
    }
}
