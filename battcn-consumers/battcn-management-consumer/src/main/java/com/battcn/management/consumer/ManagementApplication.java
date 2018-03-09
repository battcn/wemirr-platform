package com.battcn.management.consumer;


import com.battcn.swagger.annotation.EnableSwagger2Doc;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.context.ApplicationContext;
import org.springframework.web.bind.annotation.RestController;

/**
 * 主程序启动类
 *
 * @author Levin
 * @version 2.5.1
 * @since 2018-01-10
 */
@RestController
@EnableSwagger2Doc
@SpringBootApplication
@EnableAutoConfiguration(exclude = {DataSourceAutoConfiguration.class})
public class ManagementApplication {


    public static void main(String[] args) {
        ApplicationContext applicationContext = SpringApplication.run(ManagementApplication.class, args);
        ApplicationContextUtils.setApplicationContext(applicationContext);
    }


    static public class ApplicationContextUtils {

        private static ApplicationContext applicationContext;

        public static ApplicationContext getApplicationContext() {
            return applicationContext;
        }

        private static void setApplicationContext(ApplicationContext applicationContext) {
            ApplicationContextUtils.applicationContext = applicationContext;
        }

        public static Object getBean(String name) {
            return applicationContext.getBean(name);
        }

        public static <T> T getBean(Class<T> requiredType) {
            return applicationContext.getBean(requiredType);
        }
    }

}
