package com.wemirr.framework.boot.log.aspect;

import cn.hutool.core.date.DateUtil;
import cn.hutool.core.exceptions.ExceptionUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.core.util.URLUtil;
import cn.hutool.extra.servlet.JakartaServletUtil;
import cn.hutool.extra.spring.SpringUtil;
import com.wemirr.framework.boot.log.AccessLogInfo;
import com.wemirr.framework.boot.log.AccessLogProperties;
import com.wemirr.framework.boot.log.event.AccessLogEvent;
import com.wemirr.framework.boot.log.handler.AbstractLogHandler;
import com.wemirr.framework.commons.JacksonUtils;
import com.wemirr.framework.commons.NativeUserAgent;
import com.wemirr.framework.commons.RegionUtils;
import com.wemirr.framework.commons.annotation.log.AccessLog;
import com.wemirr.framework.commons.entity.Result;
import com.wemirr.framework.commons.exception.CheckedException;
import com.wemirr.framework.commons.security.AuthenticationContext;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.MDC;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.AnnotatedElementUtils;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.util.StringUtils;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.multipart.MultipartFile;

import java.lang.reflect.Method;
import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;
import java.time.Duration;
import java.time.Instant;
import java.util.*;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Collectors;

/**
 * 统一日志切面
 * <p>
 * 逻辑优先级：
 * 1. 带有 @AccessLog：执行审计入库 + (Debug模式下) 打印控制台调试日志。
 * 2. 只有 Controller/Swagger：不入库，只打印控制台调试日志。
 *
 * @author Levin & YanCh
 * @since 2025-12-11
 */
@Slf4j
@Aspect
public class AccessLogAspect {

    /**
     * 日志截断常量
     */
    private static final int MAX_LENGTH = 65535;
    private static final int MAX_RESPONSE_PREVIEW_LENGTH = 1000;
    private static final int MAX_REQUEST_URI_LENGTH = 500;

    @Resource
    private AuthenticationContext context;

    @Resource
    private AccessLogProperties accessLogProperties;

    /**
     * 允许为空，若未实现 LogHandler 则不进行持久化
     */
    @Autowired(required = false)
    private AbstractLogHandler abstractLogHandler;

    /**
     * 定义切点：
     * 1. 带有 @AccessLog 注解的方法
     * 2. 或者 com.wemirr 包下的 Controller 方法
     */
    @Pointcut("@annotation(com.wemirr.framework.commons.annotation.log.AccessLog) || execution(* com.wemirr..*controller..*Controller.*(..))")
    public void accessLogPointCut() {
    }

    @Around("accessLogPointCut()")
    public Object around(ProceedingJoinPoint joinPoint) throws Throwable {
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        Method method = signature.getMethod();
        // 1. 优先处理 @AccessLog (既要入库，也要看控制台)
        AccessLog accessLog = AnnotatedElementUtils.findMergedAnnotation(method, AccessLog.class);
        if (accessLog != null) {
            return handleAccessLog(joinPoint, accessLog);
        }
        // 2. 其次处理 Swagger / Controller 日志 (只看控制台，不入库)
        Operation operation = AnnotatedElementUtils.findMergedAnnotation(method, Operation.class);
        if (operation != null && !operation.hidden()) {
            // 传 null 表示没有自定义描述，需内部去解析 Swagger
            return handleDebugLog(joinPoint, null);
        }
        // 3. 既没有 AccessLog 也没有 Swagger Operation，直接放行
        return joinPoint.proceed();
    }

