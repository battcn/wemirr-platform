package com.wemirr.framework.log.diff.support.aop;

import cn.hutool.core.util.StrUtil;
import com.wemirr.framework.commons.security.AuthenticationContext;
import com.wemirr.framework.log.diff.core.context.DiffLogContext;
import com.wemirr.framework.log.diff.domain.DiffLogInfo;
import com.wemirr.framework.log.diff.domain.DiffLogOps;
import com.wemirr.framework.log.diff.domain.MethodExecute;
import com.wemirr.framework.log.diff.domain.VariableType;
import com.wemirr.framework.log.diff.service.IDiffLogPerformanceMonitor;
import com.wemirr.framework.log.diff.service.IDiffLogService;
import com.wemirr.framework.log.diff.service.IFunctionService;
import com.wemirr.framework.log.diff.service.impl.DiffParseFunction;
import com.wemirr.framework.log.diff.support.parse.DiffLogFunctionParser;
import com.wemirr.framework.log.diff.support.parse.DiffLogValueParser;
import lombok.extern.slf4j.Slf4j;
import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;
import org.springframework.aop.framework.AopProxyUtils;
import org.springframework.aop.support.AopUtils;
import org.springframework.beans.factory.SmartInitializingSingleton;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StopWatch;

import java.io.Serializable;
import java.lang.reflect.Method;
import java.time.Instant;
import java.util.*;

import static com.wemirr.framework.log.diff.service.IDiffLogPerformanceMonitor.*;


/**
 * 拦截器
 *
 * @author mzt.
 */
@Slf4j
public class DiffLogInterceptor extends DiffLogValueParser implements MethodInterceptor, Serializable, SmartInitializingSingleton {

    private DiffLogOperationSource diffLogOperationSource;
    private String serviceName;
    private IDiffLogService bizLogService;
    private AuthenticationContext context;

    private IDiffLogPerformanceMonitor diffLogPerformanceMonitor;

    private boolean joinTransaction;

    @Override
    public Object invoke(MethodInvocation invocation) throws Throwable {
        Method method = invocation.getMethod();
        return execute(invocation, invocation.getThis(), method, invocation.getArguments());
    }

    private Object execute(MethodInvocation invoker, Object target, Method method, Object[] args) throws Throwable {
        //代理不拦截
        if (AopUtils.isAopProxy(target)) {
            return invoker.proceed();
        }
        StopWatch stopWatch = new StopWatch(MONITOR_NAME);
        stopWatch.start(MONITOR_TASK_BEFORE_EXECUTE);
        Class<?> targetClass = getTargetClass(target);
        Object ret = null;
        MethodExecute methodExecute = new MethodExecute(method, args, targetClass);
        DiffLogContext.putEmptySpan();
        Collection<DiffLogOps> operations = new ArrayList<>();
        Map<String, String> functionNameAndReturnMap = new HashMap<>();
        try {
            operations = diffLogOperationSource.computeDiffLogOperations(method, targetClass);
            List<String> spElTemplates = getBeforeExecuteFunctionTemplate(operations);
            functionNameAndReturnMap = processBeforeExecuteFunctionTemplate(spElTemplates, targetClass, method, args);
        } catch (Exception e) {
            log.error("log record parse before function exception", e);
        } finally {
            stopWatch.stop();
        }

        try {
            ret = invoker.proceed();
            methodExecute.setResult(ret);
            methodExecute.setSuccess(true);
        } catch (Exception e) {
            methodExecute.setSuccess(false);
            methodExecute.setThrowable(e);
            methodExecute.setErrorMsg(e.getMessage());
        }
        stopWatch.start(MONITOR_TASK_AFTER_EXECUTE);
        try {
            if (!CollectionUtils.isEmpty(operations)) {
                recordExecute(methodExecute, functionNameAndReturnMap, operations);
            }
        } catch (Exception t) {
            log.error("log record parse exception", t);
            throw t;
        } finally {
            DiffLogContext.clear();
            stopWatch.stop();
            try {
                diffLogPerformanceMonitor.print(stopWatch);
            } catch (Exception e) {
                log.error("execute exception", e);
            }
        }

        if (methodExecute.getThrowable() != null) {
            throw methodExecute.getThrowable();
        }
        return ret;
    }

    private List<String> getBeforeExecuteFunctionTemplate(Collection<DiffLogOps> operations) {
        List<String> spElTemplates = new ArrayList<>();
        for (DiffLogOps operation : operations) {
            //执行之前的函数，失败模版不解析
            List<String> templates = getSpElTemplates(operation, operation.getSuccessLogTemplate());
            if (!CollectionUtils.isEmpty(templates)) {
                spElTemplates.addAll(templates);
            }
        }
        return spElTemplates;
    }

    private void recordExecute(MethodExecute methodExecute, Map<String, String> functionNameAndReturnMap,
                               Collection<DiffLogOps> operations) {
        for (DiffLogOps operation : operations) {
            try {
                if (StrUtil.isEmpty(operation.getSuccessLogTemplate())
                        && StrUtil.isEmpty(operation.getFailLogTemplate())) {
                    continue;
                }
                if (exitsCondition(methodExecute, functionNameAndReturnMap, operation)) {
                    continue;
                }
                if (!methodExecute.isSuccess()) {
                    failRecordExecute(methodExecute, functionNameAndReturnMap, operation);
                } else {
                    successRecordExecute(methodExecute, functionNameAndReturnMap, operation);
                }
            } catch (Exception t) {
                log.error("log record execute exception", t);
                if (joinTransaction) {
                    throw t;
                }
            }
        }
    }

