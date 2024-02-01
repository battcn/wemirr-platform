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
package com.wemirr.framework.storage.cloud.qiniu.connection;

import com.qiniu.cdn.CdnManager;
import com.qiniu.storage.BucketManager;
import com.qiniu.storage.UploadManager;
import com.qiniu.util.Auth;
import com.wemirr.framework.storage.cloud.qiniu.QiNiuScope;

/**
 * @author Levin
 */
public interface QiNiuConnectionFactory {
    
    /**
     * 创建 Auth
     *
     * @return Auth
     */
    Auth getAuth();
    
    /**
     * 获取 BucketManager
     *
     * @return BucketManager
     */
    BucketManager getBucketManager();
    
    /**
     * 获取 UploadManager
     *
     * @return UploadManager
     */
    UploadManager getUploadManager();
    
    /**
     * 获取 CdnManager
     *
     * @return CdnManager
     */
    CdnManager getCdnManager();
    
    /**
     * 获取域名
     *
     * @param bucket bucket
     * @return Domain
     */
    String getDomain(String bucket);
    
    /**
     * 获取文件上传 token
     *
     * @param bucket bucket
     * @param key    key
     * @return token
     */
    String getUploadToken(String bucket, String key);
    
    /**
     * 获取文件上传 token
     *
     * @param bucket bucket
     * @param scope  范围
     * @param key    key
     * @return token
     */
    String getUploadToken(String bucket, String key, QiNiuScope scope);
    
}