    /**
     * 处理 @AccessLog 逻辑 (持久化 + 可选的控制台打印)
     */
    private Object handleAccessLog(ProceedingJoinPoint joinPoint, AccessLog accessLog) throws Throwable {
        AccessLogInfo logInfo = new AccessLogInfo();
        HttpServletRequest request = getRequest();
        long startTime = System.currentTimeMillis();

        // 1. 预填充请求信息
        try {
            populateAccessLogDetails(logInfo, accessLog, joinPoint, request);
        } catch (Exception e) {
            log.warn("AccessLog build details failed", e);
        }

        Object ret = null;
        Throwable ex = null;
        try {
            // 2. 执行业务逻辑
            ret = joinPoint.proceed();

            // 3. 填充响应信息 (用于入库)
            if (ret instanceof Result<?> result && !result.isSuccessful()) {
                logInfo.setMessage(result.getMessage());
            }
            if (accessLog.response()) {
                logInfo.setResponse(truncate(JacksonUtils.toJson(ret), MAX_LENGTH));
            }
            return ret;
        } catch (Throwable e) {
            ex = e;
            // 4. 填充异常信息 (用于入库)
            logInfo.setMessage(e instanceof CheckedException ? e.getLocalizedMessage() : ExceptionUtil.stacktraceToString(e, MAX_LENGTH));
            logInfo.setResponse(e.getMessage());
            throw e;
        } finally {
            long executionTime = System.currentTimeMillis() - startTime;

            // 5. 控制台打印逻辑 (即使是 AccessLog，开启 Debug 也要打印)
            if (log.isDebugEnabled()) {
                // 使用 AccessLog 的 description 作为日志描述
                String description = accessLog.description();
                if (ex != null) {
                    log.error(formatDebugLog(joinPoint, description, ex, executionTime, request));
                } else {
                    log.debug(formatDebugLog(joinPoint, description, ret, executionTime, request));
                }
            }

            // 6. 异步发布事件/入库
            publishEvent(logInfo);
        }
    }

    /**
     * 处理纯 Debug Log 逻辑 (只打印控制台)
     */
    private Object handleDebugLog(ProceedingJoinPoint joinPoint, String description) throws Throwable {
        HttpServletRequest request = getRequest();
        if (request == null) {
            return joinPoint.proceed();
        }

        long startTime = System.currentTimeMillis();
        Object ret;
        try {
            ret = joinPoint.proceed();
        } catch (Throwable ex) {
            long executionTime = System.currentTimeMillis() - startTime;
            // 异常时打印 Error 级别日志
            log.error(formatDebugLog(joinPoint, description, ex, executionTime, request));
            throw ex;
        }
        long executionTime = System.currentTimeMillis() - startTime;

        // 成功时打印 Debug 级别日志
        if (log.isDebugEnabled()) {
            log.debug(formatDebugLog(joinPoint, description, ret, executionTime, request));
        }
        return ret;
    }


    private void populateAccessLogDetails(AccessLogInfo logInfo, AccessLog annotation, JoinPoint joinPoint, HttpServletRequest request) {
        if (context != null) {
            logInfo.setTenantId(context.tenantId());
            logInfo.setTenantCode(context.tenantCode());
            logInfo.setCreateBy(context.userId());
            logInfo.setCreateName(context.nickName());
        }
        logInfo.setTrace(MDC.get(accessLogProperties.getRequestId()));
        logInfo.setModule(annotation.module());
        logInfo.setDescription(annotation.description());
        logInfo.setAction(joinPoint.getTarget().getClass().getName() + "." + joinPoint.getSignature().getName());
        logInfo.setRequest(getSafeArgs(annotation.request(), joinPoint.getArgs(), request));
        logInfo.setToken(getRequestToken(request));

        if (request != null) {
            logInfo.setIp(JakartaServletUtil.getClientIP(request));
            logInfo.setLocation(RegionUtils.getRegion(logInfo.getIp()));
            logInfo.setUri(URLUtil.getPath(request.getRequestURI()));
            logInfo.setHttpMethod(request.getMethod());
            var userAgent = NativeUserAgent.parse(request.getHeader(HttpHeaders.USER_AGENT));
            logInfo.setEngine(userAgent.engine());
            logInfo.setOs(userAgent.os());
            logInfo.setPlatform(userAgent.platform());
            logInfo.setBrowser(userAgent.browser());
        }
        logInfo.setStartTime(Instant.now());
    }

