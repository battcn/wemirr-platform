package com.wemirr.framework.log.diff.support.aop;

import com.wemirr.framework.log.diff.core.annotation.DiffLog;
import com.wemirr.framework.log.diff.core.annotation.DiffLogs;
import com.wemirr.framework.log.diff.domain.DiffLogOps;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.BridgeMethodResolver;
import org.springframework.core.annotation.AnnotatedElementUtils;
import org.springframework.util.ClassUtils;
import org.springframework.util.ConcurrentReferenceHashMap;
import org.springframework.util.StringUtils;

import java.lang.reflect.AnnotatedElement;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.*;

/**
 * @author mzt.
 */
@Slf4j
public class DiffLogOperationSource {
    /**
     * Cache for equivalent methods on an interface implemented by the declaring class.
     */
    private static final Map<Method, Method> INTERFACE_METHOD_CACHE = new ConcurrentReferenceHashMap<>(256);

    /**
     * Determine a corresponding interface method for the given method handle, if possible.
     * <p>This is particularly useful for arriving at a public exported type on Jigsaw
     * which can be reflectively invoked without an illegal access warning.
     *
     * @param method the method to be invoked, potentially from an implementation class
     * @return the corresponding interface method, or the original method if none found
     */
    public static Method getInterfaceMethodIfPossible(Method method) {
        if (!Modifier.isPublic(method.getModifiers()) || method.getDeclaringClass().isInterface()) {
            return method;
        }
        return INTERFACE_METHOD_CACHE.computeIfAbsent(method, key -> {
            Class<?> current = key.getDeclaringClass();
            while (current != null && current != Object.class) {
                Class<?>[] interfaces = current.getInterfaces();
                for (Class<?> ifc : interfaces) {
                    try {
                        return ifc.getMethod(key.getName(), key.getParameterTypes());
                    } catch (NoSuchMethodException ex) {
                        log.trace("告警原因 - {}", ex.getLocalizedMessage());
                    }
                }
                current = current.getSuperclass();
            }
            return key;
        });
    }

    public Collection<DiffLogOps> computeDiffLogOperations(Method method, Class<?> targetClass) {
        // Don't allow no-public methods as required.
        if (!Modifier.isPublic(method.getModifiers())) {
            return Collections.emptyList();
        }

        // The method may be on an interface, but we need attributes from the target class.
        // If the target class is null, the method will be unchanged.
        Method specificMethod = ClassUtils.getMostSpecificMethod(method, targetClass);
        // If we are dealing with method with generic parameters, find the original method.
        specificMethod = BridgeMethodResolver.findBridgedMethod(specificMethod);

        // First try is the method in the target class.
        Collection<DiffLogOps> diffLogOps = parseDiffLogAnnotations(specificMethod);
        Collection<DiffLogOps> diffLogsOps = parseDiffLogsAnnotations(specificMethod);
        Collection<DiffLogOps> abstractDiffLogOps = parseDiffLogAnnotations(getInterfaceMethodIfPossible(method));
        Collection<DiffLogOps> abstractDiffLogsOps = parseDiffLogsAnnotations(getInterfaceMethodIfPossible(method));
        HashSet<DiffLogOps> result = new HashSet<>();
        result.addAll(diffLogOps);
        result.addAll(abstractDiffLogOps);
        result.addAll(diffLogsOps);
        result.addAll(abstractDiffLogsOps);
        return result;
    }

    private Collection<DiffLogOps> parseDiffLogsAnnotations(AnnotatedElement ae) {
        Collection<DiffLogOps> res = new ArrayList<>();
        Collection<DiffLogs> diffLogAnnotationAnnotations = AnnotatedElementUtils.findAllMergedAnnotations(ae, DiffLogs.class);
        if (diffLogAnnotationAnnotations.isEmpty()) {
            return res;
        }
        diffLogAnnotationAnnotations.forEach(difLogs -> {
            DiffLog[] value = difLogs.value();
            for (DiffLog diffLog : value) {
                res.add(parseDiffLogAnnotation(ae, diffLog));
            }
        });
        return res;
    }

    private Collection<DiffLogOps> parseDiffLogAnnotations(AnnotatedElement ae) {
        Collection<DiffLog> diffLogs = AnnotatedElementUtils.findAllMergedAnnotations(ae, DiffLog.class);
        Collection<DiffLogOps> ret = new ArrayList<>();
        if (diffLogs.isEmpty()) {
            return ret;
        }
        for (DiffLog diffLog : diffLogs) {
            ret.add(parseDiffLogAnnotation(ae, diffLog));
        }
        return ret;
    }

    private DiffLogOps parseDiffLogAnnotation(AnnotatedElement ae, DiffLog anno) {
        DiffLogOps ops = DiffLogOps.builder()
                .successLogTemplate(anno.success())
                .failLogTemplate(anno.fail())
                .group(anno.group())
                .businessKey(anno.businessKey())
                .tag(anno.tag())
                .condition(anno.condition())
                .successCondition(anno.successCondition())
                .build();
        validateDiffLogOperation(ae, ops);
        return ops;
    }


    private void validateDiffLogOperation(AnnotatedElement ae, DiffLogOps recordOps) {
        if (!StringUtils.hasText(recordOps.getSuccessLogTemplate()) && !StringUtils.hasText(recordOps.getFailLogTemplate())) {
            throw new IllegalStateException("Invalid diff log annotation configuration on '" +
                    ae.toString() + "'. 'one of successTemplate and failLogTemplate' attribute must be set.");
        }
    }

}
