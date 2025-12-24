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

package com.wemirr.platform.suite.online.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.wemirr.framework.commons.BeanUtilPlus;
import com.wemirr.framework.db.mybatisplus.ext.SuperServiceImpl;
import com.wemirr.platform.suite.online.domain.entity.OnlineFormData;
import com.wemirr.platform.suite.online.domain.req.OnlineFormDataSaveReq;
import com.wemirr.platform.suite.online.domain.req.OnlineFormDesignerPageReq;
import com.wemirr.platform.suite.online.repository.OnlineFormDataMapper;
import com.wemirr.platform.suite.online.service.OnlineFormDataService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Levin
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class OnlineFormDataServiceImpl extends SuperServiceImpl<OnlineFormDataMapper, OnlineFormData> implements OnlineFormDataService {
    
    @Override
    public IPage<Map<String, Object>> pageList(OnlineFormDesignerPageReq req) {
        return this.baseMapper.pageList(req.buildPage(), req).convert(x -> new HashMap<>() {
            
            {
                put("id", x.getId());
                put("definitionKey", x.getDefinitionKey());
                put("tenantId", x.getTenantId());
                put("createName", x.getCreateName());
                put("createTime", x.getCreateTime());
                putAll(x.getFormData());
            }
        });
    }
    
    @Override
    public void create(OnlineFormDataSaveReq req) {
        var bean = BeanUtilPlus.toBean(req, OnlineFormData.class);
        this.baseMapper.insert(bean);
    }
    
    @Override
    public void modify(Long id, OnlineFormDataSaveReq req) {
        var bean = BeanUtilPlus.toBean(id, req, OnlineFormData.class);
        this.baseMapper.updateById(bean);
    }
}