    private void publishEvent(AccessLogInfo logInfo) {
        logInfo.setEndTime(Instant.now());
        if (logInfo.getStartTime() != null) {
            logInfo.setDuration(Duration.between(logInfo.getStartTime(), logInfo.getEndTime()).toMillis());
        }

        // 核心优化：如果没有注入 LogHandler (说明没配置存储策略)，直接返回，不发布事件
        if (abstractLogHandler == null) {
            return;
        }

        CompletableFuture.runAsync(() -> {
            try {
                abstractLogHandler.handler(logInfo);
                SpringUtil.publishEvent(new AccessLogEvent(logInfo));
            } catch (Exception e) {
                log.error("AccessLog event publish failed", e);
            }
        });
    }

    private String getSafeArgs(boolean recordRequest, Object[] args, HttpServletRequest request) {
        if (!recordRequest || (request != null && StrUtil.contains(request.getContentType(), MediaType.MULTIPART_FORM_DATA_VALUE))) {
            return "";
        }
        try {
            List<Object> arguments = Arrays.stream(args)
                    .filter(arg -> !(arg instanceof ServletRequest || arg instanceof ServletResponse || arg instanceof MultipartFile))
                    .collect(Collectors.toList());
            return JacksonUtils.toJson(arguments);
        } catch (Exception e) {
            return "Args serialization failed";
        }
    }

    private String getRequestToken(HttpServletRequest request) {
        if (request == null) {
            return null;
        }
        return Optional.ofNullable(request.getHeader(accessLogProperties.getToken()))
                .orElseGet(() -> Optional.ofNullable(request.getParameter(accessLogProperties.getToken()))
                        .orElse((String) request.getAttribute(accessLogProperties.getToken())));
    }

    // ===========================================================================================
    // ================================ DebugLog 核心辅助方法 =====================================
    // ===========================================================================================

    /**
     * 格式化控制台日志文本
     *
     * @param descStr 优先使用传入的描述（来自 AccessLog），如果没有则尝试去拿 Swagger
     */
    private String formatDebugLog(JoinPoint joinPoint, String descStr, Object ret, long executionTime, HttpServletRequest request) {
        String className = joinPoint.getTarget().getClass().getName();
        String methodName = joinPoint.getSignature().getName() + "()";

        // 解析描述：如果有传入(AccessLog)则用传入的，否则尝试找 Swagger
        String apiDesc = descStr;
        if (!StringUtils.hasText(apiDesc)) {
            Method method = ((MethodSignature) joinPoint.getSignature()).getMethod();
            Operation operation = AnnotatedElementUtils.findMergedAnnotation(method, Operation.class);
            apiDesc = resolveApiDescription(joinPoint, operation);
        }

        String methodParam = serializeDebugArguments(joinPoint);
        String requestParamPayload = buildReadableParamPayload(request);
        Map<String, Object> methodParamMap = transStringToMap(requestParamPayload);
        String requestUri = truncate(request.getRequestURI(), MAX_REQUEST_URI_LENGTH);
        String contentType = StringUtils.hasText(request.getContentType()) ? request.getContentType() : "FORM";
        String authorization = request.getHeader(HttpHeaders.AUTHORIZATION);
        String userAgentStr = request.getHeader(HttpHeaders.USER_AGENT);
        StringBuilder sb = new StringBuilder(512);
        sb.append("\n")
                .append("*********************************Request请求***************************************").append("\n")
                .append("ClassName     :  ").append(className).append("\n")
                .append("RequestMethod :  ").append(methodName).append("\n")
                .append("RequestParams :  ").append(methodParamMap.isEmpty() ? "" : methodParamMap).append("\n")
                .append("MethodParam   :  ").append(methodParam).append("\n")
                .append("ContentType   :  ").append(contentType).append("\n")
                .append("RequestType   :  ").append(request.getMethod()).append("\n")
                .append("RequestUri    :  ").append(requestUri).append("\n")
                .append("Description   :  ").append(apiDesc == null ? "" : apiDesc).append("\n")
                .append("ServerAddr    :  ").append(resolveServerAddr(request)).append("\n")
                .append("RemoteAddr    :  ").append(JakartaServletUtil.getClientIP(request)).append("\n")
                .append("UserAgent     :  ").append(userAgentStr).append("\n")
                .append("Header        :  ").append("{Authorization=").append(authorization).append("}\n")
                .append("ExecutionTime :  ").append(executionTime).append(" ms\n");

        if (ret != null) {
            sb.append("Response      :  ").append(buildResponsePreview(ret)).append("\n");
        }
        sb.append("**************************").append(DateUtil.now()).append("***********************************").append("\n");
        return sb.toString();
    }

