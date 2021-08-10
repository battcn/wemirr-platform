package com.wemirr.framework.boot.config.log;


import cn.hutool.core.convert.Convert;
import cn.hutool.core.exceptions.ExceptionUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.core.util.URLUtil;
import cn.hutool.extra.servlet.ServletUtil;
import cn.hutool.http.useragent.Browser;
import cn.hutool.http.useragent.UserAgent;
import cn.hutool.http.useragent.UserAgentUtil;
import com.alibaba.fastjson.JSONObject;
import com.wemirr.framework.boot.config.log.event.SysLogEvent;
import com.wemirr.framework.commons.annotation.SysLog;
import com.wemirr.framework.commons.entity.Result;
import com.wemirr.framework.database.TenantEnvironment;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.core.DefaultParameterNameDiscoverer;
import org.springframework.expression.EvaluationContext;
import org.springframework.expression.Expression;
import org.springframework.expression.spel.standard.SpelExpressionParser;
import org.springframework.expression.spel.support.StandardEvaluationContext;
import org.springframework.http.MediaType;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.Arrays;
import java.util.Objects;
import java.util.function.Consumer;

/**
 * 操作日志使用spring event异步入库
 *
 * @author Levin
 * @since 2019-07-01 15:15
 */
@Slf4j
@Aspect
public class SysLogAspect {

    private static final int MAX_LENGTH = 65535;
    private static final ThreadLocal<OptLogDTO> THREAD_LOCAL = new ThreadLocal<>();
    private static final String USER_AGENT = "User-Agent";

    private static final String JING_HAO = "#";
    /**
     * 事件发布是由ApplicationContext对象管控的，我们发布事件前需要注入ApplicationContext对象调用publishEvent方法完成事件发布
     **/
    @Autowired
    private ApplicationContext applicationContext;
    @Autowired(required = false)
    private TenantEnvironment tenantEnvironment;
    /**
     * 用于SpEL表达式解析.
     */
    private final SpelExpressionParser spelExpressionParser = new SpelExpressionParser();
    /**
     * 用于获取方法参数定义名字.
     */
    private final DefaultParameterNameDiscoverer nameDiscoverer = new DefaultParameterNameDiscoverer();

    /***
     * 定义controller切入点拦截规则：拦截标记SysLog注解和指定包下的方法
     *
     * execution(public * com.wemirr.base.controller.*.*(..)) 解释：
     * 第一个* 任意返回类型
     * 第三个* 类下的所有方法
     * ()中间的.. 任意参数
     *
     * annotation(com.wemirr.framework.commons.annotation.SysLog) 解释：
     * 标记了@SysLog 注解的方法
     */
    @Pointcut("execution(public * com.wemirr..*.*(..)) && @annotation(com.wemirr.framework.commons.annotation.SysLog)")
    public void sysLogAspect() {

    }

    private OptLogDTO get() {
        OptLogDTO sysLog = THREAD_LOCAL.get();
        if (sysLog == null) {
            return new OptLogDTO();
        }
        return sysLog;
    }

    private void tryCatch(Consumer<String> consumer) {
        try {
            consumer.accept("");
        } catch (Exception e) {
            log.warn("记录操作日志异常", e);
            THREAD_LOCAL.remove();
        }
    }

    /**
     * 返回通知
     *
     * @param ret ret
     */
    @AfterReturning(returning = "ret", pointcut = "sysLogAspect()")
    public void doAfterReturning(JoinPoint joinPoint, Object ret) {
        tryCatch((ex) -> {
            SysLog sysLogAnnotation = LogUtil.getTargetAnno(joinPoint);
            if (sysLogAnnotation == null) {
                return;
            }
            OptLogDTO sysLog = get();
            sysLog.setStartTime(LocalDateTime.now());
            if (ret instanceof Result) {
                Result<?> result = Convert.convert(Result.class, ret);
                if (result == null) {
                    sysLog.setType("OPT");
                    if (sysLogAnnotation.response()) {
                        sysLog.setResult(getText(String.valueOf(ret)));
                    }
                } else {
                    if (result.isSuccessful()) {
                        sysLog.setType("OPT");
                    } else {
                        sysLog.setType("EX");
                        sysLog.setExDesc(result.getMessage());
                    }
                    if (sysLogAnnotation.response()) {
                        sysLog.setResult(result.toString());
                    }
                }
            } else {
                sysLog.setType("OPT");
                if (sysLogAnnotation.response()) {
                    sysLog.setResult(getText(String.valueOf(ret == null ? "" : ret)));
                }
            }
            publishEvent(sysLog);
        });

    }

    private void publishEvent(OptLogDTO sysLog) {
        sysLog.setFinishTime(LocalDateTime.now());
        sysLog.setConsumingTime(sysLog.getStartTime().until(sysLog.getFinishTime(), ChronoUnit.MILLIS));
        applicationContext.publishEvent(new SysLogEvent(sysLog));
        THREAD_LOCAL.remove();
    }

