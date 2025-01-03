package com.wemirr.platform.iam.auth.configuration;


import com.wemirr.platform.iam.system.domain.enums.ThirdAuthType;
import lombok.Data;
import lombok.NoArgsConstructor;
import me.zhyd.oauth.config.AuthConfig;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * 三方授权配置
 * 其他平台对接方式可以参考 <a href="https://www.justauth.cn/guide/"/>
 *
 * @author Levin
 */
@Data
@Component
@NoArgsConstructor
@ConfigurationProperties(prefix = ThirdAuthProperties.PREFIX)
public class ThirdAuthProperties {

    public static final String PREFIX = "extend.third-auth";

    /**
     * 配置MAP
     */
    private Map<ThirdAuthType, AuthConfig> configMap;


}
