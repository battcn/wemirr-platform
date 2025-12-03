package com.wemirr.framework.security.configuration;

import cn.dev33.satoken.filter.SaTokenContextFilterForJakartaServlet;
import jakarta.servlet.DispatcherType;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;

import java.util.EnumSet;

/**
 * @author xJh
 * @since  2025/12/03
 * Sa-Token 上下文配置
 * https://gitee.com/dromara/sa-token/issues/IC4XFE#note_41189047_link
 * 修复SSE报错satoken未设置上下文
 * sse只有初始化连接是同步操作。请求会经过filter初始化SaTokenContext。但是其他操作不一定，可能是异步的。filter不会过滤填充上下文。解决办法是手动注册Filter，然后设置允许异步处理
 **/
@Configuration
public class SaTokenConfiguration {

    @Bean
    public FilterRegistrationBean<SaTokenContextFilterForJakartaServlet> saTokenContextFilterForJakartaServlet() {
        FilterRegistrationBean<SaTokenContextFilterForJakartaServlet> bean =
                new FilterRegistrationBean<>(new SaTokenContextFilterForJakartaServlet());
        bean.addUrlPatterns("/*");
        bean.setOrder(Ordered.HIGHEST_PRECEDENCE);
        bean.setAsyncSupported(true);
        bean.setDispatcherTypes(EnumSet.of(DispatcherType.ASYNC, DispatcherType.REQUEST));
        return bean;
    }

}
