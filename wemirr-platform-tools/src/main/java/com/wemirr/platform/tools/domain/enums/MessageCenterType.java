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
package com.wemirr.platform.tools.domain.enums;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.wemirr.framework.db.mybatisplus.core.DictEnum;
import io.swagger.v3.oas.annotations.Parameter;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * <p>
 * 实体注释中生成的类型枚举
 * 消息中心表
 * </p>
 *
 * @author Levin
 */
@Getter
@AllArgsConstructor
@NoArgsConstructor
@JsonFormat
public enum MessageCenterType implements DictEnum<Integer> {
    
    /**
     * WAIT="待办"
     */
    WAIT(0, "待办"),
    
    /**
     * NOTIFY="通知"
     */
    NOTIFY(1, "通知"),
    
    /**
     * PUBLICITY="公告"
     */
    PUBLICITY(2, "公告"),
    
    /**
     * WARN="预警"
     */
    WARN(-1, "预警"),;
    
    private Integer type;
    
    @Parameter(description = "描述信息")
    private String desc;
    
    @JsonCreator
    public static MessageCenterType of(Integer type) {
        if (type == null) {
            return null;
        }
        for (MessageCenterType info : values()) {
            if (info.type.equals(type)) {
                return info;
            }
        }
        return null;
    }
    
    public boolean eq(String val) {
        return this.name().equalsIgnoreCase(val);
    }
    
    public boolean eq(MessageCenterType val) {
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
