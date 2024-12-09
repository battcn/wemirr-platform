package com.wemirr.framework.log.diff.support.aop;

import jakarta.annotation.Nonnull;
import org.springframework.aop.Pointcut;
import org.springframework.aop.support.AbstractBeanFactoryPointcutAdvisor;


/**
 * @author mzt.
 */
public class BeanFactoryDiffLogAdvisor extends AbstractBeanFactoryPointcutAdvisor {

    private final DiffLogPointcut pointcut = new DiffLogPointcut();

    @Override
    @Nonnull
    public Pointcut getPointcut() {
        return pointcut;
    }

    public void setDiffLogOperationSource(DiffLogOperationSource diffLogOperationSource) {
        pointcut.setDiffLogOperationSource(diffLogOperationSource);
    }
}