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
package com.wemirr.framework.storage.endpoint;

import com.google.common.collect.Maps;
import com.wemirr.framework.storage.AliYunStorageOperation;
import com.wemirr.framework.storage.properties.AliYunStorageProperties;
import jakarta.annotation.Resource;
import org.springframework.boot.actuate.endpoint.annotation.ReadOperation;

import java.util.Map;

/**
 * @author <a href="mailto:1837307557@qq.com">Levin</a>
 * @since 2.0.2
 */
public class OssEndpoint {
    
    @Resource
    private AliYunStorageProperties properties;
    
    @ReadOperation
    public Object invoke() {
        Map<String, Object> info = Maps.newLinkedHashMap();
        info.put("upload.success", AliYunStorageOperation.FILE_UPLOAD_SUCCESS.get());
        info.put("upload.fail", AliYunStorageOperation.FILE_UPLOAD_FAIL.get());
        info.put("get.count", AliYunStorageOperation.FILE_GET_COUNTS.get());
        info.put("delete.count", AliYunStorageOperation.FILE_DELETE_COUNTS.get());
        return info;
    }
}