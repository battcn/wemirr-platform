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

package com.wemirr.framework.db.mybatisplus.datascope.service;

import com.wemirr.framework.commons.FeignConstants;
import com.wemirr.framework.commons.security.DataPermission;
import com.wemirr.framework.db.properties.DatabaseProperties;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.context.annotation.Primary;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * @author Levin
 */
@Primary
@ConditionalOnProperty(prefix = DatabaseProperties.INTERCEPT_DATA_PERMISSION_PREFIX, name = "remote")
@FeignClient(name = FeignConstants.AUTH_FEIGN_NAME, dismiss404 = true)
public interface FeignDataScopeServiceImpl extends DataScopeService {
    
    /**
     * 获取用户的数据权限
     *
     * @param userId 用户ID
     * @return 结果
     */
    @Override
    @GetMapping("/{user_id}/data_permission")
    DataPermission getDataScopeById(@PathVariable("user_id") Long userId);
    
}
