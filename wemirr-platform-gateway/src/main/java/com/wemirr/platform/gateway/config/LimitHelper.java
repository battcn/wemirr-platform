package com.wemirr.platform.gateway.config;

import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.util.IdUtil;
import com.alibaba.fastjson.JSON;
import com.google.common.collect.Lists;
import com.wemirr.platform.gateway.rest.domain.LimitRule;
import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.stereotype.Component;
import org.springframework.util.AntPathMatcher;
import org.springframework.web.server.ServerWebExchange;

import java.net.InetSocketAddress;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * @author Levin
 */
@Component
@RequiredArgsConstructor
public class LimitHelper {

    private final StringRedisTemplate stringRedisTemplate;
    private static final AntPathMatcher ANT_PATH_MATCHER = new AntPathMatcher();
    private static final String DEFAULT_RULE_LIMIT = "rule:limit";
    private static final String DEFAULT_RULE_LIMIT_TOTAL = "rule:limit:total";

    public LimitRule getByPath(String path) {
        final Set<Object> keys = stringRedisTemplate.opsForHash().keys(DEFAULT_RULE_LIMIT);
        if (CollectionUtil.isEmpty(keys)) {
            return null;
        }
        final List<Object> objects = stringRedisTemplate.opsForHash().multiGet(DEFAULT_RULE_LIMIT, keys);
        for (Object object : objects) {
            LimitRule rule = JSON.parseObject(object.toString(), LimitRule.class);
            final boolean match = ANT_PATH_MATCHER.match(rule.getPath(), path);
            if (!match) {
                continue;
            }
            return rule;
        }
        return null;
    }

    public List<LimitRule> query() {
        final Set<Object> keys = stringRedisTemplate.opsForHash().keys(DEFAULT_RULE_LIMIT);
        if (CollectionUtil.isEmpty(keys)) {
            return Lists.newArrayList();
        }
        return stringRedisTemplate.opsForHash().multiGet(DEFAULT_RULE_LIMIT, keys).stream()
                .map(object -> JSON.parseObject(object.toString(), LimitRule.class)).collect(Collectors.toList());
    }

    public void create(LimitRule rule) {
        String uuid = IdUtil.fastSimpleUUID();
        rule.setId(uuid);
        stringRedisTemplate.opsForHash().put(DEFAULT_RULE_LIMIT, uuid, JSON.toJSONString(rule));
    }

    public void delete(String id) {
        stringRedisTemplate.opsForHash().delete(DEFAULT_RULE_LIMIT, id);
    }


    public boolean hostTrace(ServerWebExchange exchange) {
        final ServerHttpRequest request = exchange.getRequest();
        final InetSocketAddress remoteAddress = request.getRemoteAddress();
        if (remoteAddress == null) {
            return false;
        }
        final String hostName = remoteAddress.getHostName();
        final String path = exchange.getRequest().getURI().getPath();
        final LimitRule limitRule = getByPath(path);
        if (limitRule == null) {
            return false;
        }
        final Long increment = stringRedisTemplate.opsForHash().increment(DEFAULT_RULE_LIMIT_TOTAL, hostName, 1);
        return increment > limitRule.getTotal();
    }
}
