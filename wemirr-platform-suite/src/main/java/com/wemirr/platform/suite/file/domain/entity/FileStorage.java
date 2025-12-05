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

package com.wemirr.platform.suite.file.domain.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.wemirr.framework.commons.entity.SuperEntity;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

/**
 * @author xiao1
 * @since 2024-12
 */
@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@TableName(value = "t_file_storage")
public class FileStorage extends SuperEntity<Long> {
    
    @TableField(value = "url")
    @Schema(description = "文件访问地址")
    private String url;
    
    @Schema(description = "文件大小，单位字节")
    private Long size;
    
    @TableField(value = "format_size")
    @Schema(description = "格式化后的文件大小")
    private String formatSize;
    
    @Schema(description = "文件名称")
    @TableField(value = "filename")
    private String filename;
    
    @Schema(description = "原始文件名")
    private String originalFilename;
    
    @TableField(value = "base_path")
    @Schema(description = "基础存储路径")
    private String basePath;

    @Schema(description = "存储路径")
    @TableField(value = "path")
    private String path;

    @Schema(description = "文件扩展名")
    @TableField(value = "ext")
    private String ext;
    
    @Schema(description = "MIME类型")
    @TableField(value = "content_type")
    private String contentType;

    @Schema(description = "存储平台")
    @TableField(value = "platform")
    private String platform;
    
    @Schema(description = "缩略图访问路径")
    @TableField(value = "th_url")
    private String thUrl;

    @Schema(description = "缩略图名称")
    @TableField(value = "th_filename")
    private String thFilename;

    @Schema(description = "缩略图大小，单位字节")
    @TableField(value = "th_size")
    private Long thSize;

    @Schema(description = "缩略图MIME类型")
    @TableField(value = "th_content_type")
    private String thContentType;

    @Schema(description = "文件所属对象id")
    @TableField(value = "object_id")
    private String objectId;

    @Schema(description = "文件所属对象类型")
    @TableField(value = "object_type")
    private String objectType;

    @Schema(description = "文件元数据")
    @TableField(value = "metadata")
    private String metadata;
    
    @Schema(description = "文件用户元数据")
    @TableField(value = "user_metadata")
    private String userMetadata;

    @Schema(description = "缩略图元数据")
    @TableField(value = "th_metadata")
    private String thMetadata;

    @Schema(description = "缩略图用户元数据")
    @TableField(value = "th_user_metadata")
    private String thUserMetadata;

    @Schema(description = "附加属性")
    @TableField(value = "attr")
    private String attr;
    
    @TableField(value = "hash_info")
    private String hashInfo;
    
    @Schema(description = "文件分类")
    private String category;
    
    @Schema(description = "租户ID")
    private Long tenantId;
}
