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

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * @author Levin
 */
@Data
@Deprecated
public class StorageProperties {
    
    private Boolean enabled;
    private String secretId;
    private String secretKey;
    private String region;
    private String bucket;
    private String url;
    private String tmpDir;
    private ACL acl = ACL.DEFAULT;
    /**
     * 30分钟的有效期
     */
    private long expiration = 60 * 60 * 1000;
    
    @AllArgsConstructor
    public enum ACL {
        
        /**
         * 私有
         */
        PRIVATE("private"),
        
        /**
         * 公读
         */
        PUBLIC_READ("public-read"),
        /**
         * 公读写
         */
        PUBLIC_READ_WRITE("public-read-write"),
        
        /**
         * 默认
         */
        DEFAULT("default");
        
        /**
         * 存储的权限
         */
        private final String cannedAclHeader;
        
        public static ACL of(String cannedAclHeader) {
            for (ACL acl : values()) {
                if (acl.cannedAclHeader.equals(cannedAclHeader)) {
                    return acl;
                }
            }
            return null;
        }
        
    }
    
}
