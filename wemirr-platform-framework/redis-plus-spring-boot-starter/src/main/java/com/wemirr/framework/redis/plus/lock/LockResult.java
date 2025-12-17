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

import java.util.Optional;
import java.util.function.Consumer;
import java.util.function.Function;

/**
 * 锁执行结果（使用JDK21 Sealed类特性，限制子类）
 *
 * @param <T> 结果类型
 * @author Levin
 */
public sealed interface LockResult<T> permits LockResult.Success, LockResult.Failure {

    /**
     * 是否成功
     */
    boolean isSuccess();

    /**
     * 获取结果值
     */
    Optional<T> getValue();

    /**
     * 获取异常
     */
    Optional<Throwable> getError();

    /**
     * 成功时执行
     */
    default LockResult<T> onSuccess(Consumer<T> consumer) {
        if (this instanceof Success<T> success) {
            consumer.accept(success.value);
        }
        return this;
    }

    /**
     * 失败时执行
     */
    default LockResult<T> onFailure(Consumer<Throwable> consumer) {
        if (this instanceof Failure<T> failure) {
            consumer.accept(failure.error);
        }
        return this;
    }

    /**
     * 映射结果
     */
    default <R> LockResult<R> map(Function<T, R> mapper) {
        if (this instanceof Success<T> success) {
            return new Success<>(mapper.apply(success.value));
        }
        return new Failure<>(((Failure<T>) this).error);
    }

    /**
     * 获取结果或默认值
     */
    default T getOrElse(T defaultValue) {
        return getValue().orElse(defaultValue);
    }

    /**
     * 获取结果或抛出异常
     */
    default T getOrThrow() {
        if (this instanceof Success<T> success) {
            return success.value;
        }
        Throwable error = ((Failure<T>) this).error;
        if (error instanceof RuntimeException re) {
            throw re;
        }
        throw new RuntimeException(error);
    }

    /**
     * 成功结果
     */
    record Success<T>(T value) implements LockResult<T> {
        @Override
        public boolean isSuccess() {
            return true;
        }

        @Override
        public Optional<T> getValue() {
            return Optional.ofNullable(value);
        }

        @Override
        public Optional<Throwable> getError() {
            return Optional.empty();
        }
    }

    /**
     * 失败结果
     */
    record Failure<T>(Throwable error) implements LockResult<T> {
        @Override
        public boolean isSuccess() {
            return false;
        }

        @Override
        public Optional<T> getValue() {
            return Optional.empty();
        }

        @Override
        public Optional<Throwable> getError() {
            return Optional.ofNullable(error);
        }
    }

    /**
     * 创建成功结果
     */
    static <T> LockResult<T> success(T value) {
        return new Success<>(value);
    }

    /**
     * 创建失败结果
     */
    static <T> LockResult<T> failure(Throwable error) {
        return new Failure<>(error);
    }
}
