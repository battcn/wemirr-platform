package com.wemirr.framework.boot.async;

import jakarta.annotation.Nonnull;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.task.TaskDecorator;
import org.springframework.scheduling.annotation.AsyncConfigurer;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import java.util.concurrent.Executor;

/**
 * 异步线程支持
 *
 * @author Levin
 */
@Configuration
@EnableAsync
@EnableConfigurationProperties(AsyncProperties.class)
@RequiredArgsConstructor
public class AsyncConfiguration implements AsyncConfigurer {

    private final AsyncProperties properties;

    @Override
    public Executor getAsyncExecutor() {
        // 具体可以自己写成 properties 的方式
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        executor.setCorePoolSize(properties.getCorePoolSize());
        executor.setMaxPoolSize(properties.getMaxPoolSize());
        executor.setKeepAliveSeconds(properties.getKeepAliveSeconds());
        executor.setQueueCapacity(properties.getQueueCapacity());
        executor.setThreadNamePrefix(properties.getThreadNamePrefix());
        executor.setTaskDecorator(new RequestAttributesTaskDecorator());
        executor.initialize();
        return executor;
    }

    /**
     * 异步线程池的时候 request 上下文复制
     */
    private static class RequestAttributesTaskDecorator implements TaskDecorator {

        @Override
        @Nonnull
        public Runnable decorate(@Nonnull Runnable runnable) {
            ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
            return () -> {
                try {
                    // 在异步方法执行前将 RequestAttributes 绑定到当前线程的 ThreadLocal 中
                    RequestContextHolder.setRequestAttributes(attributes);
                    runnable.run();
                } finally {
                    // 在异步方法执行后清除当前线程的 ThreadLocal
                    RequestContextHolder.resetRequestAttributes();
                }
            };
        }
    }
}