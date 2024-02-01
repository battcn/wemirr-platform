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
import com.baomidou.mybatisplus.annotation.IEnum;
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
public enum DataScopeType implements IEnum<Integer> {
    
    /**
     * ALL=5全部
     */
    ALL(50, "全部"),
    
    /**
     * THIS_LEVEL_CHILDREN=3本级以及子级
     */
    THIS_LEVEL_CHILDREN(40, "本级以及子级"),
    /**
     * THIS_LEVEL=4本级
     */
    THIS_LEVEL(30, "本级"),
    
    /**
     * CUSTOMIZE=2自定义
     */
    CUSTOMIZE(20, "自定义"),
    /**
     * SELF=1个人
     */
    SELF(10, "个人"),
    
    IGNORE(0, "跟随系统上下文"),
    ;
    
    @EnumValue
    @JsonValue
    private final Integer type;
    private final String desc;
    
    @JsonCreator
    public static DataScopeType of(Integer type) {
        if (type == null) {
            return null;
        }
        for (DataScopeType info : values()) {
            if (info.type.equals(type)) {
                return info;
            }
        }
        return null;
    }
    
    @Override
    public Integer getValue() {
        return type;
    }
}
