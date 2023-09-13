package com.wemirr.platform.gateway;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.core.env.Environment;

/**
 * @author Levin
 */
@Slf4j
@EnableDiscoveryClient
@SpringBootApplication
public class GatewayApplication {

    public static void main(String[] args) {
        final ConfigurableApplicationContext applicationContext = SpringApplication.run(GatewayApplication.class, args);
        Environment env = applicationContext.getEnvironment();
        final String appName = env.getProperty("spring.application.name");
        String port = env.getProperty("server.port");
        log.info("""

                        ----------------------------------------------------------
                        \tApplication '{}' is running! Access URLs:
                        \tDoc: \thttp://{}:{}/doc.html
                        ----------------------------------------------------------""",
                appName, "localhost", port);
    }


}
