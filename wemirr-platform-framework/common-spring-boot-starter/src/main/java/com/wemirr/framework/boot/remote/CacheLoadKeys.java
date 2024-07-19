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

import com.wemirr.framework.boot.remote.dict.DictLoadService;
import com.wemirr.framework.commons.annotation.remote.Remote;
import com.wemirr.framework.commons.remote.LoadService;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * 封装 Remote 注解中解析出来的参数
 * <p>
 * 必须重写该类的 equals() 和 hashCode() 便于Map操作
 *
 * @author Levin
 */
@Slf4j
@Getter
@ToString
@EqualsAndHashCode
public class CacheLoadKeys {

    private final Class<?> beanClass;
    private String tag;

    /**
     * 动态查询值
     */
    private Set<Object> keys = new HashSet<>();
    private LoadService<Object> loadService;

    public CacheLoadKeys(Remote rf) {
        this.beanClass = rf.beanClass();
    }

    public CacheLoadKeys(LoadKey lk, LoadService<Object> loadService, Set<Object> keys) {
        this.beanClass = lk.getBeanClass();
        this.loadService = loadService;
        this.keys = keys;
        this.tag = lk.getTag();
    }

    /**
     * 加载数据
     *
     * @return 查询指定接口后得到的值
     */
    public Map<Object, Object> loadMap() {
        if (DictLoadService.class.isAssignableFrom(beanClass)) {
            return loadService.findByIds(tag);
        }
        return loadService.findByIds(keys);
    }
}
