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
import com.wemirr.platform.authority.domain.baseinfo.enums.ReceiverType;
import com.wemirr.platform.authority.domain.common.entity.SiteNotify;
import com.wemirr.platform.authority.domain.common.resp.CommonDataResp;

import java.util.List;

/**
 * @author levin
 */
public interface SiteNotifyService extends SuperService<SiteNotify> {
    
    /**
     * 根据类型和条件查询
     *
     * @param type   类型
     * @param search 条件
     * @return 查询结果
     */
    List<CommonDataResp> queryReceiverByType(ReceiverType type, String search);
    
    /**
     * 发布消息
     *
     * @param id id
     */
    void publish(Long id);
}
