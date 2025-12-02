package com.wemirr.platform.ai;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.scheduling.annotation.EnableAsync;

/**
 * @author xJh
 * @since 2025/09/29
 * AI应用启动类
 */
@SpringBootApplication
@EnableDiscoveryClient
@EnableAsync
@EnableFeignClients(basePackages = "com.wemirr")
public class AiApplication {

    public static void main(String[] args) {
        SpringApplication.run(AiApplication.class, args);
    }
}