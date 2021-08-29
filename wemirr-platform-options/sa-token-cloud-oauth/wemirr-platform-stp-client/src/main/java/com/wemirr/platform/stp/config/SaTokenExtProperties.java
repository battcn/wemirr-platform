package com.wemirr.platform.stp.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @author Levin
 */
@Data
@ConfigurationProperties(prefix = "sa-token.oauth2.enhance")
public class SaTokenExtProperties {

    private Boolean loadBalanced = false;
    private String tokenInfoUri;
    private String userInfoUri;

}
