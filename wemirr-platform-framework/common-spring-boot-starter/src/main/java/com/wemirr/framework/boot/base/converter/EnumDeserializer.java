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

import cn.hutool.core.util.ReflectUtil;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.JsonToken;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * enum反序列化工具
 *
 * @author Levin
 * @since 2019-07-25 22:15
 */
@Slf4j
public class EnumDeserializer extends StdDeserializer<Enum<?>> {
    
    public static final EnumDeserializer INSTANCE = new EnumDeserializer();
    private static final String ALL_ENUM_STRING_CONVERT_METHOD = "get";
    private static final String ALL_ENUM_KEY_FIELD = "code";
    
    private EnumDeserializer() {
        super(Enum.class);
    }
    
    @Override
    public Enum<?> deserialize(JsonParser p, DeserializationContext ctxt) throws IOException, JsonProcessingException {
        JsonToken token = p.getCurrentToken();
        String value = null;
        while (!token.isStructEnd()) {
            if (ALL_ENUM_KEY_FIELD.equals(p.getText())) {
                p.nextToken();
                value = p.getValueAsString();
            } else {
                p.nextToken();
            }
            token = p.getCurrentToken();
        }
        if (value == null || "".equals(value)) {
            return null;
        }
        
        Object obj = p.getCurrentValue();
        if (obj == null) {
            return null;
        }
        Field field = ReflectUtil.getField(obj.getClass(), p.getCurrentName());
        // 找不到字段
        if (field == null) {
            return null;
        }
        Class<?> fieldType = field.getType();
        try {
            Method method = fieldType.getMethod(ALL_ENUM_STRING_CONVERT_METHOD, String.class);
            return (Enum<?>) method.invoke(null, value);
        } catch (NoSuchMethodException | SecurityException | IllegalAccessException | InvocationTargetException e) {
            log.warn("解析枚举失败", e);
            return null;
        }
    }
    
}
