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

package com.wemirr.platform.suite.online.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.wemirr.framework.db.mybatisplus.ext.SuperService;
import com.wemirr.platform.suite.online.domain.entity.OnlineModel;
import com.wemirr.platform.suite.online.domain.req.OnlineFormDesignSaveReq;
import com.wemirr.platform.suite.online.domain.req.OnlineFormDesignerPageReq;
import com.wemirr.platform.suite.online.domain.req.OnlineFormDesignerSaveReq;
import com.wemirr.platform.suite.online.domain.resp.OnlineFormDesignerDetailResp;
import com.wemirr.platform.suite.online.domain.resp.OnlineFormDesignerPageResp;

/**
 * @author Levin
 */
public interface OnlineModelService extends SuperService<OnlineModel> {
    
    /**
     * 分页查询
     *
     * @param req req
     * @return 查询结果
     */
    IPage<OnlineFormDesignerPageResp> pageList(OnlineFormDesignerPageReq req);
    
    /**
     * 创建
     *
     * @param req req
     */
    void create(OnlineFormDesignerSaveReq req);
    
    /**
     * 修改
     *
     * @param id  id
     * @param req req
     */
    void modify(Long id, OnlineFormDesignerSaveReq req);
    
    /**
     * 详情
     *
     * @param id id
     * @return 查询结果
     */
    OnlineFormDesignerDetailResp detail(Long id);
    
    /**
     * 详情
     *
     * @param definitionKey definitionKey
     * @return 查询结果
     */
    OnlineFormDesignerDetailResp detail(String definitionKey);
    
    /**
     * 表单设计
     *
     * @param id  id
     * @param req req
     */
    void formDesign(Long id, OnlineFormDesignSaveReq req);
    
}
