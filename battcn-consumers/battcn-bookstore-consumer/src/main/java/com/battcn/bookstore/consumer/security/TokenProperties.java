package com.battcn.bookstore.consumer.security;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * @author Levin
 */
@Data
@Configuration
@ConfigurationProperties(prefix = "spring.security.battcn.token")
public class TokenProperties {

    private Integer expirationTime;

    /**
     * 发行人
     */
    private String issuer;

    /**
     * 使用的签名KEY
     */
    private String signingKey;

    /**
     * 刷新过期时间
     */
    private Integer refreshExpTime;

}
