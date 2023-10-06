package com.wemirr.platform.demo;

import com.wemirr.framework.security.configuration.client.annotation.EnableOAuth2Client;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.stereotype.Repository;

/**
 * @author Levin
 */
@EnableOAuth2Client
@EnableDiscoveryClient
@SpringBootApplication
@EnableFeignClients("com.wemirr")
@MapperScan(value = "com.wemirr.**.mapper", annotationClass = Repository.class)
public class DemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(DemoApplication.class, args);
    }
}
