package com.wemirr.framework.log.diff.support.aop;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.map.MapUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.core.util.URLUtil;
import cn.hutool.extra.servlet.JakartaServletUtil;
import com.wemirr.framework.commons.NativeUserAgent;
import com.wemirr.framework.commons.RegionUtils;
import com.wemirr.framework.commons.security.AuthenticationContext;
import com.wemirr.framework.log.diff.IDiffItemsToLogContentService;
import com.wemirr.framework.log.diff.core.context.DiffLogContext;
import com.wemirr.framework.log.diff.domain.DiffLogInfo;
import com.wemirr.framework.log.diff.domain.DiffLogOps;
import com.wemirr.framework.log.diff.domain.FieldChange;
import com.wemirr.framework.log.diff.domain.MethodExecute;
import com.wemirr.framework.log.diff.service.IDiffLogPerformanceMonitor;
import com.wemirr.framework.log.diff.service.IDiffLogService;
import com.wemirr.framework.log.diff.service.IFunctionService;
import com.wemirr.framework.log.diff.service.impl.DiffParseFunction;
import com.wemirr.framework.log.diff.support.parse.DiffLogFunctionParser;
import com.wemirr.framework.log.diff.support.parse.DiffLogValueParser;
import jakarta.servlet.http.HttpServletRequest;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;
import org.springframework.aop.framework.AopProxyUtils;
import org.springframework.aop.support.AopUtils;
import org.springframework.beans.factory.SmartInitializingSingleton;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StopWatch;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import java.io.Serializable;
import java.lang.reflect.Method;
import java.time.Instant;
import java.util.*;

import static com.wemirr.framework.log.diff.service.IDiffLogPerformanceMonitor.*;
import static org.springframework.http.HttpHeaders.USER_AGENT;

/**
 * 拦截器
 *
 * @author mzt.
 */
@Slf4j
public class DiffLogInterceptor extends DiffLogValueParser implements MethodInterceptor, Serializable, SmartInitializingSingleton {

    @Setter
    private DiffLogOperationSource diffLogOperationSource;
    @Setter
    private String serviceName;
    private IDiffLogService diffLogService;
    private AuthenticationContext context;
    private IDiffItemsToLogContentService diffItemsToLogContentService;
    @Setter
    private IDiffLogPerformanceMonitor diffLogPerformanceMonitor;

