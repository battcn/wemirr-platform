package com.wemirr.framework.websocket.configuration;

import org.springframework.boot.web.embedded.undertow.UndertowServletWebServerFactory;
import org.springframework.boot.web.servlet.server.ServletWebServerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.undertow.websockets.jsr.WebSocketDeploymentInfo;

/**
 * 
 * @Author: Denivko
 * @CreatedDate: 2025-06-06 14:47:25
 * @Description: 显式注册 WebSocketDeploymentInfo 到 ServletContext
 *
 */
@Configuration
public class WebSocketContainerConfigurer {

    @Bean
    public ServletWebServerFactory servletWebServerFactory() {
        UndertowServletWebServerFactory factory = new UndertowServletWebServerFactory();
        factory.addDeploymentInfoCustomizers(deploymentInfo -> {
            deploymentInfo.addServletContextAttribute(
                "io.undertow.websockets.jsr.WebSocketDeploymentInfo", 
                new WebSocketDeploymentInfo()
            );
        });
        return factory;
    }
}