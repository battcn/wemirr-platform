package com.wemirr.platform.bury.configuration;

import com.baomidou.dynamic.datasource.DynamicRoutingDataSource;
import com.baomidou.dynamic.datasource.provider.AbstractDataSourceProvider;
import com.baomidou.dynamic.datasource.provider.DynamicDataSourceProvider;
import com.baomidou.dynamic.datasource.spring.boot.autoconfigure.DynamicDataSourceProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;
import org.springframework.context.annotation.Primary;

import javax.annotation.Resource;
import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;

@Configuration
public class ShardingDynamicDataSourceConfiguration {

    @Resource
    private DynamicDataSourceProperties properties;

    /**
     * 未使用分片, 脱敏的名称(默认): shardingDataSource
     * 使用了主从: masterSlaveDataSource
     * 根据自己场景修改注入
     * //特别注意，有的spring版本不用加Lazy一样能注入，有的会空指针。
     * 根据自己测试选择，但是如果加了lazy就没强转了，也就无法添加其内部的数据源了。
     */
    @Lazy
    @Resource(name = "shardingDataSource")
    private DataSource shardingDataSource;

    @Bean
    public DynamicDataSourceProvider dynamicDataSourceProvider() {
        return new AbstractDataSourceProvider() {

            @Override
            public Map<String, DataSource> loadDataSources() {
                Map<String, DataSource> dataSourceMap = new HashMap<>();
                dataSourceMap.put("sharding", shardingDataSource);
                //打开下面的代码可以把 shardingJdbc 内部管理的子数据源也同时添加到动态数据源里 (根据自己需要选择开启)
//                dataSourceMap.putAll(((MasterSlaveDataSource) masterSlaveDataSource).getDataSourceMap());
                return dataSourceMap;
            }
        };
    }

    /**
     * 将动态数据源设置为首选的
     * 当spring存在多个数据源时, 自动注入的是首选的对象
     * 设置为主要的数据源之后，就可以支持shardingjdbc原生的配置方式了
     * 3.4.0版本及以上使用以下方式注入,老版本请阅读文档  进阶-手动注入多数据源
     */
    @Primary
    @Bean
    public DataSource dataSource() {
        DynamicRoutingDataSource dataSource = new DynamicRoutingDataSource();
        dataSource.setPrimary(properties.getPrimary());
        dataSource.setStrict(properties.getStrict());
        dataSource.setStrategy(properties.getStrategy());
        dataSource.setP6spy(properties.getP6spy());
        dataSource.setSeata(properties.getSeata());
        return dataSource;
    }
}