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
package com.wemirr.framework.commons;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.util.ReflectUtil;

import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * BeanUtil 增强
 *
 * @author Levin
 */
public class BeanUtilPlus extends BeanUtil {
    
    /**
     * 对象或Map转Bean
     *
     * @param <T>    转换的Bean类型
     * @param source Bean对象或Map
     * @param clazz  目标的Bean类型
     * @return Bean对象
     * @since 4.1.20
     */
    public static <T> T toBean(Object id, Object source, Class<T> clazz) {
        final T bean = toBean(source, clazz);
        ReflectUtil.setFieldValue(bean, "id", id);
        return bean;
    }
    
    /**
     * 转换 list （如果有枚举类型请勿使用该方法 ）
     *
     * @param sourceList       原始数据集
     * @param destinationClass 目标对象
     * @param <T>              原始数据对象类型
     * @param <E>              目标对象类型
     * @return 转换结果
     */
    public static <T, E> List<T> toBeans(Collection<E> sourceList, Class<T> destinationClass) {
        if (sourceList == null || sourceList.isEmpty() || destinationClass == null) {
            return Collections.emptyList();
        }
        return sourceList.parallelStream()
                .filter(Objects::nonNull)
                .map((source) -> toBean(source, destinationClass))
                .collect(Collectors.toList());
    }
    
}
