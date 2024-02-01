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
@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@TableName("t_file")
public class FileEntity extends SuperEntity<Long> {
    
    private Long tenantId;
    
    private String contentType;
    
    @TableField("`location`")
    private String location;
    
    @TableField("`ip`")
    private String ip;
    
    @TableField("`engine`")
    private String engine;
    
    private String engineVersion;
    
    private String os;
    
    @TableField("`bucket`")
    private String bucket;
    
    private String originName;
    
    private String targetName;
    
    @TableField("`size`")
    private Long size;
    
    /**
     * 文件的完整路径
     */
    private String fullUrl;
    
    private String mappingPath;
    
    /**
     * 对应存储的扩展字段
     */
    private String extend;
    
}
