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

import com.wemirr.framework.db.mybatisplus.ext.SuperService;
import com.wemirr.platform.authority.domain.tenant.entity.TenantDictionary;

/**
 * <p>
 * 业务接口
 * 字典类型
 * </p>
 *
 * @author Levin
 */
public interface TenantDictionaryService extends SuperService<TenantDictionary> {
    
    /**
     * 添加字典
     *
     * @param dictionary 字典信息
     */
    void addDictionary(TenantDictionary dictionary);
    
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
    void editDictionary(TenantDictionary dictionary);
}
