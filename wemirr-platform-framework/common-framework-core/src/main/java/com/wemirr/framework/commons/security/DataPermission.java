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

package com.wemirr.framework.commons.security;

import com.google.common.collect.Maps;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serial;
import java.util.List;
import java.util.Map;

/**
 * @author Levin
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DataPermission implements java.io.Serializable {
    
    @Serial
    private static final long serialVersionUID = 1L;
    
    /**
     * 权限范围
     */
    @Builder.Default
    private DataScopeType scopeType = DataScopeType.IGNORE;
    
    /**
     * 角色ID列表（用于 EXISTS 子查询）
     */
    private List<Long> roleIds;
    
    /**
     * 用户当前机构ID（用于本级/本级及子级权限）
     */
    private Long orgId;
    
    /**
     * 多维度数据权限（小数据量时使用 IN 列表，大数据量时使用 EXISTS 子查询）
     * <p>
     * 优化策略：
     * - 数据量 < 1000：使用此 Map 构建 IN 列表
     * - 数据量 >= 1000：使用 roleIds 构建 EXISTS 子查询
     * </p>
     */
    @Builder.Default
    private Map<DataResourceType, List<Object>> dataPermissionMap = Maps.newHashMap();
    
    /**
     * 判断是否应该使用 EXISTS 子查询
     */
    public boolean shouldUseExistsQuery(DataResourceType resourceType) {
        List<Object> values = dataPermissionMap.get(resourceType);
        return values != null && values.size() >= 1000;
    }
    
}
