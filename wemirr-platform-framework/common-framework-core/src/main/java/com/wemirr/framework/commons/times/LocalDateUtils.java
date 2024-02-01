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

import java.time.LocalDate;
import java.time.Month;
import java.time.format.DateTimeFormatter;

/**
 * JDK1.8 日期转换工具类
 *
 * @author Levin
 * @version 1.0.0
 */
public class LocalDateUtils {
    
    public static final DateTimeFormatter YYYY = DateTimeFormatter.ofPattern(TimeConstants.DEFAULT_YEAR_FORMAT);
    public static final DateTimeFormatter YYYY_MM = DateTimeFormatter.ofPattern(TimeConstants.DEFAULT_MONTH_FORMAT);
    public static final DateTimeFormatter YYYY_MM_DD = DateTimeFormatter.ofPattern(TimeConstants.DEFAULT_DATE_FORMAT);
    
    public static String now() {
        return format(LocalDate.now(), TimeConstants.DEFAULT_DATE_FORMAT);
    }
    
    public static String now(String format) {
        return format(LocalDate.now(), format);
    }
    
    /**
     * String 日期转换成格式为 yyyy-MM-dd HH:mm:ss 的 LocalDate
     *
     * @param dateTime 日期
     * @return 转换结果
     */
    public static LocalDate parse(String dateTime) {
        return parse(dateTime, TimeConstants.DEFAULT_DATE_FORMAT);
    }
    
    /**
     * String 日期转换成指定格式的 LocalDate
     *
     * @param localDate 日期
     * @param format    转换的日期格式
     * @return 转换结果
     */
    public static LocalDate parse(String localDate, String format) {
        if (StringUtils.isBlank(format)) {
            throw new CheckedException("format 不能为空");
        }
        if (StringUtils.equals(TimeConstants.DEFAULT_YEAR_FORMAT, format)) {
            return LocalDate.parse(localDate, YYYY);
        } else if (StringUtils.equals(TimeConstants.DEFAULT_MONTH_FORMAT, format)) {
            return LocalDate.parse(localDate, YYYY_MM);
        } else if (StringUtils.equals(TimeConstants.DEFAULT_DATE_FORMAT, format)) {
            return LocalDate.parse(localDate, YYYY_MM_DD);
        } else {
            return LocalDate.parse(localDate, DateTimeFormatter.ofPattern(format));
        }
    }
    
    /**
     * String 日期转换成格式为 yyyy-MM-dd HH:mm:ss 的 LocalDate
     *
     * @param localDate 日期
     * @return 转换结果
     */
    public static String format(LocalDate localDate) {
        return format(localDate, TimeConstants.FORMAT_YYYY_MM_DD);
    }
    
    public static String format(LocalDate localDate, String format) {
        if (StringUtils.isBlank(format)) {
            throw new CheckedException("format 不能为空");
        }
        if (StringUtils.equals(TimeConstants.DEFAULT_YEAR_FORMAT, format)) {
            return localDate.format(YYYY);
        } else if (StringUtils.equals(TimeConstants.DEFAULT_MONTH_FORMAT, format)) {
            return localDate.format(YYYY_MM);
        } else if (StringUtils.equals(TimeConstants.DEFAULT_DATE_FORMAT, format)) {
            return localDate.format(YYYY_MM_DD);
        } else {
            return localDate.format(DateTimeFormatter.ofPattern(format));
        }
    }
    
    public static LocalDate monthFirstDay(LocalDate today) {
        if (today == null) {
            return null;
        }
        Month month = today.getMonth();
        return LocalDate.of(today.getYear(), month, 1);
    }
    
    public static LocalDate monthLastDay(LocalDate today) {
        if (today == null) {
            return null;
        }
        Month month = today.getMonth();
        int length = month.length(today.isLeapYear());
        return LocalDate.of(today.getYear(), month, length);
    }
    
}
