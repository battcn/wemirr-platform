package com.wemirr.framework.database.configuration.dynamic;

import com.baomidou.dynamic.datasource.DynamicRoutingDataSource;
import com.baomidou.dynamic.datasource.creator.HikariDataSourceCreator;
import com.baomidou.dynamic.datasource.spring.boot.autoconfigure.DataSourceProperty;
import com.wemirr.framework.database.configuration.dynamic.event.body.EventAction;
import com.wemirr.framework.database.configuration.dynamic.event.body.TenantDynamicDatasource;
import com.wemirr.framework.database.properties.DatabaseProperties;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import javax.sql.DataSource;
import java.util.Objects;
import java.util.Set;

/**
 * @author Levin
 */
@Slf4j
@Component
public class DynamicDataSourceProcess {

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
        String database = properties.getDatabasePrefix() + dynamicDatasource.getCode();
        if (action == EventAction.DEL) {
            ds.removeDataSource(database);
            log.info("数据源移除成功 - {}", database);
        } else {
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
    }

}
