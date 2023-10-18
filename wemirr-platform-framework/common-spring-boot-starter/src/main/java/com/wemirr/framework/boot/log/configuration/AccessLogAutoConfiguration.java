package com.wemirr.framework.boot.log.configuration;

import com.wemirr.framework.boot.log.LogProperties;
import com.wemirr.framework.boot.log.event.AccessLogListener;
import com.wemirr.framework.boot.log.feign.AccessLogFeign;
import org.springframework.boot.autoconfigure.condition.ConditionalOnExpression;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.scheduling.annotation.EnableAsync;

/**
 * 日志自动配置
 * <p>
 * 启动条件：
 * 1，存在web环境
 *
 * @author Levin
 */
@EnableAsync
@Configuration
@EnableConfigurationProperties(LogProperties.class)
@ConditionalOnProperty(prefix = LogProperties.PREFIX, name = "enabled", havingValue = "true", matchIfMissing = true)
public class AccessLogAutoConfiguration {

    @Bean
    @ConditionalOnMissingBean
    public AccessLogAspect accessLogAspect() {
        return new AccessLogAspect();
    }


    @Bean
    @Order
    @ConditionalOnExpression("'${extend.boot.log.strategy}'.equalsIgnoreCase('feign')")
    public AccessLogListener accessLogListener(AccessLogFeign feign) {
        return new AccessLogListener(feign::listener);
    }

}
