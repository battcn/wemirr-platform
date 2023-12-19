
package com.wemirr.framework.robot.message.exception;

import org.aspectj.lang.JoinPoint;

/**
 * 异常通知接口
 *
 * @author Levin
 */
public interface IRobotExceptionMessage {

    /**
     * 发送异常内容
     *
     * @param joinPoint {@link JoinPoint}
     * @param e         {@link Exception}
     * @return true 发送成功  false 发送失败
     */
    boolean notify(JoinPoint joinPoint, Exception e);

    /**
     * 发送异常内容
     *
     * @param e {@link Exception}
     * @return true 发送成功  false 发送失败
     */
    boolean notify(Exception e);

    /**
     * 发送异常内容
     *
     * @param msg 异常消息
     * @return true 发送成功  false 发送失败
     */
    boolean notify(String msg);
}
