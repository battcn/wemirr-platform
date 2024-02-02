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

package com.wemirr.framework.db.properties;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 多租户类型
 *
 * @author Levin
 * @since 2018/11/20
 */
@Getter
@AllArgsConstructor
public enum MultiTenantType {
    
    /**
     * 非租户模式
     */
    NONE("非租户模式"),
    /**
     * 字段模式 适合数据量不大（核心数据一年不足1000w 用它足够了）
     * 在sql中拼接 tenant_code 字段
     */
    COLUMN("字段模式"),
    
    /**
     * 暂时没想到这种模式有啥好处
     * schema 模式
     * sql 表名添加 schema 库名
     */
    @Deprecated
    SCHEMA("schema模式"),
    
    /**
     * 数据量大客户多可以考虑独立数据源
     * <p>
     * 独立数据源模式
     */
    DATASOURCE("独立数据源模式"),
    ;
    private final String description;
    
}
