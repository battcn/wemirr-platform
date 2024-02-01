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
import com.wemirr.platform.tools.domain.entity.DynamicReleaseDatasource;
import com.zaxxer.hikari.HikariDataSource;

import java.util.List;

/**
 * @author Levin
 */
public interface DynamicReleaseDatasourceService extends SuperService<DynamicReleaseDatasource> {
    
    /**
     * ping 数据库连接
     *
     * @param id 数据连接ID
     * @return Ping 结果
     */
    HikariDataSource ping(Long id);
    
    /**
     * 根据连接池ID信息获取 表
     *
     * @param id ID
     * @return 表名
     */
    List<String> tables(Long id);
}
