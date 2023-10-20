package com.wemirr.framework.security.configuration;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.cloud.context.config.annotation.RefreshScope;

import java.util.ArrayList;
import java.util.List;

import static com.wemirr.framework.security.configuration.SecurityExtProperties.PLUGIN_PREFIX;

/**
 * 资源服务器对外直接暴露URL
 *
 * @author Levin
 * @since 2023-09-13
 */
@Data
@RefreshScope
@ConfigurationProperties(prefix = PLUGIN_PREFIX)
public class SecurityExtProperties {

    public static final String PLUGIN_PREFIX = "extend.oauth2";

    /**
     * 默认的过滤地址
     */
    private List<String> defaultIgnoreUrls = List.of("/getCaptcha", "/captcha", "/sms_captcha", "/message/**", "/favicon.ico", "/swagger-ui.html", "/doc.html", "/v3/api-docs/**", "/assets/**", "/css/**", "/webjars/**", "/login", "/error", "/oauth2/**");

    /**
     * 忽略资源
     */
    private Ignore ignore = new Ignore();


    private LoadType authorizationType = LoadType.redis;
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
//        private LoadType type = LoadType.redis;
        /**
         * 大概率用不上
         */
        private boolean device = false;
        /**
         * 大概率用不上
         */
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
         * 登录地址，前后端分离就填写完整的url路径，不分离填写相对路径
         */
        private String loginFormUrl = "/login";
        /**
         * 设备验证地址
         */
        private String deviceVerificationUri = "/oauth2/device_verification";

        /**
         * 需要暴露在 JWT token 中的信息（便于从Token 提取数据建晒对 DB/REDIS 的检索）
         * 注意： 尽可能不要暴露敏感数据,同时暴露的内容别太多,越多KEY越长
         */
        private String[] jwtClaims = new String[]{"userId", "tenantId", "tenantId", "tenantName", "realName"};


        /**
         * 授权确认页面地址
         */
        private String deviceActivatedUri = "http://127.0.0.1:5001/activated";

        /**
         * 授权确认页面地址
         */
        private String deviceActivateUri = "http://127.0.0.1:5001/activate";

        /**
         * 授权确认页面地址
         */
        private String consentPageUri = "http://127.0.0.1:5001/consent";
    }


    @Data
    public static class Client {

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


    private InnerService innerService = new InnerService();

    @Data
    public static class InnerService {

        /**
         * 安全头
         */
        private String header = "innerRest";
        /**
         * 安全请求头的值
         */
        private String headerValue = "innerFeign";
        /**
         * 白名单
         */
        private List<String> whiteLists;

    }

}
