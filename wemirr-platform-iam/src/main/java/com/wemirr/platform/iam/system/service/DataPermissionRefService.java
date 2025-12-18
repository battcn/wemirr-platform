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

package com.wemirr.platform.iam.system.service;

import com.wemirr.framework.commons.security.DataResourceType;
import com.wemirr.framework.db.mybatisplus.ext.SuperService;
import com.wemirr.platform.iam.system.domain.dto.req.DataPermissionAssignReq;
import com.wemirr.platform.iam.system.domain.entity.DataPermissionRef;

import java.util.List;

/**
 * 数据权限关联服务
 *
 * @author Levin
 */
public interface DataPermissionRefService extends SuperService<DataPermissionRef> {

    /**
     * 查询所有者的数据权限
     *
     * @param ownerType 所有者类型
     * @param ownerId   所有者ID
     * @param dataType  数据类型（可选，为空则查询所有）
     * @return 数据权限列表
     */
    List<DataPermissionRef> listByOwner(DataResourceType ownerType, Long ownerId, DataResourceType dataType);

    /**
     * 批量分配数据权限
     *
     * @param ownerType 所有者类型
     * @param ownerId   所有者ID
     * @param reqList   权限分配请求列表
     */
    void assignBatch(DataResourceType ownerType, Long ownerId, List<DataPermissionAssignReq> reqList);

    /**
     * 删除所有者的数据权限
     *
     * @param ownerType 所有者类型
     * @param ownerId   所有者ID
     * @param dataType  数据类型（可选，为空则删除所有）
     */
    void removeByOwner(DataResourceType ownerType, Long ownerId, DataResourceType dataType);
}
