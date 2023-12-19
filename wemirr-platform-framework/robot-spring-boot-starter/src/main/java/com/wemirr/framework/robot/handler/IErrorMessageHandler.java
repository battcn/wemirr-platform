package com.wemirr.framework.robot.handler;

import org.aspectj.lang.JoinPoint;

/**
 * 异常消息处理器
 * <p>
 *
 * @author Levin
 */
public interface IErrorMessageHandler {

    /**
     * 异常消息内容
     *
     * @param joinPoint {@link JoinPoint}
     * @param e         {@link Exception}
     * @return 异常消息内容
     */
    String message(JoinPoint joinPoint, Exception e);

    /**
     * 异常消息内容
     *
     * @param e {@link Exception}
     * @return 异常消息内容
     */
    String message(Exception e);
}
