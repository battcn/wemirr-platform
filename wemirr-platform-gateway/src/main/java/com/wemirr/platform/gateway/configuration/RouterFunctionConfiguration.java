package com.wemirr.platform.gateway.configuration;

import com.wemirr.platform.gateway.handler.ExceptionFallbackHandler;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.server.RequestPredicates;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;

/**
 * @author Levin
 * @since 2019-04-04
 * 路由配置信息
 */
@Slf4j
@Configuration
@AllArgsConstructor
public class RouterFunctionConfiguration {

    private final ExceptionFallbackHandler exceptionFallbackHandler;

    @Bean
    public RouterFunction<?> routerFunction() {
        return RouterFunctions.route(
                RequestPredicates.path("/fallback")
                        .and(RequestPredicates.accept(MediaType.APPLICATION_JSON)), exceptionFallbackHandler);

    }

}
