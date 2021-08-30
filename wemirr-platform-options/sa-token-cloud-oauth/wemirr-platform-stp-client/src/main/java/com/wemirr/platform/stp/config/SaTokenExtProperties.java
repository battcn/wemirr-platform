package com.wemirr.platform.stp.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @author Levin
 */
@Data
@ConfigurationProperties(prefix = "sa-token.oauth2.enhance")
public class SaTokenExtProperties {

    /**
     * 创建负载均衡器
     */
    private Boolean loadBalanced = false;
    /**
     * token 信息地址
     */
    private String tokenInfoUri;
    /**
     * 首选令牌信息（如果为true 则不会在请求 userInfoUri 而是从token 中拿上下文）
     */
    private Boolean preferTokenInfo = true;
    /**
     * 用户信息地址
     */
    private String userInfoUri;

}
