package com.wemirr.framework.database.configuration.dynamic;

import com.baomidou.dynamic.datasource.processor.DsProcessor;
import com.baomidou.dynamic.datasource.processor.DsSessionProcessor;
import com.baomidou.dynamic.datasource.processor.DsSpelExpressionProcessor;
import com.wemirr.framework.database.configuration.dynamic.event.DynamicDatasourceEvent;
import com.wemirr.framework.database.configuration.dynamic.event.DynamicDatasourceEventListener;
import com.wemirr.framework.database.properties.DatabaseProperties;
import org.aopalliance.intercept.MethodInvocation;
import org.springframework.cloud.bus.jackson.RemoteApplicationEventScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.Primary;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.Objects;

/**
 * @author levin
 */
@Configuration
@RemoteApplicationEventScan(basePackageClasses = DynamicDatasourceEvent.class)
@Import({DynamicDataSourceProcess.class, DynamicDatasourceEventListener.class})
public class DynamicDatasourceEventBusAutoConfiguration {

    @Resource
    private DatabaseProperties properties;

    @Bean
    @Primary
    public DsProcessor dsProcessor() {
        // 重写 DsHeaderProcessor
        DsProcessor headerProcessor = new DsProcessor() {
            private static final String HEADER_PREFIX = "#header";

            @Override
            public boolean matches(String key) {
                return key.startsWith(HEADER_PREFIX);
            }

            @Override
            public String doDetermineDatasource(MethodInvocation invocation, String key) {
                HttpServletRequest request = ((ServletRequestAttributes) Objects.requireNonNull(RequestContextHolder.getRequestAttributes())).getRequest();
                return properties.getDatabasePrefix() + request.getHeader(key.substring(8));
            }
        };
        DsSessionProcessor sessionProcessor = new DsSessionProcessor();
        DsSpelExpressionProcessor expressionProcessor = new DsSpelExpressionProcessor();
        headerProcessor.setNextProcessor(sessionProcessor);
        sessionProcessor.setNextProcessor(expressionProcessor);
        return headerProcessor;
    }

}
