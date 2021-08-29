package com.wemirr.platform.stp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * @author Levin
 */
@SpringBootApplication
@EnableDiscoveryClient
public class StpServerApplication {

    public static void main(String[] args) {
        SpringApplication.run(StpServerApplication.class, args);
    }

}
