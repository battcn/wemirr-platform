package com.wemirr.framework.security.configuration.server.third.converts;

import com.wemirr.framework.security.configuration.server.third.Oauth2UserConverter;
import com.wemirr.framework.security.constant.SecurityConstants;
import com.wemirr.framework.security.domain.OAuth2ThirdAccount;
import lombok.RequiredArgsConstructor;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * 微信用户信息转换器
 *
 * @author vains
 */
@RequiredArgsConstructor
@Component(SecurityConstants.THIRD_LOGIN_WECHAT)
public class WechatUserConverter implements Oauth2UserConverter {

    @Override
    public OAuth2ThirdAccount convert(OAuth2User oAuth2User) {
        // 获取三方用户信息
        Map<String, Object> attributes = oAuth2User.getAttributes();
        // 转换至Oauth2ThirdAccount
        OAuth2ThirdAccount thirdAccount = new OAuth2ThirdAccount();
        thirdAccount.setUniqueId(String.valueOf(attributes.get("openid")));
        thirdAccount.setThirdUsername(oAuth2User.getName());
        thirdAccount.setType(SecurityConstants.THIRD_LOGIN_WECHAT);
        thirdAccount.setLocation(attributes.get("province") + " " + attributes.get("city"));
        // 设置基础用户信息
        thirdAccount.setName(oAuth2User.getName());
        thirdAccount.setAvatarUrl(String.valueOf(attributes.get("headimgurl")));
        return thirdAccount;
    }
}
