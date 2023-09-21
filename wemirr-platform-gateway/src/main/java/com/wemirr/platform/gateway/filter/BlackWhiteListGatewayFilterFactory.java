package com.wemirr.platform.gateway.filter;

import com.alibaba.fastjson2.JSON;
import com.alibaba.fastjson2.JSONObject;
import com.google.common.net.HttpHeaders;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.cloud.gateway.support.ipresolver.XForwardedRemoteAddressResolver;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.util.List;

/**
 * XForwardedRemoteAddressResolver
 * 黑白名单过滤器
 *
 * @author Levin
 */
@Slf4j
@Order(99)
@Configuration
public class BlackWhiteListGatewayFilterFactory extends AbstractGatewayFilterFactory<BlackWhiteListGatewayFilterFactory.Config> {

    private static final String DEFAULT_FILTER_NAME = "BlackWhiteList";

    @Override
    public String name() {
        return DEFAULT_FILTER_NAME;
    }

    public BlackWhiteListGatewayFilterFactory() {
        super(Config.class);
    }


    @Override
    public GatewayFilter apply(Config config) {
        return (exchange, chain) -> {
            InetSocketAddress remoteAddress = XForwardedRemoteAddressResolver.maxTrustedIndex(1).resolve(exchange);
            final InetAddress inetAddress = remoteAddress.getAddress();
            String ip = inetAddress.getHostAddress();
            log.debug("[访问者IP地址] - [{}]", ip);
            if (config.isIgnoreIntranet() && inetAddress.isSiteLocalAddress()) {
                log.info("[忽略内网IP] - {}", inetAddress.isSiteLocalAddress());
                return chain.filter(exchange);
            }
            if (config.type == BlackWhiteListType.BLACK_LIST) {
                boolean access = config.getIpList().contains(ip);
                if (access) {
                    log.warn("[访问受限，该地址在黑名单列表] - [{}]", ip);
                    return accessRestricted(exchange);
                }
            } else if (config.type == BlackWhiteListType.WHITE_LIST) {
                boolean access = config.getIpList().contains(ip);
                if (access) {
                    return chain.filter(exchange);
                } else {
                    log.warn("[访问受限，该地址不在白名单列表] - [{}]", ip);
                    return accessRestricted(exchange);
                }
            }
            return chain.filter(exchange);
        };
    }

    private Mono<Void> accessRestricted(ServerWebExchange exchange) {
        ServerHttpResponse response = exchange.getResponse();
        response.setStatusCode(HttpStatus.FORBIDDEN);
        response.getHeaders().set(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE);
        JSONObject result = new JSONObject();
        result.put("messageId", HttpStatus.FORBIDDEN.value());
        result.put("message", "访问受限，请联系管理员");
        result.put("successful", false);
        result.put("timestamp", System.currentTimeMillis());
        return response.writeWith(Mono.just(response.bufferFactory().wrap(JSON.toJSONBytes(result))));
    }

    @Data
    public static class Config {

        private Integer maxTrustedIndex = 1;
        private BlackWhiteListType type;
        private boolean ignoreIntranet;
        private List<String> ipList;
    }

    @AllArgsConstructor
    public enum BlackWhiteListType {
        /**
         * 黑名单
         */
        BLACK_LIST,
        /**
         * 白名单
         */
        WHITE_LIST;

    }

}
