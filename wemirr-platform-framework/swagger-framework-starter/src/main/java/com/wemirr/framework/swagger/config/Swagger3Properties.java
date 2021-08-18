package com.wemirr.framework.swagger.config;

import io.swagger.v3.oas.models.ExternalDocumentation;
import io.swagger.v3.oas.models.security.SecurityScheme;
import io.swagger.v3.oas.models.servers.Server;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.List;
import java.util.Map;

/**
 * swagger3 配置增强
 * <p>
 * springdoc.swagger-ui.oauth.app-name=authority
 * springdoc.swagger-ui.oauth.client-id=client
 * springdoc.swagger-ui.oauth.client-secret=client
 * springdoc.swagger-ui.oauth.use-pkce-with-authorization-code-grant=true
 * springdoc.plus.info.title=Swagger API
 * springdoc.plus.info.description=聚合文档
 * springdoc.plus.info.terms-of-service=https://blog.battcn.com/
 * springdoc.plus.info.license.name=apache
 * springdoc.plus.info.license.url=apache
 * springdoc.plus.info.contact.name=唐亚峰
 * springdoc.plus.info.contact.url=https://cloud.battcn.com/
 * springdoc.plus.info.contact.email=1837307557@qq.com
 * springdoc.plus.security-schemes.oauth2.name=oauth2
 * springdoc.plus.security-schemes.oauth2.type=oauth2
 * springdoc.plus.security-schemes.oauth2.in=header
 * springdoc.plus.security-schemes.oauth2.bearer-format=Bearer
 * springdoc.plus.security-schemes.oauth2.flows.password.scopes.server=server
 * springdoc.plus.security-schemes.oauth2.flows.password.token-url=http://localhost:5002/oauth/token?tenant_code=0000
 *
 * @author Levin
 * @since 2018/7/25  14:00
 */
@Data
@ConfigurationProperties(prefix = "springdoc.plus")
public class Swagger3Properties {

    private Boolean enabled = true;

    /**
     * 为什么不用 swagger v3 自带的呢？因为idea不提示 防止有人不会写
     */
    private PlusInfo info;
    private ExternalDocumentation external;
    /**
     * 主要适配 oauth2（http basic配置起来更容易,可以参考oauth2 配置）
     */
    private Map<String, SecurityScheme> securitySchemes;
    /**
     * serverUrl 默认 本地地址
     **/
    private List<Server> servers;


    @Data
    public static class PlusInfo {
        private String title = null;
        private String description = null;
        private String termsOfService = null;
        private PlusContact contact = null;
        private PlusLicense license = null;
        private String version = null;
    }

    @Data
    public static class PlusContact {
        private String name = null;
        private String url = null;
        private String email = null;
    }

    @Data
    public static class PlusLicense {
        private String name = null;
        private String url = null;
    }
}
