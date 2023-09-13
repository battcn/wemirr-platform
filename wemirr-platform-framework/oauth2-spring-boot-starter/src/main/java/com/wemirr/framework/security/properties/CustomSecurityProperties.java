package com.wemirr.framework.security.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.cloud.context.config.annotation.RefreshScope;

import java.util.ArrayList;
import java.util.List;

import static com.wemirr.framework.security.properties.CustomSecurityProperties.PLUGIN_PREFIX;

/**
 * 资源服务器对外直接暴露URL
 *
 * @author Levin
 * @since 2023-09-13
 */
@Data
@RefreshScope
@ConfigurationProperties(prefix = PLUGIN_PREFIX)
public class CustomSecurityProperties {

    public static final String PLUGIN_PREFIX = "extend.oauth2.authorization";

    /**
     * 默认的过滤地址
     */
    private List<String> defaultIgnoreUrls = List.of("/captcha", "/sms_captcha", "/message/**", "/favicon.ico", "/swagger-ui.html", "/doc.html", "/v3/api-docs/**", "/assets/**", "/css/**", "/webjars/**", "/login", "/error", "/oauth2/**");

    /**
     * 服务端配置
     */
    private Server server;

    /**
     * 客户端配置
     */
    private Client client;

    @Data
    public static class Server {

        private boolean custom = true;
        private LoadType type = LoadType.redis;
        private boolean device = false;
        private boolean odic = false;
        /**
         * token 类型 (支持自定义TOKEN和默认的JWT)
         */
        private TokenType tokenType = TokenType.custom;

        /**
         * 客户端注册类型（推荐JDBC/ REDIS 我没实现）
         */
        private LoadType registeredClient = LoadType.jdbc;
        /**
         * OAuth2AuthorizationConsentService 加载的类型
         */
        private LoadType consent = LoadType.jdbc;
        /**
         * 忽略资源
         */
        private Ignore ignore = new Ignore();
    }


    @Data
    public static class Client {
        /**
         * 忽略资源
         */
        private Ignore ignore = new Ignore();

        /**
         * 客户端 token 信息获取的策略
         */
        private OpaqueToken opaqueToken;
    }


    @Data
    public static class Ignore {
        private List<String> resourceUrls = new ArrayList<>();
    }

    /**
     * 加载类型
     */
    public enum LoadType {
        /**
         * redis / jdbc
         */
        redis, jdbc
    }

    /**
     * 内省token
     */
    @Data
    public static class OpaqueToken {

        /**
         * 内省token类型 （ redis 需要内部服务共享  / rest通过 feign/http 则无限制）
         */
        private OpaqueTokenType type = OpaqueTokenType.redis;

        /**
         * 用户信息端口
         */
        private String userinfo = "http://cloud-platform-base/oauth2/userinfo";
    }

    /**
     * token 类型
     * jwt = jwt 自行百度（常用信息都会注入到token,且token默认情况下不会存在redis,没意义）
     * custom = uuid 也可以自行魔改成自己想要的（默认存redis）
     */
    public enum TokenType {
        /**
         * jwt / custom = uuid
         */
        jwt, custom
    }

    public enum OpaqueTokenType {
        /**
         * redis / jdbc
         */
        redis, rest
    }

    /**
     * 登录地址，前后端分离就填写完整的url路径，不分离填写相对路径
     */
    private String loginFormUrl = "/login";
}
