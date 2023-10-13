package com.wemirr.framework.boot.log;

import com.wemirr.framework.commons.annotation.log.AccessLog;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.reflect.MethodSignature;

import java.lang.reflect.Method;

/**
 * 日志工具类
 *
 * @author Levin
 * @since 2019-04-28 11:30
 */
@Slf4j
public class AccessLogUtil {

    /***
     * 获取操作信息
     */
    public static String getDescription(JoinPoint point) {
        AccessLog annotation = getTargetAnnotation(point);
        if (annotation == null) {
            return "";
        }
        return annotation.description();
    }

    public static String getDescription(AccessLog annotation) {
        if (annotation == null) {
            return "";
        }
        return annotation.description();
    }

    /**
     * 优先从子类获取 @SysLog：
     * 1，若子类重写了该方法，有标记就记录日志，没标记就忽略日志
     * 2，若子类没有重写该方法，就从父类获取，父类有标记就记录日志，没标记就忽略日志
     */
    public static AccessLog getTargetAnnotation(JoinPoint point) {
        try {
            AccessLog annotation = null;
            if (point.getSignature() instanceof MethodSignature) {
                Method method = ((MethodSignature) point.getSignature()).getMethod();
                if (method != null) {
                    annotation = method.getAnnotation(AccessLog.class);
                }
            }
            return annotation;
        } catch (Exception e) {
            log.warn("获取 {}.{} 的 @SysLog 注解失败", point.getSignature().getDeclaringTypeName(), point.getSignature().getName(), e);
            return null;
        }
    }

}
