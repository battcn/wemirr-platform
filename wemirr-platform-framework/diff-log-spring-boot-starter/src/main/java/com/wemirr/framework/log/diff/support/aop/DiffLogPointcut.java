package com.wemirr.framework.log.diff.support.aop;

import jakarta.annotation.Nonnull;
import org.springframework.aop.support.StaticMethodMatcherPointcut;
import org.springframework.util.CollectionUtils;

import java.io.Serializable;
import java.lang.reflect.Method;

/**
 * @author mzt.
 */
public class DiffLogPointcut extends StaticMethodMatcherPointcut implements Serializable {

    private DiffLogOperationSource diffLogOperationSource;

    @Override
    public boolean matches(@Nonnull Method method, @Nonnull Class<?> targetClass) {
        return !CollectionUtils.isEmpty(diffLogOperationSource.computeDiffLogOperations(method, targetClass));
    }

    public void setDiffLogOperationSource(DiffLogOperationSource diffLogOperationSource) {
        this.diffLogOperationSource = diffLogOperationSource;
    }
}