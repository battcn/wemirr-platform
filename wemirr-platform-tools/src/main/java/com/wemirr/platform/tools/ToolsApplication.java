package com.wemirr.platform.tools;

import com.wemirr.framework.boot.log.event.AccessLogListener;
import com.wemirr.framework.boot.log.feign.AccessLogFeign;
import com.wemirr.framework.security.configuration.client.annotation.EnableOAuth2Client;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.mongo.MongoAutoConfiguration;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Repository;

import java.net.InetAddress;

/**
 * @author Levin
 */
@Slf4j
@EnableOAuth2Client
@EnableDiscoveryClient
@SpringBootApplication(exclude = MongoAutoConfiguration.class)
@EnableFeignClients("com.wemirr")
@MapperScan(value = "com.wemirr.**.repository", annotationClass = Repository.class)
public class ToolsApplication {

    @Bean
    public AccessLogListener accessLogListener(AccessLogFeign feign) {
        return new AccessLogListener(feign::listener);
    }

    @SneakyThrows
    public static void main(String[] args) {
        final ConfigurableApplicationContext applicationContext = SpringApplication.run(ToolsApplication.class, args);
        Environment env = applicationContext.getEnvironment();
        final String appName = env.getProperty("spring.application.name");
        String host = InetAddress.getLocalHost().getHostAddress();
        String port = env.getProperty("server.port");
        log.info("""

                        ----------------------------------------------------------
                        \tApplication '{}' is running! Access URLs:
                        \tDoc: \thttp://{}:{}/doc.html
                        ----------------------------------------------------------""",
                appName, host, port);
    }
}
