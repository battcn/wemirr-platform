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

import com.baomidou.mybatisplus.core.toolkit.StringPool;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.wemirr.framework.db.mybatisplus.core.DictEnum;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.apache.commons.lang3.StringUtils;

/**
 * @author Levin
 */
@Getter
@AllArgsConstructor
@NoArgsConstructor
@JsonFormat
public enum Expression implements DictEnum<String> {
    
    /**
     * eq
     */
    EQ(StringPool.EQUALS),
    NE("<>"),
    GT(StringPool.RIGHT_CHEV),
    GE(">="),
    LT(StringPool.LEFT_CHEV),
    LE("<="),
    IS_NULL("IS NULL"),
    LIKE("LIKE"),
    IS_NOT_NULL("IS NOT NULL"),
    ;
    
    public String value;
    
    @JsonCreator
    public static Expression of(String name) {
        for (Expression expression : values()) {
            if (StringUtils.equalsIgnoreCase(expression.name(), name)) {
                return expression;
            }
        }
        return null;
    }
    
    public boolean eq(String val) {
        return this.name().equalsIgnoreCase(val);
    }
    
    public boolean eq(Expression val) {
        if (val == null) {
            return false;
        }
        return eq(val.name());
    }
    
    @Override
    public String getValue() {
        return this.value;
    }
    
    @Override
    public String toString() {
        return value;
    }
    
    @Override
    public String getDesc() {
        return value;
    }
}
