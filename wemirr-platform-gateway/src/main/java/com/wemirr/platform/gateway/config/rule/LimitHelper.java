package com.wemirr.platform.gateway.config.rule;

import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.util.IdUtil;
import com.alibaba.fastjson.JSON;
import com.google.common.collect.Lists;
import com.wemirr.platform.gateway.rest.domain.BlacklistRule;
import com.wemirr.platform.gateway.rest.domain.LimitRule;
import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;

import java.net.InetSocketAddress;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import static com.wemirr.platform.gateway.config.rule.GatewayRule.Constants.DEFAULT_RULE_LIMIT_TOTAL;
import static com.wemirr.platform.gateway.config.rule.GatewayRule.Constants.GLOBAL_RANGE;
import static com.wemirr.platform.gateway.config.rule.GatewayRule.GatewayRuleEnum.RULE_LIMIT;


/**
 * @author Levin
 */
@Component
@RequiredArgsConstructor
public class LimitHelper implements GatewayRule<LimitRule> {

    private final StringRedisTemplate stringRedisTemplate;

    private final BlacklistHelper blacklistHelper;

    public List<LimitRule> query() {
        final Set<Object> keys = stringRedisTemplate.opsForHash().keys(RULE_LIMIT.hashKey());
        if (CollectionUtil.isEmpty(keys)) {
            return Lists.newArrayList();
        }
        return stringRedisTemplate.opsForHash().multiGet(RULE_LIMIT.hashKey(), keys).stream()
                .map(object -> {
                    LimitRule rule = JSON.parseObject(object.toString(), LimitRule.class);
                    if (rule != null) {
                        final Object visits = Optional.ofNullable(stringRedisTemplate.opsForHash()
                                .get(RULE_LIMIT.visitsKey(), rule.getId())).orElse("0");
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
        stringRedisTemplate.opsForHash().put(RULE_LIMIT.hashKey(), rule.getId(), JSON.toJSONString(rule));
    }

    public void delete(String id) {
        stringRedisTemplate.opsForHash().delete(RULE_LIMIT.hashKey(), id);
    }


    public boolean hostTrace(ServerWebExchange exchange) {
        final ServerHttpRequest request = exchange.getRequest();
        final InetSocketAddress remoteAddress = request.getRemoteAddress();
        if (remoteAddress == null) {
            return false;
        }
        final String hostName = remoteAddress.getHostName();
        final LimitRule limitRule = getByPath(stringRedisTemplate, exchange.getRequest(), RULE_LIMIT);
        if (limitRule == null) {
            return false;
        }
        Long increment;
        if (limitRule.getRange() == GLOBAL_RANGE) {
            increment = stringRedisTemplate.opsForHash().increment(DEFAULT_RULE_LIMIT_TOTAL, limitRule.getId(), 1);
        } else {
            increment = stringRedisTemplate.opsForHash().increment(DEFAULT_RULE_LIMIT_TOTAL, hostName, 1);
        }
        stringRedisTemplate.opsForHash().put(RULE_LIMIT.visitsKey(), limitRule.getId(), increment + "");
        boolean overflow = increment > limitRule.getTotal();
        if (overflow && limitRule.getBlacklist()) {
            final BlacklistRule blacklistRule = blacklistHelper.getById(limitRule.getId());
            if (blacklistRule != null) {
                return true;
            }
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
        return overflow;
    }
}
