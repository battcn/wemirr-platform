package com.battcn.framework.core.exception;

import com.battcn.framework.core.exception.properties.ErrorMvcProperties;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;

/**
 * @author Levin
 * @since 2018/1/16 0016
 */
@EnableConfigurationProperties(value = {ErrorMvcProperties.class})
@ConditionalOnProperty(
        prefix = "spring.mvc.exception",
        name = "enabled",
        havingValue = "true"
)
public class ErrorResolverAutoConfiguration {

    private final ErrorMvcProperties errorMvcProperties;

    public ErrorResolverAutoConfiguration(ErrorMvcProperties errorMvcProperties) {
        this.errorMvcProperties = errorMvcProperties;
    }

    @Bean
    public ErrorResolverHandler mvcErrorHandler() {
        return new ErrorResolverHandler(errorMvcProperties);
    }


}