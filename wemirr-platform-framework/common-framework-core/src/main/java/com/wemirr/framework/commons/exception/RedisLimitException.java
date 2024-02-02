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

package com.wemirr.framework.commons.exception;

import com.wemirr.framework.commons.entity.enums.CommonError;
import com.wemirr.framework.commons.entity.enums.IntEnum;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

/**
 * 检查型异常
 *
 * @author Levin
 * @since 2019-03-13
 */
@Getter
@Setter
@EqualsAndHashCode(callSuper = true)
public class RedisLimitException extends RuntimeException {
    
    private int code;
    
    public RedisLimitException(IntEnum exception) {
        super(exception.desc());
        this.setCode(exception.type());
    }
    
    public RedisLimitException(IntEnum exception, String message) {
        super(message);
        this.setCode(exception.type());
    }
    
    public RedisLimitException(String message) {
        super(message);
        this.setCode(CommonError.TOO_MANY_REQUESTS.type());
    }
    
    public RedisLimitException(String message, Throwable cause) {
        super(message, cause);
    }
    
    public RedisLimitException(Throwable cause) {
        super(cause);
    }
    
    public RedisLimitException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
