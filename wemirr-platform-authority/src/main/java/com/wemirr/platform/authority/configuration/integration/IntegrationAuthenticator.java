package com.wemirr.platform.authority.configuration.integration;


import com.wemirr.framework.security.client.entity.UserInfoDetails;

/**
 * @author Levin
 * @since 2018-4-4
 **/
public interface IntegrationAuthenticator {

    /**
     * 处理集成认证
     *
     * @param integrationAuthentication integrationAuthentication
     * @return 认证信息
     */
    UserInfoDetails authenticate(IntegrationAuthentication integrationAuthentication);


    /**
     * 进行预处理
     *
     * @param integrationAuthentication integrationAuthentication
     */
    void prepare(IntegrationAuthentication integrationAuthentication);

    /**
     * 判断是否支持集成认证类型
     *
     * @param integrationAuthentication integrationAuthentication
     * @return 是否统一处理
     */
    boolean support(IntegrationAuthentication integrationAuthentication);

    /**
     * 认证结束后执行
     *
     * @param integrationAuthentication integrationAuthentication
     */
    void complete(IntegrationAuthentication integrationAuthentication);

}