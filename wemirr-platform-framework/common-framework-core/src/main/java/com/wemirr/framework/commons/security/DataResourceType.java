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

import com.baomidou.mybatisplus.annotation.EnumValue;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonValue;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * <p>
 * 数据权限范围，值越大，权限越大
 * </p>
 *
 * @author Levin
 */
@Getter
@JsonFormat
@AllArgsConstructor
public enum DataResourceType {
    
    /**
     * 用户（比如你可以根据 地区、机构、公司、网点等维度控制权限，只需要指定数据资源类型即可）
     */
    USER("user", "用户维度"),
    ROLE("role", "角色维度"),
    ORG("org", "机构维度"),
    TENANT("tenant", "租户维度"),
    COMPANY("company", "公司维护"),
    AREA("area", "地区维度"),;
    
    @EnumValue
    @JsonValue
    private final String resource;
    private final String desc;
    
    @JsonCreator
    public static DataResourceType of(String resource) {
        if (resource == null) {
            return null;
        }
        for (DataResourceType info : values()) {
            if (info.resource.equals(resource)) {
                return info;
            }
        }
        return null;
    }
}
