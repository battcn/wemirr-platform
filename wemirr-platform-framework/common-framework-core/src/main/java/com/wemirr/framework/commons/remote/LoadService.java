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

package com.wemirr.framework.commons.remote;

import cn.hutool.core.lang.Pair;

import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * <p>
 * 加载数据
 * </p>
 * 只保留一个方法，若一个表，想要有多重回显场景，可以新建多个实现类，返回不一样的Map
 *
 * @param <VALUE> VALUE
 * @author Levin
 */
public interface LoadService<VALUE> {
    
    /**
     * 根据id查询待回显参数.
     *
     * @param keys 唯一键（可能不是主键ID)
     * @return Map
     */
    Map<Object, VALUE> findByIds(Set<Object> keys);
    
    /**
     * 根据id查询待回显参数.
     *
     * @param tag tag
     * @return Map
     * @throws RuntimeException 异常
     */
    default Map<Object, VALUE> findByIds(String tag) {
        throw new RuntimeException("暂未实现");
    }
    
    default void refreshCache(Map<String, List<Pair<String, String>>> data) {
        
    }
    
}
