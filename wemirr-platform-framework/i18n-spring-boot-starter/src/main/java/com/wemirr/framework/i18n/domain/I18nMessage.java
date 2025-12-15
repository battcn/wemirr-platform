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

package com.wemirr.framework.i18n.domain;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serial;
import java.io.Serializable;

/**
 * 国际化消息实体
 * <p>
 * 对标于 message bundle 的文件消息的抽象，支持多租户
 *
 * @author Levin
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "国际化消息")
public class I18nMessage implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    @Schema(description = "消息代码")
    private String code;

    @Schema(description = "消息文本，支持 {0} 占位符")
    private String message;

    @Schema(description = "语言标识，如 zh_CN, en_US")
    private String locale;

    @Schema(description = "租户ID")
    private Long tenantId;

    /**
     * 构建 Redis 存储的 key
     *
     * @return locale:code 格式的 key
     */
    public String buildKey() {
        return locale + ":" + code;
    }
}