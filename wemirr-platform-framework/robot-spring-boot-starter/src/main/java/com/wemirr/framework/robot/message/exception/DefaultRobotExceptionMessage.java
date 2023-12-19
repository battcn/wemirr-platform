
package com.wemirr.framework.robot.message.exception;

import com.wemirr.framework.robot.handler.IErrorMessageHandler;
import com.wemirr.framework.robot.message.push.RobotMessageHandler;
import lombok.AllArgsConstructor;
import org.aspectj.lang.JoinPoint;

import java.util.List;

/**
 * 机器人发送异常消息
 * <p>
 *
 * @author Levin
 */
@AllArgsConstructor
public class DefaultRobotExceptionMessage implements IRobotExceptionMessage {

    /**
     * 允许多端发送
     */
    private List<RobotMessageHandler> robotMessageTemplateList;
    private IErrorMessageHandler IErrorMessageHandler;

    @Override
    public boolean notify(JoinPoint joinPoint, Exception e) {
        try {
            String message = IErrorMessageHandler.message(joinPoint, e);
            for (RobotMessageHandler robot : robotMessageTemplateList) {
                robot.notify(message);
            }
            return true;
        } catch (Throwable t) {
            /**
             * 捕获可能异常，切面记录日志
             */
            return false;
        }
    }

    @Override
    public boolean notify(Exception e) {
        try {
            String message = IErrorMessageHandler.message(e);
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
        } catch (Throwable t) {
            return false;
        }
    }
}
