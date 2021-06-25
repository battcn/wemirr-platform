package com.wemirr.platform.nepxion;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * @author Levin
 */
@EnableDiscoveryClient
@SpringBootApplication
public class NepxionApplication2 {


    public static void main(String[] args) {
        System.setProperty("spring.profiles.active", "weight2");
        SpringApplication.run(NepxionApplication2.class, args);
    }
}
