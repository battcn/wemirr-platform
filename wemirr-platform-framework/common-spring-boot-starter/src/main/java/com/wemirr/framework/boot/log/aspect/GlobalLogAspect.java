package com.wemirr.framework.boot.log.aspect;

import cn.hutool.core.date.DateUtil;
import cn.hutool.extra.servlet.JakartaServletUtil;
import cn.hutool.http.useragent.UserAgent;
import cn.hutool.http.useragent.UserAgentUtil;
import com.wemirr.framework.commons.JacksonUtils;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.http.HttpHeaders;
import org.springframework.util.StringUtils;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.multipart.MultipartFile;

import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;
import java.util.*;
import java.util.stream.Collectors;

/**
 *
 * @author YanCh
 * @since 2025-12-05 15:56
 **/
@Slf4j
@Aspect
public class GlobalLogAspect {

    /**
     * 日志输出控制常量, 全局约束字段长度与性能阈值
     */
    private static final int MAX_RESPONSE_PREVIEW_LENGTH = 500;
    private static final int MAX_ARGUMENT_PREVIEW_LENGTH = 1000;
    private static final int MAX_REQUEST_URI_LENGTH = 200;
    private static final int MAX_PARAM_VALUE_LENGTH = 200;

    @Pointcut("execution(* com.wemirr..*controller..*Controller.*(..))")
    public void logPointCut() {
    }

    /**
     * 统一切入 Controller 接口, 根据环境输出不同日志
     * @param joinPoint joinPoint
     * @param operation operation
     * @return Object
     * @throws Throwable Throwable
     */
    @Around("logPointCut() && @annotation(operation)")
    public Object logAround(ProceedingJoinPoint joinPoint, Operation operation) throws Throwable {
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        if (attributes == null || operation.hidden()) {
            return joinPoint.proceed();
        }
        HttpServletRequest request = attributes.getRequest();
        long startTime = System.currentTimeMillis();
        Object ret;
        try {
            ret = joinPoint.proceed();
        } catch (Throwable ex) {
            long executionTime = System.currentTimeMillis() - startTime;
            log.error(formatRequestLog(joinPoint, operation, ex, executionTime, request));
            throw ex;
        }
        long executionTime = System.currentTimeMillis() - startTime;
        log.debug(formatRequestLog(joinPoint, operation, ret, executionTime, request));
        return ret;
    }

    private String formatRequestLog(JoinPoint joinPoint, Operation operation, Object ret, long executionTime, HttpServletRequest request) {
        String className = joinPoint.getTarget().getClass().getName();
        String methodName = joinPoint.getSignature().getName() + "()";
        String apiDesc = resolveApiDescription(joinPoint, operation);
        String methodParam = serializeArguments(joinPoint);
        String requestParamPayload = buildReadableParamPayload(request);
        Map<String, Object> methodParamMap = requestParamPayload.isEmpty() ? Collections.emptyMap() : transStringToMap(requestParamPayload);
        String requestUri = truncate(request.getRequestURI(), MAX_REQUEST_URI_LENGTH);
        String contentType = StringUtils.hasText(request.getContentType()) ? request.getContentType() : "FORM";
        String authorization = maskAuthorization(request.getHeader(HttpHeaders.AUTHORIZATION));
        String userAgentHeader = request.getHeader(HttpHeaders.USER_AGENT);
        UserAgent userAgent = parseUserAgent(userAgentHeader);
        String responsePreview = buildResponsePreview(ret);

        StringBuilder sb = new StringBuilder(512);
        sb.append("\n")
                .append("*********************************Request请求***************************************")
                .append("\n")
                .append("ClassName     :  ").append(className).append("\n")
                .append("RequestMethod :  ").append(methodName).append("\n")
                .append("MethodParam   :  ").append(methodParam).append("\n")
                .append("ContentType   :  ").append(contentType).append("\n")
                .append("RequestParams :  ").append(methodParamMap.isEmpty() ? "" : methodParamMap).append("\n")
                .append("RequestType   :  ").append(request.getMethod()).append("\n")
                .append("Description   :  ").append(apiDesc).append("\n")
                .append("ServerAddr    :  ").append(request.getScheme()).append("://")
                .append(request.getServerName()).append(":").append(request.getServerPort()).append("\n")
                .append("RemoteAddr    :  ").append(JakartaServletUtil.getClientIP(request)).append("\n")
                .append("DeviceName    :  ").append(userAgent.getPlatform().getName()).append("\n")
                .append("BrowserName   :  ").append(userAgent.getBrowser().getName()).append("\n")
                .append("UserAgent     :  ").append(userAgentHeader).append("\n")
                .append("RequestUri    :  ").append(requestUri).append("\n")
                .append("Header        :  ").append('{')
                .append(HttpHeaders.AUTHORIZATION).append("=")
                .append(authorization)
                .append('}').append('\n')
                .append("ExecutionTime :  ").append(executionTime).append(" ms\n");

        if (responsePreview != null) {
            sb.append("Response      :  ").append(responsePreview).append("\n");
        }
        sb.append("**************************").append(DateUtil.now())
                .append("***********************************").append("\n");
        return sb.toString();
    }

