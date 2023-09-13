package com.wemirr.platform.authority;

import com.wemirr.framework.boot.log.event.SysLogListener;
import com.wemirr.framework.security.configuration.server.annotation.EnableOAuth2Server;
import com.wemirr.framework.websocket.redis.EnableRedisWebSocket;
import com.wemirr.platform.authority.service.OptLogService;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
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
@EnableRedisWebSocket
@EnableCaching
@EnableDiscoveryClient
@SpringBootApplication
@EnableFeignClients(basePackages = "com.wemirr")
@MapperScan(value = "com.wemirr.**.repository", annotationClass = Repository.class)
@EnableOAuth2Server
public class AuthorityApplication {

    @Bean
    public SysLogListener sysLogListener(OptLogService optLogService) {
        return new SysLogListener(optLogService::save);
    }

    @SneakyThrows
    public static void main(String[] args) {
        final ConfigurableApplicationContext applicationContext = SpringApplication.run(AuthorityApplication.class, args);
        Environment env = applicationContext.getEnvironment();
        final String appName = env.getProperty("spring.application.name");
        String host = InetAddress.getLocalHost().getHostAddress();
        String port = env.getProperty("server.port");
        log.info("""

                        ----------------------------------------------------------
                        \tApplication '{}' is running! Access URLs:
                        \tDoc: \thttp://{}:{}/doc.html
                        ----------------------------------------------------------""",
                appName, host, port);
    }

}
