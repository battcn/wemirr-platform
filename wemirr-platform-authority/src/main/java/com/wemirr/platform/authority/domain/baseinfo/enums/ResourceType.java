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
package com.wemirr.platform.authority.domain.baseinfo.enums;

import com.baomidou.mybatisplus.annotation.EnumValue;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonValue;
import com.wemirr.framework.db.mybatisplus.core.DictEnum;
import io.swagger.v3.oas.annotations.media.Schema;
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
@Schema(description = "枚举")
@JsonFormat
public enum ResourceType implements DictEnum<Integer> {
    
    /**
     * 按钮
     */
    BUTTON(2, "按钮"),
    /**
     * 菜单
     */
    MENU(1, "菜单"),
    /**
     * 三方
     */
    ROUTE(3, "路由"),
    /**
     * 三方
     */
    BUILD_PUBLISH(5, "一键发布模板"),
    ;
    @EnumValue
    @JsonValue
    private Integer type;
    
    @Schema(description = "描述")
    private String desc;
    
    @JsonCreator
    public static ResourceType of(Integer type) {
        if (type == null) {
            return null;
        }
        for (ResourceType info : values()) {
            if (info.type.equals(type)) {
                return info;
            }
        }
        return null;
    }
    
    public boolean eq(String val) {
        return this.name().equalsIgnoreCase(val);
    }
    
    public boolean eq(ResourceType val) {
        if (val == null) {
            return false;
        }
        return eq(val.name());
    }
    
    @Override
    public Integer getValue() {
        return this.type;
    }
    
    @Override
    public String toString() {
        return String.valueOf(type);
    }
    
}
