package com.wemirr.framework.db.configuration.dynamic;

import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.io.FileUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.dynamic.datasource.DynamicRoutingDataSource;
import com.baomidou.dynamic.datasource.creator.HikariDataSourceCreator;
import com.baomidou.dynamic.datasource.spring.boot.autoconfigure.DataSourceProperty;
import com.baomidou.dynamic.datasource.support.ScriptRunner;
import com.google.common.collect.Lists;
import com.wemirr.framework.commons.exception.CheckedException;
import com.wemirr.framework.db.configuration.dynamic.event.body.EventAction;
import com.wemirr.framework.db.configuration.dynamic.event.body.TenantDynamicDatasource;
import com.wemirr.framework.db.properties.DatabaseProperties;
import jakarta.annotation.Resource;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;

import javax.sql.DataSource;
import java.io.File;
import java.sql.Connection;
import java.sql.Statement;
import java.util.List;
import java.util.Objects;
import java.util.Set;

/**
 * @author Levin
 */
@Slf4j
public class TenantDynamicDataSourceProcess {

    private static final String CREATE_DATABASE_SCRIPT = "CREATE DATABASE IF NOT EXISTS %s DEFAULT CHARACTER SET utf8 COLLATE utf8_general_ci;";


    @Resource
    private DataSource dataSource;
    @Resource
    private HikariDataSourceCreator hikariDataSourceCreator;
    @Resource
    private DatabaseProperties properties;

    public void handler(EventAction action, TenantDynamicDatasource dynamicDatasource) {
        if (Objects.isNull(dynamicDatasource)) {
            log.warn("event dynamicDatasource is null....");
            return;
        }
        if (Objects.isNull(action)) {
            log.warn("event action is null....");
            return;
        }
        log.info("接收租户事件消息: - {} - {}", action, dynamicDatasource);
        DynamicRoutingDataSource ds = (DynamicRoutingDataSource) dataSource;
        final DatabaseProperties.MultiTenant multiTenant = properties.getMultiTenant();
        String database = multiTenant.getDsPrefix() + dynamicDatasource.getCode();
        if (action == EventAction.DEL) {
            ds.removeDataSource(database);
            log.info("数据源移除成功 - {}", database);
            return;
        }
        if (action == EventAction.INIT) {
            // 创建数据库
            DataSourceProperty dataSourceProperty = new DataSourceProperty();
            dataSourceProperty.setPoolName(dynamicDatasource.getPoolName() + "_" + dynamicDatasource.getCode());
            dataSourceProperty.setDriverClassName(dynamicDatasource.getDriverClassName());
            String url = "jdbc:mysql://" + dynamicDatasource.getHost();
            dataSourceProperty.setUrl(url);
            dataSourceProperty.setUsername(dynamicDatasource.getUsername());
            dataSourceProperty.setPassword(dynamicDatasource.getPassword());
            dataSourceProperty.setLazy(false);
            DataSource dataSource = hikariDataSourceCreator.createDataSource(dataSourceProperty);
            log.debug("数据源信息 - {} - {} - {}", dataSourceProperty.getUsername(), dataSourceProperty.getPassword(), database);
            final String createDatabaseScript = String.format(CREATE_DATABASE_SCRIPT, database);
            log.debug("数据库创建执行成功 - {}", createDatabaseScript);
            try (Connection conn = dataSource.getConnection(); Statement stat = conn.createStatement()) {
                stat.executeUpdate(createDatabaseScript);
            } catch (Exception e) {
                log.error("执行创建数据库脚本异常", e);
            }
        }
        DataSourceProperty dataSourceProperty = new DataSourceProperty();
        dataSourceProperty.setPoolName(dynamicDatasource.getPoolName() + "_" + dynamicDatasource.getCode());
        dataSourceProperty.setDriverClassName(dynamicDatasource.getDriverClassName());
        String url = "jdbc:mysql://" + dynamicDatasource.getHost() +
                "/" + database + "?useUnicode=true&characterEncoding=utf8&allowMultiQueries=true&serverTimezone=GMT%2B8&useSSL=false&allowPublicKeyRetrieval=true";
        dataSourceProperty.setUrl(url);
        dataSourceProperty.setUsername(dynamicDatasource.getUsername());
        dataSourceProperty.setPassword(dynamicDatasource.getPassword());
        dataSourceProperty.setLazy(true);
        DataSource dataSource = hikariDataSourceCreator.createDataSource(dataSourceProperty);
        log.debug("数据源信息 - {} - {} - {}", dataSourceProperty.getUsername(), dataSourceProperty.getPassword(), database);
        ds.addDataSource(database, dataSource);
        log.info("数据源添加成功 - {}", database);
        final Set<String> dsSets = ds.getDataSources().keySet();
        log.debug("连接池信息 - {}", dsSets);

    }

    public String buildDb(String tenantCode) {
        final DatabaseProperties.MultiTenant multiTenant = properties.getMultiTenant();
        if (StringUtils.isBlank(tenantCode) || StringUtils.equals(tenantCode, multiTenant.getSuperTenantCode())) {
            return multiTenant.getDefaultDsName();
        }
        return multiTenant.getDsPrefix() + tenantCode;
    }

    public void initSqlScript(Long tenantId, String tenantCode) {
        runScript(tenantId, tenantCode);
    }

    @SneakyThrows
    private void runScript(Long tenantId, String tenantCode) {
        if (tenantId == null) {
            throw CheckedException.badRequest("租户ID不能为空");
        }
        String dsKey = buildDb(tenantCode);
        DynamicRoutingDataSource ds = (DynamicRoutingDataSource) dataSource;
        // 从ThreadLocal中获取当前数据源
        final DataSource dataSource = ds.getDataSource(dsKey);
        ScriptRunner scriptRunner = new ScriptRunner(false, ";");
        final DatabaseProperties.MultiTenant multiTenant = properties.getMultiTenant();
        final List<String> tenantSqlScripts = multiTenant.getTenantSqlScripts();

        if (CollectionUtil.isEmpty(tenantSqlScripts)) {
            return;
        }
        for (String scriptPath : tenantSqlScripts) {
            final List<String> scriptContent = FileUtil.readUtf8Lines(scriptPath);
            final File tmpFile = FileUtil.createTempFile(new File(Objects.requireNonNull(this.getClass().getResource("/")).getPath()));
            List<String> newSqlScript = Lists.newArrayList();
            for (String text : scriptContent) {
                final String context = StrUtil.replace(text, "${tenant_id}", String.valueOf(tenantId));
                newSqlScript.add(context);
            }
            FileUtil.writeLines(newSqlScript, tmpFile, "UTF-8");
            scriptRunner.runScript(dataSource, tmpFile.getName());
            FileUtil.del(tmpFile);
        }
    }
}
