package com.wemirr.framework.job.configuration;

import com.wemirr.framework.job.XxlJobProperties;
import com.xxl.job.core.executor.impl.XxlJobSpringExecutor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.core.annotation.Order;

/**
 * @author Levin
 */
@Slf4j
@Order(9999)
@EnableConfigurationProperties(XxlJobProperties.class)

public class XxlJobAutoConfiguration {

    @Bean
    public XxlJobSpringExecutor xxlJobExecutor(XxlJobProperties properties) {
        log.info(">>>>>>>>>>> xxl-job config init.");
        XxlJobSpringExecutor xxlJobSpringExecutor = new XxlJobSpringExecutor();
        xxlJobSpringExecutor.setAdminAddresses(properties.getAdminAddresses());
        xxlJobSpringExecutor.setAccessToken(properties.getAccessToken());
        final XxlJobProperties.Executor executor = properties.getExecutor();
        xxlJobSpringExecutor.setAppname(executor.getAppName());
        if (executor.getIp() != null) {
            xxlJobSpringExecutor.setIp(executor.getIp());
        }
        if (executor.getIp() != null) {
            xxlJobSpringExecutor.setPort(executor.getPort());
        }
        xxlJobSpringExecutor.setLogPath(executor.getLogPath());
        xxlJobSpringExecutor.setLogRetentionDays(executor.getLogRetentionDays());
        return xxlJobSpringExecutor;
    }
}
