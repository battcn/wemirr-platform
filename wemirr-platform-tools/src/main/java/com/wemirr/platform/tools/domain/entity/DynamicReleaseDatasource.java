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
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.SuperBuilder;

/**
 * @author Levin
 */
@Data
@EqualsAndHashCode(callSuper = true)
@SuperBuilder(toBuilder = true)
@TableName("gen_dynamic_release_datasource")
public class DynamicReleaseDatasource extends SuperEntity<Long> {
    
    @TableField("`database`")
    private String database;
    @TableField("`username`")
    private String username;
    @TableField("`password`")
    private String password;
    @TableField("`host`")
    private String host;
    @TableField("`port`")
    private Integer port;
    @TableField("`description`")
    private String description;
    @TableField("`locked`")
    private Boolean locked;
    
}
