package com.wemirr.framework.boot.async;

import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;

import java.time.Duration;
import java.time.Instant;
import java.util.List;
import java.util.concurrent.*;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.IntStream;

/**
 * JDK21 虚拟线程工具服务
 * <p>提供多种异步任务执行方式，包括并行处理、超时控制、重试机制等</p>
 *
 * <h3>应用场景</h3>
 * <ul>
 *   <li>I/O密集型任务：文件操作、数据库查询、网络请求</li>
 *   <li>Web服务：处理大量并发HTTP请求</li>
 *   <li>数据处理：并行处理大量数据</li>
 *   <li>微服务架构：服务间异步通信</li>
 * </ul>
 *
 * <h3>线程安全</h3>
 * <p>本类所有方法线程安全，可在多线程环境下安全使用</p>
 *
 * @author YanCh
 * @since 1.0.0
 */
@Slf4j
public class VirtualThreadService {

    /**
     * 全局共享的虚拟线程执行器
     * <p>JDK21虚拟线程执行器轻量级，每个任务创建新的虚拟线程</p>
     * <p>注意：该执行器不需要显式关闭，虚拟线程完成后自动回收</p>
     */
    private final ExecutorService virtualExecutor = Executors.newVirtualThreadPerTaskExecutor();

    /**
     * 执行单个异步任务 (Fire-and-Forget)
     */
    public CompletableFuture<Void> runAsync(Runnable task) {
        return CompletableFuture.runAsync(task, virtualExecutor);
    }

    /**
     * 执行单个异步任务并返回结果
     * @param supplier   要执行的业务逻辑 (e.g. 调用不稳定的第三方 API)
     */
    public <T> CompletableFuture<T> supplyAsync(Supplier<T> supplier) {
        return CompletableFuture.supplyAsync(supplier, virtualExecutor);
    }

    /**
     * 【优化】并行处理集合数据（带并发度限制）
     * <p>
     * 虚拟线程虽然廉价，但数据库连接池只有 100 个。
     * 如果不限流，瞬间 10000 个虚拟线程会把 DB 打挂。
     *
     * @param items       数据源
     * @param processor   业务逻辑
     * @param parallelism 最大并发度（例如：DB 连接池大小 / 2）
     */
    public <T, R> List<R> processCollectionParallel(List<T> items, Function<T, R> processor, int parallelism) {
        if (items == null || items.isEmpty()) {
            return List.of();
        }
        // 使用 Semaphore 限制同时运行的虚拟线程数量（保护下游）
        Semaphore semaphore = new Semaphore(parallelism);
        // 使用 JDK 21 新特性：ExecutorService 实现了 AutoCloseable
        // 当 try 块结束时，会自动调用 close()，一直阻塞直到所有提交的任务完成
        try (var executor = Executors.newVirtualThreadPerTaskExecutor()) {
            List<Future<R>> futures = items.stream()
                    .map(item -> executor.submit(() -> {
                        try {
                            semaphore.acquire(); // 获取许可
                            return processor.apply(item);
                        } finally {
                            semaphore.release(); // 释放许可
                        }
                    }))
                    .toList();

            // JDK21范式：try-with-resources自动等待所有任务完成
            return futures.stream()
                    .map(future -> {
                        try {
                            return future.get();
                        } catch (InterruptedException e) {
                            Thread.currentThread().interrupt();
                            throw new RuntimeException("任务被中断", e);
                        } catch (Exception e) {
                            throw new RuntimeException("任务执行失败", e);
                        }
                    })
                    .toList();
        } catch (Exception e) {
            log.error("并行处理异常", e);
            throw new RuntimeException(e);
        }
    }

