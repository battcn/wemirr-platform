package com.wemirr.platform.nepxion;

import org.camunda.bpm.spring.boot.starter.annotation.EnableProcessApplication;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * @author Levin
 */
@EnableDiscoveryClient
@EnableProcessApplication
@SpringBootApplication
public class NepxionApplication {

    public static void main(String[] args) {
        SpringApplication.run(NepxionApplication.class, args);
    }

}
