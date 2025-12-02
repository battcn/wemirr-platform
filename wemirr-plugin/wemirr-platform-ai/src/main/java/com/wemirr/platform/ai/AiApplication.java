package com.wemirr.platform.ai;

import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.core.env.Environment;
import org.springframework.scheduling.annotation.EnableAsync;

import java.net.InetAddress;

/**
 * @author xJh
 * @since 2025/09/29
 * AI应用启动类
 */
@Slf4j
@EnableAsync
@SpringBootApplication
@EnableDiscoveryClient
@EnableFeignClients(basePackages = "com.wemirr")
public class AiApplication {

    /**
     * 启动类.
     *
     * @param args 启动参数
     */
    @SneakyThrows
    public static void main(final String[] args) {
        final ConfigurableApplicationContext applicationContext = SpringApplication.run(AiApplication.class, args);
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