    @Setter
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
            if (CollUtil.isNotEmpty(operations)) {
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
                               Collection<DiffLogOps> logOps) {
        for (DiffLogOps ops : logOps) {
            try {
                if (StrUtil.isEmpty(ops.getSuccessLogTemplate())
                        && StrUtil.isEmpty(ops.getFailLogTemplate())) {
                    continue;
                }
                if (exitsCondition(methodExecute, functionNameAndReturnMap, ops)) {
                    continue;
                }
                if (!methodExecute.isSuccess()) {
                    failRecordExecute(methodExecute, functionNameAndReturnMap, ops);
                } else {
                    successRecordExecute(methodExecute, functionNameAndReturnMap, ops);
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
                                      DiffLogOps ops) {
        // 若存在 successCondition 条件模版，解析出成功/失败的模版
        String action;
        boolean flag = true;
        if (!StrUtil.isEmpty(ops.getSuccessCondition())) {
            String condition = singleProcessTemplate(methodExecute, ops.getSuccessCondition(), functionNameAndReturnMap);
            if (StrUtil.equalsIgnoreCase(condition, "true")) {
                action = ops.getSuccessLogTemplate();
            } else {
                action = ops.getFailLogTemplate();
                flag = false;
            }
        } else {
            action = ops.getSuccessLogTemplate();
        }
        if (StrUtil.isEmpty(action)) {
            // 没有日志内容则忽略
            return;
        }
        List<String> spElTemplates = getSpElTemplates(ops, action);
        Map<String, String> expressionValues = processTemplate(spElTemplates, methodExecute, functionNameAndReturnMap);
        saveLog(methodExecute.getMethod(), !flag, ops, action, expressionValues);
    }

    private void failRecordExecute(MethodExecute methodExecute, Map<String, String> functionNameAndReturnMap,
                                   DiffLogOps ops) {
        if (StrUtil.isBlank(ops.getFailLogTemplate())) {
            return;
        }

        String action = ops.getFailLogTemplate();
        List<String> spElTemplates = getSpElTemplates(ops, action);
        Map<String, String> expressionValues = processTemplate(spElTemplates, methodExecute, functionNameAndReturnMap);
        saveLog(methodExecute.getMethod(), true, ops, action, expressionValues);
    }

    private boolean exitsCondition(MethodExecute methodExecute,
                                   Map<String, String> functionNameAndReturnMap, DiffLogOps ops) {
        if (StrUtil.isNotBlank(ops.getCondition())) {
            String condition = singleProcessTemplate(methodExecute, ops.getCondition(), functionNameAndReturnMap);
            return StrUtil.equalsIgnoreCase(condition, "false");
        }
        return false;
    }

    private void saveLog(Method method, boolean flag, DiffLogOps ops,
                         String description, Map<String, String> expressions) {
        if (StrUtil.isBlank(expressions.get(description))
                || (!diffLog && description.contains("#") && Objects.equals(description, expressions.get(description)))) {
            return;
        }
        var contextBuilder = MapUtil.<String, Object>builder()
                .put("class", method.getDeclaringClass().getName())
                .put("method", method.getName());
        RequestAttributes attributes = RequestContextHolder.getRequestAttributes();
        if (attributes instanceof ServletRequestAttributes requestAttributes) {
            HttpServletRequest request = requestAttributes.getRequest();
            final String ip = JakartaServletUtil.getClientIP(request);
            contextBuilder.put("ip", ip);
            contextBuilder.put("uri", URLUtil.getPath(request.getRequestURI()));
            contextBuilder.put("location", RegionUtils.getRegion(ip));
            var userAgent = NativeUserAgent.parse(request.getHeader(USER_AGENT));
            contextBuilder.put("engine", userAgent.engine());
            contextBuilder.put("os", userAgent.os());
            contextBuilder.put("platform", userAgent.platform());
            contextBuilder.put("browser", userAgent.browser());
        }
        // 从上下文获取新旧对象，计算字段变更列表
        Object oldObj = DiffLogContext.getVariable(DiffParseFunction.OLD_OBJECT);
        Object newObj = DiffLogContext.getVariable(DiffParseFunction.NEW_OBJECT);
        List<FieldChange> fieldChanges = diffItemsToLogContentService.toFieldChanges(oldObj, newObj);

        DiffLogInfo diffLogInfo = DiffLogInfo.builder()
                .serviceName(serviceName)
                .businessGroup(expressions.get(ops.getGroup()))
                .businessTag(expressions.get(ops.getTag()))
                .businessKey(expressions.get(ops.getBusinessKey()))
                .description(expressions.get(description))
                .status(flag)
                .tenantId(context.tenantId())
                .createBy(context.userId())
                .createName(context.nickName())
                .createTime(Instant.now())
                .extra(contextBuilder.build())
                .variables(fieldChanges)
                .build();
        diffLogService.handler(diffLogInfo);
    }

    private List<String> getSpElTemplates(DiffLogOps ops, String... actions) {
        List<String> template = new ArrayList<>();
        template.add(ops.getGroup());
        template.add(ops.getBusinessKey());
        template.add(ops.getTag());
        template.addAll(Arrays.asList(actions));
        return template;
    }

    private Class<?> getTargetClass(Object target) {
        return AopProxyUtils.ultimateTargetClass(Objects.requireNonNull(target, "target"));
    }


    public void setDiffLog(boolean diffLog) {
        this.diffLog = diffLog;
    }

    @Override
    public void afterSingletonsInstantiated() {
        diffLogService = beanFactory.getBean(IDiffLogService.class);
        context = beanFactory.getBean(AuthenticationContext.class);
        diffItemsToLogContentService = beanFactory.getBean(IDiffItemsToLogContentService.class);
        this.setLogFunctionParser(new DiffLogFunctionParser(beanFactory.getBean(IFunctionService.class)));
        this.setDiffParseFunction(beanFactory.getBean(DiffParseFunction.class));
    }
}
