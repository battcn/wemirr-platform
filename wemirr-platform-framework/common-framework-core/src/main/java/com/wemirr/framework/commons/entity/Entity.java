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

import java.io.Serial;
import java.io.Serializable;
import java.time.Instant;

/**
 * 基础实体类
 * <p>包含ID、创建人、创建时间等基础字段，所有业务实体应继承此类</p>
 *
 * <h3>字段说明</h3>
 * <ul>
 *   <li>id - 主键ID（雪花算法生成）</li>
 *   <li>createBy - 创建人ID（自动填充）</li>
 *   <li>createName - 创建人名称（自动填充）</li>
 *   <li>createTime - 创建时间（自动填充）</li>
 * </ul>
 *
 * @param <T> 主键类型（通常为Long）
 * @author Levin
 * @since 1.0.0
 * @see SuperEntity
 */
@Data
@SuperBuilder(toBuilder = true)
@NoArgsConstructor
@ToString(callSuper = true)
public class Entity<T> implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    // ==================== 字段名常量（Java属性名） ====================

    /**
     * 租户ID属性名
     */
    public static final String TENANT_ID = "tenantId";

    /**
     * ID属性名
     */
    public static final String FIELD_ID = "id";

    /**
     * 创建时间属性名
     */
    public static final String CREATE_TIME = "createTime";

    /**
     * 创建人ID属性名
     */
    public static final String CREATE_USER = "createBy";

    /**
     * 创建人名称属性名
     */
    public static final String CREATE_USER_NAME = "createName";

    // ==================== 列名常量（数据库列名） ====================

    /**
     * 租户ID列名
     */
    public static final String TENANT_ID_COLUMN = "tenant_id";

    /**
     * 创建时间列名
     */
    public static final String CREATE_TIME_COLUMN = "create_time";

    /**
     * 创建人ID列名
     */
    public static final String CREATE_USER_COLUMN = "create_by";

    /**
     * 创建人名称列名
     */
    public static final String CREATE_USER_NAME_COLUMN = "create_name";
    @Schema(description = "ID")
    @TableId(value = FIELD_ID, type = IdType.ASSIGN_ID)
    @OrderBy
    @NotNull(message = "ID不能为空", groups = Update.class)
    private T id;
    
    /**
     * 创建人ID
     */
    @TableField(value = CREATE_USER_COLUMN, fill = FieldFill.INSERT)
    @Schema(description = "创建人ID")
    private T createBy;
    
    /**
     * 创建人名称
     */
    @TableField(value = CREATE_USER_NAME_COLUMN, fill = FieldFill.INSERT)
    @Schema(description = "创建人名称")
    private String createName;
    
    /**
     * 创建时间（依托数据库功能）
     */
    @TableField(value = CREATE_TIME_COLUMN, fill = FieldFill.INSERT)
    @Schema(description = "创建时间")
    private Instant createTime;
    
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
