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

package com.wemirr.platform.iam.tenant.service;

import com.wemirr.framework.db.mybatisplus.ext.SuperService;
import com.wemirr.platform.iam.tenant.domain.dto.req.TenantConfigReq;
import com.wemirr.platform.iam.tenant.domain.dto.req.TenantDbBindingSaveReq;
import com.wemirr.platform.iam.tenant.domain.dto.req.TenantSaveReq;
import com.wemirr.platform.iam.tenant.domain.dto.req.TenantSettingReq;
import com.wemirr.platform.iam.tenant.domain.dto.resp.TenantDbBindingResp;
import com.wemirr.platform.iam.tenant.domain.dto.resp.TenantSettingResp;
import com.wemirr.platform.iam.tenant.domain.entity.Tenant;

/**
 * @author Levin
 */
public interface TenantService extends SuperService<Tenant> {

    /**
     * 保存租户
     *
     * @param req 租户信息
     */
    void create(TenantSaveReq req);

    /**
     * 保存租户
     *
     * @param id  id
     * @param req 租户信息
     */
    void modify(Long id, TenantSaveReq req);

    /**
     * 租户配置
     *
     * @param tenantId tenantId
     * @param req      租户配置
     */
    void tenantConfig(Long tenantId, TenantConfigReq req);

    /**
     * 初始化SQL脚本
     *
     * @param id id
     */
    void initSqlScript(Long id);

    /**
     * 字典刷新
     *
     * @param tenantId tenantId
     */
    void refreshTenantDict(Long tenantId);

    /**
     * 租户设置信息
     *
     * @param tenantId 租户ID
     * @return 查询结果
     */
    TenantSettingResp settingInfo(Long tenantId);

    /**
     * 保存租户设置
     *
     * @param tenantId 租户ID
     * @param req      设置信息
     */
    void saveSetting(Long tenantId, TenantSettingReq req);

    /**
     * DB 关联信息
     * @param id id
     * @return 查询结果
     */
    TenantDbBindingResp dbRef(Long id);

    /**
     * 绑定DB
     * @param id id
     * @param req req
     */
    void dbBinding(Long id, TenantDbBindingSaveReq req);

}
