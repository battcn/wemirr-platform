package com.wemirr.framework.security.domain;

import lombok.Data;

import java.time.Instant;

/**
 * <p>
 * 三方登录账户信息表
 * </p>
 *
 * @author Levin
 * @since 2023-07-04
 */
@Data
public class OAuth2ThirdAccount {

    /**
     * 三方登录唯一id
     */
    private String uniqueId;

    /**
     * 三方登录用户名
     */
    private String thirdUsername;

    /**
     * 三方登录获取的认证信息
     */
    private String credentials;

    /**
     * 三方登录获取的认证信息的过期时间
     */
    private Instant credentialsExpiresAt;

    /**
     * 三方登录类型
     */
    private String type;

    /**
     * 博客地址
     */
    private String blog;

    /**
     * 地址
     */
    private String location;

    /**
     * 用户名、昵称
     */
    private String name;

    /**
     * 头像地址
     */
    private String avatarUrl;
}
