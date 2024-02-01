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
import com.wemirr.framework.commons.entity.Entity;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

/**
 * <p>
 * 实体类
 * 登录日志
 * </p>
 *
 * @author Levin
 * @since 2019-11-01
 */
@Data
@EqualsAndHashCode(callSuper = true)
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
@TableName("common_login_log")
public class LoginLog extends Entity<Long> {
    
    /**
     * 登录IP
     */
    @Schema(description = "登录IP")
    private String ip;
    
    /**
     * 登录人ID
     */
    @Schema(description = "登录人ID")
    @TableField("user_id")
    private Long userId;
    
    @Schema(description = "租户ID")
    private Long tenantId;
    
    /**
     * 登录人姓名
     */
    @Schema(description = "登录人姓名")
    private String name;
    
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
    @TableField(value = "browser")
    private String browser;
    
    /**
     * 操作系统
     */
    @Schema(description = "操作系统")
    @TableField(value = "os")
    private String os;
    
    /**
     * 登录地点
     */
    @Schema(description = "登录地点")
    @TableField(value = "location")
    private String location;
    
    private String clientId;
    
}
