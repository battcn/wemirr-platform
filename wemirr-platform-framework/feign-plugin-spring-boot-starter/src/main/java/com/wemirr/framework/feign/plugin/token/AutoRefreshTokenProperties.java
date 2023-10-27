package com.wemirr.framework.feign.plugin.token;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * 自动生成 token 请求内部服务(对动态数据源支持不一定好)
 *
 * @author Levin
 */
@SuppressWarnings("ALL")
@Data
@ConfigurationProperties(prefix = AutoRefreshTokenProperties.TOKEN_PREFIX)
public class AutoRefreshTokenProperties {

    public static final String TOKEN_PREFIX = "extend.feign.plugin.token";
    public static final String X_AUTO_TOKEN = "X-Auto-Token=true";
    public static final String X_AUTO_TOKEN_KEYWORD = "X-Auto-Token";
    public static final String AUTHORIZATION = "Authorization";

    private boolean enabled;
    private String includeTokenHeader = X_AUTO_TOKEN_KEYWORD;
    private String serverTokenHeader = AUTHORIZATION;
    private OAuth oAuth;
    private String uri;

    /**
     * true 走feign 否则普通 http 请求
     */
    private boolean loadBalance;
    private Cache cache = new Cache();

    @Data
    public static class OAuth {
        private String clientId = "wemirr";
        private String clientSecret = "wemirr";
        private String username;
        private String password;
        private String tenantCode;
        private String grantType = "password";
        private String loginType = "password";
        private String scope = "server";
    }

    @Data
    public static class Cache {

        /**
         * 过期时间-秒
         * 默认 1小时
         */
        private long expire = 60 * 60;
        private int initialCapacity = 30;
        private long maximumSize = 100;

    }


}
