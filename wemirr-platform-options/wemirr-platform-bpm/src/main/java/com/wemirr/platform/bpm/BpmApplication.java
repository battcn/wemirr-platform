package com.wemirr.platform.bpm;

import org.camunda.bpm.spring.boot.starter.annotation.EnableProcessApplication;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * "bpmn-js": "^8.7.3",
 * "bpmn-js-properties-panel": "^0.44.0",
 * "camunda-bpmn-moddle": "^6.0.0",
 *
 * @author Levin
 */
@SpringBootApplication
@EnableDiscoveryClient
@EnableProcessApplication
public class BpmApplication {

    public static void main(String[] args) {
        SpringApplication.run(BpmApplication.class, args);
    }

}
