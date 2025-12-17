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

package com.wemirr.framework.db.dynamic;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.io.IoUtil;
import com.baomidou.dynamic.datasource.DynamicRoutingDataSource;
import com.baomidou.dynamic.datasource.creator.DataSourceProperty;
import com.baomidou.dynamic.datasource.creator.hikaricp.HikariDataSourceCreator;
import com.wemirr.framework.commons.MvelHelper;
import com.wemirr.framework.commons.exception.CheckedException;
import com.wemirr.framework.db.dynamic.core.DynamicDataSourceEvent;
import com.wemirr.framework.db.dynamic.core.EventAction;
import com.wemirr.framework.db.properties.DatabaseProperties;
import com.wemirr.framework.db.properties.MultiTenantType;
import com.wemirr.framework.db.utils.JdbcUrlUtils;
import com.zaxxer.hikari.HikariDataSource;
import jakarta.annotation.Resource;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.jdbc.datasource.init.DatabasePopulatorUtils;
import org.springframework.jdbc.datasource.init.ResourceDatabasePopulator;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;

import javax.sql.DataSource;
import java.io.File;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 动态数据源处理器 (纯净版 - 无业务依赖)
 *
 * @author Levin
 */
@Slf4j
@Component
public class DynamicDataSourceHandler {

    private static final String TENANT_POOL_PREFIX = "TenantPool_%s";

    private final Map<String, DataSource> sharedPools = new ConcurrentHashMap<>();

    @Resource
    private DynamicRoutingDataSource dynamicRoutingDataSource;
    @Resource
    private HikariDataSourceCreator hikariDataSourceCreator;
    @Resource
    private DatabaseProperties databaseProperties;
    @Resource
    private ResourceLoader resourceLoader;

    /**
     * 事件响应入口
     */
    public void handler(EventAction action, DynamicDataSourceEvent event) {
        if (event == null || action == null) {
            log.warn("DynamicDataSourceEvent is null, skip.");
            return;
        }
        var dsKey = databaseProperties.getMultiTenant().getDsPrefix() + event.getTenantCode();
        log.info("Handle DataSource Event -> Action: [{}], Key: [{}]", action, dsKey);

        switch (action) {
            case DEL -> removeDataSource(dsKey);
            case INIT -> createPhysicalDatabase(event);
            case ADD -> addDataSource(dsKey, event);
            default -> log.warn("Unknown event action: {}", action);
        }
    }

    /**
     * [公开 API] 初始化 SQL 脚本 (带被动恢复能力)
     * <p>
     * 这里的 event 必须包含完整的物理连接信息 (jdbcUrl, username, password 等)
     * 由调用方负责组装。
     *
     * @param event     包含完整连接信息的数据对象
     * @param variables 模板变量 (e.g. tenant_id, tenant_name)
     */
    public void initSqlScript(DynamicDataSourceEvent event, Map<String, Object> variables) {
        if (event == null || event.getTenantCode() == null) {
            throw CheckedException.badRequest("Event or TenantCode cannot be null");
        }

        if (event.getJdbcUrl() == null) {
            throw CheckedException.badRequest("JDBC URL cannot be null");
        }

        ensureSchemaName(event);

        var tenantCode = event.getTenantCode();
        var dsKey = databaseProperties.getMultiTenant().getDsPrefix() + tenantCode;

        // 初始化必须保证顺序：先建库，再建/刷新数据源，最后执行脚本。
        // 不能依赖“数据源是否存在”来决定是否建库，否则在库不存在但数据源已注册的情况下会直接报 Unknown database。
        createPhysicalDatabase(event);
        addDataSource(dsKey, event);

        var dataSource = dynamicRoutingDataSource.getDataSource(dsKey);
        if (dataSource == null) {
            throw CheckedException.notFound("Failed to initialize DataSource for " + tenantCode);
        }

        log.info("Starting SQL script execution for tenant [{}]...", tenantCode);
        runScript(dataSource, variables);
    }

