package com.wemirr.framework.boot.config;

import com.baomidou.dynamic.datasource.toolkit.DynamicDataSourceContextHolder;
import com.wemirr.framework.database.properties.DatabaseProperties;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Slf4j
@Configuration
public class DynamicDatasourceWebAutoConfig implements WebMvcConfigurer {

    @Resource
    private DatabaseProperties properties;


    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new HandlerInterceptor() {
            /**
             * 在请求处理之前进行调用（Controller方法调用之前）
             */
            @Override
            public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
                String requestURI = request.getRequestURI();
                String tenantCode = request.getHeader(properties.getTenantCodeColumn());
                if (StringUtils.isBlank(tenantCode)) {
                    tenantCode = request.getParameter(properties.getTenantCodeColumn());
                }
                String databasePrefix = properties.getDatabasePrefix();
                String dsKey = properties.getDefaultDataSourceName();
                if (StringUtils.isNotBlank(tenantCode)) {
                    dsKey = databasePrefix + tenantCode;
                }
                log.info("经过多数据源Interceptor,数据源是{},路径是{}", dsKey, requestURI);
                DynamicDataSourceContextHolder.push(dsKey);
                return true;
            }

            /**
             * 在整个请求结束之后被调用，也就是在DispatcherServlet 渲染了对应的视图之后执行（主要是用于进行资源清理工作）
             */
            @Override
            public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) {
                DynamicDataSourceContextHolder.clear();
            }
        });
    }
}