package com.battcn.bookstore.consumer;


import com.alibaba.dubbo.config.spring.context.annotation.EnableDubbo;
import com.alibaba.dubbo.config.spring.context.annotation.EnableDubboConfig;
import com.battcn.framework.core.cros.annotation.EnableCorsFilter;
import com.battcn.framework.core.exception.annotation.EnableGlobalErrorResolver;
import com.battcn.framework.security.annotation.EnableSecurityToken;
import com.battcn.swagger.annotation.EnableSwagger2Doc;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

/**
 * 主程序启动类
 *
 * @author Levin
 * @version 2.5.1
 * @since 2018-01-10
 */
@EnableDubbo
@EnableSecurityToken
@EnableGlobalErrorResolver
@EnableCorsFilter
@EnableSwagger2Doc
@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class})
public class BookStoreApplication {


    public static void main(String[] args) {
        SpringApplication.run(BookStoreApplication.class, args);
    }


}
