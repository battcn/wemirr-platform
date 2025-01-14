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

package com.wemirr.platform.iam.system.repository;

import com.baomidou.mybatisplus.annotation.InterceptorIgnore;
import com.wemirr.framework.db.mybatisplus.ext.SuperMapper;
import com.wemirr.platform.iam.system.domain.entity.Resource;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * <p>
 * Mapper 接口
 * 资源
 * </p>
 *
 * @author Levin
 */
@Repository
public interface ResourceMapper extends SuperMapper<Resource> {
    
    /**
     * 查询指定用户资源权限
     *
     * @param userId 用户ID
     * @return 查询结果
     */
    @InterceptorIgnore(tenantLine = "true", dataPermission = "false")
    List<String> selectPermissionByUserId(Long userId);
}