    private void ensureSchemaName(DynamicDataSourceEvent event) {
        if (event.getSchemaName() != null && !event.getSchemaName().isBlank()) {
            return;
        }

        var strategy = event.getStrategy();
        if (MultiTenantType.SCHEMA == strategy) {
            throw CheckedException.badRequest("SchemaName cannot be null");
        }

        // DATABASE 策略下仅对 MySQL 支持从 jdbcUrl 推导目标库名
        if (event.getDbType() == null || !"mysql".equalsIgnoreCase(event.getDbType())) {
            throw CheckedException.badRequest("SchemaName cannot be null");
        }

        var schema = JdbcUrlUtils.extractSchema(event.getJdbcUrl());
        if (schema == null || schema.isBlank()) {
            throw CheckedException.badRequest("SchemaName cannot be null");
        }
        event.setSchemaName(schema);
    }

    // =========================== 私有核心逻辑 ===========================

    private void createPhysicalDatabase(DynamicDataSourceEvent event) {
        // 关键点：创建数据库时不能依赖“目标库已存在”。
        // 当前 event.getJdbcUrl() 可能指向一个不存在的库（例如 v4-pit），会导致连接失败，进而无法执行 CREATE DATABASE。
        // 这里使用 bootstrapUrl：优先移除库名（jdbc:mysql://host:port/?...），否则回退到 /mysql。
        var bootstrapUrl = buildBootstrapJdbcUrl(event.getJdbcUrl(), event.getDbType());
        var defaultProp = createProperty("Init-Temp-" + event.getTenantCode(), event, bootstrapUrl);
        defaultProp.setLazy(false);

        try (var defaultDs = (HikariDataSource) hikariDataSourceCreator.createDataSource(defaultProp)) {
            log.info("Ensuring schema [{}] exists...", event.getSchemaName());
            SchemaUtil.createSchemaIfNotExists(event.getDbType(), event.getSchemaName(), defaultDs);
        } catch (Exception e) {
            log.error("Failed to create schema: {}", event.getSchemaName(), e);
            throw CheckedException.badRequest("Database initialization failed: " + e.getMessage());
        }
    }

    private String buildBootstrapJdbcUrl(String jdbcUrl, String dbType) {
        if (!"mysql".equalsIgnoreCase(dbType) || jdbcUrl == null) {
            return jdbcUrl;
        }

        // MySQL 中 information_schema 恒存在，作为 bootstrap 库最稳定
        return JdbcUrlUtils.replaceSchema(jdbcUrl, "information_schema");
    }

    private void addDataSource(String dsKey, DynamicDataSourceEvent event) {
        if (isPostgreSqlSchemaStrategy(event)) {
            addPostgreSqlSchemaDataSource(dsKey, event);
            return;
        }

        // 替换 Schema
        var finalUrl = JdbcUrlUtils.replaceSchema(event.getJdbcUrl(), event.getSchemaName());
        var poolName = TENANT_POOL_PREFIX.formatted(event.getTenantCode());
        var property = createProperty(poolName, event, finalUrl);

        if (dynamicRoutingDataSource.getDataSources().containsKey(dsKey)) {
            dynamicRoutingDataSource.removeDataSource(dsKey);
        }

        var dataSource = hikariDataSourceCreator.createDataSource(property);
        dynamicRoutingDataSource.addDataSource(dsKey, dataSource);
        log.info("DataSource [{}] added. URL: {}", dsKey, finalUrl);
    }

    private boolean isPostgreSqlSchemaStrategy(DynamicDataSourceEvent event) {
        return event.getDbType() != null
                && "postgresql".equalsIgnoreCase(event.getDbType())
                && event.getStrategy() != null
                && MultiTenantType.SCHEMA == event.getStrategy();
    }

