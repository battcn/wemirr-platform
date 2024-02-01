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
package com.wemirr.platform.authority.domain.tenant.converts;

import com.wemirr.framework.commons.entity.BaseConverts;
import com.wemirr.framework.db.dynamic.core.TenantDynamicDatasource;
import com.wemirr.platform.authority.domain.tenant.resp.TenantDatasourceResp;
import org.springframework.beans.BeanUtils;

/**
 * @author levin
 */
public class TenantDatasourceConverts {
    
    public static final TenantDynamicDatasourceVo2TenantDynamicDatasourceConverts TENANT_DYNAMIC_DATASOURCE_VO_2_TENANT_DYNAMIC_DATASOURCE_CONVERTS =
            new TenantDynamicDatasourceVo2TenantDynamicDatasourceConverts();
    
    public static class TenantDynamicDatasourceVo2TenantDynamicDatasourceConverts implements BaseConverts<TenantDatasourceResp, TenantDynamicDatasource> {
        
        @Override
        public TenantDynamicDatasource convert(TenantDatasourceResp source) {
            if (source == null) {
                return null;
            }
            TenantDynamicDatasource target = new TenantDynamicDatasource();
            BeanUtils.copyProperties(source, target);
            return target;
        }
    }
    
}
