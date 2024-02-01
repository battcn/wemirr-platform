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

import cn.hutool.core.util.ObjectUtil;
import com.google.common.collect.Maps;
import com.wemirr.framework.db.mybatisplus.core.DictEnum;
import org.springframework.core.convert.converter.Converter;
import org.springframework.core.convert.converter.ConverterFactory;

import java.util.Map;

/**
 * @author Levin
 */
@SuppressWarnings("all")
public class IntegerCodeToEnumConverterFactory implements ConverterFactory<Integer, DictEnum> {
    
    private static final Map<Class, Converter> CONVERTERS = Maps.newHashMap();
    
    /**
     * 获取一个从 Integer 转化为 T 的转换器，T 是一个泛型，有多个实现
     *
     * @param targetType 转换后的类型
     * @return 返回一个转化器
     */
    @Override
    public <T extends DictEnum> Converter<Integer, T> getConverter(Class<T> targetType) {
        Converter<Integer, T> converter = CONVERTERS.get(targetType);
        if (converter == null) {
            converter = new IntegerToEnumConverter<>(targetType);
            CONVERTERS.put(targetType, converter);
        }
        return converter;
    }
    
    public static class IntegerToEnumConverter<T extends DictEnum> implements Converter<Integer, T> {
        
        private Map<Integer, T> enumMap = Maps.newHashMap();
        
        public IntegerToEnumConverter(Class<T> enumType) {
            T[] enums = enumType.getEnumConstants();
            for (T e : enums) {
                enumMap.put((Integer) e.getValue(), e);
            }
        }
        
        @Override
        public T convert(Integer source) {
            T t = enumMap.get(source);
            if (ObjectUtil.isNull(t)) {
                throw new IllegalArgumentException("无法匹配对应的枚举类型");
            }
            return t;
        }
    }
    
}