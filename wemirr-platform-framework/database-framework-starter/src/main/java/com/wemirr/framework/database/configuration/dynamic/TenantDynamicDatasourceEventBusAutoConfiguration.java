package com.wemirr.framework.database.configuration.dynamic;

import com.baomidou.dynamic.datasource.processor.DsHeaderProcessor;
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
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.cloud.bus.jackson.RemoteApplicationEventScan;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

/**
 * 如果要实现自定义 database 库名的话 也很容易
 *
 * @author levin
 */
@Slf4j
@Configuration
@ConditionalOnProperty(prefix = "extend.mybatis-plus.multi-tenant", name = "type", havingValue = "datasource")
@RemoteApplicationEventScan(basePackageClasses = DynamicDatasourceEvent.class)
public class TenantDynamicDatasourceEventBusAutoConfiguration {


    @Bean
    public TenantDynamicDataSourceProcess tenantDynamicDataSourceProcess() {
        return new TenantDynamicDataSourceProcess();
    }

    @Bean
    public ApplicationListener<DynamicDatasourceEvent> dynamicDatasourceEventListener(TenantDynamicDataSourceProcess process) {
        return new DynamicDatasourceEventListener(process);
    }

    @Bean
    @Primary
    public DsProcessor dsProcessor(DatabaseProperties properties) {
        // 重写 DsHeaderProcessor
        DsProcessor contentProcessor = new DsProcessor() {
            private static final String CUSTOM_PREFIX = "#custom";

            @Override
            public boolean matches(String key) {
                return key.startsWith(CUSTOM_PREFIX);
            }

            @Override
            public String doDetermineDatasource(MethodInvocation invocation, String key) {
                ServletRequestAttributes attributes = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes());
                DatabaseProperties.MultiTenant multiTenant = properties.getMultiTenant();
                if (attributes == null) {
                    log.debug("attributes为空,切换默认数据源 - {}", multiTenant.getDefaultDsName());
                    return multiTenant.getDefaultDsName();
                }
                HttpServletRequest request = attributes.getRequest();
                if (multiTenant.isUseTenantContent()) {
                    TenantEnvironment tenantEnvironment = SpringUtils.getBean(TenantEnvironment.class);
                    if (tenantEnvironment.anonymous()) {
                        log.debug("匿名用户,切换默认数据源 - {}", multiTenant.getDefaultDsName());
                        return multiTenant.getDefaultDsName();
                    }
                    String tenantCode = tenantEnvironment.tenantCode();
                    return getTenantDB(request, multiTenant, tenantCode);
                }
                String tenantCode = request.getHeader(key.substring(8));
                return getTenantDB(request, multiTenant, tenantCode);
            }
        };
        DsHeaderProcessor headerProcessor = new DsHeaderProcessor();
        DsSessionProcessor sessionProcessor = new DsSessionProcessor();
        DsSpelExpressionProcessor expressionProcessor = new DsSpelExpressionProcessor();
        contentProcessor.setNextProcessor(headerProcessor);
        headerProcessor.setNextProcessor(sessionProcessor);
        sessionProcessor.setNextProcessor(expressionProcessor);
        return contentProcessor;
    }

    private String getTenantDB(HttpServletRequest request, DatabaseProperties.MultiTenant multiTenant, String tenantCode) {
        if (StringUtils.isBlank(tenantCode) || StringUtils.equals(tenantCode, multiTenant.getSuperTenantCode())) {
            log.debug("tenantCode 为空或者为超级租户,切换默认数据源 - {}", multiTenant.getDefaultDsName());
            return multiTenant.getDefaultDsName();
        }
        String db = multiTenant.getDsPrefix() + tenantCode;
        log.debug("数据源切换至 - {} - {} - {}", tenantCode, db, request.getRequestURI());
        return db;
    }

}
