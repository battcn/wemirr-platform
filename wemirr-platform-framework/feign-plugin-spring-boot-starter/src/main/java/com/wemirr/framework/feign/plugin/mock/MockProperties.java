package com.wemirr.framework.feign.plugin.mock;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author levin
 */
@Data
@ConfigurationProperties(prefix = MockProperties.MOCK_PREFIX)
public class MockProperties {

    public static final String MOCK_PREFIX = "extend.feign.plugin.mock";

    private boolean enabled;
    /**
     * 如果每个服务的 mock server 地址不一样，使用该配置,key:服务名  value: ip地址 ,例
     */
    private Map<String, Server> serverMap = new ConcurrentHashMap<>();

    @Data
    public static class Server {
        private String serverUrl;
        private boolean oldServer = false;
        private String dataField = "data";
    }
}