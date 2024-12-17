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
            log.info("""

                            ----------------------------全局日志开始----------------------------
                            \t租户ID - {} - 用户ID - {} - 用户名称 - {}
                            \t令牌 - {}\s
                            \t方法 - {} - 地址 - {}\s
                            \t参数 - {}\s
                            \t返回 - {}\s
                            \t异常 - {}\s
                            \t耗时 - {}\s
                            ----------------------------全局日志结束----------------------------""",
                    info.getTenantId(), info.getCreatedBy(), info.getCreatedName(),
                    info.getToken(),
                    info.getAction(), info.getUri(),
                    info.getRequest(),
                    info.getResponse(),
                    info.getMessage(),
                    info.getDuration());
        } else {
            log.error("""

                            ----------------------------全局日志开始----------------------------
                            \t租户ID - {} - 用户ID - {} - 用户名称 - {}
                            \t令牌 - {}\s
                            \t方法 - {} - 地址 - {}\s
                            \t参数 - {}\s
                            \t返回 - {}\s
                            \t异常 - {}\s
                            \t耗时 - {}\s
                            ----------------------------全局日志结束----------------------------""",
                    info.getTenantId(), info.getCreatedBy(), info.getCreatedName(),
                    info.getToken(),
                    info.getAction(), info.getUri(),
                    info.getRequest(),
                    info.getResponse(),
                    info.getMessage(),
                    info.getDuration());
        }
        return info;
    }

}