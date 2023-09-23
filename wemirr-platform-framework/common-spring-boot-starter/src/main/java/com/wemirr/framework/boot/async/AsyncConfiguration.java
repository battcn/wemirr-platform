package com.wemirr.framework.boot.async;

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
public class AsyncConfiguration implements AsyncConfigurer {

    @Override
    public Executor getAsyncExecutor() {
        // 具体可以自己写成 properties 的方式
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        executor.setCorePoolSize(10);
        executor.setMaxPoolSize(20);
        executor.setQueueCapacity(100);
        executor.setThreadNamePrefix("wemirr-async-thread-");
        executor.setTaskDecorator(new RequestAttributesTaskDecorator());
        executor.initialize();
        return executor;
    }

    private static class RequestAttributesTaskDecorator implements TaskDecorator {

        @Override
        public Runnable decorate(Runnable runnable) {
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