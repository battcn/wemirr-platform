package com.wemirr.framework.excel.configuration;

import com.wemirr.framework.excel.web.handler.ExcelWriteFileReturnValueHandler;
import com.wemirr.framework.excel.web.handler.ResponseExcelReturnValueHandler;
import com.wemirr.framework.excel.web.resolver.RequestExcelArgumentResolver;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
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


    private final RequestMappingHandlerAdapter requestMappingHandlerAdapter;
    private final ExcelWriteFileReturnValueHandler excelWriteFileReturnValueHandler;
    private final ResponseExcelReturnValueHandler responseExcelReturnValueHandler;


    /**
     * 追加 Excel返回值处理器 到 springmvc 中
     */
    @PostConstruct
    public void setReturnValueHandlers() {
        List<HandlerMethodReturnValueHandler> returnValueHandlers = requestMappingHandlerAdapter
                .getReturnValueHandlers();

        List<HandlerMethodReturnValueHandler> newHandlers = new ArrayList<>();
        newHandlers.add(responseExcelReturnValueHandler);
        newHandlers.add(excelWriteFileReturnValueHandler);
        assert returnValueHandlers != null;
        newHandlers.addAll(returnValueHandlers);
        requestMappingHandlerAdapter.setReturnValueHandlers(newHandlers);
    }

    /**
     * 追加 Excel 请求处理器 到 springmvc 中
     */
    @PostConstruct
    public void setRequestExcelArgumentResolver() {
        List<HandlerMethodArgumentResolver> argumentResolvers = requestMappingHandlerAdapter.getArgumentResolvers();
        List<HandlerMethodArgumentResolver> resolverList = new ArrayList<>();
        resolverList.add(new RequestExcelArgumentResolver());
        if (argumentResolvers != null) {
            resolverList.addAll(argumentResolvers);
        }
        requestMappingHandlerAdapter.setArgumentResolvers(resolverList);
    }


}
