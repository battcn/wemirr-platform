package com.wemirr.framework.boot.security;

import com.wemirr.framework.commons.security.AuthenticationContext;
import com.wemirr.framework.security.utils.SecurityUtils;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author Levin
 */
@Configuration
public class AuthenticationContextConfiguration {

    @Bean
    public AuthenticationContext authenticationContext() {
        return new AuthenticationContext() {
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
