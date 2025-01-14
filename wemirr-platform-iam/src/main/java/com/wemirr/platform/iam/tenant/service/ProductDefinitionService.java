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
import com.wemirr.platform.iam.system.domain.dto.resp.RolePermissionResp;
import com.wemirr.platform.iam.tenant.domain.dto.req.ProductDefPermissionReq;
import com.wemirr.platform.iam.tenant.domain.dto.req.ProductDefinitionSaveReq;
import com.wemirr.platform.iam.tenant.domain.entity.ProductDefinition;

/**
 * @author Levin
 */
public interface ProductDefinitionService extends SuperService<ProductDefinition> {
    
    /**
     * 创建产品定义
     *
     * @param req req
     */
    void create(ProductDefinitionSaveReq req);
    
    /**
     * 修改
     *
     * @param id  id
     * @param req req
     */
    void modify(Long id, ProductDefinitionSaveReq req);
    
    /**
     * 产品授予权限
     *
     * @param productId productId
     * @param req       req
     */
    void permissions(Long productId, ProductDefPermissionReq req);
    
    /**
     * 查询套餐权限
     *
     * @param id id
     * @return 查询权限
     */
    RolePermissionResp findPermissions(Long id);
    
    /**
     * 删除产品
     *
     * @param id id
     */
    void delete(Long id);
    
}
