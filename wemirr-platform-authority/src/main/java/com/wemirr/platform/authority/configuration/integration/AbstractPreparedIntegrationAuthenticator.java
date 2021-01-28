package com.wemirr.platform.authority.configuration.integration;

import com.wemirr.framework.security.client.entity.UserInfoDetails;
import org.springframework.core.Ordered;

/**
 * @author Levin
 * @since 2018-4-4
 **/
public abstract class AbstractPreparedIntegrationAuthenticator implements IntegrationAuthenticator, Ordered {
    /**
     * 处理集成认证
     *
     * @param integrationAuthentication integrationAuthentication
     * @return 认证信息
     */
    @Override
    public abstract UserInfoDetails authenticate(IntegrationAuthentication integrationAuthentication);

    /**
     * 进行预处理
     *
     * @param integrationAuthentication integrationAuthentication
     */
    @Override
    public abstract void prepare(IntegrationAuthentication integrationAuthentication);

    /**
     * 判断是否支持集成认证类型
     *
     * @param integrationAuthentication integrationAuthentication
     * @return 是否统一处理
     */
    @Override
    public abstract boolean support(IntegrationAuthentication integrationAuthentication);

    /**
     * 认证结束后执行
     *
     * @param integrationAuthentication integrationAuthentication
     */
    @Override
    public void complete(IntegrationAuthentication integrationAuthentication) {

    }
}
