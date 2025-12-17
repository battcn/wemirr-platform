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

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableLogic;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.Accessors;
import lombok.experimental.SuperBuilder;

import java.time.Instant;

/**
 * 增强实体类
 * <p>在{@link Entity}基础上增加修改人、修改时间、逻辑删除等字段</p>
 *
 * <h3>字段说明</h3>
 * <ul>
 *   <li>lastModifyTime - 最后修改时间（自动填充）</li>
 *   <li>lastModifyBy - 最后修改人ID（自动填充）</li>
 *   <li>lastModifyName - 最后修改人名称（自动填充）</li>
 *   <li>deleted - 逻辑删除标识</li>
 * </ul>
 *
 * @param <T> 主键类型（通常为Long）
 * @author Levin
 * @since 1.0.0
 * @see Entity
 */
@Data
@NoArgsConstructor
@SuperBuilder(toBuilder = true)
@Accessors(chain = true)
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
public class SuperEntity<T> extends Entity<T> {

    // ==================== 字段名常量（Java属性名） ====================

    /**
     * 逻辑删除属性名
     */
    public static final String DELETED = "deleted";

    /**
     * 最后修改时间属性名
     */
    public static final String UPDATE_TIME = "lastModifyTime";

    /**
     * 最后修改人ID属性名
     */
    public static final String UPDATE_USER = "lastModifyBy";

    /**
     * 最后修改人名称属性名
     */
    public static final String UPDATE_USER_NAME = "lastModifyName";

    // ==================== 列名常量（数据库列名） ====================

    /**
     * 最后修改时间列名
     */
    public static final String UPDATE_TIME_COLUMN = "last_modify_time";

    /**
     * 最后修改人ID列名
     */
    public static final String UPDATE_USER_COLUMN = "last_modify_by";

    /**
     * 最后修改人名称列名
     */
    public static final String UPDATE_USER_NAME_COLUMN = "last_modify_name";

    @Schema(description = "最后修改时间")
    @TableField(value = UPDATE_TIME_COLUMN, fill = FieldFill.UPDATE)
    private Instant lastModifyTime;

    @Schema(description = "最后修改人ID")
    @TableField(value = UPDATE_USER_COLUMN, fill = FieldFill.UPDATE)
    private T lastModifyBy;

    @Schema(description = "最后修改人名称")
    @TableField(value = UPDATE_USER_NAME_COLUMN, fill = FieldFill.UPDATE)
    private String lastModifyName;

    @TableLogic(value = "false", delval = "true")
    @TableField(value = DELETED, fill = FieldFill.INSERT)
    @Schema(description = "逻辑删除")
    private Boolean deleted;

}
