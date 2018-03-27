package com.battcn.framework.security;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @author Levin
 */
@Data
@ConfigurationProperties(prefix = "spring.security.battcn.token")
public class SecurityTokenProperties {

    private boolean enabled;

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
