package com.wemirr.platform.gateway.filter;

import cn.hutool.core.util.IdUtil;
import com.wemirr.platform.gateway.configuration.rule.BlacklistHelper;
import com.wemirr.platform.gateway.configuration.rule.LimitHelper;
import com.wemirr.platform.gateway.utils.MonoHelper;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.MDC;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.context.annotation.Primary;
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

    private static final String TRACE_ID = "n-d-trace-id";
    @Resource
    private BlacklistHelper blacklistHelper;
    @Resource
    private LimitHelper limitHelper;

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        final String traceId = IdUtil.fastSimpleUUID();
        MDC.put(TRACE_ID, traceId);
        if (blacklistHelper.valid(exchange)) {
            return MonoHelper.wrap(exchange, "访问失败,您已进入黑名单");
        }
        if (limitHelper.hostTrace(exchange)) {
            return MonoHelper.wrap(exchange, "访问失败,已达到最大阈值");
        }
        return chain.filter(exchange);
    }
}
