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
package com.wemirr.platform.tools.domain.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.wemirr.framework.commons.entity.SuperEntity;
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
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@TableName("gen_dynamic_release_column")
public class DynamicReleaseColumn extends SuperEntity<Long> {
    
    private Long gridId;
    @TableField("`title`")
    private String title;
    @TableField("`key`")
    private String key;
    @TableField("`type`")
    private String type;
    @TableField("`disabled`")
    private Boolean disabled;
    @TableField("`form`")
    private String form;
    @TableField("`search`")
    private String search;
    @TableField("`dict`")
    private String dict;
    @TableField("`width`")
    private Integer width;
    private Boolean showOverflowTooltip;
    @TableField("`fixed`")
    private Boolean fixed;
    @TableField("`simple`")
    private Boolean simple;
    private String columnJson;
    
}
