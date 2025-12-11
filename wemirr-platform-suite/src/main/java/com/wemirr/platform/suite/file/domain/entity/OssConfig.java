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
 * 对象存储配置
 *
 * @author xiao1
 * @since 2025-12
 */
@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@TableName("oss_config")
public class OssConfig extends SuperEntity<Long> {

    @Schema(description = "租户ID")
    private Long tenantId;

    @Schema(description = "存储类型 (MINIO, ALIYUN, TENCENT, QINIU, S3)")
    private String type;

    @Schema(description = "配置编码 (唯一标识, 如: minio_local)")
    private String platform;

    // ================== 认证信息 ==================

    @Schema(description = "AccessKey")
    private String accessKey;

    @Schema(description = "SecretKey")
    // 建议：如果返回前端不需要展示SK，可以加 @JsonIgnore 或在VO层处理
    private String secretKey;

    @Schema(description = "存储桶名称")
    private String bucketName;

    // ================== 网络配置 ==================

    @Schema(description = "连接端点 (Endpoint)")
    private String endPoint;

    @Schema(description = "存储区域 (Region)")
    private String region;

    @Schema(description = "访问域名 (CDN/自定义域名)")
    private String domain;

    @Schema(description = "基础路径/前缀")
    private String basePath;

    // ================== 状态控制 ==================

    /**
     * 是否启用 [true:启用; false:禁用]
     * 业务逻辑需保证：同个租户下只能开启一个配置 (is_default)
     */
    @Schema(description = "状态")
    private Boolean status;
}