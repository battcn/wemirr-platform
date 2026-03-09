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

package com.wemirr.platform.iam.system.service.impl;

import cn.hutool.core.bean.BeanUtil;
import com.wemirr.framework.commons.JacksonUtils;
import com.wemirr.framework.db.mybatisplus.wrap.Wraps;
import com.wemirr.framework.log.diff.domain.DiffLogInfo;
import com.wemirr.framework.log.diff.domain.req.DiffLogInfoQueryReq;
import com.wemirr.framework.log.diff.service.IDiffLogService;
import com.wemirr.platform.iam.base.domain.entity.DiffLogEntity;
import com.wemirr.platform.iam.base.repository.DiffLogMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Levin
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class DiffLogServiceImpl implements IDiffLogService {
    
    private final DiffLogMapper diffLogMapper;
    
    @Override
    public void handler(DiffLogInfo logInfo) {
        log.info("[diff log] {}", JacksonUtils.toJson(logInfo));
        DiffLogEntity entity = BeanUtil.toBean(logInfo, DiffLogEntity.class);
        this.diffLogMapper.insert(entity);
    }
    
    @Override
    public List<DiffLogInfo> queryLog(DiffLogInfoQueryReq req) {
        return this.diffLogMapper.selectObjs(Wraps.<DiffLogEntity>lbQ()
                .eq(DiffLogEntity::getBusinessGroup, req.getBusinessGroup())
                .eq(DiffLogEntity::getBusinessTag, req.getBusinessTag())
                .eq(DiffLogEntity::getBusinessKey, req.getBusinessKey()));
    }
    
}
