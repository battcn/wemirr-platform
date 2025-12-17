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

package com.wemirr.framework.redis.plus.exception;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

/**
 * Redis 限流异常
 *
 * @author Levin
 */
@Getter
@Setter
@EqualsAndHashCode(callSuper = true)
public class RedisLimitException extends RuntimeException {

    private int code;

    public RedisLimitException(String message) {
        super(message);
        this.code = 429;
    }

    public RedisLimitException(int code, String message) {
        super(message);
        this.code = code;
    }

    public RedisLimitException(String message, Throwable cause) {
        super(message, cause);
        this.code = 429;
    }

    public RedisLimitException(Throwable cause) {
        super(cause);
        this.code = 429;
    }

    public RedisLimitException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
        this.code = 429;
    }
}