    private void addPostgreSqlSchemaDataSource(String dsKey, DynamicDataSourceEvent event) {
        var poolKey = event.getJdbcUrl() + "|" + event.getUsername();
        var shared = sharedPools.computeIfAbsent(poolKey, k -> {
            var poolName = "TenantSharedPool_" + Integer.toHexString(k.hashCode());
            var property = createProperty(poolName, event, event.getJdbcUrl());
            return hikariDataSourceCreator.createDataSource(property);
        });

        if (dynamicRoutingDataSource.getDataSources().containsKey(dsKey)) {
            dynamicRoutingDataSource.removeDataSource(dsKey);
        }

        var switching = new SchemaSwitchingDataSource(shared, event.getSchemaName(), poolKey);
        dynamicRoutingDataSource.addDataSource(dsKey, switching);
        log.info("DataSource [{}] added. URL: {}", dsKey, event.getJdbcUrl());
    }

    private void removeDataSource(String dsKey) {
        var current = dynamicRoutingDataSource.getDataSource(dsKey);
        if (current == null) {
            return;
        }

        if (current instanceof SchemaSwitchingDataSource switching) {
            var poolKey = switching.getPoolKey();
            dynamicRoutingDataSource.removeDataSource(dsKey);
            cleanupSharedPoolIfNoLongerUsed(poolKey);
            return;
        }

        if (dynamicRoutingDataSource.getDataSources().containsKey(dsKey)) {
            dynamicRoutingDataSource.removeDataSource(dsKey);
        }
    }

    private void cleanupSharedPoolIfNoLongerUsed(String poolKey) {
        boolean stillUsed = dynamicRoutingDataSource.getDataSources().values().stream()
                .anyMatch(ds -> ds instanceof SchemaSwitchingDataSource s && poolKey.equals(s.getPoolKey()));
        if (stillUsed) {
            return;
        }

        var removed = sharedPools.remove(poolKey);
        if (removed instanceof HikariDataSource hikari) {
            hikari.close();
        }
    }

    @SneakyThrows
    private void runScript(DataSource dataSource, Map<String, Object> variables) {
        Assert.notNull(dataSource, "dataSource");
        var scriptPaths = databaseProperties.getMultiTenant().getTenantSqlScripts();
        if (CollUtil.isEmpty(scriptPaths)) {
            log.warn("No tenant SQL scripts configured.");
            return;
        }

        for (String rawPath : scriptPaths) {
            final String path = Objects.requireNonNull(rawPath, "SQL script path cannot be null");
            log.info("Processing script: {}", path);
            var resource = resourceLoader.getResource(path);
            if (!resource.exists()) {
                throw CheckedException.notFound("SQL script not found: " + path);
            }

            final String filename = new File(path).getName();
            try {
                final byte[] bytes;
                try (var inputStream = resource.getInputStream()) {
                    var lines = IoUtil.readUtf8Lines(inputStream, new ArrayList<>());
                    var formattedSql = lines.stream()
                            .map(line -> MvelHelper.format(line, variables))
                            .reduce((a, b) -> a + "\n" + b)
                            .orElse("");
                    bytes = formattedSql.getBytes(StandardCharsets.UTF_8);
                    Assert.notNull(bytes, "sqlBytes");
                }

                final String description = Objects.requireNonNull(path, "description");
                var formattedResource = new ByteArrayResource(bytes, description) {
                    @Override
                    public String getFilename() {
                        return filename;
                    }
                };

                var populator = new ResourceDatabasePopulator();
                populator.setSqlScriptEncoding(StandardCharsets.UTF_8.name());
                populator.setSeparator(";");
                populator.setContinueOnError(false);
                populator.addScript(formattedResource);

                DatabasePopulatorUtils.execute(populator, dataSource);
            } catch (Exception e) {
                log.error("Failed to execute tenant SQL script: {}", path, e);
                throw CheckedException.badRequest("SQL script execution failed: " + path + ", " + e.getMessage());
            }
        }
    }

    private DataSourceProperty createProperty(String poolName, DynamicDataSourceEvent event, String url) {
        var property = new DataSourceProperty();
        property.setPoolName(poolName);
        property.setDriverClassName(event.getDriverClassName());
        property.setUrl(url);
        property.setUsername(event.getUsername());
        property.setPassword(event.getPassword());
        property.setLazy(true);
        return property;
    }
}