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

package com.wemirr.platform.authority.service.impl;

import cn.hutool.core.collection.CollectionUtil;
import com.baomidou.dynamic.datasource.annotation.DSTransactional;
import com.wemirr.framework.db.mybatisplus.ext.SuperServiceImpl;
import com.wemirr.framework.db.mybatisplus.wrap.Wraps;
import com.wemirr.platform.authority.domain.baseinfo.entity.RoleRes;
import com.wemirr.platform.authority.domain.baseinfo.entity.UserRole;
import com.wemirr.platform.authority.domain.baseinfo.req.RoleResSaveReq;
import com.wemirr.platform.authority.domain.baseinfo.req.UserRoleSaveReq;
import com.wemirr.platform.authority.domain.baseinfo.resp.RoleResMenuMapperResp;
import com.wemirr.platform.authority.domain.baseinfo.resp.RoleResResp;
import com.wemirr.platform.authority.repository.baseinfo.RoleResMapper;
import com.wemirr.platform.authority.service.RoleResService;
import com.wemirr.platform.authority.service.UserRoleService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * <p>
 * 业务实现类
 * 角色的资源
 * </p>
 *
 * @author Levin
 * @since 2019-07-03
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class RoleResServiceImpl extends SuperServiceImpl<RoleResMapper, RoleRes> implements RoleResService {
    
    private final UserRoleService userRoleService;
    
    @Override
    public RoleResResp findAuthorityIdByRoleId(Long roleId) {
        final List<RoleResMenuMapperResp> list = this.baseMapper.selectRoleResByRoleId(roleId);
        List<Long> menuIdList = list.stream().filter(xx -> xx.getType() == 1 || xx.getType() == 5)
                .mapToLong(RoleResMenuMapperResp::getId).boxed().distinct().collect(Collectors.toList());
        List<Long> resourceIdList = list.stream().filter(xx -> xx.getType() == 2)
                .mapToLong(RoleResMenuMapperResp::getId).boxed().distinct().collect(Collectors.toList());
        return RoleResResp.builder()
                .menuIdList(menuIdList)
                .resourceIdList(resourceIdList)
                .build();
    }
    
    @Override
    public boolean saveUserRole(UserRoleSaveReq req) {
        userRoleService.remove(Wraps.<UserRole>lbQ().eq(UserRole::getRoleId, req.getRoleId()));
        List<UserRole> list = req.getUserIdList().stream()
                .map(userId -> UserRole.builder().userId(userId).roleId(req.getRoleId()).build()).toList();
        userRoleService.saveBatch(list);
        return true;
    }
    
    @Override
    @DSTransactional
    public void saveRoleAuthority(RoleResSaveReq dto) {
        // 删除角色和资源的关联
        super.remove(Wraps.<RoleRes>lbQ().eq(RoleRes::getRoleId, dto.getRoleId()));
        resHandler(dto, dto.getRoleId());
    }
    
    private void resHandler(RoleResSaveReq data, Long roleId) {
        final Set<Long> set = data.getResIds();
        if (CollectionUtil.isEmpty(set)) {
            return;
        }
        final List<RoleRes> roleRes = set.stream().filter(Objects::nonNull)
                .map(resId -> RoleRes.builder().resId(resId).roleId(roleId).build())
                .toList();
        super.insertBatch(roleRes);
    }
}
