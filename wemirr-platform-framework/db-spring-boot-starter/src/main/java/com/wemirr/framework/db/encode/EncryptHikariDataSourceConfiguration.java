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
package com.wemirr.framework.db.encode;

import cn.hutool.core.util.CharsetUtil;
import cn.hutool.crypto.SecureUtil;
import com.wemirr.framework.commons.exception.CheckedException;
import com.zaxxer.hikari.HikariDataSource;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

/**
 * 通过 DataSourceAutoConfiguration 源代码可以看出 Spring Boot 启动的时候通过
 * properties.initializeDataSourceBuilder() 去实例化的，那么我们只需要在实例化数据源后解密密码即可
 *
 * @author Levin
 * @see org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration
 */
@Slf4j
@Configuration
@ConditionalOnProperty(name = "spring.datasource.hikari.extend.encrypt", havingValue = "true")
@EnableConfigurationProperties({DataSourceProperties.class, HikariDataSourceExtProperties.class})
public class EncryptHikariDataSourceConfiguration {
    
    private static final String DEFAULT_PUB_KEY = "wemirr_pub";
    private static final String DATA_SOURCE = "dataSource";
    
    @Primary
    @Bean(DATA_SOURCE)
    public HikariDataSource dataSource(DataSourceProperties properties,
                                       HikariDataSourceExtProperties extProperties) {
        final String pubKey = StringUtils.defaultIfBlank(extProperties.getPubKey(), DEFAULT_PUB_KEY);
        String password = properties.getPassword();
        log.debug("[数据库密文密码] - [{}]", password);
        try {
            password = SecureUtil.aes(pubKey.getBytes(CharsetUtil.CHARSET_UTF_8)).decryptStr(password);
        } catch (Exception e) {
            log.warn("[数据库密码解析失败] - [{}]", e.getLocalizedMessage());
            throw new CheckedException("数据库密码解密失败！", e);
        }
        final HikariDataSource dataSource = properties.initializeDataSourceBuilder().type(HikariDataSource.class).build();
        dataSource.setPassword(password);
        if (StringUtils.isNotBlank(properties.getName())) {
            dataSource.setPoolName(properties.getName());
        }
        return dataSource;
    }
    
}
