package com.wemirr.framework.security.client.utils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.wemirr.framework.commons.exception.CheckedException;
import com.wemirr.framework.security.client.entity.UserInfoDetails;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.provider.OAuth2Authentication;

/**
 * @author Levin
 */
public class SecurityUtils {

    public static OAuth2Authentication getAuthentication() {
        return (OAuth2Authentication) SecurityContextHolder.getContext().getAuthentication();
    }

    /**
     * 获取用户详细信息(只适用于 authority 模块)
     *
     * @return 结果
     */
    public static UserInfoDetails getAuthInfo() {
        OAuth2Authentication authentication = getAuthentication();
        if (authentication == null || anonymous()) {
            throw CheckedException.forbidden("认证信息不存在");
        }
        Authentication userAuthentication = authentication.getUserAuthentication();
        if (userAuthentication.getPrincipal() instanceof UserInfoDetails) {
            return (UserInfoDetails) userAuthentication.getPrincipal();
        }
        String detailsText = JSON.toJSONString(userAuthentication.getDetails());
        final JSONObject detailJson = JSON.parseObject(detailsText);
        return detailJson.getObject(AUTH_DETAILS_PRINCIPAL, UserInfoDetails.class);
    }

    public static final String AUTH_DETAILS_PRINCIPAL = "principal";
    public static final String ANONYMOUS_USER = "anonymousUser";

    /**
     * 是否为匿名用户
     *
     * @return 是（true）|不是（false）
     */
    public static boolean anonymous() {
        final Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null || authentication instanceof AnonymousAuthenticationToken) {
            return true;
        }
        if (authentication.getPrincipal() == null || authentication instanceof UsernamePasswordAuthenticationToken) {
            return true;
        }
        return authentication.getPrincipal().equals(ANONYMOUS_USER);
    }
}
