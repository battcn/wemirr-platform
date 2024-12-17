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

package com.wemirr.framework.storage.properties;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.boot.context.properties.ConfigurationProperties;

import static com.wemirr.framework.storage.StorageOperation.OSS_CONFIG_PREFIX_TENCENT;

/**
 * @author Levin
 * @since 2018-09-17 11:09
 **/
@Data
@EqualsAndHashCode(callSuper = true)
@ConfigurationProperties(prefix = OSS_CONFIG_PREFIX_TENCENT)
public class TencentStorageProperties extends BaseStorageProperties {
    
    /**
     * AppId
     */
    private String appId;
    /**
     * 前缀
     */
    private String prefix;
    
    /**
     * 绑定的域名
     */
    private String domain;
    
    /**
     * 所属地区
     */
    private String region;
    
}
