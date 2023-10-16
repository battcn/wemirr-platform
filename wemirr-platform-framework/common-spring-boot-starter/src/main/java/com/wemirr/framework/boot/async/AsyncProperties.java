package com.wemirr.framework.boot.async;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @author Levin
 */
@Data
@ConfigurationProperties(AsyncProperties.PREFIX)
public class AsyncProperties {

    public static final String PREFIX = "extend.boot.async";

    /**
     * 核心线程数 并非越大越好
     * 默认 10 个
     */
    private int corePoolSize = 10;

    /**
     * 最大线程数 并非越大越好，当线程数过大的时候上下文切换也会耗费时间
     * 默认 50 个
     */
    private int maxPoolSize = 50;

    /**
     * 设置 ThreadPoolExecutor 的心跳时间
     */
    private int keepAliveSeconds = 60;

    /**
     * 为了防止线程溢出，设置队列长度
     * 默认最大 10000 个任务
     */
    private int queueCapacity = 10000;

    private String threadNamePrefix = "wemirr-async-thread-";


}
