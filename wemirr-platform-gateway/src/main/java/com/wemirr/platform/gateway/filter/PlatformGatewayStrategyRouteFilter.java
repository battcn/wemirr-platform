package com.wemirr.platform.gateway.filter;

import cn.hutool.core.util.IdUtil;
import com.alibaba.fastjson2.JSON;
import com.alibaba.fastjson2.JSONObject;
import com.wemirr.platform.gateway.configuration.rule.BlacklistHelper;
import com.wemirr.platform.gateway.configuration.rule.LimitHelper;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.MDC;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.context.annotation.Primary;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

/**
 * @author Levin
 */
@Slf4j
@Primary
@Component
public class PlatformGatewayStrategyRouteFilter implements GlobalFilter {

    @Resource
    private BlacklistHelper blacklistHelper;
    @Resource
    private LimitHelper limitHelper;

    private static final String TRACE_ID = "n-d-trace-id";

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        final String traceId = IdUtil.fastSimpleUUID();
        MDC.put(TRACE_ID, traceId);
        if (blacklistHelper.valid(exchange)) {
            return wrap(exchange, "访问失败,您已进入黑名单");
        }
        if (limitHelper.hostTrace(exchange)) {
            return wrap(exchange, "访问失败,已达到最大阈值");
        }
        return chain.filter(exchange);
    }

    private Mono<Void> wrap(ServerWebExchange exchange, String message) {
        ServerHttpResponse response = exchange.getResponse();
        response.setStatusCode(HttpStatus.SERVICE_UNAVAILABLE);
        response.getHeaders().set(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE);
        JSONObject result = new JSONObject();
        result.put("code", HttpStatus.SERVICE_UNAVAILABLE.value());
        result.put("message", message);
        result.put("successful", false);
        result.put("timestamp", System.currentTimeMillis());
        log.warn("[错误信息] - [{}]", result.toJSONString());
        return response.writeWith(Mono.just(response.bufferFactory().wrap(JSON.toJSONBytes(result))));
    }
}
