package com.wemirr.platform.gateway.config;


import com.wemirr.platform.gateway.config.rule.BlacklistHelper;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.boot.autoconfigure.web.ServerProperties;
import org.springframework.boot.autoconfigure.web.WebProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.web.reactive.error.ErrorAttributes;
import org.springframework.boot.web.reactive.error.ErrorWebExceptionHandler;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.codec.ServerCodecConfigurer;
import org.springframework.web.reactive.result.view.ViewResolver;

import java.util.Collections;
import java.util.List;

/**
 * 覆盖默认的异常处理
 *
 * @author Levin
 */
@Configuration
@EnableConfigurationProperties({ServerProperties.class, WebProperties.class})
public class ErrorHandlerConfiguration {

    private final BlacklistHelper blacklistHelper;
    private final ServerProperties serverProperties;
    private final ApplicationContext applicationContext;
    private final List<ViewResolver> viewResolvers;
    private final WebProperties webProperties;
    private final ServerCodecConfigurer serverCodecConfigurer;

    public ErrorHandlerConfiguration(ServerProperties serverProperties,
                                     BlacklistHelper blacklistHelper,
                                     WebProperties webProperties,
                                     ObjectProvider<List<ViewResolver>> viewResolversProvider,
                                     ServerCodecConfigurer serverCodecConfigurer,
                                     ApplicationContext applicationContext) {
        this.serverProperties = serverProperties;
        this.blacklistHelper = blacklistHelper;
        this.applicationContext = applicationContext;
        this.viewResolvers = viewResolversProvider.getIfAvailable(Collections::emptyList);
        this.serverCodecConfigurer = serverCodecConfigurer;
        this.webProperties = webProperties;
    }

    @Bean
    @Order(Ordered.HIGHEST_PRECEDENCE)
    public ErrorWebExceptionHandler errorWebExceptionHandler(ErrorAttributes errorAttributes) {
        JsonExceptionHandler exceptionHandler = new JsonExceptionHandler(errorAttributes, blacklistHelper,
                this.webProperties, this.serverProperties.getError(), this.applicationContext);
        exceptionHandler.setViewResolvers(this.viewResolvers);
        exceptionHandler.setMessageWriters(this.serverCodecConfigurer.getWriters());
        exceptionHandler.setMessageReaders(this.serverCodecConfigurer.getReaders());
        return exceptionHandler;
    }

}