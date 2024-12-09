package com.wemirr.framework.log.diff.support.parse;

import com.wemirr.framework.log.diff.core.context.DiffLogContext;
import org.springframework.context.expression.MethodBasedEvaluationContext;
import org.springframework.core.ParameterNameDiscoverer;

import java.lang.reflect.Method;
import java.util.Map;

/**
 * @author mzt.
 */
public class DiffLogEvaluationContext extends MethodBasedEvaluationContext {

    public DiffLogEvaluationContext(Object rootObject, Method method, Object[] arguments,
                                    ParameterNameDiscoverer parameterNameDiscoverer, Object ret, String errorMsg) {
        super(rootObject, method, arguments, parameterNameDiscoverer);
        Map<String, Object> variables = DiffLogContext.getVariables();
        Map<String, Object> globalVariable = DiffLogContext.getGlobalVariableMap();
        if (variables != null) {
            setVariables(variables);
        }
        if (globalVariable != null && !globalVariable.isEmpty()) {
            for (Map.Entry<String, Object> entry : globalVariable.entrySet()) {
                if (lookupVariable(entry.getKey()) == null) {
                    setVariable(entry.getKey(), entry.getValue());
                }
            }
        }
        setVariable("_ret", ret);
        setVariable("_errorMsg", errorMsg);
    }
}