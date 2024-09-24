package com.wemirr.framework.boot.log.handler;


import com.wemirr.framework.boot.log.AccessLogInfo;
import lombok.extern.slf4j.Slf4j;

/**
 * 默认的日志处理
 *
 * @author 付为地, Levin
 */
@Slf4j
public class DefaultHandlerAbstract extends AbstractLogHandler {

    /**
     * 执行日志打印
     */
    @Override
    public AccessLogInfo handler(AccessLogInfo info) {
        if (info.getStatus()) {
            log.error("\n----------------------------全局日志开始----------------------------\n\t" +
                            "租户ID - {} - 用户ID - {} - 用户名称 - {}\n\t" +
                            "令牌 - {} \n\t" +
                            "方法 - {} - 地址 - {} \n\t" +
                            "参数 - {} \n\t" +
                            "返回 - {} \n\t" +
                            "异常 - {} \n\t" +
                            "耗时 - {} \n" +
                            "----------------------------全局日志结束----------------------------",
                    info.getTrace(),
                    info.getTenantId(), info.getCreatedBy(), info.getCreatedName(),
                    info.getToken(),
                    info.getAction(), info.getUri(),
                    info.getRequest(),
                    info.getResponse(),
                    info.getMessage(),
                    info.getConsumingTime());
        } else {
            log.info("\n----------------------------全局日志开始----------------------------\n\t" +
                            "租户ID - {} - 用户ID - {} - 用户名称 - {}\n\t" +
                            "令牌 - {} \n\t" +
                            "方法 - {} - 地址 - {} \n\t" +
                            "参数 - {} \n\t" +
                            "返回 - {} \n\t" +
                            "异常 - {} \n\t" +
                            "耗时 - {} \n" +
                            "----------------------------全局日志结束----------------------------",
                    info.getTrace(),
                    info.getTenantId(), info.getCreatedBy(), info.getCreatedName(),
                    info.getToken(),
                    info.getAction(), info.getUri(),
                    info.getRequest(),
                    info.getResponse(),
                    info.getMessage(),
                    info.getConsumingTime());
        }
        return info;
    }

}