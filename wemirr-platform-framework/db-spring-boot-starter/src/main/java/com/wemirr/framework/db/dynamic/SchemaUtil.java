package com.wemirr.framework.db.dynamic;

import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.List;
import java.util.regex.Pattern;

/**
 * @author Levin
 */
@Slf4j
public class SchemaUtil {

    private static final Pattern MYSQL_SCHEMA_NAME_PATTERN = Pattern.compile("[0-9a-zA-Z_\\-]+", Pattern.CASE_INSENSITIVE);

    private static final Pattern POSTGRES_SCHEMA_NAME_PATTERN = Pattern.compile("[0-9a-zA-Z_\\-]+", Pattern.CASE_INSENSITIVE);

    @SneakyThrows
    public static void createSchemaIfNotExists(String protocol, String schema, DataSource dataSource) {
        switch (protocol) {
            case "mysql" -> createMySqlSchema(dataSource, schema);
            case "postgresql" -> createPostgreSqlSchema(dataSource, schema);
            case "oracle" -> createOracleSchema(dataSource);
            case "sqlserver" -> createSqlServerSchema(dataSource);
            default -> log.debug("不支持的数据库类型: " + schema);
        }
    }

    // MySQL Schema 创建
    @SneakyThrows
    private static void createMySqlSchema(DataSource hikari, String schemaName) {
        if (schemaName == null || !MYSQL_SCHEMA_NAME_PATTERN.matcher(schemaName).matches()) {
            throw new IllegalArgumentException("Illegal mysql schema name: " + schemaName);
        }

        final String checkSchemaSql = "SELECT 1 FROM INFORMATION_SCHEMA.SCHEMATA WHERE SCHEMA_NAME = ?";
        final String createSchemaSql = "CREATE DATABASE IF NOT EXISTS `" + schemaName + "` CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci";

        try (Connection conn = hikari.getConnection()) {
            boolean exists;
            try (PreparedStatement ps = conn.prepareStatement(checkSchemaSql)) {
                ps.setString(1, schemaName);
                try (ResultSet rs = ps.executeQuery()) {
                    exists = rs.next();
                }
            }

            if (!exists) {
                try (Statement stmt = conn.createStatement()) {
                    stmt.execute(createSchemaSql);
                }
                log.info("MySQL Schema 创建成功: {}", schemaName);
            } else {
                log.info("MySQL Schema 已存在: {}", schemaName);
            }
        }
    }

    // PostgreSQL Schema 创建
    @SneakyThrows
    private static void createPostgreSqlSchema(DataSource ds, String schemaName) {
        if (schemaName == null || !POSTGRES_SCHEMA_NAME_PATTERN.matcher(schemaName).matches()) {
            throw new IllegalArgumentException("Illegal postgresql schema name: " + schemaName);
        }

        final String checkSchemaSql = "SELECT 1 FROM information_schema.schemata WHERE schema_name = ?";
        final String createSchemaSql = "CREATE SCHEMA \"" + schemaName + "\"";

        try (Connection conn = ds.getConnection()) {
            boolean exists;
            try (PreparedStatement ps = conn.prepareStatement(checkSchemaSql)) {
                ps.setString(1, schemaName);
                try (ResultSet rs = ps.executeQuery()) {
                    exists = rs.next();
                }
            }

            if (!exists) {
                try (Statement stmt = conn.createStatement()) {
                    stmt.execute(createSchemaSql);
                }
                log.info("PostgreSQL Schema 创建成功: {}", schemaName);
            } else {
                log.info("PostgreSQL Schema 已存在: {}", schemaName);
            }
        }
    }

    // Oracle Schema 创建
    @SneakyThrows
    private static void createOracleSchema(DataSource ds) {
        String checkSchemaSql = "SELECT USERNAME FROM ALL_USERS WHERE USERNAME = ?";
        cn.hutool.db.Db db = cn.hutool.db.Db.use(ds);
        String schemaName = ds.getConnection().getSchema();
        List<String> schemas = db.query(checkSchemaSql, String.class, schemaName);
        if (schemas.isEmpty()) {
            String createSchemaSql = cn.hutool.core.util.StrUtil.format(
                    "CREATE USER {} IDENTIFIED BY password DEFAULT TABLESPACE users TEMPORARY TABLESPACE temp",
                    schemaName.toUpperCase());
            db.execute(createSchemaSql);
            log.debug("Oracle Schema 创建成功: " + schemaName.toUpperCase());
        } else {
            log.debug("Oracle Schema 已存在: " + schemaName.toUpperCase());
        }
    }

    // SQL Server Schema 创建
    @SneakyThrows
    private static void createSqlServerSchema(DataSource ds) {
        String checkSchemaSql = "SELECT name FROM sys.schemas WHERE name = ?";
        cn.hutool.db.Db db = cn.hutool.db.Db.use(ds);
        String schemaName = ds.getConnection().getSchema();
        List<String> schemas = db.query(checkSchemaSql, String.class, schemaName);
        if (schemas.isEmpty()) {
            String createSchemaSql = cn.hutool.core.util.StrUtil.format("CREATE SCHEMA {}", schemaName);
            db.execute(createSchemaSql);
            log.debug("SQL Server Schema 创建成功: " + schemaName);
        } else {
            log.debug("SQL Server Schema 已存在: " + schemaName);
        }
    }
}
