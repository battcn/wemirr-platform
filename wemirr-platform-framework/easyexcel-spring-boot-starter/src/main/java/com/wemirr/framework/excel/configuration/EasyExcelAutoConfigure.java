package com.wemirr.framework.excel.configuration;

import com.wemirr.framework.excel.web.handler.ExcelWriteFileReturnValueHandler;
import com.wemirr.framework.excel.web.handler.ResponseExcelReturnValueHandler;
import com.wemirr.framework.excel.web.resolver.RequestExcelArgumentResolver;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.HandlerMethodReturnValueHandler;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * 自动装配
 *
 * @author Levin
 */
@Configuration
@AutoConfiguration
@RequiredArgsConstructor
@EnableConfigurationProperties(EasyExcelProperties.class)
@Import(ExcelHandlerConfiguration.class)
public class EasyExcelAutoConfigure {

    private final ApplicationContext context;
    private final RequestMappingHandlerAdapter requestMappingHandlerAdapter;
    private final ExcelWriteFileReturnValueHandler excelWriteFileReturnValueHandler;
    private final ResponseExcelReturnValueHandler responseExcelReturnValueHandler;

    @PostConstruct
    public void init() {
        // 设置导出返回处理器
        setReturnValueHandlers();
        // 设置导入请求参数解析
        setRequestExcelArgumentResolver();
    }


    public void setReturnValueHandlers() {
        var returnValueHandlers = requestMappingHandlerAdapter.getReturnValueHandlers();
        final List<HandlerMethodReturnValueHandler> handlers = new ArrayList<>();
        // 拦截 @ResponseExcel 注解
        handlers.add(responseExcelReturnValueHandler);
        // 拦截 ExcelWriteFile 对象
        handlers.add(excelWriteFileReturnValueHandler);
        if (returnValueHandlers != null) {
            handlers.addAll(returnValueHandlers);
        }
        requestMappingHandlerAdapter.setReturnValueHandlers(handlers);
    }


    public void setRequestExcelArgumentResolver() {
        List<HandlerMethodArgumentResolver> argumentResolvers = requestMappingHandlerAdapter.getArgumentResolvers();
        List<HandlerMethodArgumentResolver> resolverList = new ArrayList<>();
        // 拦截 @RequestExcel 注解
        resolverList.add(new RequestExcelArgumentResolver(context));
        if (argumentResolvers != null) {
            resolverList.addAll(argumentResolvers);
        }
        requestMappingHandlerAdapter.setArgumentResolvers(resolverList);
    }


}
