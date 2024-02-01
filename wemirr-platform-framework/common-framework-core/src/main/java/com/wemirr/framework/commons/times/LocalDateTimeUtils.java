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
package com.wemirr.framework.commons.times;

import com.wemirr.framework.commons.exception.CheckedException;
import org.apache.commons.lang3.StringUtils;

import java.time.Duration;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.Optional;

/**
 * JDK1.8 日期转换工具类
 *
 * @author Levin
 * @version 1.0.0
 */
public class LocalDateTimeUtils {
    
    public static final DateTimeFormatter YYYY_MM_DD = DateTimeFormatter.ofPattern(TimeConstants.FORMAT_YYYY_MM_DD);
    public static final DateTimeFormatter YYYY_MM_DD_HH_MM = DateTimeFormatter.ofPattern(TimeConstants.FORMAT_YYYY_MM_DD_HH_MM);
    public static final DateTimeFormatter YYYY_MM_DD_HH_MM_SS = DateTimeFormatter.ofPattern(TimeConstants.FORMAT_YYYY_MM_DD_HH_MM_SS);
    
    public static String now() {
        return format(LocalDateTime.now(), TimeConstants.FORMAT_YYYY_MM_DD_HH_MM_SS);
    }
    
    public static String now(String format) {
        return format(LocalDateTime.now(), format);
    }
    
    /**
     * String 日期转换成格式为 yyyy-MM-dd HH:mm:ss 的 LocalDateTime
     *
     * @param dateTime 日期
     * @return 转换结果
     */
    public static LocalDateTime parse(String dateTime) {
        return parse(dateTime, TimeConstants.FORMAT_YYYY_MM_DD_HH_MM_SS);
    }
    
    /**
     * String 日期转换成指定格式的 LocalDateTime
     *
     * @param dateTime 日期
     * @param format   转换的日期格式
     * @return 转换结果
     */
    public static LocalDateTime parse(String dateTime, String format) {
        if (StringUtils.isBlank(format)) {
            throw new CheckedException("format 不能为空");
        }
        if (StringUtils.equals(TimeConstants.FORMAT_YYYY_MM_DD, format)) {
            return LocalDateTime.parse(dateTime, YYYY_MM_DD);
        } else if (StringUtils.equals(TimeConstants.FORMAT_YYYY_MM_DD_HH_MM, format)) {
            return LocalDateTime.parse(dateTime, YYYY_MM_DD_HH_MM);
        } else if (StringUtils.equals(TimeConstants.FORMAT_YYYY_MM_DD_HH_MM_SS, format)) {
            return LocalDateTime.parse(dateTime, YYYY_MM_DD_HH_MM_SS);
        } else {
            return LocalDateTime.parse(dateTime, DateTimeFormatter.ofPattern(format));
        }
    }
    
    /**
     * String 日期转换成格式为 yyyy-MM-dd HH:mm:ss 的 LocalDateTime
     *
     * @param localDateTime 日期
     * @return 转换结果
     */
    public static String format(LocalDateTime localDateTime) {
        return format(localDateTime, TimeConstants.FORMAT_YYYY_MM_DD_HH_MM_SS);
    }
    
    public static String format(LocalDateTime localDateTime, String format) {
        if (StringUtils.isBlank(format)) {
            throw new CheckedException("format 不能为空");
        }
        if (StringUtils.equals(TimeConstants.FORMAT_YYYY_MM_DD, format)) {
            return localDateTime.format(YYYY_MM_DD);
        } else if (StringUtils.equals(TimeConstants.FORMAT_YYYY_MM_DD_HH_MM, format)) {
            return localDateTime.format(YYYY_MM_DD_HH_MM);
        } else if (StringUtils.equals(TimeConstants.FORMAT_YYYY_MM_DD_HH_MM_SS, format)) {
            return localDateTime.format(YYYY_MM_DD_HH_MM_SS);
        } else {
            return localDateTime.format(DateTimeFormatter.ofPattern(format));
        }
    }
    
    /**
     * 根据两个时间段，计算出耗时
     *
     * @param startTime 开始时间
     * @param endTime   结束时间
     * @return xx天xx小时xx分xx秒
     */
    public static String getDuration(Instant endTime, Instant startTime) {
        startTime = Optional.ofNullable(startTime).orElse(new Date().toInstant());
        endTime = Optional.ofNullable(endTime).orElse(new Date().toInstant());
        Duration duration = Duration.between(startTime, endTime);
        
        long days = duration.toDays();
        long hours = duration.toHoursPart();
        long minutes = duration.toMinutesPart();
        long seconds = duration.toSecondsPart();
        
        return String.format("%d天%d小时%d分%d秒", days, hours, minutes, seconds);
    }
    
}
