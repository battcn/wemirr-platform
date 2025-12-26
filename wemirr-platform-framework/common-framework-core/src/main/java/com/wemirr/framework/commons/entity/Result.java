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

package com.wemirr.framework.commons.entity;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.wemirr.framework.commons.JacksonUtils;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * 统一API响应结果封装
 * <p>用于规范化接口返回数据格式，支持成功、失败、验证异常等多种场景</p>
 *
 * <h3>使用示例</h3>
 * <pre>{@code
 * // 成功响应
 * return Result.success(data);
 * return Result.ok(data);
 *
 * // 失败响应
 * return Result.fail("操作失败");
 * return Result.fail(400, "参数错误");
 *
 * // 验证失败
 * return Result.validFail("用户名不能为空");
 * }</pre>
 *
 * @param <T> 响应数据类型
 * @author Levin
 * @since 1.0.0
 */
@Getter
@Setter
@Accessors(chain = true)
@NoArgsConstructor
public class Result<T> implements Serializable {

    // ==================== 响应消息常量 ====================

    /**
     * 默认成功消息
     */
    public static final String OPERATION_SUCCESS = "操作成功!";

    /**
     * 默认错误消息
     */
    public static final String DEF_ERROR_MESSAGE = "系统繁忙，请稍候再试";

    /**
     * 熔断超时消息
     */
    public static final String HYSTRIX_ERROR_MESSAGE = "请求超时，请稍候再试";

    // ==================== 响应码常量 ====================

    /**
     * 成功响应码
     */
    public static final int SUCCESS_CODE = 200;

    /**
     * 通用失败响应码
     */
    public static final int FAIL_CODE = -1;

    /**
     * 超时响应码
     */
    public static final int TIMEOUT_CODE = -2;

    /**
     * 参数验证异常响应码
     */
    public static final int VALID_EX_CODE = -9;

    /**
     * 业务操作异常响应码
     */
    public static final int OPERATION_EX_CODE = -400;
    
    @Schema(description = "是否成功")
    private boolean successful = true;
    
    @Schema(description = "消息ID")
    private int code;
    
    @Schema(description = "消息内容")
    private String message;
    
    @Schema(description = "时间戳")
    private long timestamp;
    
    @JsonInclude(JsonInclude.Include.NON_NULL)
    @Schema(description = "返回数据")
    private T data;
    
    private Result(Builder<T> builder) {
        this.code = builder.code;
        this.message = builder.message;
        this.timestamp = builder.timestamp;
        this.successful = builder.successful;
        this.data = builder.data;
    }
    
    public Result(int code, T data, String message, boolean successful) {
        this.code = code;
        this.data = data;
        this.message = message;
        this.successful = successful;
        this.timestamp = System.currentTimeMillis();
    }
    
    public Result(int code, T data, String message) {
        this.code = code;
        this.data = data;
        this.message = message;
        this.successful = code == SUCCESS_CODE;
        this.timestamp = System.currentTimeMillis();
    }
    
    public static <E> Result<E> result(int code, E data, String msg) {
        return new Result<>(code, data, msg);
    }
    
    public static <E> Result<E> success() {
        return new Result<>(SUCCESS_CODE, null, OPERATION_SUCCESS);
    }
    
    public static <T> Result<T> success(T data) {
        return getResponse(SUCCESS_CODE, OPERATION_SUCCESS, true, data);
    }
    
    public static <T> Result<T> success(String message, T data) {
        return getResponse(SUCCESS_CODE, message, true, data);
    }
    
    public static <T> Result<T> success(String message) {
        return success(message, null);
    }
    
    public static Result<String> success(int code) {
        return success(code, OPERATION_SUCCESS);
    }
    
    public static <T> Result<T> success(int code, String message, T data) {
        return getResponse(code, message, true, data);
    }
    
    public static Result<String> success(int code, String message) {
        return success(code, message, null);
    }
    
    public static <T> Result<T> success(int code, T data) {
        return getResponse(code, OPERATION_SUCCESS, true, data);
    }
    
    public static <T> Result<T> ok(T data) {
        return success(SUCCESS_CODE, OPERATION_SUCCESS, data);
    }
    
    public static <E> Result<E> validFail(String msg) {
        return new Result<>(VALID_EX_CODE, null, (msg == null || msg.isEmpty()) ? DEF_ERROR_MESSAGE : msg);
    }
    
    public static <E> Result<E> validFail(String msg, Object... args) {
        String message = (msg == null || msg.isEmpty()) ? DEF_ERROR_MESSAGE : msg;
        return new Result<>(VALID_EX_CODE, null, String.format(message, args));
    }
    
    public static <E> Result<E> fail(String msg, Object... args) {
        String message = (msg == null || msg.isEmpty()) ? DEF_ERROR_MESSAGE : msg;
        return new Result<>(OPERATION_EX_CODE, null, String.format(message, args));
    }
    
    /**
     * 请求失败消息，根据异常类型，获取不同的提供消息
     *
     * @param throwable 异常
     * @param <E>       E
     * @return RPC调用结果
     */
    public static <E> Result<E> fail(Throwable throwable) {
        return fail(FAIL_CODE, throwable != null ? throwable.getMessage() : DEF_ERROR_MESSAGE);
    }
    
    public static <T> Result<T> fail(String message, T data) {
        return getResponse(400, message, false, data);
    }
    
    public static <T> Result<T> fail(String message) {
        return new Result<>(OPERATION_EX_CODE, null, message);
    }
    
    public static <T> Result<T> fail(int code, String message) {
        return getResponse(code, message, false, null);
    }
    
    public static <T> Result<T> fail(int code, String message, T data) {
        return getResponse(code, message, false, data);
    }
    
    public static <T> Result<T> getResponse(int code, String message, boolean successful, T data) {
        return new Builder<T>(code, System.currentTimeMillis(), successful).message(message).data(data).build();
    }
    
    @Override
    public String toString() {
        return JacksonUtils.toJson(this);
    }
    
    public static class Builder<T> {
        
        private final int code;
        private final long timestamp;
        private final boolean successful;
        private String message;
        private T data;
        
        public Builder(int code, long timestamp, boolean successful) {
            this.code = code;
            this.timestamp = timestamp;
            this.successful = successful;
        }
        
        public Builder<T> message(String message) {
            this.message = message;
            return this;
        }
        
        public Builder<T> data(T data) {
            this.data = data;
            return this;
        }
        
        public Result<T> build() {
            return new Result<>(this);
        }
    }
}
