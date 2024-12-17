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

import cn.hutool.core.date.DateField;
import cn.hutool.core.date.DateTime;
import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.StrUtil;
import lombok.extern.slf4j.Slf4j;

import java.time.Instant;
import java.time.ZoneId;
import java.util.Locale;
import java.util.Objects;
import java.util.TimeZone;

/**
 * 美国	Locale("en", "US")	ZoneId.of("America/New_York") （纽约时区）
 * 中国	Locale("zh", "CN")	ZoneId.of("Asia/Shanghai") （上海时区）
 * 印度	Locale("en", "IN")	ZoneId.of("Asia/Kolkata") （加尔各答时区）
 * 英国	Locale("en", "GB")	ZoneId.of("Europe/London") （伦敦时区）
 * 日本	Locale("ja", "JP")	ZoneId.of("Asia/Tokyo") （东京时区）
 * 澳大利亚	Locale("en", "AU")	ZoneId.of("Australia/Sydney") （悉尼时区）
 * 加拿大	Locale("en", "CA")	ZoneId.of("America/Toronto") （多伦多时区）
 * 德国	Locale("de", "DE")	ZoneId.of("Europe/Berlin") （柏林时区）
 * 法国	Locale("fr", "FR")	ZoneId.of("Europe/Paris") （巴黎时区）
 * 韩国	Locale("ko", "KR")	ZoneId.of("Asia/Seoul") （首尔时区）
 * 俄罗斯	Locale("ru", "RU")	ZoneId.of("Europe/Moscow") （莫斯科时区）
 * 巴西	Locale("pt", "BR")	ZoneId.of("America/Sao_Paulo") （圣保罗时区）
 * 南非	Locale("en", "ZA")	ZoneId.of("Africa/Johannesburg") （约翰内斯堡时区
 *
 * @author Levin
 */
@Slf4j
public class TimeZoneUtil {
    
    public static final String UTC = "UTC";
    
    public static TimeZone toTimeZone(Locale locale) {
        // 获取 locale 对应的国家代码
        String country = locale.getCountry();
        if (StrUtil.isBlank(country)) {
            return TimeZone.getDefault();
        }
        // 根据国家代码获取对应的时区 ID
        return switch (country) {
            case "US" -> TimeZone.getTimeZone("America/New_York");
            case "CN" -> TimeZone.getTimeZone("Asia/Shanghai");
            case "IN" -> TimeZone.getTimeZone("Asia/Kolkata");
            case "GB" -> TimeZone.getTimeZone("Europe/London");
            case "JP" -> TimeZone.getTimeZone("Asia/Tokyo");
            case "AU" -> TimeZone.getTimeZone("Australia/Sydney");
            case "CA" -> TimeZone.getTimeZone("America/Toronto");
            case "DE" -> TimeZone.getTimeZone("Europe/Berlin");
            case "FR" -> TimeZone.getTimeZone("Europe/Paris");
            case "KR" -> TimeZone.getTimeZone("Asia/Seoul");
            case "RU" -> TimeZone.getTimeZone("Europe/Moscow");
            case "BR" -> TimeZone.getTimeZone("America/Sao_Paulo");
            case "ZA" -> TimeZone.getTimeZone("Africa/Johannesburg");
            default -> TimeZone.getDefault();
        };
    }
    
    public static ZoneId toZoneId(Locale locale) {
        // 获取 locale 对应的国家代码
        String country = locale.getCountry();
        if (StrUtil.isBlank(country)) {
            return ZoneId.systemDefault();
        }
        // 根据国家代码获取对应的时区 ID
        return switch (country) {
            case "US" -> ZoneId.of("America/New_York");
            case "CN" -> ZoneId.of("Asia/Shanghai");
            case "IN" -> ZoneId.of("Asia/Kolkata");
            case "GB" -> ZoneId.of("Europe/London");
            case "JP" -> ZoneId.of("Asia/Tokyo");
            case "AU" -> ZoneId.of("Australia/Sydney");
            case "CA" -> ZoneId.of("America/Toronto");
            case "DE" -> ZoneId.of("Europe/Berlin");
            case "FR" -> ZoneId.of("Europe/Paris");
            case "KR" -> ZoneId.of("Asia/Seoul");
            case "RU" -> ZoneId.of("Europe/Moscow");
            case "BR" -> ZoneId.of("America/Sao_Paulo");
            case "ZA" -> ZoneId.of("Africa/Johannesburg");
            default -> ZoneId.systemDefault();
        };
    }
    
    public static Instant toOffsetUtcTime(String dateStr, Locale locale) {
        if (StrUtil.isBlank(dateStr)) {
            return null;
        }
        DateTime dateTime = DateUtil.parse(dateStr);
        if (dateTime == null) {
            return null;
        }
        TimeZone timeZone = TimeZoneUtil.toTimeZone(locale);
        // 说明当前系统时区就是 临时区
        if (!Objects.equals(TimeZone.getDefault(), TimeZone.getTimeZone(UTC))) {
            // 如果系统不是临时区,那么就将当前时间转换成系统的UTC存储即可
            return dateTime.setTimeZone(timeZone).toInstant();
        }
        // 获取目标时区的偏移量（相对于 UTC），单位为小时
        int offsetHours = timeZone.getRawOffset() / (1000 * 60 * 60);
        dateTime.offset(DateField.HOUR_OF_DAY, -offsetHours);
        log.info("Locale: {}, 时区: {}, 偏移量: {} 小时", locale, timeZone.getID(), -offsetHours);
        return dateTime.toInstant();
    }
}
