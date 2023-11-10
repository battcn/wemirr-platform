package com.wemirr.framework.db.mybatisplus.intercept.data;

import com.wemirr.framework.commons.security.AuthenticationContext;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;

/**
 * @author Levin
 */
@Slf4j
@Aspect
@RequiredArgsConstructor
public class DataScopeAnnotationAspect {

    private final DataScopeService dataScopeService;
    private final AuthenticationContext context;

    @Pointcut("execution(public * com.wemirr..*.*(..)) && @annotation(com.wemirr.framework.db.mybatisplus.intercept.data.DataScope)")
    public void dataScopePointcut() {

    }

    /**
     * 异常通知
     *
     * @param point point
     * @param e     e
     */
    @AfterThrowing(pointcut = "dataScopePointcut()", throwing = "e")
    public void doAfterThrowable(JoinPoint point, Throwable e) {
        e.printStackTrace();
        DataPermissionContextHolder.remove();
    }

    /**
     * AOP 前置增强
     */
    @Before(value = "dataScopePointcut()")
    public void before() {
        final DataPermission permission = DataPermissionContextHolder.get();
        if (permission != null) {
            return;
        }
        final DataPermission remotePermission = dataScopeService.getDataScopeById(context.userId());
        log.debug("远程获取 data permission - {}", remotePermission);
        DataPermissionContextHolder.set(remotePermission);
    }

    /**
     * 返回通知
     *
     * @param ret ret
     */
    @AfterReturning(returning = "ret", pointcut = "dataScopePointcut()")
    public void doAfterReturning(JoinPoint joinPoint, Object ret) {
        DataPermissionContextHolder.remove();
    }

}
