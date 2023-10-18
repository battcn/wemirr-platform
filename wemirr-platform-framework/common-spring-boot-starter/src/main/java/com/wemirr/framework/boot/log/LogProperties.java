package com.wemirr.framework.boot.log;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @author Levin
 */
@Data
@ConfigurationProperties(LogProperties.PREFIX)
public class LogProperties {

    public static final String PREFIX = "extend.boot.log";

    private boolean enabled = true;

    /**
     * 日志存储策略
     * 如果不是 authority 服务 请配置 feign 的策略
     */
    private StoreStrategy strategy = StoreStrategy.local;

    public enum StoreStrategy {

        feign, local

    }


}
