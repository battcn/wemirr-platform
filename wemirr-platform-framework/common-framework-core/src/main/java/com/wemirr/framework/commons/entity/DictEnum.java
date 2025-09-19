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

package com.wemirr.framework.commons.entity;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.annotation.IEnum;

import java.io.Serializable;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.toList;

/**
 * 枚举类型基类
 *
 * @param <T> T 泛型
 * @author Levin
 * @since 2019/07/26
 */
public interface DictEnum<T extends Serializable> extends IEnum<T> {
    
    char SEPARATOR = ',';
    
    /**
     * 枚举数组转集合
     *
     * @param dictList 枚举
     * @return 集合
     */
    static List<Dict<?>> toDictList(DictEnum<?>[] dictList) {
        if (dictList == null) {
            return null;
        }
        return Arrays.stream(dictList).map(dictionary -> new Dict<>(dictionary.getValue(), dictionary.getLabel())).collect(toList());
    }
    
    /**
     * 获取指定类型枚举映射
     *
     * @param enumClass 枚举类
     * @param type      类型
     * @param <E>       包装类
     * @return 枚举值
     */
    static <E extends DictEnum<?>> E of(Class<E> enumClass, Object type) {
        E[] enumConstants = enumClass.getEnumConstants();
        for (E e : enumConstants) {
            final Object value = e.getValue();
            if (Objects.equals(type, value)) {
                return e;
            }
        }
        return null;
    }
    
    /**
     * 转换成字符串
     *
     * @param dictList 枚举集合
     * @return 转换结果
     */
    static <E extends DictEnum<?>> String toStr(List<E> dictList) {
        if (dictList == null) {
            return null;
        }
        return dictList.stream()
                .filter(Objects::nonNull)
                .map(DictEnum::getValue)
                .filter(Objects::nonNull)
                .map(String::valueOf)
                .collect(Collectors.joining(","));
    }
    
    /**
     * 转换成集合枚举
     *
     * @param enumClass 枚举类
     * @param str       枚举 比如 1,2,3
     * @return 转换结果
     */
    static <E extends DictEnum<?>> List<E> toDictList(Class<E> enumClass, String str) {
        if (StrUtil.isBlank(str)) {
            return null;
        }
        final List<String> split = StrUtil.split(str, SEPARATOR);
        return split.stream().filter(Objects::nonNull)
                .map(type -> of(enumClass, type))
                .collect(toList());
    }
    
    /**
     * 描述信息
     *
     * @return 描述
     */
    String getLabel();
    
    /**
     * 语言
     *
     * @return 语言
     */
    default String getLanguage() {
        return null;
    }
    
}
