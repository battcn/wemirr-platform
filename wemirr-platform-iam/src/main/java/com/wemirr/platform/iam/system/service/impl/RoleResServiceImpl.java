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
import com.wemirr.framework.db.mybatisplus.ext.SuperServiceImpl;
import com.wemirr.framework.db.mybatisplus.wrap.Wraps;
import com.wemirr.platform.iam.system.domain.dto.req.RoleResSaveReq;
import com.wemirr.platform.iam.system.domain.dto.req.UserRoleSaveReq;
import com.wemirr.platform.iam.system.domain.entity.RoleRes;
import com.wemirr.platform.iam.system.domain.entity.UserRole;
import com.wemirr.platform.iam.system.repository.RoleResMapper;
import com.wemirr.platform.iam.system.repository.UserRoleMapper;
import com.wemirr.platform.iam.system.service.RoleResService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Set;

import static java.util.stream.Collectors.toList;

/**
 * 角色资源
 *
 * @author Levin
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class RoleResServiceImpl extends SuperServiceImpl<RoleResMapper, RoleRes> implements RoleResService {

    private final UserRoleMapper userRoleMapper;

    @Override
    @DSTransactional(rollbackFor = Exception.class)
    public boolean assignUser(UserRoleSaveReq req) {
        userRoleMapper.delete(Wraps.<UserRole>lbQ().eq(UserRole::getRoleId, req.getRoleId()));
        List<UserRole> list = req.getUserIdList().stream()
                .map(userId -> UserRole.builder().userId(userId).roleId(req.getRoleId()).build()).collect(toList());
        userRoleMapper.insertBatchSomeColumn(list);
        return true;
    }

    @Override
    @DSTransactional(rollbackFor = Exception.class)
    public void assignResource(RoleResSaveReq req) {
        // 删除角色和资源的关联
        super.remove(Wraps.<RoleRes>lbQ().eq(RoleRes::getRoleId, req.getRoleId()));
        resHandler(req, req.getRoleId());
    }

    private void resHandler(RoleResSaveReq data, Long roleId) {
        final Set<Long> resIdList = data.getResIdList();
        if (CollUtil.isEmpty(resIdList)) {
            return;
        }
        final List<RoleRes> list = resIdList.stream().filter(Objects::nonNull)
                .map(resId -> RoleRes.builder().resId(resId).roleId(roleId).build()).collect(toList());
        this.baseMapper.insertBatchSomeColumn(list);
    }
}
