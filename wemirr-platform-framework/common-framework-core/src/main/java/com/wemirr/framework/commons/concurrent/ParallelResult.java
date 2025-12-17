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

import java.util.List;
import java.util.function.Consumer;

/**
 * 并行执行结果封装（JDK21 Record）
 * <p>封装并行任务执行的结果，包含成功/失败统计、执行时间等信息</p>
 *
 * <h3>使用示例</h3>
 * <pre>{@code
 * ParallelResult<User> result = AsyncExecutor.parallelWithResult(tasks);
 *
 * // 检查是否全部成功
 * if (result.allSuccess()) {
 *     List<User> users = result.successValues();
 * }
 *
 * // 处理失败情况
 * if (result.hasFailures()) {
 *     result.onFailure(error -> log.error("Task failed", error));
 * }
 *
 * // 链式处理
 * result.onSuccess(user -> process(user))
 *       .onFailure(error -> log.error("Failed", error));
 * }</pre>
 *
 * @param results       所有任务执行结果列表
 * @param totalCount    任务总数
 * @param successCount  成功数量
 * @param failureCount  失败数量
 * @param elapsedMillis 总执行耗时（毫秒）
 * @param <T>           结果类型
 * @author Levin
 * @since 1.0.0
 */
public record ParallelResult<T>(
        List<TaskResult<T>> results,
        long totalCount,
        long successCount,
        long failureCount,
        long elapsedMillis
) {

    /**
     * 是否全部成功
     */
    public boolean allSuccess() {
        return failureCount == 0;
    }

    /**
     * 是否有失败
     */
    public boolean hasFailures() {
        return failureCount > 0;
    }

    /**
     * 获取所有成功的值
     */
    public List<T> successValues() {
        return results.stream()
                .filter(TaskResult::success)
                .map(TaskResult::value)
                .toList();
    }

    /**
     * 获取所有失败的结果
     */
    public List<TaskResult<T>> failures() {
        return results.stream()
                .filter(r -> !r.success())
                .toList();
    }

    /**
     * 处理成功结果
     */
    public ParallelResult<T> onSuccess(Consumer<T> consumer) {
        results.stream()
                .filter(TaskResult::success)
                .map(TaskResult::value)
                .forEach(consumer);
        return this;
    }

    /**
     * 处理失败结果
     */
    public ParallelResult<T> onFailure(Consumer<Throwable> consumer) {
        results.stream()
                .filter(r -> !r.success())
                .map(TaskResult::error)
                .forEach(consumer);
        return this;
    }

    /**
     * 单个任务结果
     */
    public record TaskResult<T>(
            int index,
            boolean success,
            T value,
            Throwable error,
            long elapsedMillis
    ) {
        /**
         * 创建成功结果
         */
        public static <T> TaskResult<T> success(int index, T value, long elapsedMillis) {
            return new TaskResult<>(index, true, value, null, elapsedMillis);
        }

        /**
         * 创建失败结果
         */
        public static <T> TaskResult<T> failure(int index, Throwable error, long elapsedMillis) {
            return new TaskResult<>(index, false, null, error, elapsedMillis);
        }
    }
}
