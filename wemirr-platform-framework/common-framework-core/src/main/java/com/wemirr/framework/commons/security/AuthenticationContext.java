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

package com.wemirr.framework.commons.security;

import java.util.List;

/**
 * 认证上下文接口
 *
 * @author Levin
 */
public interface AuthenticationContext {

    /**
     * 租户编码
     *
     * @return 租户编码
     */
    default String tenantCode() {
        return null;
    }

    default AuthenticationDetails getContext() {
        return null;
    }

    /**
     * 登录的客户端ID
     *
     * @return id
     */
    String clientId();

    /**
     * 租户ID
     *
     * @return id
     */
    Long tenantId();

    /**
     * 租户名称
     *
     * @return 租户名称
     */
    String tenantName();

    /**
     * 用户ID
     *
     * @return id
     */
    Long userId();

    /**
     * 名称
     *
     * @return 名称
     */
    String nickName();

    /**
     * 手机号
     *
     * @return 手机号
     */
    String mobile();

    /**
     * 匿名用户
     *
     * @return 是否为匿名
     */
    boolean anonymous();

    /**
     * 功能权限
     *
     * @return 功能权限
     */
    List<String> funcPermissionList();

    /**
     * 角色权限
     *
     * @return 角色权限
     */
    List<String> rolePermissionList();

    /**
     * 数据权限
     *
     * @return 数据权限范围
     */
    DataPermission dataPermission();
}
