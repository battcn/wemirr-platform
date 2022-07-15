package com.wemirr.framework.swagger.config;


import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import io.swagger.v3.oas.models.servers.Server;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringBootVersion;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @author Levin
 * swagger配置
 */
@Slf4j
@Configuration
@ConditionalOnProperty(name = "springdoc.plus.enabled", havingValue = "true", matchIfMissing = true)
@EnableConfigurationProperties(Swagger3Properties.class)
public class Swagger3AutoConfiguration {

    @Value("${spring.mvc.servlet.path:/}")
    private String servletPath;
    @Value("${spring.application.name}")
    private String applicationName;


    @Bean
    @ConditionalOnMissingBean
    public Swagger3Properties swagger3Properties() {
        return new Swagger3Properties();
    }

    @Bean
    public OpenAPI springDocOpenApi(Swagger3Properties swagger3Properties) {
        if (swagger3Properties == null) {
            return new OpenAPI();
        }
        //配置认证、请求头参数
        Components components = new Components();
        List<SecurityRequirement> security = new ArrayList<>();
        if (!CollectionUtils.isEmpty(swagger3Properties.getSecuritySchemes())) {
            for (Map.Entry<String, SecurityScheme> entry : swagger3Properties.getSecuritySchemes().entrySet()) {
                components.addSecuritySchemes(entry.getKey(), entry.getValue());
                SecurityRequirement requirement = new SecurityRequirement();
                requirement.addList(entry.getKey());
                security.add(requirement);
            }
        }

        Info info = new Info();
        Swagger3Properties.PlusInfo plusInfo = swagger3Properties.getInfo();
        if (plusInfo != null) {
            License license = new License();
            Swagger3Properties.PlusLicense plusLicense = plusInfo.getLicense();
            if (plusLicense != null) {
                license.name(plusLicense.getName()).url(plusLicense.getUrl());
            }
            Contact contact = new Contact();
            Swagger3Properties.PlusContact plusContact = plusInfo.getContact();
            if (plusContact != null) {
                contact.email(plusContact.getEmail()).name(plusContact.getName()).url(plusContact.getUrl());
            }
            info.title(plusInfo.getTitle()).description(plusInfo.getDescription())
                    .termsOfService(plusInfo.getTermsOfService()).version(plusInfo.getVersion() == null ? version() : plusInfo.getVersion())
                    .contact(contact).license(license)
            ;
        }
        // 接口调试路径
        List<Server> servers = new ArrayList<>();
        servers.add(localServer());
        if (!CollectionUtils.isEmpty(swagger3Properties.getServers())) {
            servers.addAll(swagger3Properties.getServers());
        }
        return new OpenAPI().components(components).servers(servers)
                .info(info).externalDocs(swagger3Properties.getExternal())
                .security(security);
    }

    private Server localServer() {
        Server server = new Server();
        log.warn("=================================== 提示 =================================== ");
        log.warn("如果你要修改项目名称规则或网关路由配置或者网关端口,请调整此处取值逻辑");
        server.setUrl("http://localhost:9000/" + applicationName.substring(applicationName.lastIndexOf("-") + 1) + servletPath);
        log.warn("=================================== 提示 =================================== ");
        server.setDescription("本地服务环境");
        return server;
    }

    private String version() {
        return "Spring Boot Version: " + SpringBootVersion.getVersion();
    }
}
