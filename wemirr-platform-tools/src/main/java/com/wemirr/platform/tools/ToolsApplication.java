package com.wemirr.platform.tools;

import cn.hutool.core.lang.generator.Generator;
import cn.hutool.core.lang.generator.SnowflakeGenerator;
import com.wemirr.framework.commons.StringUtils;
import com.wemirr.framework.security.client.annotation.EnableOAuth2Client;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.mongo.MongoAutoConfiguration;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Repository;

import java.net.InetAddress;

/**
 * @author Levin
 */
@Slf4j
@EnableOAuth2Client
@EnableDiscoveryClient
@SpringBootApplication(exclude = MongoAutoConfiguration.class)
@EnableFeignClients("com.wemirr")
@MapperScan(value = "com.wemirr.**.mapper", annotationClass = Repository.class)
public class ToolsApplication {


    @Bean
    public Generator<Long> generator() {
        return new SnowflakeGenerator(1, 1);
    }


    @SneakyThrows
    public static void main(String[] args) {
        final ConfigurableApplicationContext applicationContext = SpringApplication.run(ToolsApplication.class, args);
        Environment env = applicationContext.getEnvironment();
        final String appName = env.getProperty("spring.application.name");
        String host = InetAddress.getLocalHost().getHostAddress();
        String port = StringUtils.defaultString(env.getProperty("server.port"), "8080");
        log.info("""

                        ----------------------------------------------------------
                        \tApplication '{}' is running! Access URLs:
                        \tDoc: \thttp://{}:{}/doc.html
                        ----------------------------------------------------------""",
                appName, host, port);
    }
}
