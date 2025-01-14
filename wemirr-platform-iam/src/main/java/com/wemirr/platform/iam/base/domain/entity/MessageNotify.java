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

package com.wemirr.platform.iam.base.domain.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.wemirr.framework.commons.entity.SuperEntity;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

/**
 * @author Levin
 */
@Data
@SuperBuilder(toBuilder = true)
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@TableName(value = "b_message_notify")
public class MessageNotify extends SuperEntity<Long> {
    
    @Schema(description = "标题")
    private String title;
    
    @Schema(description = "消息类型")
    private String type;
    
    @Schema(description = "消息模板ID")
    private Long templateId;
    
    @Schema(description = "消息变量")
    private String variables;
    
    @Schema(description = "内容")
    private String content;
    
    @Schema(description = "订阅人 比如 邮箱,手机号,钉钉ID等")
    private String subscribe;
    
    @Schema(description = "接收用户ID")
    private Long userId;
    
    @Schema(description = "用户昵称")
    private String nickname;
    
    @Schema(description = "租户ID")
    private Long tenantId;
    
}