    /**
     * 异常通知
     */
    @AfterThrowing(pointcut = "sysLogAspect()", throwing = "e")
    public void doAfterThrowable(JoinPoint joinPoint, Throwable e) {
        tryCatch((aaa) -> {
            SysLog sysLogAnnotation = LogUtil.getTargetAnno(joinPoint);
            if (sysLogAnnotation == null) {
                return;
            }
            OptLogDTO sysLog = get();
            sysLog.setType("EX");

            // 遇到错误时，请求参数若为空，则记录
            if (!sysLogAnnotation.request() && sysLogAnnotation.requestByError() && StrUtil.isEmpty(sysLog.getParams())) {
                Object[] args = joinPoint.getArgs();
                HttpServletRequest request = ((ServletRequestAttributes) Objects.requireNonNull(RequestContextHolder.getRequestAttributes())).getRequest();
                String strArgs = getArgs(sysLogAnnotation, args, request);
                sysLog.setParams(getText(strArgs));
            }
            // 异常对象
            sysLog.setExDetail(ExceptionUtil.stacktraceToString(e, MAX_LENGTH));
            sysLog.setExDesc(e.getMessage());
            publishEvent(sysLog);
        });
    }

    /**
     * 截取指定长度的字符串
     *
     * @param val val
     * @return String
     */
    private String getText(String val) {
        return StrUtil.sub(val, 0, 65535);
    }

    @Before(value = "sysLogAspect()")
    public void recordLog(JoinPoint joinPoint) {
        tryCatch((val) -> {
            SysLog sysLogAnnotation = LogUtil.getTargetAnno(joinPoint);
            if (sysLogAnnotation == null) {
                return;
            }
            OptLogDTO sysLog = get();
            sysLog.setCreatedBy(tenantEnvironment.userId());
            sysLog.setCreatedName(tenantEnvironment.realName());
            // String controllerDescription = joinPoint.getTarget().getClass().getSimpleName()
            String controllerMethodDescription = LogUtil.getDescribe(sysLogAnnotation);
            if (StrUtil.isNotEmpty(controllerMethodDescription) && StrUtil.contains(controllerMethodDescription, JING_HAO)) {
                //获取方法参数值
                Object[] args = joinPoint.getArgs();

                MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();
                controllerMethodDescription = getValBySpEL(controllerMethodDescription, methodSignature, args);
            }
            sysLog.setDescription(controllerMethodDescription);
            // 类名
            sysLog.setClassPath(joinPoint.getTarget().getClass().getName());
            //获取执行的方法名
            sysLog.setActionMethod(joinPoint.getSignature().getName());

            // 参数
            Object[] args = joinPoint.getArgs();

            HttpServletRequest request = ((ServletRequestAttributes) Objects.requireNonNull(RequestContextHolder.getRequestAttributes())).getRequest();
            String strArgs = getArgs(sysLogAnnotation, args, request);
            sysLog.setParams(getText(strArgs));

//            sysLog.setTrace(MDC.get(BaseContextConstants.LOG_TRACE_ID));

            sysLog.setIp(ServletUtil.getClientIP(request));
            sysLog.setRequestUri(URLUtil.getPath(request.getRequestURI()));
            sysLog.setHttpMethod(request.getMethod());
            final UserAgent userAgent = UserAgentUtil.parse(request.getHeader("user-agent"));
            sysLog.setEngine(userAgent.getEngine().getName());
            sysLog.setEngineVersion(userAgent.getEngineVersion());
            sysLog.setOs(userAgent.getOs().getName());
            sysLog.setPlatform(userAgent.getPlatform().getName());
            sysLog.setVersion(userAgent.getVersion());
            final Browser browser = userAgent.getBrowser();
            sysLog.setBrowser(browser.getName());
            String ua = request.getHeader(USER_AGENT);
            sysLog.setBrowserVersion(browser.getVersion(ua));

            //sysLog.setTenantCode(request.getHeader(BaseContextConstants.TENANT));
//            if (StrUtil.isEmpty(sysLog.getTrace())) {
//                sysLog.setTrace(request.getHeader(BaseContextConstants.TRACE_ID_HEADER));
//            }
            sysLog.setStartTime(LocalDateTime.now());

            THREAD_LOCAL.set(sysLog);
        });
    }

    private String getArgs(SysLog sysLogAnnotation, Object[] args, HttpServletRequest request) {
        String strArgs = "";
        if (sysLogAnnotation.request()) {
            try {
                if (!request.getContentType().contains(MediaType.MULTIPART_FORM_DATA_VALUE)) {
                    strArgs = JSONObject.toJSONString(args);
                }
            } catch (Exception e) {
                try {
                    strArgs = Arrays.toString(args);
                } catch (Exception ex) {
                    log.warn("解析参数异常", ex);
                }
            }
        }
        return strArgs;
    }

    /**
     * 解析spEL表达式
     */
    private String getValBySpEL(String spel, MethodSignature methodSignature, Object[] args) {
        try {
            //获取方法形参名数组
            String[] paramNames = nameDiscoverer.getParameterNames(methodSignature.getMethod());
            if (paramNames != null && paramNames.length > 0) {
                Expression expression = spelExpressionParser.parseExpression(spel);
                // spring的表达式上下文对象
                EvaluationContext context = new StandardEvaluationContext();
                // 给上下文赋值
                for (int i = 0; i < args.length; i++) {
                    context.setVariable(paramNames[i], args[i]);
                    context.setVariable("p" + i, args[i]);
                }
                return Objects.requireNonNull(expression.getValue(context)).toString();
            }
        } catch (Exception e) {
            log.warn("解析操作日志的el表达式出错", e);
        }
        return spel;
    }


}

