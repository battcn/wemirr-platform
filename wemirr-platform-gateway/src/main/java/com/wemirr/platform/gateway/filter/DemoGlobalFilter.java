package com.wemirr.platform.gateway.filter;

import com.wemirr.platform.gateway.utils.MonoHelper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.context.annotation.Profile;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

/**
 * @author Levin
 */
@Slf4j
@Component
@Profile("demo")
public class DemoGlobalFilter implements GlobalFilter {

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        HttpMethod method = exchange.getRequest().getMethod();
        if (method == HttpMethod.PUT || method == HttpMethod.DELETE) {
            return MonoHelper.wrap(exchange, "演示环境,禁止破坏基础数据,请下载代码自行部署");
        }
        return chain.filter(exchange);
    }

}
