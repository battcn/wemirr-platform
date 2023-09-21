package com.wemirr.platform.gateway.config.rule;

import cn.hutool.core.collection.CollectionUtil;
import com.alibaba.fastjson2.JSON;
import com.alibaba.nacos.common.utils.UuidUtils;
import com.google.common.collect.Lists;
import com.wemirr.platform.gateway.rest.domain.BlacklistRule;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;

import java.net.InetSocketAddress;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import static com.wemirr.platform.gateway.config.rule.GatewayRule.GatewayRuleEnum.RULE_BLACKLIST;


/**
 * @author Levin
 */
@Slf4j
@Component
public class BlacklistHelper implements GatewayRule<BlacklistRule> {

    @Resource
    private StringRedisTemplate stringRedisTemplate;


    public void setBlack(ServerWebExchange exchange) {
        final InetSocketAddress remoteAddress = exchange.getRequest().getRemoteAddress();
        if (remoteAddress == null) {
            log.warn("无效的远程地址");
            return;
        }
        final String hostName = remoteAddress.getHostName();
        // 默认黑名单待 1 小时
        log.info("新进黑名单 - {}", hostName);
        final String path = exchange.getRequest().getURI().getPath();
        // 添加黑名单
        BlacklistRule record = new BlacklistRule();
        record.setId(UuidUtils.generateUuid());
        record.setDescription("触发 sentinel 限流规则" + path + "拉入黑名单1小时");
        record.setStatus(true);
        final LocalDateTime now = LocalDateTime.now();
        record.setStartTime(now);
        record.setEndTime(now.plusHours(1));
        record.setIp(remoteAddress.getAddress().getHostAddress());
        record.setMethod(Objects.requireNonNull(exchange.getRequest().getMethod()).name());
        record.setPath(path);
        saveOrUpdate(record);
    }


    public BlacklistRule getById(String id) {
        final Object object = stringRedisTemplate.opsForHash().get(RULE_BLACKLIST.hashKey(), id);
        if (object == null) {
            return null;
        }
        return JSON.parseObject(object.toString(), BlacklistRule.class);
    }

    public List<BlacklistRule> query() {
        final Set<Object> keys = stringRedisTemplate.opsForHash().keys(RULE_BLACKLIST.hashKey());
        if (CollectionUtil.isEmpty(keys)) {
            return Lists.newArrayList();
        }
        return stringRedisTemplate.opsForHash().multiGet(RULE_BLACKLIST.hashKey(), keys).stream()
                .map(object -> {
                    BlacklistRule rule = JSON.parseObject(object.toString(), BlacklistRule.class);
                    if (rule != null) {
                        final Object visits = Optional.ofNullable(stringRedisTemplate.opsForHash()
                                .get(RULE_BLACKLIST.visitsKey(), rule.getId())).orElse("0");
                        rule.setVisits(Long.parseLong(visits.toString()));
                    }
                    return rule;
                }).collect(Collectors.toList());
    }

    public boolean valid(ServerWebExchange exchange) {
        final InetSocketAddress remoteAddress = exchange.getRequest().getRemoteAddress();
        if (remoteAddress == null) {
            return false;
        }
        final BlacklistRule rule = getByPath(stringRedisTemplate, exchange.getRequest(), RULE_BLACKLIST);
        boolean flag = rule != null;
        if (flag) {
            stringRedisTemplate.opsForHash().increment(RULE_BLACKLIST.visitsKey(), rule.getId(), 1);
        }
        return flag;
    }

    public void saveOrUpdate(BlacklistRule rule) {
        if (rule == null) {
            return;
        }
        if (rule.getId() == null) {
            rule.setId(UuidUtils.generateUuid());
        }
        if (rule.getCreatedTime() == null) {
            rule.setCreatedTime(LocalDateTime.now());
        }
        final String content = JSON.toJSONString(rule);
        stringRedisTemplate.opsForHash().put(RULE_BLACKLIST.hashKey(), rule.getId(), content);
    }

    public void delete(String id) {
        stringRedisTemplate.opsForHash().delete(RULE_BLACKLIST.hashKey(), id);
    }


}
