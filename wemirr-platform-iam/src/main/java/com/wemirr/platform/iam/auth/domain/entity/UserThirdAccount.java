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
import com.wemirr.framework.commons.entity.SuperEntity;
import com.wemirr.platform.iam.system.domain.enums.ThirdAuthType;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import lombok.experimental.SuperBuilder;

import java.time.Instant;

/**
 * @author Levin
 */
@Data
@SuperBuilder
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@Schema(description = "第三方授权账户实体类")
@NoArgsConstructor
@AllArgsConstructor
@TableName("t_user_third_account")
public class UserThirdAccount extends SuperEntity<Long> {
    
    @Schema(description = "平台用户 ID")
    private Long userId;
    
    @Schema(description = "平台类型")
    private ThirdAuthType type;
    
    @Schema(description = "第三方平台用户唯一标识")
    private String accountId;
    
    @Schema(description = "用户名")
    private String username;
    
    @Schema(description = "昵称")
    private String nickname;
    
    @Schema(description = "邮箱")
    private String email;
    
    @Schema(description = "头像 URL")
    private String avatar;
    
    @Schema(description = "访问令牌")
    private String accessToken;
    
    @Schema(description = "刷新令牌")
    private String refreshToken;
    
    @Schema(description = "令牌过期时间")
    private Instant tokenExpireTime;
    
}
