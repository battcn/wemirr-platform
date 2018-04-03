package com.battcn.framework.core.exception.properties;


import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @author Levin
 * @since 2018/1/16 0016
 */
@Data
@ConfigurationProperties("spring.mvc.exception")
public class ErrorMvcProperties {

    private Boolean enabled;
    private String defaultMessage;
    private Boolean exposeErrorClass;

}