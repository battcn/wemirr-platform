package com.wemirr.platform.authority.configuration.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.time.Duration;

/**
 * @author Levin
 */
@Data
@ConfigurationProperties(prefix = "security.oauth2")
public class OAuth2Properties {

    /**
     * 生成的 accessToken 有效期（默认 1 天）
     */
    private Duration accessTokenValidityTimeToLive = Duration.ofDays(1);
    /**
     * 生成的 refreshToken 有效期（默认 30 天）
     */
    private Duration refreshTokenValidityTimeToLive = Duration.ofDays(30);

}
