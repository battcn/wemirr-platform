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
package com.wemirr.framework.db.configuration;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import com.baomidou.mybatisplus.extension.plugins.MybatisPlusInterceptor;
import com.baomidou.mybatisplus.extension.plugins.handler.TenantLineHandler;
import com.baomidou.mybatisplus.extension.plugins.inner.*;
import com.wemirr.framework.commons.security.AuthenticationContext;
import com.wemirr.framework.db.mybatisplus.datascope.handler.DataScopePermissionHandler;
import com.wemirr.framework.db.mybatisplus.handler.MyBatisMetaObjectHandler;
import com.wemirr.framework.db.mybatisplus.injector.MySqlInjector;
import com.wemirr.framework.db.properties.DatabaseProperties;
import com.wemirr.framework.db.properties.MultiTenantType;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.sf.jsqlparser.expression.Expression;
import net.sf.jsqlparser.expression.LongValue;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;

import java.util.List;

/**
 * Mybatis 常用重用拦截器
 * <p>
 * 拦截器执行一定是：
 * WriteInterceptor > DataScopeInterceptor > PaginationInterceptor
 *
 * @author Levin
 * @since 2018/10/24
 */
@Slf4j
@RequiredArgsConstructor
@Configuration
@EnableConfigurationProperties(DatabaseProperties.class)
public abstract class BaseMybatisConfiguration {
    
    private final DatabaseProperties properties;
    private final AuthenticationContext context;
    
    /**
     * 新的分页插件,一缓和二缓遵循mybatis的规则,
     * 需要设置 MybatisConfiguration#useDeprecatedExecutor = false
     * 避免缓存出现问题(该属性会在旧插件移除后一同移除)
     */
    @Bean
    @Order(5)
    @ConditionalOnMissingBean
    public MybatisPlusInterceptor mybatisPlusInterceptor() {
        MybatisPlusInterceptor interceptor = new MybatisPlusInterceptor();
        final DatabaseProperties.MultiTenant multiTenant = properties.getMultiTenant();
        if (MultiTenantType.NONE != multiTenant.getType()) {
            // 新增多租户拦截器
            interceptor.addInnerInterceptor(new TenantLineInnerInterceptor(new TenantLineHandler() {
                
                @Override
                public Expression getTenantId() {
                    // 租户ID
                    log.debug("当前租户ID - {}", context.tenantId());
                    return context.tenantId() == null ? null : new LongValue(context.tenantId());
                }
                
                @Override
                public boolean ignoreTable(String tableName) {
                    final List<String> tables = multiTenant.getIncludeTables();
                    // 判断哪些表不需要尽心多租户判断,返回false表示都需要进行多租户判断
                    return context.anonymous() || !tables.contains(tableName);
                }
                
                @Override
                public String getTenantIdColumn() {
                    return multiTenant.getTenantIdColumn();
                }
                
            }));
        }
        // 加载其它插件
        loadInnerInterceptor(interceptor);
        return interceptor;
    }
    
    /**
     * mybatis-plus 分页插件
     *
     * @param pagination 参数配置
     * @return 插件
     */
    public PaginationInnerInterceptor paginationInnerInterceptor(final DatabaseProperties.PaginationInterceptProperties pagination) {
        // 新增MYSQL分页拦截器,一定要先设置租户判断后才进行分页拦截设置
        PaginationInnerInterceptor paginationInnerInterceptor = new PaginationInnerInterceptor(pagination.getDbType());
        paginationInnerInterceptor.setMaxLimit(pagination.getMaxLimit());
        paginationInnerInterceptor.setOverflow(pagination.isOverflow());
        paginationInnerInterceptor.setDialect(pagination.getDialect());
        return paginationInnerInterceptor;
    }
    
    protected void loadInnerInterceptor(MybatisPlusInterceptor interceptor) {
        final DatabaseProperties.Intercept intercept = properties.getIntercept();
        if (intercept.getDataPermission().isEnabled()) {
            // 分页拦截器之前的插件 => 数据权限插件
            interceptor.addInnerInterceptor(new DataPermissionInterceptor(new DataScopePermissionHandler(context)));
        }
        // 分页插件
        interceptor.addInnerInterceptor(paginationInnerInterceptor(intercept.getPagination()));
        if (intercept.isBlockAttack()) {
            // 防止全表更新与删除插件: BlockAttackInnerInterceptor
            interceptor.addInnerInterceptor(new BlockAttackInnerInterceptor());
        }
        if (intercept.isIllegalSql()) {
            // SQL性能规范插件，限制比较多，慎用哦
            interceptor.addInnerInterceptor(new IllegalSQLInnerInterceptor());
        }
    }
    
    @Bean
    @ConditionalOnMissingBean
    public MySqlInjector getMySqlInjector() {
        return new MySqlInjector();
    }
    
    @Bean
    @ConditionalOnMissingBean
    public MetaObjectHandler metaObjectHandler() {
        return new MyBatisMetaObjectHandler(context);
    }
}
