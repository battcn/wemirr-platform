package com.wemirr.platform.nepxion;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * @author Levin
 */
@EnableDiscoveryClient
@SpringBootApplication
public class NepxionApplication1 {


    public static void main(String[] args) {
        System.setProperty("spring.profiles.active", "weight1");
        SpringApplication.run(NepxionApplication1.class, args);
    }
}
