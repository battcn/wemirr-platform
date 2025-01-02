package com.wemirr.platform.gateway.utils;

import com.alibaba.fastjson2.JSON;
import com.alibaba.fastjson2.JSONObject;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

/**
 * @author Levin
 */
@Slf4j
public class MonoHelper {

    public static Mono<Void> wrap(ServerWebExchange exchange, String message) {
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
