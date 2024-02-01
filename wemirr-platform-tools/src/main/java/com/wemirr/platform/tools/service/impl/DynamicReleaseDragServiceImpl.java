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
package com.wemirr.platform.tools.service.impl;

import com.wemirr.framework.commons.security.AuthenticationContext;
import com.wemirr.framework.db.mybatisplus.ext.SuperServiceImpl;
import com.wemirr.platform.tools.domain.entity.DynamicReleaseDrag;
import com.wemirr.platform.tools.domain.req.DynamicReleaseDragReq;
import com.wemirr.platform.tools.repository.DynamicReleaseDragMapper;
import com.wemirr.platform.tools.service.DynamicReleaseDragService;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

/**
 * @author Levin
 */
@Service
@RequiredArgsConstructor
public class DynamicReleaseDragServiceImpl extends SuperServiceImpl<DynamicReleaseDragMapper, DynamicReleaseDrag> implements DynamicReleaseDragService {
    
    private final AuthenticationContext authenticationContext;
    
    @Override
    public void add(DynamicReleaseDragReq req) {
        final String model = req.getModel();
        String tableName = StringUtils.join("tenant_", authenticationContext.tenantId(), "_", model);
        baseMapper.insert(DynamicReleaseDrag.builder()
                .label(req.getLabel())
                .model(model).tableName(tableName).locked(false)
                .description(req.getDescription())
                .tenantId(authenticationContext.tenantId())
                .build());
    }
    
    @Override
    public void edit(Long id, DynamicReleaseDragReq req) {
        DynamicReleaseDrag drag = new DynamicReleaseDrag();
        drag.setId(id);
        drag.setDescription(req.getDescription());
        drag.setLocked(req.getLocked());
        drag.setModel(req.getModel());
        drag.setLabel(req.getLabel());
        this.baseMapper.updateById(drag);
    }
    
}
