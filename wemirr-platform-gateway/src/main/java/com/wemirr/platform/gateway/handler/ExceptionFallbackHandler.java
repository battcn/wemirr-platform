package com.wemirr.platform.gateway.handler;

import com.alibaba.fastjson2.JSONObject;
import jakarta.annotation.Nonnull;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.server.HandlerFunction;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

import java.util.Optional;

import static org.springframework.cloud.gateway.support.ServerWebExchangeUtils.GATEWAY_ORIGINAL_REQUEST_URL_ATTR;

/**
 * 降级处理
 *
 * @author Levin
 * @since 2018/7/5
 */
@Slf4j
@Component
public class ExceptionFallbackHandler implements HandlerFunction<ServerResponse> {

    @Override
    @Nonnull
    public Mono<ServerResponse> handle(ServerRequest serverRequest) {
        Optional<Object> originalUris = serverRequest.attribute(GATEWAY_ORIGINAL_REQUEST_URL_ATTR);
        originalUris.ifPresent(originalUri -> log.error("网关执行请求:{}失败,服务降级处理", originalUri));
        JSONObject obj = new JSONObject();
        obj.put("code", HttpStatus.SERVICE_UNAVAILABLE.value());
        obj.put("messageId", HttpStatus.SERVICE_UNAVAILABLE.value());
        obj.put("message", "服务繁忙，请稍后再试");
        obj.put("successful", false);
        obj.put("timestamp", System.currentTimeMillis());
        return ServerResponse.status(HttpStatus.INTERNAL_SERVER_ERROR.value())
                .contentType(MediaType.APPLICATION_JSON)
                .body(BodyInserters.fromValue(obj));
    }
}
