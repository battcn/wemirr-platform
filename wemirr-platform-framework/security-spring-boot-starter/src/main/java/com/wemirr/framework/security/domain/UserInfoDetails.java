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

package com.wemirr.framework.security.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.wemirr.framework.commons.security.AuthenticationDetails;
import com.wemirr.framework.commons.security.DataPermission;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Map;

/**
 * @author Levin
 * @since 2019-04-03
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserInfoDetails implements Serializable, AuthenticationDetails {

    @Serial
    private static final long serialVersionUID = 1L;

    @Schema(description = "用户ID")
    private Long userId;
    @Schema(description = "邮箱")
    private String email;
    @Schema(description = "手机号")
    private String mobile;
    @Schema(description = "租户ID")
    private Long tenantId;
    @Schema(description = "租户编码")
    private String tenantCode;
    @Schema(description = "租户名称")
    private String tenantName;
    @Schema(description = "昵称")
    private String nickName;
    @Schema(description = "用户名")
    private String username;
    @JsonIgnore
    @Schema(hidden = true)
    private String password;
    @Schema(description = "头像")
    private String avatar;
    @Schema(description = "启用状态")
    private Boolean enabled;
    @Schema(description = "描述信息")
    private String description;
    @Schema(description = "生日")
    private LocalDate birthday;
    @Schema(description = "机构ID")
    private Long orgId;

    @Schema(description = "登录日志")
    private Map<String, Object> loginLog;
    /**
     * 功能权限（资源码）
     */
    @Schema(description = "功能权限（资源码）")
    @Builder.Default
    private Collection<String> funcPermissions = new ArrayList<>();

    @Builder.Default
    @Schema(description = "功能权限（角色编码）")
    private Collection<String> roles = new ArrayList<>();
    /**
     * 数据权限(可视范围)
     */
    @Schema(description = "数据权限(可视范围)")
    private DataPermission dataPermission;

}