    /**
     * 带超时的任务执行 (使用 JDK 21 InvokeAll 范式)
     * <p>
     * 在指定的超时时间内执行任务。如果任务超时，将强制取消（中断）该任务并抛出异常。
     *
     * <p><strong>设计思想 (JDK 21)：</strong>
     * <br>这里采用了 "One Executor Per Task" (每个任务一个执行器) 的模式。
     * <br>在传统线程池模型中，创建 Executor 是昂贵的。但在 JDK 21 中，{@code newVirtualThreadPerTaskExecutor}
     * 非常轻量，它不持有任何操作系统资源。因此，我们可以放心地在方法内部创建一个临时的 Executor，
     * 利用 try-with-resources 自动管理其生命周期。
     *
     * @param <T>     任务返回值的类型
     * @param task    要执行的具体任务 (Callable)，支持返回值。
     * 注意：任务代码应当响应中断 (Interruption)，否则即使超时取消，后台逻辑可能仍无法立即停止。
     * @param timeout 最大等待时间 (Duration)。
     * @return 任务成功执行后的结果。
     * @throws RuntimeException 如果任务执行期间发生其他异常 (由 @SneakyThrows 包装)。
     */
    @SneakyThrows
    public <T> T executeWithTimeout(Callable<T> task, Duration timeout) {
        // 使用 try-with-resources 创建一个临时的虚拟线程执行器
        // 当 try 块结束时，executor.close() 会被自动调用
        try (var executor = Executors.newVirtualThreadPerTaskExecutor()) {
            // 提交任务，获取 Future 句柄
            Future<T> future = executor.submit(task);
            try {
                // 阻塞等待结果，直到超时
                return future.get(timeout.toMillis(), TimeUnit.MILLISECONDS);
            } catch (TimeoutException e) {
                // 【关键步骤】一旦超时，必须显式取消任务
                // 参数 true 表示如果线程正在运行，则发送中断信号 (interrupt)
                // 虚拟线程对中断的响应非常灵敏，这通常能立即停止 I/O 操作
                future.cancel(true);
                throw e;
            }
        }
    }

    /**
     * 批量分片处理 (更加优雅的流式处理)
     * <p>
     * 将大集合切割成多个小批次，每个批次在一个独立的虚拟线程中运行。
     * 例如：有 1000 条数据，batchSize 为 100，则会启动 10 个虚拟线程，
     * 每个线程内部串行处理这 100 条数据，这 10 个线程之间是并行的。
     *
     * @param <T>       输入数据的类型 (Type of input elements)
     * @param <R>       返回结果的类型 (Type of result elements)
     * @param items     要处理的数据源列表 (e.g. 1000 个用户ID)
     * @param processor 具体的业务处理逻辑 (e.g. 根据 ID 查询用户信息)
     * 这是一个函数，输入 T，返回 R。
     * @param batchSize 每个批次的大小 (e.g. 100)。
     * 决定了并发的粒度：
     * - batchSize 越小，并发度越高，生成的虚拟线程越多。
     * - batchSize 越大，并发度越低，每个线程处理的任务越重。
     * @return 处理后的结果列表 (List<R>)，顺序不保证与原 List 一致 (取决于合并时的流处理)，
     * 但在当前实现中，利用 Stream 是有序合并的，所以结果顺序通常能保持一致。
     */
    public <T, R> List<R> processBatch(List<T> items, Function<T, R> processor, int batchSize) {
        // 计算分片
        int total = items.size();
        int chunks = (total + batchSize - 1) / batchSize;

        try (var executor = Executors.newVirtualThreadPerTaskExecutor()) {
            List<Future<List<R>>> batchFutures = IntStream.range(0, chunks)
                    .mapToObj(i -> {
                        int start = i * batchSize;
                        int end = Math.min(start + batchSize, total);
                        List<T> batch = items.subList(start, end);

                        // 提交一个批次任务
                        return executor.submit(() -> batch.stream().map(processor).toList());
                    })
                    .toList();

            // 等待所有批次完成并聚合结果
            return batchFutures.stream()
                    .map(future -> {
                        try {
                            return future.get();
                        } catch (InterruptedException e) {
                            Thread.currentThread().interrupt();
                            throw new RuntimeException("批量任务被中断", e);
                        } catch (Exception e) {
                            throw new RuntimeException("批量任务执行失败", e);
                        }
                    })
                    .flatMap(List::stream)
                    .toList();
        }
    }


