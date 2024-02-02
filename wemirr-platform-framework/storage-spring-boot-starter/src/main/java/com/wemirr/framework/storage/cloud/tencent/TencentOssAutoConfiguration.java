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

package com.wemirr.framework.storage.cloud.tencent;

import com.qcloud.cos.COSClient;
import com.qcloud.cos.ClientConfig;
import com.qcloud.cos.auth.BasicCOSCredentials;
import com.qcloud.cos.auth.COSCredentials;
import com.qcloud.cos.region.Region;
import com.wemirr.framework.storage.TencentStorageOperation;
import com.wemirr.framework.storage.properties.TencentStorageProperties;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import static com.wemirr.framework.storage.StorageOperation.OSS_CONFIG_PREFIX_TENCENT;
import static com.wemirr.framework.storage.StorageOperation.TENCENT_STORAGE_OPERATION;

/**
 * OSS自动配置
 *
 * @author Levin
 * @since 2018-09-18 12:24
 **/
@Configuration
@EnableConfigurationProperties({TencentStorageProperties.class})
@ConditionalOnProperty(prefix = OSS_CONFIG_PREFIX_TENCENT, name = "enabled", havingValue = "true")
public class TencentOssAutoConfiguration {
    
    @Bean
    public COSClient cosClient(TencentStorageProperties properties) {
        COSCredentials credentials = new BasicCOSCredentials(properties.getAccessKey(), properties.getSecretKey());
        // 初始化客户端配置
        ClientConfig clientConfig = new ClientConfig(new Region(properties.getRegion()));
        return new COSClient(credentials, clientConfig);
    }
    
    @Bean(TENCENT_STORAGE_OPERATION)
    public TencentStorageOperation tencentStorageOperation(COSClient cosClient, TencentStorageProperties properties) {
        return new TencentStorageOperation(cosClient, properties);
    }
    
}
