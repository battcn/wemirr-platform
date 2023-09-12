package com.wemirr.platform.gateway.config;

import com.google.common.collect.Maps;
import com.wemirr.framework.commons.exception.CheckedException;
import com.wemirr.platform.gateway.config.rule.BlacklistHelper;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.boot.autoconfigure.web.ErrorProperties;
import org.springframework.boot.autoconfigure.web.WebProperties;
import org.springframework.boot.autoconfigure.web.reactive.error.DefaultErrorWebExceptionHandler;
import org.springframework.boot.web.error.ErrorAttributeOptions;
import org.springframework.boot.web.reactive.error.ErrorAttributes;
import org.springframework.context.ApplicationContext;
import org.springframework.http.HttpStatus;
import org.springframework.web.reactive.function.server.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.Map;

/**
 * 自定义网关异常处理
 *
 * <p>异常时用JSON代替HTML异常信息<p>
 *
 * @author Levin
 */
@Slf4j
public class JsonExceptionHandler extends DefaultErrorWebExceptionHandler {

    private final BlacklistHelper blacklistHelper;

    JsonExceptionHandler(ErrorAttributes errorAttributes, BlacklistHelper blacklistHelper,
                         WebProperties webProperties,
                         ErrorProperties errorProperties,
                         ApplicationContext applicationContext) {
        super(errorAttributes, webProperties.getResources(), errorProperties, applicationContext);
        this.blacklistHelper = blacklistHelper;
    }

    private static final String UNABLE_ERROR = "Unable to find instance for";

    /**
     * 获取异常属性
     */
    @Override
    protected Map<String, Object> getErrorAttributes(ServerRequest request, ErrorAttributeOptions errorAttributeOptions) {
        int code = HttpStatus.INTERNAL_SERVER_ERROR.value();
        Throwable error = super.getError(request);
        final String message = error.getMessage();
        if (StringUtils.contains(message, UNABLE_ERROR)) {
            return response(HttpStatus.NOT_FOUND.value(), "网络异常，请稍后再试");
        }
//        if (error instanceof ParamFlowException) {
//            // 触发限流规则直接拉黑名单
//            ParamFlowException flowException = (ParamFlowException) error
//            log.error("[触发限流规则] - {} - {}", flowException.getResourceName(), flowException.getRule())
//            blacklistHelper.setBlack(request.exchange())
//            return response(HttpStatus.SERVICE_UNAVAILABLE.value(), "访问量过大，请稍后再试")
//        }
        if (error instanceof ResponseStatusException) {
            code = HttpStatus.SERVICE_UNAVAILABLE.value();
        }
        return response(code, this.buildMessage(request, error));
    }

    /**
     * 指定响应处理方法为JSON处理的方法
     *
     * @param errorAttributes errorAttributes
     */
    @Override
    protected RouterFunction<ServerResponse> getRoutingFunction(ErrorAttributes errorAttributes) {
        return RouterFunctions.route(RequestPredicates.all(), this::renderErrorResponse);
    }

    /**
     * 根据code获取对应的HttpStatus
     *
     * @param errorAttributes errorAttributes
     */
    @Override
    protected int getHttpStatus(Map<String, Object> errorAttributes) {
        return HttpStatus.OK.value();
    }

    /**
     * 构建异常信息
     *
     * @param request 请求信息
     * @param ex      异常消息
     * @return 返回结果
     */
    private String buildMessage(ServerRequest request, Throwable ex) {
        if (ex instanceof CheckedException) {
            return ex.getLocalizedMessage();
        }
        StringBuilder message = new StringBuilder("Failed to handle request [");
        message.append(request.method().name());
        message.append(" ");
        message.append(request.uri());
        message.append("]");
        if (ex != null) {
            message.append(": ");
            message.append(ex.getMessage());
        }
        return message.toString();
    }

    /**
     * 构建返回的JSON数据格式
     *
     * @param status       状态码
     * @param errorMessage 异常信息
     * @return 返回结果
     */
    private static Map<String, Object> response(int status, String errorMessage) {
        Map<String, Object> map = Maps.newLinkedHashMap();
        map.put("code", status);
        map.put("timestamp", System.currentTimeMillis());
        map.put("message", errorMessage);
        map.put("successful", false);
        log.warn("[响应结果] - [{}]", map.toString());
        return map;
    }

}