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
import com.wemirr.platform.authority.domain.common.entity.I18nData;
import com.wemirr.platform.authority.domain.common.req.I18nDataSaveReq;
import com.wemirr.platform.authority.domain.common.req.I18nPageReq;
import com.wemirr.platform.authority.domain.common.resp.I18nDataPageResp;

/**
 * @author Levin
 */
public interface I18nDataService extends SuperService<I18nData> {
    
    /**
     * 分页查询
     *
     * @param req req
     * @return 查询结果
     */
    IPage<I18nDataPageResp> pageList(I18nPageReq req);
    
    /**
     * 添加 i18n 数据
     *
     * @param req req
     */
    void add(I18nDataSaveReq req);
    
    /**
     * 编辑I18N数据
     *
     * @param id  id
     * @param req req
     */
    void edit(Long id, I18nDataSaveReq req);
}