    /**
     * 带重试机制的任务执行器 (专为虚拟线程优化)
     * <p>
     * 该方法会尝试执行给定的任务，如果任务抛出异常，则暂停一段时间后重试，直到成功或达到最大重试次数。
     * <p>
     * <strong>为什么适合虚拟线程？</strong>
     * <br>在传统线程模型中，Thread.sleep 会阻塞操作系统线程，导致资源浪费。
     * <br>而在 JDK 21 虚拟线程中，调用 Thread.sleep 只会 "卸载 (Unmount)" 当前虚拟线程，
     * 底层的载体线程 (Carrier Thread) 会立即去执行其他任务。这意味着即使有 1000 个任务在 sleep 等待重试，
     * 也不会占用任何系统 CPU 资源。
     *
     * @param <T>        任务返回值的类型
     * @param supplier   要执行的业务逻辑 (e.g. 调用不稳定的第三方 API)
     * @param maxRetries 最大重试次数 (不包含首次执行)。
     * 例如：设置为 3，表示最多执行 4 次 (1次首次 + 3次重试)。
     * @param retryDelay 每次重试之间的等待间隔 (Duration)。
     * @return 任务执行成功后的结果
     * @throws RuntimeException 如果在所有重试机会用尽后仍然失败，抛出此异常 (包含最后一次的原始异常)
     */
    public <T> T retry(Supplier<T> supplier, int maxRetries, Duration retryDelay) {
        Exception lastException = null;
        for (int i = 0; i <= maxRetries; i++) {
            try {
                return supplier.get();
            } catch (Exception e) {
                lastException = e;
                if (i == maxRetries) {
                    break;
                }
                log.warn("任务执行失败，准备第 {} 次重试. 原因: {}", i + 1, e.getMessage());
                try {
                    // 在虚拟线程中，Thread.sleep 是极低成本的（会自动卸载线程，不阻塞系统线程）
                    Thread.sleep(retryDelay.toMillis());
                } catch (InterruptedException ie) {
                    Thread.currentThread().interrupt();
                    throw new RuntimeException("重试等待中断", ie);
                }
            }
        }
        throw new RuntimeException("重试耗尽，最终失败", lastException);
    }

    /**
     * 简易性能基准测试工具 (专为虚拟线程场景设计)
     * <p>
     * 创建一个全新的、命名的虚拟线程来执行指定任务，并记录其从开始到结束的挂钟时间 (Wall-clock time)。
     *
     * <p><strong>设计意图：</strong>
     * <br>此方法强制通过 {@code Thread.ofVirtual()} 启动一个新的线程，而不是直接在当前线程运行。
     * 这样做是为了确保测试环境的隔离性，验证代码在虚拟线程环境下的真实表现（例如验证 ThreadLocal 在虚拟线程中的行为，或测试虚拟线程调度开销）。
     *
     * <p><strong>注意：</strong>
     * <br>虽然任务是在虚拟线程中异步执行的，但本方法通过 {@code .join()} 阻塞了调用线程，
     * 直到测试任务完成。因此，这是一个同步方法。
     *
     * @param task     要测试的业务代码块 (Runnable)。
     * 建议包含完整的业务流程，例如 "读取DB -> 计算 -> 写入Redis"。
     * @param taskName 任务名称。
     * 用于日志标识和设置虚拟线程名称 (例如 "bench-taskName")，便于在调试器或 Dump 文件中追踪。
     * @throws RuntimeException 如果在等待线程结束时被中断 (由 @SneakyThrows 包装 InterruptedException)
     */
    @SneakyThrows
    public void benchmark(Runnable task, String taskName) {
        Instant start = Instant.now();
        // 直接在当前虚拟线程执行，或者强制新开一个
        Thread.ofVirtual().name("bench-" + taskName).start(() -> {
            try {
                task.run();
                log.info("任务 [{}] 完成，耗时: {} ms", taskName, Duration.between(start, Instant.now()).toMillis());
            } catch (Exception e) {
                log.error("任务 [{}] 失败", taskName, e);
            }
        }).join();
    }
}
