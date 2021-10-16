package com.wemirr.framework.database.mybatis.auth;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.core.Ordered;

/**
 * 服务间接口不鉴权处理逻辑
 *
 * @author Levin
 */
@Slf4j
@Aspect
@AllArgsConstructor
public class DataScopeAspect implements Ordered {


    @Around("@annotation(scope)")
    public Object around(ProceedingJoinPoint point, Scope scope) throws Throwable {
        final DataScope build = DataScope.builder().scopeType(scope.scopeType()).selfScopeName(scope.selfScopeName()).build();
        DataScopeEnvironment.set(build);
        Object object = point.proceed();
        DataScopeEnvironment.remove();
        return object;
    }

    @Override
    public int getOrder() {
        return Ordered.HIGHEST_PRECEDENCE + 1;
    }

}
