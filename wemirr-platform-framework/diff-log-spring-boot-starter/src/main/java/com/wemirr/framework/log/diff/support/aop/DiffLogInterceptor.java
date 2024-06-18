package com.wemirr.framework.log.diff.support.aop;

import cn.hutool.core.map.MapUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.core.util.URLUtil;
import cn.hutool.extra.servlet.JakartaServletUtil;
import cn.hutool.http.useragent.UserAgent;
import cn.hutool.http.useragent.UserAgentUtil;
import com.wemirr.framework.commons.security.AuthenticationContext;
import com.wemirr.framework.log.diff.core.context.DiffLogContext;
import com.wemirr.framework.log.diff.domain.DiffLogInfo;
import com.wemirr.framework.log.diff.domain.DiffLogOps;
import com.wemirr.framework.log.diff.domain.MethodExecute;
import com.wemirr.framework.log.diff.service.IDiffLogPerformanceMonitor;
import com.wemirr.framework.log.diff.service.IDiffLogService;
import com.wemirr.framework.log.diff.service.IFunctionService;
import com.wemirr.framework.log.diff.service.impl.DiffParseFunction;
import com.wemirr.framework.log.diff.support.parse.DiffLogFunctionParser;
import com.wemirr.framework.log.diff.support.parse.DiffLogValueParser;
import jakarta.servlet.http.HttpServletRequest;
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

    private DiffLogOperationSource diffLogOperationSource;
    private String serviceName;
    private IDiffLogService diffLogService;
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
        saveLog(methodExecute.getMethod(), !flag, operation, action, expressionValues);
    }

    private void failRecordExecute(MethodExecute methodExecute, Map<String, String> functionNameAndReturnMap,
                                   DiffLogOps operation) {
        if (StrUtil.isBlank(operation.getFailLogTemplate())) {
            return;
        }

        String action = operation.getFailLogTemplate();
        List<String> spElTemplates = getSpElTemplates(operation, action);
        Map<String, String> expressionValues = processTemplate(spElTemplates, methodExecute, functionNameAndReturnMap);
        saveLog(methodExecute.getMethod(), true, operation, action, expressionValues);
    }

    private boolean exitsCondition(MethodExecute methodExecute,
                                   Map<String, String> functionNameAndReturnMap, DiffLogOps operation) {
        if (StrUtil.isNotBlank(operation.getCondition())) {
            String condition = singleProcessTemplate(methodExecute, operation.getCondition(), functionNameAndReturnMap);
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
        var variables = MapUtil.builder()
                .put("class", method.getDeclaringClass())
                .put("method", method.getName())
                .put("method", method.getName());
        RequestAttributes attributes = RequestContextHolder.getRequestAttributes();
        if (attributes instanceof ServletRequestAttributes requestAttributes) {
            HttpServletRequest request = requestAttributes.getRequest();
            final String ip = JakartaServletUtil.getClientIP(request);
            variables.put("ip", ip);
            variables.put("uri", URLUtil.getPath(request.getRequestURI()));
//            log.setLocation(RegionUtils.getRegion(ip));
            final UserAgent userAgent = UserAgentUtil.parse(request.getHeader(USER_AGENT));
            variables.put("engine", userAgent.getEngine().getName());
            variables.put("os", userAgent.getOs().getName());
            variables.put("platform", userAgent.getPlatform().getName());
            variables.put("browser", userAgent.getBrowser().getName());
        }
        DiffLogInfo diffLogInfo = DiffLogInfo.builder()
                .serviceName(serviceName)
                .group(expressions.get(ops.getGroup()))
                .tag(expressions.get(ops.getTag()))
                .businessKey(expressions.get(ops.getBusinessKey()))
                .extra(expressions.get(ops.getExtra()))
                .description(expressions.get(description))
                .status(flag)
                .tenantId(context.tenantId())
                .createdBy(context.userId())
                .createdName(context.realName())
                .createTime(Instant.now())
                .variables(variables.build())
                .build();
        diffLogService.handler(diffLogInfo);
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

    public void setDiffLogService(IDiffLogService diffLogService) {
        this.diffLogService = diffLogService;
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
        diffLogService = beanFactory.getBean(IDiffLogService.class);
        context = beanFactory.getBean(AuthenticationContext.class);
        this.setLogFunctionParser(new DiffLogFunctionParser(beanFactory.getBean(IFunctionService.class)));
        this.setDiffParseFunction(beanFactory.getBean(DiffParseFunction.class));
    }
}
