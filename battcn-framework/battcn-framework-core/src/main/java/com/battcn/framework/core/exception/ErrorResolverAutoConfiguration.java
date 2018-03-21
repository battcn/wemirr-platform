package com.battcn.framework.core.exception;

import com.battcn.framework.core.exception.properties.ErrorMvcProperties;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;

/**
 * @author Levin
 * @since 2018/1/16 0016
 */
@Slf4j
@EnableConfigurationProperties(value = {ErrorMvcProperties.class})
@ConditionalOnProperty(
        prefix = "battcn.exception.mvc",
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