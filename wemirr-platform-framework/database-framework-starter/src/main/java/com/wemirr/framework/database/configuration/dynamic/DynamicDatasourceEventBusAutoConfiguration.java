package com.wemirr.framework.database.configuration.dynamic;

import com.baomidou.dynamic.datasource.processor.DsProcessor;
import com.baomidou.dynamic.datasource.processor.DsSessionProcessor;
import com.baomidou.dynamic.datasource.processor.DsSpelExpressionProcessor;
import com.wemirr.framework.commons.StringUtils;
import com.wemirr.framework.database.SpringUtils;
import com.wemirr.framework.database.TenantEnvironment;
import com.wemirr.framework.database.configuration.dynamic.event.DynamicDatasourceEvent;
import com.wemirr.framework.database.configuration.dynamic.event.DynamicDatasourceEventListener;
import com.wemirr.framework.database.properties.DatabaseProperties;
import lombok.extern.slf4j.Slf4j;
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

/**
 * 如果要实现自定义 database 库名的话 也很容易
 *
 * @author levin
 */
@Slf4j
@Configuration
@RemoteApplicationEventScan(basePackageClasses = DynamicDatasourceEvent.class)
@Import({DynamicDataSourceProcess.class, DynamicDatasourceEventListener.class})
public class DynamicDatasourceEventBusAutoConfiguration {

    @Resource
    private DatabaseProperties properties;

    @Bean
    @Primary
    public DsProcessor dsProcessor() {
        // 从登录的上下文读取
        DsProcessor contentProcessor = new DsProcessor() {
            private static final String HEADER_PREFIX = "#content";

            @Override
            public boolean matches(String key) {
                return key.startsWith(HEADER_PREFIX);
            }

            @Override
            public String doDetermineDatasource(MethodInvocation invocation, String key) {
                DatabaseProperties.MultiTenant multiTenant = properties.getMultiTenant();
                TenantEnvironment tenantEnvironment = SpringUtils.getBean(TenantEnvironment.class);
                String tenantCode = tenantEnvironment.realName();
                if (StringUtils.isBlank(tenantCode) || StringUtils.equals(tenantCode, multiTenant.getSuperTenantCode())) {
                    return multiTenant.getDefaultDsName();
                }
                return multiTenant.getDsPrefix() + tenantCode;
            }
        };
        // 重写 DsHeaderProcessor
        DsProcessor headerProcessor = new DsProcessor() {
            private static final String HEADER_PREFIX = "#header";

            @Override
            public boolean matches(String key) {
                return key.startsWith(HEADER_PREFIX);
            }

            @Override
            public String doDetermineDatasource(MethodInvocation invocation, String key) {
                ServletRequestAttributes attributes = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes());
                DatabaseProperties.MultiTenant multiTenant = properties.getMultiTenant();
                if (attributes == null) {
                    log.debug("attributes为空,数据源切换至 - {}", multiTenant.getDefaultDsName());
                    return multiTenant.getDefaultDsName();
                }
                HttpServletRequest request = attributes.getRequest();
                String tenantCode = request.getHeader(key.substring(8));
                log.debug("tenantCode - {}", tenantCode);
                if (StringUtils.isBlank(tenantCode) || StringUtils.equals(tenantCode, multiTenant.getSuperTenantCode())) {
                    log.debug("tenantCode 为空,数据源切换至 - {}", multiTenant.getDefaultDsName());
                    return multiTenant.getDefaultDsName();
                }
                String db = multiTenant.getDsPrefix() + tenantCode;
                log.debug("数据源切换至 - {}", db);
                return db;
            }
        };
        DsSessionProcessor sessionProcessor = new DsSessionProcessor();
        DsSpelExpressionProcessor expressionProcessor = new DsSpelExpressionProcessor();
        contentProcessor.setNextProcessor(headerProcessor);
        headerProcessor.setNextProcessor(sessionProcessor);
        sessionProcessor.setNextProcessor(expressionProcessor);
        return headerProcessor;
    }

}