    /**
     * 安全序列化方法
     * @param obj obj
     * @return String
     */
    private String safeSerialize(Object obj) {
        if (obj == null) {
            return "null";
        }
        try {
            return JacksonUtils.toJson(obj);
        } catch (Exception e) {
            log.warn("Failed to serialize object: {}", obj.getClass().getName(), e);
            return "[Unserializable: " + obj.getClass().getSimpleName() + "]";
        }
    }

    // 将 queryString/formData 解析为 map, 保持插入顺序
    private Map<String, Object> transStringToMap(String mapString) {
        if (!StringUtils.hasText(mapString)) {
            return Collections.emptyMap();
        }
        String[] pairs = mapString.split("&");
        Map<String, Object> result = new LinkedHashMap<>(pairs.length);
        for (String pair : pairs) {
            if (!StringUtils.hasText(pair)) {
                continue;
            }
            int idx = pair.indexOf('=');
            if (idx <= 0) {
                continue;
            }
            String key = pair.substring(0, idx);
            String value = pair.substring(idx + 1);
            result.putIfAbsent(key, value);
        }
        return result;
    }

    // 组装 API 描述信息, 统一日志格式
    private String resolveApiDescription(JoinPoint joinPoint, Operation operation) {
        Tag tag = joinPoint.getTarget().getClass().getAnnotation(Tag.class);
        return tag != null ? tag.name() + "-" + operation.summary() : operation.summary();
    }

    // 过滤无法序列化的参数并截断, 避免日志过长
    private String serializeArguments(JoinPoint joinPoint) {
        List<Object> arguments = Arrays.stream(joinPoint.getArgs())
                .filter(arg -> !(arg instanceof ServletRequest ||
                        arg instanceof ServletResponse ||
                        arg instanceof MultipartFile))
                .collect(Collectors.toList());
        return truncate(safeSerialize(arguments), MAX_ARGUMENT_PREVIEW_LENGTH);
    }

    // 按照 queryString/form 参数构建可读文本, 包含容错
    private String buildReadableParamPayload(HttpServletRequest request) {
        String queryString = request.getQueryString();
        if (StringUtils.hasText(queryString)) {
            try {
                return URLDecoder.decode(queryString, StandardCharsets.UTF_8);
            } catch (IllegalArgumentException ex) {
                log.warn("Query string decode failed: {}", ex.getMessage());
                return queryString;
            }
        }
        Map<String, String[]> params = request.getParameterMap();
        if (params.isEmpty()) {
            return "";
        }
        // form 表单参数需要自行拼接, 为了避免日志爆炸对单个值做限长
        return params.entrySet().stream()
                .map(entry -> entry.getKey() + "=" + Arrays.stream(entry.getValue())
                        .map(value -> truncate(value, MAX_PARAM_VALUE_LENGTH))
                        .collect(Collectors.joining(",")))
                .collect(Collectors.joining("&"));
    }

    private String truncate(String value, int maxLength) {
        if (!StringUtils.hasText(value) || value.length() <= maxLength) {
            return value == null ? "" : value;
        }
        return value.substring(0, maxLength) + "...Length(" + value.length() + ")";
    }

    /**
     * Authorization 只保留前缀, 防止泄漏敏感信息
     * @param authorization authorization
     * @return String
     */
    private String maskAuthorization(String authorization) {
        if (!StringUtils.hasText(authorization)) {
            return "";
        }
        if (authorization.length() <= 16) {
            return "***";
        }
        return authorization.substring(0, 12) + "...Length(" + authorization.length() + ")";
    }

    private UserAgent parseUserAgent(String userAgentHeader) {
        return UserAgentUtil.parse(userAgentHeader == null ? "" : userAgentHeader);
    }

    // 统一响应/异常输出, 控制行长度
    private String buildResponsePreview(Object ret) {
        switch (ret) {
            case null -> {
                return null;
            }
            case Throwable throwable -> {
                String message = throwable.getClass().getName() + ": " + (throwable.getMessage() == null ? "" : throwable.getMessage());
                return truncate(message, MAX_RESPONSE_PREVIEW_LENGTH);
            }
            case CharSequence sequence -> {
                return truncate(sequence.toString(), MAX_RESPONSE_PREVIEW_LENGTH);
            }
            default -> {
            }
        }
        return truncate(safeSerialize(ret), MAX_RESPONSE_PREVIEW_LENGTH);
    }
}
