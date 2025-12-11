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

package com.wemirr.platform.suite.file.domain.dto.resp;

import cn.hutool.core.io.FileUtil;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.time.Instant;

/**
 * OSS文件分页响应对象
 *
 * @author xiao1
 * @since 2025-12
 */
@Data
@Schema(description = "OSS文件分页响应数据")
public class OssFilePageResp {

    @Schema(description = "主键ID")
    private Long id;

    @Schema(description = "租户ID")
    private Long tenantId;

    @Schema(description = "存储平台标识")
    private String platform;

    @Schema(description = "文件分类")
    private String category;

    @Schema(description = "原始文件名")
    private String originalFilename;

    @Schema(description = "当前文件名")
    private String filename;

    @Schema(description = "文件扩展名")
    private String ext;

    @Schema(description = "MIME类型")
    private String contentType;

    @Schema(description = "文件大小 (字节)")
    private Long size;

    @Schema(description = "格式化后的文件大小 (如: 1.5MB)")
    private String formatSize;

    @Schema(description = "文件访问地址")
    private String url;

    @Schema(description = "缩略图访问地址")
    private String thUrl;

    @Schema(description = "缩略图大小 (字节)")
    private Long thSize;

    @Schema(description = "基础存储路径")
    private String basePath;

    @Schema(description = "存储路径")
    private String path;

    @Schema(description = "上传人姓名")
    private String createName;

    @Schema(description = "上传时间")
    private Instant createTime;

    /**
     * 格式化后的文件大小 (例如: 1.5MB)
     * 利用 Hutool 工具类动态计算，不存库
     */
    @Schema(description = "格式化后的文件大小")
    public String getFormatSize() {
        if (this.size == null) {
            return "0B";
        }
        // 使用 Hutool 格式化
        return FileUtil.readableFileSize(this.size);
    }

}