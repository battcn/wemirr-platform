package com.wemirr.framework.robot.message.exception;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;

/**
 * 异常捕获切面处理类
 * <p>
 *
 * @author Levin
 */
@Slf4j
@Aspect
@RequiredArgsConstructor
public class RobotExceptionNotifyAspect {

    private final IRobotExceptionMessage sendException;

    /**
     * 切入点配置
     */
    @Pointcut("@within(org.springframework.web.bind.annotation.RestController)")
    public void pointcut() {
    }

    /**
     * 切点方法执行异常调用
     *
     * @param joinPoint {@link JoinPoint}
     * @param e         {@link Exception}
     */
    @AfterThrowing(value = "pointcut()", throwing = "e")
    public void doAfterThrowing(JoinPoint joinPoint, Exception e) {
        if (!sendException.notify(joinPoint, e)) {
            log.error("exception message sending failed", e);
        }
    }
}
