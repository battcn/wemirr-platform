package com.wemirr.platform.gateway.config.rule;

import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.util.IdUtil;
import com.alibaba.fastjson.JSON;
import com.google.common.collect.Lists;
import com.wemirr.platform.gateway.rest.domain.BlacklistRule;
import com.wemirr.platform.gateway.rest.domain.LimitRule;
import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.http.HttpMethod;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.stereotype.Component;
import org.springframework.util.AntPathMatcher;
import org.springframework.web.server.ServerWebExchange;

import java.net.InetSocketAddress;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
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
    private static final String DEFAULT_RULE_LIMIT_VISITS = "rule:limit:visits";
    private static final String DEFAULT_RULE_LIMIT_TOTAL = "rule:limit:total";

    private final BlacklistHelper blacklistHelper;

    public LimitRule getByPath(ServerHttpRequest request) {
        final Set<Object> keys = stringRedisTemplate.opsForHash().keys(DEFAULT_RULE_LIMIT);
        if (CollectionUtil.isEmpty(keys)) {
            return null;
        }
        final String path = request.getURI().getPath();
        final HttpMethod httpMethod = request.getMethod();
        final List<Object> objects = stringRedisTemplate.opsForHash().multiGet(DEFAULT_RULE_LIMIT, keys);
        for (Object object : objects) {
            LimitRule rule = JSON.parseObject(object.toString(), LimitRule.class);
            //（1）? 匹配一个字符（除过操作系统默认的文件分隔符）
            //（2）* 匹配0个或多个字符
            //（3）**匹配0个或多个目录
            //（4）{spring:[a-z]+} 将正则表达式[a-z]+匹配到的值,赋值给名为 spring 的路径变量.
            //    (PS:必须是完全匹配才行,在SpringMVC中只有完全匹配才会进入controller层的方法)
            final boolean match = ANT_PATH_MATCHER.match(rule.getPath(), path);
            if (match && Objects.equals(HttpMethod.valueOf(rule.getMethod()), httpMethod)) {
                return rule;
            }
        }
        return null;
    }

    public List<LimitRule> query() {
        final Set<Object> keys = stringRedisTemplate.opsForHash().keys(DEFAULT_RULE_LIMIT);
        if (CollectionUtil.isEmpty(keys)) {
            return Lists.newArrayList();
        }
        return stringRedisTemplate.opsForHash().multiGet(DEFAULT_RULE_LIMIT, keys).stream()
                .map(object -> {
                    LimitRule rule = JSON.parseObject(object.toString(), LimitRule.class);
                    if (rule != null) {
                        final Object visits = Optional.ofNullable(stringRedisTemplate.opsForHash()
                                .get(DEFAULT_RULE_LIMIT_VISITS, rule.getId())).orElse("0");
                        rule.setVisits(Long.parseLong(visits.toString()));
                    }
                    return rule;
                }).collect(Collectors.toList());
    }

    public void saveOrUpdate(LimitRule rule) {
        if (rule == null) {
            return;
        }
        if (rule.getId() == null) {
            String uuid = IdUtil.fastSimpleUUID();
            rule.setId(uuid);
        }
        if (rule.getCreatedTime() == null) {
            rule.setCreatedTime(LocalDateTime.now());
        }
        stringRedisTemplate.opsForHash().put(DEFAULT_RULE_LIMIT, rule.getId(), JSON.toJSONString(rule));
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
        final LimitRule limitRule = getByPath(exchange.getRequest());
        if (limitRule == null) {
            return false;
        }
        final Long increment = stringRedisTemplate.opsForHash().increment(DEFAULT_RULE_LIMIT_TOTAL, hostName, 1);
        stringRedisTemplate.opsForHash().put(DEFAULT_RULE_LIMIT_VISITS, limitRule.getId(), increment + "");
        boolean overflow = increment > limitRule.getTotal();
        if (overflow && limitRule.getBlacklist()) {
            final BlacklistRule blacklistRule = blacklistHelper.getById(limitRule.getId());
            if (blacklistRule == null) {
                // 添加黑名单
                BlacklistRule record = new BlacklistRule();
                record.setId(limitRule.getId());
                record.setDescription("访问" + limitRule.getPath() + "频率过快被拉入黑名单24小时");
                record.setStatus(true);
                final LocalDateTime now = LocalDateTime.now();
                record.setStartTime(now);
                record.setEndTime(now.plusDays(1));
                record.setIp(remoteAddress.getAddress().getHostAddress());
                record.setMethod(limitRule.getMethod());
                record.setPath(limitRule.getPath());
                blacklistHelper.saveOrUpdate(record);
            }
        }
        return overflow;
    }
}
