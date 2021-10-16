package com.wemirr.platform.tools;

import cn.hutool.core.lang.generator.Generator;
import cn.hutool.core.lang.generator.SnowflakeGenerator;
import com.wemirr.framework.security.client.annotation.EnableOauth2ClientResourceServer;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Repository;

/**
 * @author Levin
 */
@EnableOauth2ClientResourceServer
@EnableDiscoveryClient
@SpringBootApplication
@EnableFeignClients("com.wemirr")
@MapperScan(value = "com.wemirr.**.mapper", annotationClass = Repository.class)
public class ToolsApplication {


    @Bean
    public Generator<Long> generator() {
        return new SnowflakeGenerator(1, 1);
    }

    public static void main(String[] args) {
        SpringApplication.run(ToolsApplication.class, args);
    }
}
