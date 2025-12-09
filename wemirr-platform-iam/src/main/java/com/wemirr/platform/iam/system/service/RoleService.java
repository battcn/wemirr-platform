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

import com.wemirr.framework.db.mybatisplus.ext.SuperService;
import com.wemirr.platform.iam.system.domain.dto.req.RoleSaveReq;
import com.wemirr.platform.iam.system.domain.dto.resp.RoleDetailResp;
import com.wemirr.platform.iam.system.domain.dto.resp.RolePermissionResp;
import com.wemirr.platform.iam.system.domain.entity.Role;

import java.util.List;

/**
 * <p>
 * 业务接口
 * 角色
 * </p>
 *
 * @author Levin
 * @since 2019-07-03
 */
public interface RoleService extends SuperService<Role> {

    /**
     * 根据 scope 查询角色
     *
     * @return 查询结果
     */
    @Override
    List<Role> list();

    /**
     * 根据角色ID移除
     *
     * @param roleId roleId
     */
    void removeByRoleId(Long roleId);

    /**
     * 1，保存角色
     *
     * @param req req
     */
    void create(RoleSaveReq req);

    /**
     * 修改角色
     *
     * @param roleId 角色ID
     * @param req    req
     */
    void modify(Long roleId, RoleSaveReq req);

    /**
     * 给角色分配用户
     *
     * @param roleId     roleId
     * @param userIdList userIdList
     */
    void assignUser(Long roleId, List<Long> userIdList);

    /**
     * 根据角色ID查询资源码
     *
     * @param roleId 角色ID
     * @return 查询结果
     */
    RolePermissionResp findRolePermissionById(Long roleId);

    /**
     * 角色详情
     * @param id id
     * @return 查询结果
     */
    RoleDetailResp detail(Long id);

}
