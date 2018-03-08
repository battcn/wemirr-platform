package com.battcn.managent.consumer;

import com.battcn.swagger.annotation.EnableSwagger2Doc;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

/**
 * 主程序启动类
 *
 * @author Levin
 * @version 2.5.1
 * @since 2018-01-10
 */
@EnableSwagger2Doc
@SpringBootApplication
public class ManagementApplication {

    public static void main(String[] args) {
        ApplicationContext context = SpringApplication.run(ManagementApplication.class, args);
    }

}
