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
package com.wemirr.framework.commons.validation.time;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.time.format.DateTimeFormatter;

/**
 * 日期格式验证
 *
 * @author Levin
 * @version 1.0.0
 * @since 2020-06-06
 */
public class LocalDateTimeValidator implements ConstraintValidator<LocalDateTime, java.time.LocalDateTime> {
    
    private LocalDateTime localDateTime;
    
    @Override
    public void initialize(LocalDateTime localDateTime) {
        this.localDateTime = localDateTime;
    }
    
    @Override
    public boolean isValid(java.time.LocalDateTime value, ConstraintValidatorContext context) {
        // 如果 value 为空则不进行格式验证，为空验证可以使用 @NotBlank @NotNull @NotEmpty 等注解来进行控制，职责分离
        if (value == null) {
            return true;
        }
        try {
            value.format(DateTimeFormatter.ofPattern(localDateTime.format()));
        } catch (Exception e) {
            return false;
        }
        return true;
    }
}