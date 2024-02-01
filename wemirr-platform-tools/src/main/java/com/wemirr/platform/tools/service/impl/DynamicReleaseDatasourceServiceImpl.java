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

import com.wemirr.framework.commons.exception.CheckedException;
import com.wemirr.framework.db.mybatisplus.ext.SuperServiceImpl;
import com.wemirr.platform.tools.domain.entity.DynamicReleaseDatasource;
import com.wemirr.platform.tools.repository.DynamicReleaseDatasourceMapper;
import com.wemirr.platform.tools.service.DynamicReleaseDatasourceService;
import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
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
public class DynamicReleaseDatasourceServiceImpl extends SuperServiceImpl<DynamicReleaseDatasourceMapper, DynamicReleaseDatasource> implements DynamicReleaseDatasourceService {
    
    @Override
    public HikariDataSource ping(Long id) {
        final DynamicReleaseDatasource datasource = this.baseMapper.selectById(id);
        if (datasource == null) {
            throw CheckedException.notFound("数据源不存在");
        }
        final String host = datasource.getHost();
        final Integer port = datasource.getPort();
        final String username = datasource.getUsername();
        final String password = datasource.getPassword();
        final String database = datasource.getDatabase();
        HikariConfig config = new HikariConfig();
        String jdbcUrl = "jdbc:mysql://" + host + ":" + port + "/" + database + "?useUnicode=true&characterEncoding=utf8&useSSL=false";
        config.setJdbcUrl(jdbcUrl);
        config.setUsername(username);
        config.setPassword(password);
        config.setMaximumPoolSize(1);
        config.setConnectionTestQuery("SELECT 1 FROM DUAL");
        // 目前只支持 MYSQL
        try {
            // 尝试 ping 一下
            return new HikariDataSource(config);
        } catch (Exception ex) {
            log.error("错误信息", ex);
            throw CheckedException.badRequest("连接失败 " + ex.getLocalizedMessage());
        }
    }
    
    @Override
    public List<String> tables(Long id) {
        HikariDataSource dataSource = ping(id);
        // dataSource.
        return null;
    }
}
