package com.wemirr.framework.security.constant;

/**
 * security 常量类
 *
 * @author Levin
 */
public class SecurityConstants {


    /**
     * 微信登录相关参数——openid：用户唯一id
     */
    public static final String WECHAT_PARAMETER_OPENID = "openid";

    /**
     * 微信登录相关参数——forcePopup：强制此次授权需要用户弹窗确认
     */
    public static final String WECHAT_PARAMETER_FORCE_POPUP = "forcePopup";
    /**
     * 三方登录类型——微信
     */
    public static final String THIRD_LOGIN_WECHAT = "wechat";

    /**
     * 微信登录相关参数——secret：微信的应用秘钥
     */
    public static final String WECHAT_PARAMETER_SECRET = "secret";

    /**
     * 微信登录相关参数——appid：微信的应用id
     */
    public static final String WECHAT_PARAMETER_APPID = "appid";


    /**
     * 三方登录类型——Gitee
     */
    public static final String THIRD_LOGIN_GITEE = "gitee";

    /**
     * 三方登录类型——Github
     */
    public static final String THIRD_LOGIN_GITHUB = "github";

    /**
     * 随机字符串请求头名字
     */
    public static final String NONCE_HEADER_NAME = "nonceId";

    /**
     * 登录方式入参名
     */
    public static final String LOGIN_TYPE_NAME = "login_type";

    /**
     * 权限在token中的key
     */
    public static final String CLAIM_AUTHORITIES = "authorities";

    public static final String CLAIM_USERINFO = "userinfo";

    /**
     * 自定义 grant type —— 短信验证码
     */
    public static final String GRANT_TYPE_CUSTOM = "urn:ietf:params:oauth:grant-type:custom";


    public static class RedisConstants {

        /**
         * jwk set缓存前缀
         */
        public static final String AUTHORIZATION_JWS_PREFIX_KEY = "authorization_jws";

        /**
         * 认证信息存储前缀
         */
        public static final String SECURITY_CONTEXT_PREFIX_KEY = "security_context:";
        /**
         * 默认过期时间，默认30分钟
         */
        public static final long DEFAULT_TIMEOUT_SECONDS = 60L * 30;

    }
}
