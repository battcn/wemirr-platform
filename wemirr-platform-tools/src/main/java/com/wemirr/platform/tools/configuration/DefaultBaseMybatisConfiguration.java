package com.wemirr.platform.tools.configuration;

import com.baomidou.mybatisplus.extension.plugins.inner.InnerInterceptor;
import com.wemirr.framework.database.datasource.BaseMybatisConfiguration;
import com.wemirr.framework.database.datasource.TenantEnvironment;
import com.wemirr.framework.database.mybatis.auth.DataScopeInnerInterceptor;
import com.wemirr.framework.database.properties.DatabaseProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Levin
 */
@Configuration
@EnableConfigurationProperties(DatabaseProperties.class)
public class DefaultBaseMybatisConfiguration extends BaseMybatisConfiguration {

    @Bean
    public TenantEnvironment tenantEnvironment() {
        return new TenantEnvironment() {
            @Override
            public Long tenantId() {
                return 1L;
            }

            @Override
            public Long userId() {
                return 2L;
            }

            @Override
            public String realName() {
                return "不告诉你";
            }

            @Override
            public boolean anonymous() {
                return false;
            }
        };
    }

    @Autowired
    private ApplicationContext applicationContext;

    @Override
    protected List<InnerInterceptor> getPaginationBeforeInnerInterceptor() {
        List<InnerInterceptor> list = new ArrayList<>();
        boolean isDataScope = properties.isDataScope();
        if (isDataScope) {
            list.add(new DataScopeInnerInterceptor(applicationContext, tenantEnvironment()));
        }
        return list;
    }
}