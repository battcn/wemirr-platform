package com.wemirr.framework.boot.remote.configuration;


import com.wemirr.framework.boot.remote.RemoteService;
import com.wemirr.framework.boot.remote.properties.RemoteProperties;
import com.wemirr.framework.commons.remote.LoadService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Map;

/**
 * 关联字段数据注入工具 自动配置类
 *
 * @author Levin
 */
@Slf4j
@Configuration
@AllArgsConstructor
@EnableConfigurationProperties(RemoteProperties.class)
public class RemoteAutoConfiguration {


    @Bean
    @ConditionalOnMissingBean
    @ConditionalOnProperty(prefix = RemoteProperties.PREFIX, name = "aop-enabled", havingValue = "true", matchIfMissing = true)
    public RemoteResultAspect getRemoteResultAspect(RemoteService remoteService) {
        return new RemoteResultAspect(remoteService);
    }

    /**
     * 回显服务
     *
     * @param strategyMap 回显查询实例
     * @return RemoteService
     */
    @Bean
    @ConditionalOnMissingBean
    public RemoteService getRemoteService(RemoteProperties remoteProperties, Map<String, LoadService> strategyMap) {
        return new RemoteService(remoteProperties, strategyMap);
    }
}

