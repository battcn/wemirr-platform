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
import lombok.Getter;
import lombok.RequiredArgsConstructor;

/**
 * <p>
 * 实体注释中生成的类型枚举
 * 消息中心表
 * </p>
 *
 * @author Levin
 */
@Getter
@JsonFormat
@RequiredArgsConstructor
public enum DictFiledType implements DictEnum<String> {
    
    /**
     * radio
     */
    RADIO("radio", "radio"),
    /**
     * checkbox
     */
    CHECKBOX("checkbox", "checkbox"),
    /**
     * select
     */
    SELECT("select", "select"),;
    
    private final String type;
    
    @Parameter(description = "描述信息")
    private final String desc;
    
    @JsonCreator
    public static DictFiledType of(String type) {
        if (type == null) {
            return null;
        }
        for (DictFiledType info : values()) {
            if (info.type.equals(type)) {
                return info;
            }
        }
        return null;
    }

    @Override
    public String getValue() {
        return this.type;
    }
    
    @Override
    public String toString() {
        return this.type;
    }
    
}
