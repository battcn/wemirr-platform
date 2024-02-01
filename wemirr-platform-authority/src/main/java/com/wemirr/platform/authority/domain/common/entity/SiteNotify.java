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
package com.wemirr.platform.authority.domain.common.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.wemirr.framework.commons.entity.SuperEntity;
import com.wemirr.platform.authority.domain.baseinfo.enums.ReceiverType;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

/**
 * @author Levin
 */
@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
@TableName(value = "sys_site_notify")
public class SiteNotify extends SuperEntity<Long> {
    
    @TableField("`title`")
    @Schema(description = "标题")
    private String title;
    
    @TableField("`level`")
    @Schema(description = "等级")
    private String level;
    
    @TableField("`status`")
    @Schema(description = "状态")
    private Boolean status;
    
    @TableField("`type`")
    @Schema(description = "类型")
    private ReceiverType type;
    
    @TableField("`receiver`")
    @Schema(description = "接收人")
    private String receiver;
    
    @TableField("`description`")
    @Schema(description = "描述")
    private String description;
    
    @TableField("`content`")
    @Schema(description = "内容")
    private String content;
    
}
