package com.wemirr.framework.boot.log.handler;


import cn.hutool.core.date.DatePattern;
import cn.hutool.core.date.DateUtil;
import com.wemirr.framework.boot.log.AccessLogInfo;
import lombok.extern.slf4j.Slf4j;

import java.util.Optional;

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
        // 构建日志字符串
        StringBuilder sb = new StringBuilder();

        // 1. 头部
        sb.append("\n");
        sb.append("*********************************Request请求***************************************\n");

        // 2. 内容主体 (使用 String.format 或直接拼接以保持格式对齐)
        // 注意：AccessLogInfo 中缺少 ClassName 字段，这里假设 module 字段存的是类名，或者你需要扩展实体类
        sb.append(String.format("RequestUri     :  %s\n", info.getUri()));
        sb.append(String.format("HttpMethod     :  %s\n", info.getHttpMethod()));
        sb.append(String.format("Action         :  %s\n", Optional.ofNullable(info.getAction()).orElse("N/A")));
        sb.append(String.format("MethodRequest  :  %s\n", info.getRequest()));
        sb.append(String.format("MethodResponse :  %s\n", info.getResponse()));
        sb.append(String.format("Message        :  %s\n", info.getMessage()));
        // 实体类无此字段，通常固定或需新增字段
        sb.append(String.format("ContentType    :  %s\n", "application/json;charset=UTF-8"));
        sb.append(String.format("Description    :  %s\n", info.getDescription()));
        sb.append(String.format("RemoteAddr     :  %s\n", info.getIp()));
        sb.append(String.format("DeviceName     :  %s\n", info.getOs()));
        sb.append(String.format("BrowserName    :  %s\n", info.getBrowser()));
        sb.append(String.format("UserAgent      :  %s\n", info.getEngine()));
        sb.append(String.format("Authorization  :  %s\n", info.getToken()));
        sb.append(String.format("ExecutionTime  :  %s ms\n", info.getDuration()));
        String endTimeStr = info.getEndTime() != null
                ? DateUtil.format(java.util.Date.from(info.getEndTime()), DatePattern.NORM_DATETIME_PATTERN)
                : DateUtil.now();
        sb.append(String.format("**************************%s***********************************", endTimeStr));
        if (info.getStatus()) {
            log.info(sb.toString());
        } else {
            log.error(sb.toString());
        }
        return info;
    }
}