package com.wemirr.platform.gateway.config.rule;

import cn.hutool.core.collection.CollectionUtil;
import com.alibaba.fastjson.JSON;
import com.google.common.collect.Lists;
import com.nepxion.discovery.common.util.UuidUtil;
import com.wemirr.platform.gateway.rest.domain.BlacklistRule;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;

import javax.annotation.Resource;
import java.net.InetSocketAddress;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

/**
 * @author Levin
 */
@Slf4j
@Component
public class BlacklistHelper {

    @Resource
    private StringRedisTemplate stringRedisTemplate;

    private final static String BLACK_LIST_HASH = "gateway:blacklist";
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


    public BlacklistRule getById(String id) {
        final Object object = stringRedisTemplate.opsForHash().get(BLACK_LIST_HASH, id);
        if (object == null) {
            return null;
        }
        return JSON.parseObject(object.toString(), BlacklistRule.class);
    }

    public List<BlacklistRule> query() {
        final Set<Object> keys = stringRedisTemplate.opsForHash().keys(BLACK_LIST_HASH);
        if (CollectionUtil.isEmpty(keys)) {
            return Lists.newArrayList();
        }
        return stringRedisTemplate.opsForHash().multiGet(BLACK_LIST_HASH, keys).stream()
                .map(object -> JSON.parseObject(object.toString(), BlacklistRule.class)).collect(Collectors.toList());
    }

    public boolean valid(ServerWebExchange exchange) {
        final InetSocketAddress remoteAddress = exchange.getRequest().getRemoteAddress();
        if (remoteAddress == null) {
            return false;
        }
        final String hostName = remoteAddress.getHostName();
        if (Objects.nonNull(stringRedisTemplate.opsForValue().get(getKey(hostName)))) {
            return true;
        }
        return Objects.nonNull(stringRedisTemplate.opsForHash().get(BLACK_LIST_HASH, hostName));
    }

    public void saveOrUpdate(BlacklistRule rule) {
        if (rule == null) {
            return;
        }
        if (rule.getId() == null) {
            rule.setId(UuidUtil.getUUID());
        }
        if (rule.getCreatedTime() == null) {
            rule.setCreatedTime(LocalDateTime.now());
        }
        final String content = JSON.toJSONString(rule);
        stringRedisTemplate.opsForHash().put(BLACK_LIST_HASH, rule.getId(), content);
    }

    public void delete(String id) {
        stringRedisTemplate.opsForHash().delete(BLACK_LIST_HASH, id);
    }


    public String getKey(String hostName) {
        return String.format(BLACK_LIST, hostName);
    }


}
