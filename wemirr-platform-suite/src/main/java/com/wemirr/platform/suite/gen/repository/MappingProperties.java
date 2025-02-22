package com.wemirr.platform.suite.gen.repository;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * @author xiao1
 * @date 2025-02
 */
@Component
@ConfigurationProperties(prefix = "myapp.mappings")
public class MappingProperties {

    private Map<String, String> mappings;

    // Getter 和 Setter
    public Map<String, String> getMappings() {
        return mappings;
    }

    public void setMappings(Map<String, String> mappings) {
        this.mappings = mappings;
    }
}
