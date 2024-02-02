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

import cn.hutool.core.map.MapUtil;
import com.google.common.collect.HashBiMap;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.Maps;

import java.util.Collections;
import java.util.Iterator;
import java.util.Map;
import java.util.Objects;
import java.util.function.Function;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * Map 类增强
 *
 * @author Levin
 * @since 2020/07/29
 */
public class MapHelper {

    /**
     * 增强 guava 的 Maps.uniqueIndex方法
     * <p>
     * guava 的 Maps.uniqueIndex方法可以实现：
     * </p>
     * 将 list&lt;V&gt 转成 Map&lt;K , V&gt
     * K 需要自己指定， V不能指定
     * </p>
     * 本方法实现了：
     * <p>
     * 将 list&lt;V&gt 转成 Map&lt;K , M&gt
     * K 需要自己指定， M需要自己指定
     * </p>
     *
     * @param values        需要转换的集合 可以是任何实现了 Iterable 接口的集合(如List, Set, Collection)
     * @param keyFunction   转换后Map的键的转换方式
     * @param valueFunction 转换后Map的值的转换方式
     * @param <K>           转换后Map的键 类型
     * @param <V>           转换前Iterable的迭代类型
     * @param <M>           转换后Map的值 类型
     * @return 转换集合
     * @throws IllegalArgumentException IllegalArgumentException
     */
    public static <K, V, M> ImmutableMap<K, M> uniqueIndex(Iterable<V> values, Function<? super V, K> keyFunction, Function<? super V, M> valueFunction) {
        Iterator<V> iterator = values.iterator();
        checkNotNull(keyFunction);
        checkNotNull(valueFunction);
        ImmutableMap.Builder<K, M> builder = ImmutableMap.builder();
        while (iterator.hasNext()) {
            V value = iterator.next();
            builder.put(keyFunction.apply(value), valueFunction.apply(value));
        }
        try {
            return builder.build();
        } catch (IllegalArgumentException duplicateKeys) {
            throw new IllegalArgumentException(duplicateKeys.getMessage() + ".若要在键下索引多个值，请使用: Multimaps.index.");
        }
    }

    /**
     * 增强 guava 的 Maps.uniqueIndex方法
     * <p>
     * guava 的 Maps.uniqueIndex方法可以实现：
     * <br>
     * 将 list&lt;V&gt 转成 Map&lt;K , V&gt
     * K 需要自己指定， V不能指定
     * </p>
     * 本方法实现了：
     * <p>
     * 将 list&lt;V&gt 转成 Map&lt;K , M&gt
     * K 需要自己指定， M需要自己指定
     * </p>
     *
     * @param values        需要转换的集合 可以是任何实现了 Iterable 接口的集合(如List, Set, Collection)
     * @param keyFunction   转换后Map的键的转换方式
     * @param valueFunction 转换后Map的值的转换方式
     * @param <K>           转换后Map的键 类型
     * @param <V>           转换前Iterable的迭代类型
     * @param <M>           转换后Map的值 类型
     * @return ImmutableMap
     */
    public static <K, V, M> Map<K, M> toImmutableMap(Iterable<V> values, Function<? super V, K> keyFunction, Function<? super V, M> valueFunction) {
        return uniqueIndex(values, keyFunction, valueFunction);
    }

    /**
     * List 转 HashMap
     * K 需要自己指定， M需要自己指定
     * </p>
     *
     * @param values        需要转换的集合 可以是任何实现了 Iterable 接口的集合(如List, Set, Collection)
     * @param keyFunction   转换后Map的键的转换方式
     * @param valueFunction 转换后Map的值的转换方式
     * @param <K>           转换后Map的键 类型
     * @param <V>           转换前Iterable的迭代类型
     * @param <M>           转换后Map的值 类型
     * @return ImmutableMap
     */
    public static <K, V, M> Map<K, M> toHashMap(Iterable<V> values, Function<? super V, K> keyFunction, Function<? super V, M> valueFunction) {
        checkNotNull(values);
        Iterator<V> iterator = values.iterator();
        checkNotNull(keyFunction);
        Map<K, M> map = Maps.newHashMap();
        while (iterator.hasNext()) {
            V value = iterator.next();
            map.put(keyFunction.apply(value), Objects.isNull(value) ? null : valueFunction.apply(value));
        }
        return map;
    }

    /**
     * 转换 Map 的 K 和 V
     *
     * @param map map
     * @param <K> K
     * @param <V> V
     * @return 转换结果
     */
    public static <K, V> Map<V, K> inverse(Map<K, V> map) {
        if (MapUtil.isEmpty(map)) {
            return Collections.emptyMap();
        }
        HashBiMap<K, V> biMap = HashBiMap.create();
        map.forEach(biMap::forcePut);
        return biMap.inverse();
    }

}
