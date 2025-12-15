package com.wemirr.framework.log.diff.support.parse;

import org.springframework.aop.support.AopUtils;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.context.expression.AnnotatedElementKey;
import org.springframework.context.expression.BeanFactoryResolver;
import org.springframework.context.expression.CachedExpressionEvaluator;
import org.springframework.expression.EvaluationContext;
import org.springframework.expression.Expression;

import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * DATE 4:38 PM
 *
 * @author mzt.
 */
public class DiffLogExpressionEvaluator extends CachedExpressionEvaluator {

    private final Map<AnnotatedElementKey, Method> targetMethodCache = new ConcurrentHashMap<>(64);
    private final Map<ExpressionKey, Expression> expressionCache = new ConcurrentHashMap<>(64);

    public Object parseExpression(String conditionExpression, AnnotatedElementKey methodKey, EvaluationContext evalContext) {
        return getExpression(this.expressionCache, methodKey, conditionExpression).getValue(evalContext, Object.class);
    }

    /**
     * Create an {@link EvaluationContext}.
     *
     * @param method      the method
     * @param args        the method arguments
     * @param targetClass the target class
     * @param result      the return value (can be {@code null}) or
     * @param errorMsg    errorMsg
     * @param beanFactory Spring beanFactory
     * @return the evaluation context
     */
    public EvaluationContext createEvaluationContext(Method method, Object[] args, Class<?> targetClass,
                                                     Object result, String errorMsg, BeanFactory beanFactory) {
        Method targetMethod = getTargetMethod(targetClass, method);
        DiffLogEvaluationContext evaluationContext = new DiffLogEvaluationContext(
                null, targetMethod, args, getParameterNameDiscoverer(), result, errorMsg);
        registerBuiltInFunctions(evaluationContext);
        if (beanFactory != null) {
            evaluationContext.setBeanResolver(new BeanFactoryResolver(beanFactory));
        }
        return evaluationContext;
    }

    private void registerBuiltInFunctions(DiffLogEvaluationContext evaluationContext) {
        try {
            Method toBean = DiffLogSpelFunctions.class.getDeclaredMethod("toBean", Object.class, Object.class);
            Method toBeanByClass = DiffLogSpelFunctions.class.getDeclaredMethod("toBeanByClass", Object.class, Object.class, Class.class);
            Method toBeanByClassName = DiffLogSpelFunctions.class.getDeclaredMethod("toBeanByClassName", Object.class, Object.class, String.class);
            if (Modifier.isStatic(toBean.getModifiers())) {
                evaluationContext.registerFunction("toBean", toBean);
            }
            if (Modifier.isStatic(toBeanByClass.getModifiers())) {
                evaluationContext.registerFunction("toBeanByClass", toBeanByClass);
            }
            if (Modifier.isStatic(toBeanByClassName.getModifiers())) {
                evaluationContext.registerFunction("toBeanByClassName", toBeanByClassName);
            }
        } catch (NoSuchMethodException ignored) {
            // ignored
        }
    }

    private Method getTargetMethod(Class<?> targetClass, Method method) {
        AnnotatedElementKey methodKey = new AnnotatedElementKey(method, targetClass);
        return targetMethodCache.computeIfAbsent(methodKey, k -> AopUtils.getMostSpecificMethod(method, targetClass));
    }
}
