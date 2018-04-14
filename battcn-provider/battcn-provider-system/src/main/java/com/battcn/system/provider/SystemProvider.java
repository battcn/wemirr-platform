package com.battcn.system.provider;

import com.alibaba.dubbo.config.spring.context.annotation.DubboComponentScan;
import com.alibaba.dubbo.config.spring.context.annotation.EnableDubboConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author Levin
 * @since 2018/3/8 0008
 */
@EnableDubboConfig
@DubboComponentScan
@SpringBootApplication
public class SystemProvider {

    public static void main(String[] args) {
        SpringApplication.run(SystemProvider.class, args);
    }
}
