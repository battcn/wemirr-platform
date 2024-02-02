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

package com.wemirr.framework.storage.domain;

import com.wemirr.framework.storage.FileUtils;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.commons.lang3.StringUtils;

import java.io.InputStream;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * 请求参数
 *
 * @author Levin
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class StorageRequest implements java.io.Serializable {

    /**
     * 如果为空则取应用配置的
     */
    private String bucket;

    /**
     * 原始文件名称
     */
    private String originName;

    /**
     * 如果为 true 则会随机生成文件名
     */
    @Builder.Default
    private boolean randomName = true;

    private PrefixRule rule;

    private String prefix;

    /**
     * content 与 inputStream 二选一
     */
    private byte[] content;
    /**
     * inputStream 与 content 二选一
     */
    private InputStream inputStream;

    private String contentType;

    private Object tenantId;

    private Object userId;

    public enum PrefixRule {
        /**
         * 无规则 默认提取 prefix 否则自动生成前缀
         */
        none,
        /**
         * 当前日期+月份
         */
        now_date_mouth,

        /**
         * 当前年月日
         */
        now_date_mouth_day,

        /**
         * 租户当前日期策略
         */
        tenant_now_date_mouth_day
    }

    /**
     * 获取目标名字
     *
     * @return 目标名称
     * @throws RuntimeException RuntimeException
     */
    public String buildTargetName() {
        if (StringUtils.isBlank(this.getOriginName())) {
            throw new RuntimeException("originName 不能为空");
        }
        final PrefixRule rule = this.getRule();
        if (rule == null) {
            return FileUtils.targetName(this.isRandomName(), this.getPrefix(), this.getOriginName());
        }
        String prefix;
        switch (rule) {
            case now_date_mouth -> prefix = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMM"));
            case now_date_mouth_day -> prefix = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMdd"));
            case tenant_now_date_mouth_day -> {
                if (tenantId == null || userId == null) {
                    throw new RuntimeException("tenantId or userId not null");
                }
                prefix = tenantId + "/" + userId + LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMdd"));
            }
            case none -> prefix = this.getPrefix();
            default -> {
                return this.getPrefix();
            }
        }
        return FileUtils.targetName(this.isRandomName(), prefix, this.getOriginName());
    }
}