    private void successRecordExecute(MethodExecute methodExecute, Map<String, String> functionNameAndReturnMap,
                                      DiffLogOps operation) {
        // 若存在 isSuccess 条件模版，解析出成功/失败的模版
        String action = "";
        boolean flag = true;
        if (!StrUtil.isEmpty(operation.getIsSuccess())) {
            String condition = singleProcessTemplate(methodExecute, operation.getIsSuccess(), functionNameAndReturnMap);
            if (StrUtil.equalsIgnoreCase(condition, "true")) {
                action = operation.getSuccessLogTemplate();
            } else {
                action = operation.getFailLogTemplate();
                flag = false;
            }
        } else {
            action = operation.getSuccessLogTemplate();
        }
        if (StrUtil.isEmpty(action)) {
            // 没有日志内容则忽略
            return;
        }
        List<String> spElTemplates = getSpElTemplates(operation, action);
        Map<String, String> expressionValues = processTemplate(spElTemplates, methodExecute, functionNameAndReturnMap);
        saveLog(methodExecute.getMethod(), !flag, operation, context.userId(), action, expressionValues);
    }

    private void failRecordExecute(MethodExecute methodExecute, Map<String, String> functionNameAndReturnMap,
                                   DiffLogOps operation) {
        if (StrUtil.isBlank(operation.getFailLogTemplate())) {
            return;
        }

        String action = operation.getFailLogTemplate();
        List<String> spElTemplates = getSpElTemplates(operation, action);
        Map<String, String> expressionValues = processTemplate(spElTemplates, methodExecute, functionNameAndReturnMap);
        saveLog(methodExecute.getMethod(), true, operation, context.userId(), action, expressionValues);
    }

    private boolean exitsCondition(MethodExecute methodExecute,
                                   Map<String, String> functionNameAndReturnMap, DiffLogOps operation) {
        if (!StrUtil.isBlank(operation.getCondition())) {
            String condition = singleProcessTemplate(methodExecute, operation.getCondition(), functionNameAndReturnMap);
            if (StrUtil.equalsIgnoreCase(condition, "false")) {
                return true;
            }
        }
        return false;
    }

    private void saveLog(Method method, boolean flag, DiffLogOps ops, Object operatorId,
                         String description, Map<String, String> expressionValues) {
        if (StrUtil.isBlank(expressionValues.get(description)) ||
                (!diffLog && description.contains("#") && Objects.equals(description, expressionValues.get(description)))) {
            return;
        }
        DiffLogInfo diffLogInfo = DiffLogInfo.builder()
                .serviceName(serviceName)
                .group(expressionValues.get(ops.getGroup()))
                .tag(expressionValues.get(ops.getTag()))
                .businessKey(expressionValues.get(ops.getBusinessKey()))
                .extra(expressionValues.get(ops.getExtra()))
                .variables(getCodeVariable(method))
                .description(expressionValues.get(description))
                .status(flag)
                .createdBy(context.userId())
                .createdName(context.realName())
                .createTime(Instant.now())
                .build();
        bizLogService.handler(diffLogInfo);
    }

    private Map<VariableType, Object> getCodeVariable(Method method) {
        Map<VariableType, Object> map = new HashMap<>();
        map.put(VariableType.CLASS, method.getDeclaringClass());
        map.put(VariableType.METHOD, method.getName());
        return map;
    }

    private List<String> getSpElTemplates(DiffLogOps operation, String... actions) {
        List<String> template = new ArrayList<>();
        template.add(operation.getGroup());
        template.add(operation.getBusinessKey());
        template.add(operation.getTag());
        template.add(operation.getExtra());
        template.addAll(Arrays.asList(actions));
        return template;
    }

    private Class<?> getTargetClass(Object target) {
        return AopProxyUtils.ultimateTargetClass(target);
    }


    public void setDiffLogOperationSource(DiffLogOperationSource diffLogOperationSource) {
        this.diffLogOperationSource = diffLogOperationSource;
    }

    public void setServiceName(String serviceName) {
        this.serviceName = serviceName;
    }

    public void setDiffLogService(IDiffLogService bizLogService) {
        this.bizLogService = bizLogService;
    }

    public void setDiffLogPerformanceMonitor(IDiffLogPerformanceMonitor diffLogPerformanceMonitor) {
        this.diffLogPerformanceMonitor = diffLogPerformanceMonitor;
    }

    public void setJoinTransaction(boolean joinTransaction) {
        this.joinTransaction = joinTransaction;
    }

    public void setDiffLog(boolean diffLog) {
        this.diffLog = diffLog;
    }

    @Override
    public void afterSingletonsInstantiated() {
        bizLogService = beanFactory.getBean(IDiffLogService.class);
        context = beanFactory.getBean(AuthenticationContext.class);
        this.setLogFunctionParser(new DiffLogFunctionParser(beanFactory.getBean(IFunctionService.class)));
        this.setDiffParseFunction(beanFactory.getBean(DiffParseFunction.class));
    }
}
