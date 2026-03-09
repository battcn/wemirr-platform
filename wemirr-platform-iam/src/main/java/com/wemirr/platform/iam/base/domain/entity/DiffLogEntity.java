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

package com.wemirr.platform.iam.base.domain.entity;

import com.baomidou.mybatisplus.annotation.*;
import com.baomidou.mybatisplus.extension.handlers.JacksonTypeHandler;
import com.wemirr.framework.log.diff.domain.DiffLogInfo;
import com.wemirr.framework.log.diff.domain.FieldChange;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;
import java.util.Map;

/**
 * 差异日志实体，继承自 DiffLogInfo 并添加数据库映射
 *
 * @author Levin
 */
@Data
@EqualsAndHashCode(callSuper = true)
@TableName(value = "c_diff_log", autoResultMap = true)
public class DiffLogEntity extends DiffLogInfo {

    @TableId(type = IdType.ASSIGN_ID)
    @OrderBy
    private Long id;

    @TableField(typeHandler = JacksonTypeHandler.class)
    @Schema(description = "请求上下文信息（IP、浏览器、URI等）")
    protected Map<String, Object> extra;

    @TableField(typeHandler = JacksonTypeHandler.class)
    @Schema(description = "字段变更记录列表")
    protected List<FieldChange> variables;

}
