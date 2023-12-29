
package com.wemirr.framework.robot.message.exception;

import com.wemirr.framework.robot.handler.IErrorMessageHandler;
import com.wemirr.framework.robot.message.push.RobotMessageHandler;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;

import java.util.List;

/**
 * 机器人发送异常消息
 * <p>
 *
 * @author Levin
 */
@Slf4j
@AllArgsConstructor
public class DefaultRobotExceptionMessage implements IRobotExceptionMessage {

    /**
     * 允许多端发送
     */
    private List<RobotMessageHandler> robotMessageTemplateList;
    private IErrorMessageHandler iErrorMessageHandler;

    @Override
    public boolean notify(JoinPoint joinPoint, Exception e) {
        try {
            String message = iErrorMessageHandler.message(joinPoint, e);
            for (RobotMessageHandler robot : robotMessageTemplateList) {
                robot.notify(message);
            }
            return true;
        } catch (Throwable tx) {
            log.error("通知异常", tx);
            return false;
        }
    }

    @Override
    public boolean notify(Exception e) {
        try {
            String message = iErrorMessageHandler.message(e);
            for (RobotMessageHandler robot : robotMessageTemplateList) {
                robot.notify(message);
            }
            return true;
        } catch (Throwable t) {
            return false;
        }
    }

    @Override
    public boolean notify(String msg) {
        try {
            for (RobotMessageHandler robot : robotMessageTemplateList) {
                robot.notify(msg);
            }
            return true;
        } catch (Throwable tx) {
            log.error("通知异常", tx);
            return false;
        }
    }
}
