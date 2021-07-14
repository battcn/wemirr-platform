package com.wemirr.platform.gateway.config.rule;

import cn.hutool.core.collection.CollectionUtil;
import com.alibaba.fastjson.JSON;
import com.google.common.collect.Lists;
import com.nepxion.discovery.common.util.UuidUtil;
import com.wemirr.platform.gateway.rest.domain.BlacklistRule;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Component;
import org.springframework.util.AntPathMatcher;
import org.springframework.web.server.ServerWebExchange;

import javax.annotation.Resource;
import java.net.InetSocketAddress;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
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

    private static final AntPathMatcher ANT_PATH_MATCHER = new AntPathMatcher();
    private final static String BLACK_LIST_HASH = "gateway:blacklist";
    private final static String BLACK_LIST = "gateway:blacklist:%s";
    private static final String DEFAULT_RULE_LIMIT_VISITS = "gateway:blacklist:visits";

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
                .map(object -> {
                    BlacklistRule rule = JSON.parseObject(object.toString(), BlacklistRule.class);
                    if (rule != null) {
                        final Object visits = Optional.ofNullable(stringRedisTemplate.opsForHash()
                                .get(DEFAULT_RULE_LIMIT_VISITS, rule.getId())).orElse("0");
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
        final Set<Object> keys = stringRedisTemplate.opsForHash().keys(BLACK_LIST_HASH);
        if (CollectionUtil.isEmpty(keys)) {
            return false;
        }
        final String path = exchange.getRequest().getURI().getPath();
        final HttpMethod httpMethod = exchange.getRequest().getMethod();
        final List<Object> objects = stringRedisTemplate.opsForHash().multiGet(BLACK_LIST_HASH, keys);
        if (CollectionUtil.isEmpty(objects)) {
            return false;
        }
        for (Object object : objects) {
            BlacklistRule rule = JSON.parseObject(object.toString(), BlacklistRule.class);
            //（1）? 匹配一个字符（除过操作系统默认的文件分隔符）
            //（2）* 匹配0个或多个字符
            //（3）**匹配0个或多个目录
            //（4）{spring:[a-z]+} 将正则表达式[a-z]+匹配到的值,赋值给名为 spring 的路径变量.
            //    (PS:必须是完全匹配才行,在SpringMVC中只有完全匹配才会进入controller层的方法)
            final boolean match = ANT_PATH_MATCHER.match(rule.getPath(), path);
            if (!match || !Objects.equals(HttpMethod.valueOf(rule.getMethod()), httpMethod)) {
                continue;
            }
            stringRedisTemplate.opsForHash().increment(DEFAULT_RULE_LIMIT_VISITS, rule.getId(), 1);
            return true;
        }
        return false;
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
