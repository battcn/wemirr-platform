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

import com.wemirr.framework.db.mybatisplus.ext.SuperService;
import com.wemirr.platform.authority.domain.baseinfo.entity.Org;
import com.wemirr.platform.authority.domain.baseinfo.req.OrgSaveReq;

import java.util.List;

/**
 * <p>
 * 业务接口
 * 组织
 * </p>
 *
 * @author Levin
 * @since 2019-07-22
 */
public interface OrgService extends SuperService<Org> {
    
    /**
     * 批量删除以及删除其子节点
     *
     * @param id id
     */
    void remove(Long id);
    
    /**
     * 添加组织
     *
     * @param req req
     */
    void addOrg(OrgSaveReq req);
    
    /**
     * 获取当前机构本级及子级的ID
     *
     * @param id id
     * @return 本级及子级ID
     */
    List<Long> getFullTreeIdPath(Long id);
}
