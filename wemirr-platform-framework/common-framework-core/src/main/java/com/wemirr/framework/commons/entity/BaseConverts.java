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

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 对象转换接口
 *
 * @param <S> 源对象
 * @param <T> 目标对象
 * @author Levin
 * @version 1.0.0
 * @since 2019-03-19
 */
public interface BaseConverts<S, T> {
    
    /**
     * 类型转换
     *
     * @param source 原对象
     * @return 目标对象
     */
    default T convert(S source) {
        return null;
    }
    
    /**
     * 类型转换
     *
     * @param source 原对象
     * @param id     ID
     * @return 目标对象
     */
    default T convert(S source, Long id) {
        return null;
    }
    
    /**
     * 批量类型转换
     *
     * @param sources 原对象
     * @return 目标对象
     */
    default List<T> converts(List<S> sources) {
        return sources == null || sources.size() <= 0 ? new ArrayList<>() : sources.stream().map(this::convert).collect(Collectors.toList());
    }
    
}
