package com.wemirr.platform.gateway.filter;

import cn.hutool.core.util.IdUtil;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
//import com.nepxion.discovery.plugin.strategy.gateway.filter.DefaultGatewayStrategyRouteFilter;
//import com.nepxion.discovery.plugin.strategy.gateway.filter.GatewayStrategyFilterResolver;
import com.wemirr.platform.gateway.config.rule.BlacklistHelper;
import com.wemirr.platform.gateway.config.rule.LimitHelper;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.MDC;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.cloud.gateway.support.ServerWebExchangeUtils;
import org.springframework.context.annotation.Primary;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import jakarta.annotation.Resource;
import java.util.Map;

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

    private final static String TRACE_ID = "n-d-trace-id";

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        final String traceId = IdUtil.fastSimpleUUID();
        MDC.put(TRACE_ID, traceId);
        if (blacklistHelper.valid(exchange)) {
            return wrap(exchange, HttpStatus.SERVICE_UNAVAILABLE, "访问失败,您已进入黑名单");
        }
        if (limitHelper.hostTrace(exchange)) {
            return wrap(exchange, HttpStatus.SERVICE_UNAVAILABLE, "访问失败,已达到最大阈值");
        }
//        final Object serviceName = exchange.getAttribute(ServerWebExchangeUtils.GATEWAY_ORIGINAL_REQUEST_URL_ATTR);
        // 通过过滤器设置路由Header头部信息，并全链路传递到服务端  true 不擦除外部请求头 false 擦除
//        GatewayStrategyFilterResolver.setHeader(exchange.getRequest(), exchange.getRequest().mutate(), TRACE_ID, traceId, true);
        return chain.filter(exchange);
    }

    private Mono<Void> wrap(ServerWebExchange exchange, HttpStatus status, String message) {
        ServerHttpResponse response = exchange.getResponse();
        response.setStatusCode(status);
        response.getHeaders().set(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE);
        JSONObject result = new JSONObject();
        result.put("code", status.value());
        result.put("message", message);
        result.put("successful", false);
        result.put("timestamp", System.currentTimeMillis());
        log.warn("[错误信息] - [{}]", result.toJSONString());
        return response.writeWith(Mono.just(response.bufferFactory().wrap(JSON.toJSONBytes(result))));
    }
}
