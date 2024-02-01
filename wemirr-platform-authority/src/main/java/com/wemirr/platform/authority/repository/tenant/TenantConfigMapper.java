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

package com.wemirr.platform.authority.repository.tenant;

import com.wemirr.framework.db.mybatisplus.ext.SuperMapper;
import com.wemirr.platform.authority.domain.tenant.entity.TenantConfig;
import com.wemirr.platform.authority.domain.tenant.resp.TenantDatasourceResp;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 租户配置.
 *
 * @author Levin
 */
@Repository
public interface TenantConfigMapper extends SuperMapper<TenantConfig> {
    
    /**
     * 查询所有可用的动态数据源.
     *
     * @param id id
     * @return 查询结果
     */
    List<TenantDatasourceResp> selectTenantDbById(@Param("id") Long id);
    
    /**
     * 获取租户动态数据源
     *
     * @param tenantId tenantId
     * @return 查询结果
     */
    TenantDatasourceResp getTenantDynamicDatasourceByTenantId(Long tenantId);
    
}
