package com.wemirr.framework.security.client.utils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.TypeReference;
import com.wemirr.framework.commons.exception.CheckedException;
import com.wemirr.framework.security.client.entity.SecurityDictionary;
import com.wemirr.framework.security.client.entity.SecurityUserDetail;
import com.wemirr.framework.security.client.entity.UserInfoDetails;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.provider.OAuth2Authentication;

import java.util.List;

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
        if (authentication == null) {
            throw CheckedException.forbidden("认证信息不存在");
        }
        Authentication userAuthentication = authentication.getUserAuthentication();
        if (userAuthentication.getPrincipal() instanceof UserInfoDetails) {
            return (UserInfoDetails) userAuthentication.getPrincipal();
        }
        String json = JSON.toJSONString(userAuthentication.getDetails());
        JSONObject details = JSON.parseObject(json);
        UserInfoDetails userInfoDetails = new UserInfoDetails();
        userInfoDetails.setUserId(details.getLong("userId"));
        userInfoDetails.setUsername(details.getString("username"));
        userInfoDetails.setNickName(details.getString("nickName"));
        userInfoDetails.setRealName(details.getString("realName"));
        userInfoDetails.setTenantId(details.getLong("tenantId"));
        userInfoDetails.setCompanyName(details.getString("companyName"));
        userInfoDetails.setEnabled(true);
        return userInfoDetails;
    }

    /**
     * 获取用户详细信息（不适用于 authority 模块 ）
     * 代码懒得优化了。
     *
     * @return 结果
     */
    public static SecurityUserDetail getSecurityUserDetail() {
        OAuth2Authentication authentication = getAuthentication();
        if (authentication == null) {
            throw CheckedException.forbidden("认证信息不存在");
        }
        Authentication userAuthentication = authentication.getUserAuthentication();
        String json = JSON.toJSONString(userAuthentication.getDetails());
        JSONObject details = JSON.parseObject(json);
        List<SecurityDictionary<Long>> departments = details.getObject("departments", new TypeReference<List<SecurityDictionary<Long>>>() {
        });
        final SecurityUserDetail.SecurityUserDetailBuilder detailBuilder = SecurityUserDetail.builder()
                .userId(details.getLong("userId"))
                .tenantId(details.getLong("tenantId"))
                .companyName(details.getString("companyName"))
                .mobile(details.getString("mobile"))
                .departments(departments)
                .realName(details.getString("realName"))
                .nickName(details.getString("nickName"))
                .principal(details.getString("username"))
                .authorities(authentication.getAuthorities());
        return detailBuilder.build();
    }

    public static Long userId() {
        return getSecurityUserDetail().getUserId();
    }

    public static String principal() {
        return getSecurityUserDetail().getPrincipal();
    }

    public static final String ANONYMOUS_USER = "anonymousUser";

    public boolean isAuth() {
        final Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null) {
            return false;
        }
        if (authentication.getPrincipal() == null) {
            return false;
        }
        return !authentication.getPrincipal().equals(ANONYMOUS_USER);
    }


    /**
     * 是否为匿名用户
     *
     * @return 是（true）|不是（false）
     */
    public static boolean anonymous() {
        final Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null) {
            return true;
        }
        return authentication.getPrincipal().equals(ANONYMOUS_USER);
    }


    public static Long getUserId() {
        return getSecurityUserDetail().getUserId();
    }

    public static Long getTenantId() {
        return getSecurityUserDetail().getTenantId();
    }

    public static String getRealName() {
        return getSecurityUserDetail().getRealName();
    }



}
