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
package com.wemirr.framework.boot.remote;

import cn.hutool.core.util.ReflectUtil;
import com.wemirr.framework.commons.annotation.remote.Remote;
import lombok.extern.slf4j.Slf4j;

import java.io.Serializable;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 类管理器
 *
 * @author Levin
 */
@Slf4j
public class ClassManager implements Serializable {
    
    private static final Map<String, List<Field>> CACHE = new HashMap<>();
    
    public static List<Field> getFields(Class<?> clazz) {
        if (CACHE.containsKey(clazz.getName())) {
            return CACHE.get(clazz.getName());
        }
        Field[] declaredFields = ReflectUtil.getFields(clazz);
        int mod;
        // 循环遍历所有的属性进行判断
        List<Field> fieldList = new ArrayList<>();
        for (Field field : declaredFields) {
            mod = field.getModifiers();
            // 如果是 static, final, volatile, transient 的字段，则直接跳过
            if (Modifier.isStatic(mod) || Modifier.isFinal(mod) || Modifier.isVolatile(mod)) {
                continue;
            }
            Remote remote = field.getDeclaredAnnotation(Remote.class);
            if (remote == null) {
                continue;
            }
            fieldList.add(field);
        }
        CACHE.put(clazz.getName(), fieldList);
        return fieldList;
    }
}
