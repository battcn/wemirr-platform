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
import org.springframework.beans.factory.annotation.Value;

/**
 * @author Levin
 */
@Data
public class BaseStorageProperties implements java.io.Serializable {
    
    private boolean enabled = false;
    
    @Value("${spring.application.name:'oss'}")
    private String bucket;
    
    private String mappingPath = "http://minio.battcn.com/";
    
    /**
     * 访问key
     **/
    private String accessKey;
    
    /**
     * 访问秘钥
     **/
    private String secretKey;
    
    /**
     * 本地文件临时目录
     */
    private String tmpDir;
    
    public enum StorageType {
        
        /**
         * MINIO
         */
        MINIO,
        ALIYUN,
        TENCENT,
        QINIU,
        ;
    }
    
}
