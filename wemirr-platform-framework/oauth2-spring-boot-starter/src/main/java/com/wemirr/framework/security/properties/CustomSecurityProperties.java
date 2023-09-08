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
 * @since 2019-04-04
 */
@Data
@RefreshScope
@ConfigurationProperties(prefix = PLUGIN_PREFIX)
public class CustomSecurityProperties {

    public static final String PLUGIN_PREFIX = "extend.oauth2.authorization";

    private LoadType registeredClient = LoadType.jdbc;
    private LoadType type = LoadType.redis;
    /**
     * OAuth2AuthorizationConsentService 加载的类型
     */
    private LoadType consent = LoadType.jdbc;
    private Ignore serverIgnore = new Ignore();
    private Ignore clientIgnore = new Ignore();
    /**
     * 客户端 token 信息获取的策略
     */
    private OpaqueToken opaqueToken;
    /**
     * 默认的过滤地址
     */
    private List<String> defaultIgnoreUrls = List.of("/favicon.ico","/swagger-ui.html","/doc.html","/v3/api-docs/**","/assets/**","/css/**", "/captcha", "/sms_captcha", "/webjars/**", "/login", "/error", "/oauth2/**");

    @Data
    public static class Ignore {
        private List<String> webUrls = new ArrayList<>();
        private List<String> resourceUrls = new ArrayList<>();
    }


    private boolean device = true;
    private boolean odic;
    private boolean custom = true;

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
    ;
}
