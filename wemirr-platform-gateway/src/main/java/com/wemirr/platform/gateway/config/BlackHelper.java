package com.wemirr.platform.gateway.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;

import javax.annotation.Resource;
import java.net.InetSocketAddress;
import java.util.Objects;
import java.util.concurrent.TimeUnit;

/**
 * @author Levin
 */
@Slf4j
@Component
public class BlackHelper {

    @Resource
    private StringRedisTemplate stringRedisTemplate;

    private final static String BLACK_LIST = "gateway:blacklist:%s";

    public void setBlack(ServerWebExchange exchange) {
        final InetSocketAddress remoteAddress = exchange.getRequest().getRemoteAddress();
        if (remoteAddress == null) {
            log.warn("无效的远程地址");
            return;
        }
        final String hostName = remoteAddress.getHostName();
        final String key = getKey(hostName);
        stringRedisTemplate.opsForValue().set(key, hostName);
        // 默认黑名单待 1 小时
        stringRedisTemplate.expire(key, 1, TimeUnit.HOURS);
        log.info("新进黑名单 - {}", hostName);
    }

    public boolean valid(ServerWebExchange exchange) {
        final InetSocketAddress remoteAddress = exchange.getRequest().getRemoteAddress();
        if (remoteAddress == null) {
            return false;
        }
        final String hostName = remoteAddress.getHostName();
        return Objects.nonNull(stringRedisTemplate.opsForValue().get(getKey(hostName)));
    }

    public String getKey(String hostName) {
        return String.format(BLACK_LIST, hostName);
    }


}
