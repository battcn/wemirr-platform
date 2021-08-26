package com.wemirr.platform.authority.configuration;

import com.baomidou.mybatisplus.extension.plugins.inner.InnerInterceptor;
import com.wemirr.framework.database.TenantEnvironment;
import com.wemirr.framework.database.configuration.BaseMybatisConfiguration;
import com.wemirr.framework.database.mybatis.auth.DataScopeInnerInterceptor;
import com.wemirr.framework.database.properties.DatabaseProperties;
import com.wemirr.framework.security.client.utils.SecurityUtils;
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
                return SecurityUtils.getAuthInfo().getTenantId();
            }
            @Override
            public Long userId() {
                return SecurityUtils.getAuthInfo().getUserId();
            }

            @Override
            public String realName() {
                return SecurityUtils.getAuthInfo().getRealName();
            }

            @Override
            public boolean anonymous() {
                return SecurityUtils.anonymous();
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