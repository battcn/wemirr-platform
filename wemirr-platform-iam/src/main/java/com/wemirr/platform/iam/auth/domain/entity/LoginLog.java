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

package com.wemirr.platform.iam.auth.domain.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.wemirr.framework.commons.entity.Entity;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

/**
 * 登录日志
 *
 * @author Levin
 */
@Data
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
@TableName("c_login_log")
@EqualsAndHashCode(callSuper = true)
public class LoginLog extends Entity<Long> {
    
    @Schema(description = "登录IP")
    private String ip;
    
    @Schema(description = "租户ID")
    private Long tenantId;
    
    @Schema(description = "租户编码")
    private String tenantCode;
    
    /**
     * 登录人账号
     */
    @Schema(description = "登录人账号")
    private String principal;
    
    @Schema(description = "平台类型")
    private String platform;
    
    @Schema(description = "引擎类型")
    private String engine;
    
    /**
     * 浏览器名称
     */
    @Schema(description = "浏览器名称")
    private String browser;
    
    /**
     * 操作系统
     */
    @Schema(description = "操作系统")
    private String os;
    
    /**
     * 登录地点
     */
    @Schema(description = "登录地点")
    private String location;
    
    @Schema(description = "客户端ID")
    private String clientId;
    
    @Schema(description = "登录类型")
    private String loginType;
}
