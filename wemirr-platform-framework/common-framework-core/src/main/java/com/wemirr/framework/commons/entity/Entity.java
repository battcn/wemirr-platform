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

package com.wemirr.framework.commons.entity;

import com.baomidou.mybatisplus.annotation.*;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.groups.Default;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.SuperBuilder;

import java.io.Serializable;
import java.time.Instant;

/**
 * 在考虑是否应该移动到 database 模块
 * 基础实体
 *
 * @param <T> T
 * @author Levin
 */
@Data
@SuperBuilder(toBuilder = true)
@NoArgsConstructor
@ToString(callSuper = true)
public class Entity<T> implements Serializable {

    public static final String FIELD_ID = "id";
    public static final String CREATE_TIME = "createdTime";
    public static final String CREATE_TIME_COLUMN = "created_time";
    public static final String CREATE_USER = "createdBy";
    public static final String CREATE_USER_NAME = "createdName";
    public static final String CREATE_USER_NAME_COLUMN = "created_name";

    public static final String CREATE_USER_COLUMN = "created_by";

    @Schema(description = "ID")
    @TableId(value = FIELD_ID, type = IdType.ASSIGN_ID)
    @OrderBy
    @NotNull(message = "id不能为空", groups = Update.class)
    private T id;

    /**
     * 创建人ID
     */
    @TableField(value = CREATE_USER_COLUMN, fill = FieldFill.INSERT)
    @Schema(description = "创建人ID")
    private T createdBy;

    /**
     * 创建人名称
     */
    @TableField(value = CREATE_USER_NAME_COLUMN, fill = FieldFill.INSERT)
    @Schema(description = "创建人名称")
    private String createdName;

    /**
     * 创建时间（依托数据库功能）
     */
    @TableField(value = CREATE_TIME_COLUMN, fill = FieldFill.INSERT)
    @Schema(description = "创建时间")
    private Instant createdTime;

    /**
     * 保存和缺省验证组
     */
    public interface Save extends Default {

    }

    /**
     * 更新和缺省验证组
     */
    public interface Update extends Default {

    }

}
