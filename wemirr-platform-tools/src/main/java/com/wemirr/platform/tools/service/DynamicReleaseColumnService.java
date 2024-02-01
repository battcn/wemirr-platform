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
package com.wemirr.platform.tools.service;

import com.wemirr.framework.db.mybatisplus.ext.SuperService;
import com.wemirr.platform.tools.domain.entity.DynamicReleaseColumn;
import com.wemirr.platform.tools.domain.req.DynamicReleaseGridColumnReq;
import com.wemirr.platform.tools.domain.resp.DynamicReleaseGridColumnResp;

import java.util.List;

/**
 * @author Levin
 */
public interface DynamicReleaseColumnService extends SuperService<DynamicReleaseColumn> {
    
    /**
     * 编辑动态发布字段
     *
     * @param gridId 表格ID
     * @param req    修改内容
     */
    void edit(Long gridId, DynamicReleaseGridColumnReq req);
    
    /**
     * 根据表格ID查询
     *
     * @param gridId gridId
     * @return 查询结果
     */
    List<DynamicReleaseGridColumnResp> listByGridId(Long gridId);
}
