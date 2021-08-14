package com.wemirr.platform.nepxion;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * @author Levin
 */
@EnableDiscoveryClient
@SpringBootApplication
public class NepxionMysqlApplication {


    public static void main(String[] args) {
        System.setProperty("spring.profiles.active", "h2");
        SpringApplication.run(NepxionMysqlApplication.class, args);
    }
}
