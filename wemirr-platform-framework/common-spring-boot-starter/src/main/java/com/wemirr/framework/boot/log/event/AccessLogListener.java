package com.wemirr.framework.boot.log.event;

import com.wemirr.framework.boot.log.AccessLogInfo;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.event.EventListener;
import org.springframework.core.annotation.Order;
import org.springframework.scheduling.annotation.Async;

import java.util.function.Consumer;


/**
 * 异步监听日志事件
 *
 * @author Levin
 */
@Slf4j
@AllArgsConstructor
public class AccessLogListener {

    private final Consumer<AccessLogInfo> consumer;

    @Async
    @Order
    @EventListener(AccessLogEvent.class)
    public void accessLogListener(AccessLogEvent event) {
        AccessLogInfo info = (AccessLogInfo) event.getSource();
        if (info == null) {
            log.warn("日志为空，忽略操作日志...");
            return;
        }
        consumer.accept(info);
    }

}
