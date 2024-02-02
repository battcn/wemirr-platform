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

package com.wemirr.framework.boot.sensitive;

import com.fasterxml.jackson.annotation.JacksonAnnotationsInside;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author Levin
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.FIELD, ElementType.TYPE})
@JacksonAnnotationsInside
@JsonSerialize(using = SensitiveSerialize.class)
public @interface Sensitive {
    
    /**
     * 脱敏规则
     */
    Type type();
    
    int startInclude() default 0;
    
    int endInclude() default 0;
    
    /**
     * 脱敏策略（有策略的情况下）
     */
    Class<? extends SensitiveStrategy> strategy() default DefaulSensitiveStrategy.class;
    
    enum Type {
        /**
         * 策略模式
         */
        STRATEGY,
        /**
         * 未知的
         */
        UN_KNOW,
        /**
         * 中文名
         */
        CHINESE_NAME,
        /**
         * 身份证号
         */
        ID_CARD,
        /**
         * 座机号
         */
        FIXED_PHONE,
        /**
         * 手机号
         */
        MOBILE_PHONE,
        /**
         * 地址
         */
        ADDRESS,
        /**
         * 电子邮件
         */
        EMAIL,
        /**
         * 密码
         */
        PASSWORD,
        /**
         * 中国大陆车牌，包含普通车辆、新能源车辆
         */
        CAR_LICENSE,
        /**
         * 银行卡
         */
        BANK_CARD,
        /**
         * IPv4地址
         */
        IPV4,
        /**
         * IPv6地址
         */
        IPV6,
        /**
         * 定义了一个first_mask的规则，只显示第一个字符。
         */
        FIRST_MASK
    }
    
}