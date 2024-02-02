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

package com.wemirr.framework.boot.base.converter;

import org.springframework.core.convert.converter.Converter;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.LinkedHashMap;
import java.util.Map;

import static com.wemirr.framework.commons.times.TimeConstants.DEFAULT_DATE_TIME_FORMAT;

/**
 * 解决入参为 Date类型
 *
 * @author Levin
 * @since 2019-04-30
 */
public class String2LocalDateTimeConverter extends BaseDateConverter<LocalDateTime> implements Converter<String, LocalDateTime> {
    
    protected static final Map<String, String> FORMAT = new LinkedHashMap<>(2);
    
    static {
        FORMAT.put(DEFAULT_DATE_TIME_FORMAT, "^\\d{4}-\\d{1,2}-\\d{1,2} {1}\\d{1,2}:\\d{1,2}:\\d{1,2}$");
        FORMAT.put("yyyy/MM/dd HH:mm:ss", "^\\d{4}/\\d{1,2}/\\d{1,2} {1}\\d{1,2}:\\d{1,2}:\\d{1,2}$");
    }
    
    @Override
    protected Map<String, String> getFormat() {
        return FORMAT;
    }
    
    @Override
    public LocalDateTime convert(String source) {
        return super.convert(source, (key) -> LocalDateTime.parse(source, DateTimeFormatter.ofPattern(key)));
    }
}
