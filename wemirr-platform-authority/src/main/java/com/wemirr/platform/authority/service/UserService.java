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

package com.wemirr.platform.authority.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.wemirr.framework.db.mybatisplus.ext.SuperService;
import com.wemirr.platform.authority.domain.baseinfo.entity.User;
import com.wemirr.platform.authority.domain.baseinfo.req.UserPageReq;
import com.wemirr.platform.authority.domain.baseinfo.req.UserSaveReq;
import com.wemirr.platform.authority.domain.baseinfo.resp.UserResp;
import com.wemirr.platform.authority.domain.common.req.ChangeUserInfoReq;

/**
 * <p>
 * 业务接口
 * 账号
 * </p>
 *
 * @author Levin
 * @since 2019-07-03
 */
public interface UserService extends SuperService<User> {
    
    /**
     * 添加用户
     *
     * @param req 用户信息
     */
    void addUser(UserSaveReq req);
    
    /**
     * 数据权限 分页
     *
     * @param req req
     * @return 查询结果
     */
    IPage<UserResp> pageList(UserPageReq req);
    
    /**
     * 修改密码
     *
     * @param userId      用户ID
     * @param orgPassword 原始密码
     * @param newPassword 新密码
     */
    void changePassword(Long userId, String orgPassword, String newPassword);
    
    /**
     * 根据ID删除用户
     *
     * @param id id
     */
    void deleteById(Long id);
    
    /**
     * 修改用户信息
     *
     * @param req req
     */
    void changeInfo(ChangeUserInfoReq req);
}