    /**
     * 解析真实的请求入口地址 (适配 JDK 21+)
     * 优先级：Origin (跨域/浏览器端) > Referer (来源) > X-Forwarded-Host (代理) > Host (直连)
     */
    private String resolveServerAddr(HttpServletRequest request) {
        // 1. 优先获取 Origin (最准确反映前端域名)
        String origin = request.getHeader(HttpHeaders.ORIGIN);
        if (StringUtils.hasText(origin)) {
            return origin;
        }

        // 2. 其次获取 Referer (适配 JDK 21，使用 URI 替代 URL)
        String referer = request.getHeader(HttpHeaders.REFERER);
        if (StringUtils.hasText(referer)) {
            try {
                // JDK 20+ 推荐用法：先构建 URI
                java.net.URI uri = java.net.URI.create(referer);
                return uri.getScheme() + "://" + uri.getAuthority();
            } catch (Exception e) {
                // 解析失败则忽略
            }
        }
        // 3. 再次获取 X-Forwarded-Host (网关/Nginx 代理头)
        String forwardedHost = request.getHeader("X-Forwarded-Host");
        if (StringUtils.hasText(forwardedHost)) {
            String forwardedProto = request.getHeader("X-Forwarded-Proto");
            String scheme = StringUtils.hasText(forwardedProto) ? forwardedProto : request.getScheme();
            return scheme + "://" + forwardedHost;
        }

        // 4. 最后回退到当前服务接收到的 Host
        return request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort();
    }

    private String resolveApiDescription(JoinPoint joinPoint, Operation operation) {
        if (operation == null) {
            return "No Description";
        }
        Tag tag = joinPoint.getTarget().getClass().getAnnotation(Tag.class);
        return tag != null ? tag.name() + "-" + operation.summary() : operation.summary();
    }

    private String serializeDebugArguments(JoinPoint joinPoint) {
        List<Object> arguments = Arrays.stream(joinPoint.getArgs())
                .filter(arg -> !(arg instanceof ServletRequest || arg instanceof ServletResponse || arg instanceof MultipartFile))
                .collect(Collectors.toList());
        return truncate(safeJsonSerialize(arguments), MAX_LENGTH);
    }

    private String buildReadableParamPayload(HttpServletRequest request) {
        String queryString = request.getQueryString();
        if (StrUtil.isBlank(queryString)) {
            return "";
        }
        try {
            return URLDecoder.decode(queryString, StandardCharsets.UTF_8);
        } catch (Exception ex) {
            return queryString;
        }
    }

    private Map<String, Object> transStringToMap(String mapString) {
        if (!StringUtils.hasText(mapString)) {
            return Collections.emptyMap();
        }
        String[] pairs = mapString.split("&");
        Map<String, Object> result = new LinkedHashMap<>(pairs.length);
        for (String pair : pairs) {
            int idx = pair.indexOf('=');
            if (idx > 0) {
                result.put(pair.substring(0, idx), pair.substring(idx + 1));
            }
        }
        return result;
    }

    private String buildResponsePreview(Object ret) {
        if (ret instanceof Throwable t) {
            return truncate(t.getClass().getName() + ": " + t.getMessage(), MAX_RESPONSE_PREVIEW_LENGTH);
        }
        return truncate(safeJsonSerialize(ret), MAX_RESPONSE_PREVIEW_LENGTH);
    }

    private String safeJsonSerialize(Object obj) {
        if (obj == null) {
            return "null";
        }
        try {
            return JacksonUtils.toJson(obj);
        } catch (Exception e) {
            return "[Unserializable]";
        }
    }

    private HttpServletRequest getRequest() {
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        return attributes != null ? attributes.getRequest() : null;
    }

    private String truncate(String value, int maxLength) {
        if (!StringUtils.hasText(value) || value.length() <= maxLength) {
            return value == null ? "" : value;
        }
        return value.substring(0, maxLength) + "...";
    }
}