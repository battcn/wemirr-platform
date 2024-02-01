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

package com.wemirr.platform.authority.service;

import com.alicp.jetcache.anno.CacheInvalidate;
import com.alicp.jetcache.anno.CacheRefresh;
import com.alicp.jetcache.anno.CacheType;
import com.alicp.jetcache.anno.Cached;
import com.wemirr.framework.commons.entity.KeyVal;
import com.wemirr.framework.db.mybatisplus.ext.SuperService;
import com.wemirr.platform.authority.domain.common.entity.Dictionary;

import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * <p>
 * 业务接口
 * 字典类型
 * </p>
 *
 * @author Levin
 * @since 2019-07-02
 */
public interface DictionaryService extends SuperService<Dictionary> {
    
    String SYS_DICT_CACHE_KEY_PREFIX = "sys:dict:-";
    
    /**
     * 添加字典
     *
     * @param dictionary 字典信息
     */
    void addDictionary(Dictionary dictionary);
    
    /**
     * 删除字典
     *
     * @param id id
     */
    void deleteById(Long id);
    
    /**
     * 编辑字典
     *
     * @param dictionary 字典信息
     */
    void editDictionary(Dictionary dictionary);
    
    /**
     * 刷新缓存
     *
     * @param code code
     */
    @CacheInvalidate(name = SYS_DICT_CACHE_KEY_PREFIX, key = "#code")
    void refresh(String code);
    
    /**
     * 根据 code 查询
     *
     * @param code code
     * @return 查询结果
     */
    @Cached(name = SYS_DICT_CACHE_KEY_PREFIX, key = "#code", expire = 10, timeUnit = TimeUnit.MINUTES, cacheType = CacheType.LOCAL)
    @CacheRefresh(refresh = 5, timeUnit = TimeUnit.MINUTES)
    List<KeyVal> findItemByCode(String code);
    
}
