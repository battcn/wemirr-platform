package com.wemirr.framework.security.configuration.server.support;


import com.wemirr.framework.security.domain.UserInfoDetails;
import org.apache.commons.lang3.StringUtils;
import org.springframework.core.Ordered;

/**
 * @author Levin
 **/
public interface IntegrationAuthenticator extends Ordered {

    String DEFAULT_AUTH_TYPE = "password";

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
    default boolean support(IntegrationAuthentication integrationAuthentication) {
        return StringUtils.equals(integrationAuthentication.getLoginType(), loginType());
    }


    /**
     * 登录方式
     *
     * @return 默认密码登录
     */
    default String loginType() {
        return DEFAULT_AUTH_TYPE;
    }

    /**
     * 认证结束后执行
     *
     * @param integrationAuthentication integrationAuthentication
     */
    default void complete(IntegrationAuthentication integrationAuthentication) {

    }

}