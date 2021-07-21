package com.wemirr.platform.authority.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * todo 实际开发建议注释或者删除
 * 演示环境拦截器
 *
 * @author Levin
 */
@Profile({"demo", "dev"})
@Configuration
public class CustomWebMvcConfigure implements WebMvcConfigurer {

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(demoProfileInterceptor()).addPathPatterns("/**");
        WebMvcConfigurer.super.addInterceptors(registry);
    }

    @Bean
    public DemoProfileInterceptor demoProfileInterceptor() {
        return new DemoProfileInterceptor();
    }
}