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

package com.wemirr.framework.storage.cloud.aliyun;

import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClientBuilder;
import com.wemirr.framework.storage.AliYunStorageOperation;
import com.wemirr.framework.storage.endpoint.OssEndpoint;
import com.wemirr.framework.storage.properties.AliYunStorageProperties;
import lombok.AllArgsConstructor;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import static com.wemirr.framework.storage.StorageOperation.ALI_YUN_STORAGE_OPERATION;
import static com.wemirr.framework.storage.StorageOperation.OSS_CONFIG_PREFIX_ALIYUN;

/**
 * 阿里云 OSS
 *
 * @author Levin
 */
@Configuration
@AllArgsConstructor
@EnableConfigurationProperties(AliYunStorageProperties.class)
@ConditionalOnProperty(prefix = OSS_CONFIG_PREFIX_ALIYUN, name = "enabled", havingValue = "true")
public class AliYunOssAutoConfiguration {
    
    @Bean(destroyMethod = "shutdown")
    public OSS ossClient(AliYunStorageProperties properties) {
        return new OSSClientBuilder().build(properties.getEndpoint(), properties.getAccessKey(), properties.getSecretKey());
    }
    
    @Bean(ALI_YUN_STORAGE_OPERATION)
    public AliYunStorageOperation aliYunStorageOperation(OSS ossClient, AliYunStorageProperties properties) {
        return new AliYunStorageOperation(ossClient, properties);
    }
    
    @Bean
    public OssEndpoint aliYunOssEndpoint() {
        return new OssEndpoint();
    }
}
