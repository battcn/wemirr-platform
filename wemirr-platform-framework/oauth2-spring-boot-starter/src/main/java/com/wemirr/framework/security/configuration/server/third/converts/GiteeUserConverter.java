package com.wemirr.framework.security.configuration.server.third.converts;


import com.wemirr.framework.security.configuration.server.third.Oauth2UserConverter;
import com.wemirr.framework.security.constant.SecurityConstants;
import com.wemirr.framework.security.domain.OAuth2ThirdAccount;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * 转换通过码云登录的用户信息
 *
 * @author vains
 */
@Component(SecurityConstants.THIRD_LOGIN_GITEE)
public class GiteeUserConverter implements Oauth2UserConverter {

    @Override
    public OAuth2ThirdAccount convert(OAuth2User oAuth2User) {
        // 获取三方用户信息
        Map<String, Object> attributes = oAuth2User.getAttributes();
        // 转换至Oauth2ThirdAccount
        OAuth2ThirdAccount thirdAccount = new OAuth2ThirdAccount();
        thirdAccount.setUniqueId(String.valueOf(attributes.get("id")));
        thirdAccount.setThirdUsername(oAuth2User.getName());
        thirdAccount.setType(SecurityConstants.THIRD_LOGIN_GITEE);
        thirdAccount.setBlog(String.valueOf(attributes.get("blog")));
        // 设置基础用户信息
        thirdAccount.setName(oAuth2User.getName());
        thirdAccount.setAvatarUrl(String.valueOf(attributes.get("avatar_url")));
        return thirdAccount;
    }
}
