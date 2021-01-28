package com.wemirr.platform.authority;

import com.wemirr.framework.boot.config.EnableGlobalException;
import com.wemirr.framework.boot.config.log.event.SysLogListener;
import com.wemirr.framework.security.client.annotation.EnableOauth2ClientResourceServer;
import com.wemirr.framework.websocket.redis.EnableRedisWebSocket;
import com.wemirr.platform.authority.service.OptLogService;
import feign.Logger;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.stereotype.Repository;

/**
 * @author Levin
 */
@EnableRedisWebSocket
@EnableCaching
@EnableGlobalException
@EnableResourceServer
@EnableDiscoveryClient
@SpringBootApplication
@EnableFeignClients(basePackages = "com.wemirr")
@MapperScan(value = "com.wemirr.**.mapper", annotationClass = Repository.class)
@EnableOauth2ClientResourceServer
public class AuthorityApplication {

    @Bean
    public Logger.Level level() {
        return Logger.Level.FULL;
    }

    @Bean
    public SysLogListener sysLogListener(OptLogService optLogService) {
        return new SysLogListener(optLogService::save);
    }

    public static void main(String[] args) {
        SpringApplication.run(AuthorityApplication.class, args);
    }

}
