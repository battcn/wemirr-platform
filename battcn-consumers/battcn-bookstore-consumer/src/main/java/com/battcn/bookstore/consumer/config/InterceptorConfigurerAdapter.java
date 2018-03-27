package com.battcn.bookstore.consumer.config;

import com.battcn.bookstore.consumer.config.interceptor.AuthenticateInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 * 创建 JAVA Bean 继承 WebMvcConfigurerAdapter 类，重写 addInterceptors 方法
 *
 * @author Levin
 */
@Configuration
public class InterceptorConfigurerAdapter extends WebMvcConfigurerAdapter {

    private AuthenticateInterceptor authenticateInterceptor;

    @Autowired
    public InterceptorConfigurerAdapter(AuthenticateInterceptor authenticateInterceptor) {
        this.authenticateInterceptor = authenticateInterceptor;
    }

    /**
     * 该方法用于注册拦截器
     * 可注册多个拦截器，多个拦截器组成一个拦截器链
     */
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(authenticateInterceptor);
        super.addInterceptors(registry);
    }
}