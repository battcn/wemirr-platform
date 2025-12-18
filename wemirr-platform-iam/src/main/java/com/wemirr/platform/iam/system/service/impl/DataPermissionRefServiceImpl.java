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

package com.wemirr.platform.iam.system.service.impl;

import cn.hutool.core.collection.CollUtil;
import com.baomidou.dynamic.datasource.annotation.DSTransactional;
import com.wemirr.framework.commons.security.DataResourceType;
import com.wemirr.framework.db.mybatisplus.ext.SuperServiceImpl;
import com.wemirr.framework.db.mybatisplus.wrap.Wraps;
import com.wemirr.platform.iam.system.domain.dto.req.DataPermissionAssignReq;
import com.wemirr.platform.iam.system.domain.entity.DataPermissionRef;
import com.wemirr.platform.iam.system.repository.DataPermissionRefMapper;
import com.wemirr.platform.iam.system.service.DataPermissionRefService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 数据权限关联服务实现
 *
 * @author Levin
 */
@Slf4j
@Service
public class DataPermissionRefServiceImpl extends SuperServiceImpl<DataPermissionRefMapper, DataPermissionRef>
        implements DataPermissionRefService {

    @Override
    public List<DataPermissionRef> listByOwner(DataResourceType ownerType, Long ownerId, DataResourceType dataType) {
        var wrapper = Wraps.<DataPermissionRef>lbQ()
                .eq(DataPermissionRef::getOwnerType, ownerType)
                .eq(DataPermissionRef::getOwnerId, ownerId)
                .eq(dataType != null, DataPermissionRef::getDataType, dataType);
        return baseMapper.selectList(wrapper);
    }

    @Override
    @DSTransactional(rollbackFor = Exception.class)
    public void assignBatch(DataResourceType ownerType, Long ownerId, List<DataPermissionAssignReq> reqList) {
        // 先删除该所有者的所有数据权限
        removeByOwner(ownerType, ownerId, null);
        
        if (CollUtil.isEmpty(reqList)) {
            return;
        }
        
        var refs = new java.util.ArrayList<DataPermissionRef>();
        for (var req : reqList) {
            var dataType = req.getDataType();
            var scopeType = req.getScopeType();
            var dataIds = req.getDataIds();
            
            if (scopeType == 20) {
                // 自定义类型：必须有 dataIds，否则跳过
                if (CollUtil.isEmpty(dataIds)) {
                    continue;
                }
                dataIds.forEach(dataId -> refs.add(buildRef(ownerType, ownerId, dataType, scopeType, dataId)));
            } else {
                // 非自定义类型(10个人/30本级/40本级及子级/50全部)：只保存一条记录，标记 scopeType 即可
                refs.add(buildRef(ownerType, ownerId, dataType, scopeType, null));
            }
        }
        
        if (CollUtil.isNotEmpty(refs)) {
            baseMapper.insertBatchSomeColumn(refs);
        }
    }

    @Override
    public void removeByOwner(DataResourceType ownerType, Long ownerId, DataResourceType dataType) {
        var wrapper = Wraps.<DataPermissionRef>lbQ()
                .eq(DataPermissionRef::getOwnerType, ownerType)
                .eq(DataPermissionRef::getOwnerId, ownerId);
        if (dataType != null) {
            wrapper.eq(DataPermissionRef::getDataType, dataType);
        }
        baseMapper.delete(wrapper);
    }

    private DataPermissionRef buildRef(DataResourceType ownerType, Long ownerId,
                                       DataResourceType dataType, Integer scopeType, Long dataId) {
        return DataPermissionRef.builder()
                .ownerType(ownerType)
                .ownerId(ownerId)
                .dataType(dataType)
                .scopeType(scopeType)
                .dataId(dataId)
                .build();
    }
}
