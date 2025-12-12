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

import com.baomidou.mybatisplus.annotation.TableName;
import com.wemirr.framework.commons.entity.SuperEntity;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

/**
 * 对象存储文件记录
 * * @author xiao1
 * @since 2025-12
 */
@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@TableName(value = "oss_file")
public class OssFile extends SuperEntity<Long> {

    @Schema(description = "租户ID")
    private Long tenantId;

    @Schema(description = "存储平台标识")
    private String platform;

    @Schema(description = "文件分类")
    private String category;

    // ================== 核心信息 ==================

    @Schema(description = "原始文件名")
    private String originalFilename;

    @Schema(description = "文件名称")
    private String filename;

    @Schema(description = "文件访问地址")
    private String url;

    @Schema(description = "文件大小，单位字节")
    private Long size;

    @Schema(description = "MIME类型")
    private String contentType;

    @Schema(description = "文件扩展名")
    private String ext;

    // ================== 物理存储信息 ==================

    @Schema(description = "基础存储路径")
    private String basePath;

    @Schema(description = "存储路径")
    private String path;

    @Schema(description = "文件ACL (private/public-read)")
    private String fileAcl;

    // ================== 缩略图信息 ==================

    @Schema(description = "缩略图访问路径")
    private String thUrl;

    @Schema(description = "缩略图名称")
    private String thFilename;

    @Schema(description = "缩略图大小，单位字节")
    private Long thSize;

    @Schema(description = "缩略图MIME类型")
    private String thContentType;

    @Schema(description = "缩略图文件ACL")
    private String thFileAcl;

    @Schema(description = "文件所属对象id")
    private String objectId;

    @Schema(description = "文件所属对象类型")
    private String objectType;

    @Schema(description = "哈希信息")
    private String hashInfo;

    @Schema(description = "附加属性")
    private String attr;

    @Schema(description = "文件元数据")
    private String metadata;

    @Schema(description = "文件用户元数据")
    private String userMetadata;

    @Schema(description = "缩略图元数据")
    private String thMetadata;

    @Schema(description = "缩略图用户元数据")
    private String thUserMetadata;
}