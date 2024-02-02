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

package com.wemirr.framework.db.dynamic.core;

import com.baomidou.mybatisplus.annotation.EnumValue;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonValue;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
/**
 * <p>
 * TenantType
 * </p>
 *
 * @author Levin
 * @since 2020-02-14
 */
@Getter
@AllArgsConstructor
@NoArgsConstructor
@JsonFormat
public enum EventAction {
    
    /**
     * 初始化
     */
    INIT(0, "在添加之前初始化"),
    /**
     * 添加
     */
    ADD(1, "添加"),
    /**
     * 删除
     */
    DEL(-1, "删除"),
    ;
    @EnumValue
    @JsonValue
    private Integer type;
    
    private String desc;
    
    public static EventAction of(Integer type) {
        if (type == null) {
            return null;
        }
        for (EventAction info : values()) {
            if (info.type.equals(type)) {
                return info;
            }
        }
        return null;
    }
    
    @Override
    public String toString() {
        return String.valueOf(type);
    }
    
}
