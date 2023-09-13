package com.wemirr.framework.security.configuration.server.third;

import com.wemirr.framework.security.domain.OAuth2ThirdAccount;
import org.springframework.security.oauth2.core.user.OAuth2User;

/**
 * oauth2 三方登录获取到的用户信息转换策略接口
 *
 * @author vains
 */
public interface Oauth2UserConverter {

    /**
     * 将oauth2登录的认证信息转为 {@link OAuth2ThirdAccount}
     *
     * @param oAuth2User oauth2登录获取的用户信息
     * @return 项目中的用户信息
     */
    OAuth2ThirdAccount convert(OAuth2User oAuth2User);

}
