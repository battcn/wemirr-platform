package com.wemirr.framework.feign.plugin;

import com.wemirr.framework.feign.plugin.mock.MockProperties;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @author levin
 */
@Data
@ConfigurationProperties(prefix = FeignPluginProperties.PLUGIN_PREFIX)
public class FeignPluginProperties {

    public static final String PLUGIN_PREFIX = "extend.feign.plugin";
    private boolean enabled = true;
    private MockProperties mock = new MockProperties();

}