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
import com.wemirr.framework.db.mybatisplus.handler.type.JsonTypeHandler;
import com.wemirr.framework.log.diff.domain.DiffLogInfo;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Map;

/**
 * @author Levin
 */
@Data
@EqualsAndHashCode(callSuper = true)
@TableName("c_diff_log")
public class DiffLogEntity extends DiffLogInfo {
    
    @TableField(typeHandler = JsonTypeHandler.class)
    @Schema(description = "日志的代码信息")
    protected Map<Object, Object> variables;
    @TableId(type = IdType.ASSIGN_ID)
    @OrderBy
    private Long id;
}
