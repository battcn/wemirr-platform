package com.wemirr.framework.robot.handler;


import cn.hutool.core.exceptions.ExceptionUtil;
import cn.hutool.extra.servlet.JakartaServletUtil;
import com.alibaba.fastjson2.JSON;
import jakarta.servlet.http.HttpServletRequest;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.Signature;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import java.time.Instant;
import java.util.Objects;

/**
 * 默认异常消息处理器
 * <p>
 *
 * @author Levin
 */
public class DefaultIErrorMessageHandler implements IErrorMessageHandler {

    /**
     * 换行
     */
    private final String LINE_BREAK = "\n";

    @Override
    public String message(JoinPoint joinPoint, Exception e) {
        StringBuilder error = new StringBuilder();
        error.append("Time: ").append(Instant.now());
        HttpServletRequest request = ((ServletRequestAttributes) Objects.requireNonNull(RequestContextHolder.getRequestAttributes())).getRequest();
        final String ip = JakartaServletUtil.getClientIP(request);
        error.append(LINE_BREAK).append("IP: ").append(ip);
        Signature signature = joinPoint.getSignature();
        error.append(LINE_BREAK).append("Method: ").append(signature.getDeclaringTypeName()).append(".").append(signature.getName());
        error.append(LINE_BREAK).append("Args: ").append(JSON.toJSONString(joinPoint.getArgs()));
        error.append(LINE_BREAK).append("Exception: ").append(ExceptionUtil.stacktraceToString(e));
        return error.toString();
    }

    @Override
    public String message(Exception e) {
        return "Time: " + Instant.now() +
                LINE_BREAK + "Exception: " + ExceptionUtil.stacktraceToString(e);
    }
}
