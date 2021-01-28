package com.wemirr.platform.gateway.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Levin
 */
@Data
@ConfigurationProperties(prefix = "ignore")
public class IgnoreProperties {

    private List<String> clients = new ArrayList<>();
    private List<String> swaggerProviders = new ArrayList<>();

}
