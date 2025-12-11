package com.wemirr.framework.boot.log.handler;


import com.wemirr.framework.boot.log.AccessLogInfo;
import lombok.extern.slf4j.Slf4j;

/**
 * 默认的日志处理
 *
 * @author Levin
 */
@Slf4j
public class DefaultHandlerAbstract extends AbstractLogHandler {

    /**
     * 执行日志打印
     */
    @Override
    public AccessLogInfo handler(AccessLogInfo info) {
        return info;
    }
}