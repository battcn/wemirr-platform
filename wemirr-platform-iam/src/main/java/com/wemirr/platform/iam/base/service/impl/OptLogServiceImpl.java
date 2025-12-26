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

package com.wemirr.platform.iam.base.service.impl;

import cn.hutool.core.bean.BeanUtil;
import com.wemirr.framework.boot.log.AccessLogInfo;
import com.wemirr.framework.commons.JacksonUtils;
import com.wemirr.framework.db.mybatisplus.ext.SuperServiceImpl;
import com.wemirr.framework.db.utils.TenantHelper;
import com.wemirr.platform.iam.base.domain.entity.OptLog;
import com.wemirr.platform.iam.base.repository.OptLogMapper;
import com.wemirr.platform.iam.base.service.OptLogService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * @author Levin
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class OptLogServiceImpl extends SuperServiceImpl<OptLogMapper, OptLog> implements OptLogService {
    
    private final OptLogMapper optLogMapper;
    
    @Override
    public void listener(AccessLogInfo info) {
        TenantHelper.executeWithTenantDb(info.getTenantCode(), () -> {
            log.debug("[日志信息] - {}", JacksonUtils.toJson(info));
            return this.optLogMapper.insert(BeanUtil.toBean(info, OptLog.class));
        });
    }
    
}
