package com.wemirr.framework.swagger.config;


import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import io.swagger.v3.oas.models.servers.Server;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringBootVersion;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.*;

/**
 * @author Levin
 * swagger配置
 */
@Configuration
@ConditionalOnProperty(name = "springdoc.plus.enabled", havingValue = "true", matchIfMissing = true)
@EnableConfigurationProperties(Swagger3Properties.class)
public class Swagger3AutoConfiguration {

    @Value("${server.port:8080}")
    private int port;
    @Value("${spring.mvc.servlet.path:/}")
    private String servletPath;


    @Bean
    @ConditionalOnMissingBean
    public Swagger3Properties swagger3Properties() {
        return new Swagger3Properties();
    }

    @Bean
    public OpenAPI springDocOpenAPI(Swagger3Properties swagger3Properties) {
        //配置认证、请求头参数
        Components components = new Components();
        List<SecurityRequirement> security = new ArrayList<>();
        for (Map.Entry<String, SecurityScheme> entry : swagger3Properties.getSecuritySchemes().entrySet()) {
            components.addSecuritySchemes(entry.getKey(), entry.getValue());
            SecurityRequirement requirement = new SecurityRequirement();
            requirement.addList(entry.getKey());
            security.add(requirement);
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
        List<Server> servers = Optional.ofNullable(swagger3Properties.getServers()).orElseGet(() -> Collections.singletonList(localServer()));
        return new OpenAPI().components(components)
                .info(info).externalDocs(swagger3Properties.getExternal())
                .security(security).servers(servers);
    }

    private Server localServer() {
        Server server = new Server();
        server.setUrl("http://localhost:" + port + "" + servletPath);
        return server;
    }

    private String version() {
        return "Spring Boot Version: " + SpringBootVersion.getVersion();
    }


}
