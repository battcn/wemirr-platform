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
import lombok.Setter;

/**
 * 检查型异常
 *
 * @author Levin
 * @since 2019-03-13
 */
@Setter
@EqualsAndHashCode(callSuper = true)
public class RedisLockException extends RuntimeException {
    
    private int code;
    
    public RedisLockException(IntEnum exception) {
        super(exception.desc());
        this.setCode(exception.type());
    }
    
    public RedisLockException(IntEnum exception, String message) {
        super(message);
        this.setCode(exception.type());
    }
    
    public RedisLockException(String message) {
        super(message);
        this.setCode(CommonError.REQUEST_PARAM_ERROR.type());
    }
    
    public RedisLockException(String message, Throwable cause) {
        super(message, cause);
    }
    
    public RedisLockException(Throwable cause) {
        super(cause);
    }
    
    public RedisLockException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
