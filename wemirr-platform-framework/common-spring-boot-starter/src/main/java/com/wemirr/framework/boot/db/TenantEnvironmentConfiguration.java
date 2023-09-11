package com.wemirr.framework.boot.db;

import com.wemirr.framework.db.TenantEnvironment;
import com.wemirr.framework.security.utils.SecurityUtils;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author Levin
 */
@Configuration
public class TenantEnvironmentConfiguration {

    @Bean
    public TenantEnvironment tenantEnvironment() {
        return new TenantEnvironment() {
            @Override
            public Long tenantId() {
                if (SecurityUtils.anonymous() || SecurityUtils.getAuthInfo() == null) {
                    return null;
                }
                return SecurityUtils.getAuthInfo().getTenantId();
            }

            @Override
            public String tenantCode() {
                if (SecurityUtils.anonymous() || SecurityUtils.getAuthInfo() == null) {
                    return null;
                }
                return SecurityUtils.getAuthInfo().getTenantCode();

            }

            @Override
            public Long userId() {
                if (SecurityUtils.anonymous() || SecurityUtils.getAuthInfo() == null) {
                    return null;
                }
                return SecurityUtils.getAuthInfo().getUserId();
            }

            @Override
            public String realName() {
                if (SecurityUtils.anonymous() || SecurityUtils.getAuthInfo() == null) {
                    return null;
                }
                return SecurityUtils.getAuthInfo().getRealName();
            }

            @Override
            public boolean anonymous() {
                return SecurityUtils.anonymous();
            }
        };
    }

}
