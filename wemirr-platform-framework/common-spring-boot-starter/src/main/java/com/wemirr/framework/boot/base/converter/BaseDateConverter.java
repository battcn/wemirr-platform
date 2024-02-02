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

import java.util.Map;
import java.util.Set;
import java.util.function.Function;

/**
 * 解决入参为 Date类型
 *
 * @author Levin
 * @since 2019-04-30
 */
public abstract class BaseDateConverter<T> {
    
    public T convert(String source, Function<String, T> function) {
        if (source == null || source.isEmpty()) {
            return null;
        }
        source = source.trim();
        Set<Map.Entry<String, String>> entries = getFormat().entrySet();
        for (Map.Entry<String, String> entry : entries) {
            if (source.matches(entry.getValue())) {
                return function.apply(entry.getKey());
            }
        }
        throw new IllegalArgumentException("无效的日期参数格式:'" + source + "'");
    }
    
    /**
     * 获取子类 具体的格式化表达式
     *
     * @return Map<String, String>
     */
    protected abstract Map<String, String> getFormat();
}
