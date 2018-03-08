package com.battcn.demo.consumer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author Levin
 * @since 2018/03/08
 */
@SpringBootApplication(scanBasePackages = "com.battcn.demo.consumer.controller")
public class DubboConsumerDemo {

    public static void main(String[] args) {
        SpringApplication.run(DubboConsumerDemo.class, args);
    }

}