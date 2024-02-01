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
package com.wemirr.framework.db.mybatisplus.core;

import cn.hutool.core.collection.CollectionUtil;
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
 * @author Levin
 * @since 2019/07/26
 */
public interface DictEnum<T extends Serializable> extends IEnum<T> {
    
    /**
     * 描述信息
     *
     * @return 描述
     */
    String getDesc();
    
    /**
     * 语言
     *
     * @return 语言
     */
    default String getLanguage() {
        return null;
    }
    
    /**
     * 获取枚举编码
     *
     * @return 编码
     */
    default String getCode() {
        return String.valueOf(this.getValue());
    }
    
    /**
     * 枚举数组转集合
     *
     * @param dictionaries 枚举
     * @return 集合
     */
    static List<BaseDict> getList(DictEnum<?>[] dictionaries) {
        if (dictionaries == null) {
            return null;
        }
        return Arrays.stream(dictionaries).map(dictionary -> BaseDict.builder()
                .code(dictionary.getCode()).desc(dictionary.getDesc())
                .build()).collect(Collectors.toList());
    }
    
    /**
     * 获取指定类型枚举映射
     *
     * @param enumClass 枚举类
     * @param type      类型
     * @param <E>       包装类
     * @return 枚举值
     */
    static <E extends DictEnum<?>> E of(Class<E> enumClass, Serializable type) {
        E[] enumConstants = enumClass.getEnumConstants();
        for (E e : enumConstants) {
            final Serializable value = e.getValue();
            if (value == type) {
                return e;
            }
        }
        return null;
    }
    
    char SEPARATOR = ',';
    
    /**
     * 转换成字符串
     *
     * @param dictionaries 枚举
     * @return 转换结果
     */
    static <E extends DictEnum<?>> String of(List<E> dictionaries) {
        if (CollectionUtil.isEmpty(dictionaries)) {
            return null;
        }
        return dictionaries.stream()
                .filter(Objects::nonNull)
                .map(DictEnum::getCode).collect(Collectors.joining(","));
    }
    
    /**
     * 转换成集合枚举
     *
     * @param enumClass    枚举类
     * @param dictionaries 枚举
     * @return 转换结果
     */
    static <E extends DictEnum<?>> List<E> of(Class<E> enumClass, String dictionaries) {
        if (StrUtil.isBlank(dictionaries)) {
            return null;
        }
        final List<String> split = StrUtil.split(dictionaries, SEPARATOR);
        return split.stream().filter(Objects::nonNull)
                .map(type -> of(enumClass, Integer.parseInt(type)))
                .collect(toList());
    }
    
}
