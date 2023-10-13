package com.wemirr.framework.boot.log.event;

import com.wemirr.framework.boot.log.AccessLogInfo;
import org.springframework.context.ApplicationEvent;

/**
 * 系统日志事件
 *
 * @author Levin
 * @since 2019-07-01 15:13
 */
public class AccessLogEvent extends ApplicationEvent {

    public AccessLogEvent(AccessLogInfo source) {
        super(source);
    }
}
