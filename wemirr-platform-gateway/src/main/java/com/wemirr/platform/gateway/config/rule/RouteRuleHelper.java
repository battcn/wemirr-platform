package com.wemirr.platform.gateway.config.rule;

import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.util.URLUtil;
import cn.hutool.extra.spring.SpringUtil;
import com.alibaba.fastjson.JSON;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.wemirr.framework.commons.StringUtils;
import com.wemirr.framework.commons.exception.CheckedException;
import com.wemirr.platform.gateway.rest.domain.RouteRule;
import com.wemirr.platform.gateway.route.RedisRouteDynamicGatewayService;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.cloud.gateway.filter.FilterDefinition;
import org.springframework.cloud.gateway.handler.predicate.PredicateDefinition;
import org.springframework.cloud.gateway.route.CompositeRouteDefinitionLocator;
import org.springframework.cloud.gateway.route.RouteDefinition;
import org.springframework.cloud.gateway.route.RouteDefinitionRepository;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static java.util.stream.Collectors.toList;
import static java.util.stream.Collectors.toMap;

/**
 * @author Levin
 * @see org.springframework.cloud.gateway.actuate.GatewayControllerEndpoint
 */
@Slf4j
@Component
@RequiredArgsConstructor
public class RouteRuleHelper {

    private static final String KEY = "KEY";
    private final StringRedisTemplate stringRedisTemplate;
    private final RedisRouteDynamicGatewayService redisRouteDynamicGatewayService;
    private final RouteDefinitionRepository routeDefinitionRepository;
    private final DiscoveryClient discoveryClient;


    @SneakyThrows
    public void saveOrUpdate(RouteRule rule) {
        if (rule == null) {
            throw CheckedException.notFound("rule 不能为空");
        }
        if (rule.getCreatedTime() == null) {
            rule.setCreatedTime(LocalDateTime.now());
        }
        if (rule.getStatus() == null) {
            rule.setStatus(false);
        }
        log.debug("请求参数 - {}", JSON.toJSONString(rule));
        stringRedisTemplate.opsForHash().put(KEY, rule.getId(), JSON.toJSONString(rule));
    }

    private boolean publish(String id) {
        final List<String> services = discoveryClient.getServices();
        final Object object = stringRedisTemplate.opsForHash().get(KEY, id);
        if (object == null) {
            return false;
        }
        RouteRule rule = JSON.parseObject(object.toString(), RouteRule.class);
        RouteDefinition routeDefinition = new RouteDefinition();
        routeDefinition.setId(rule.getId());
        routeDefinition.setOrder(rule.getOrder());
        routeDefinition.setUri(URLUtil.toURI(rule.getUri()));
        if (CollectionUtil.isNotEmpty(rule.getPredicates())) {
            final List<PredicateDefinition> predicates = rule.getPredicates().stream().map(predicate -> {
                final HashMap<String, String> args = Maps.newHashMap();
                args.put(predicate.getName(), String.join(",", predicate.getArgs()));
                PredicateDefinition definition = new PredicateDefinition();
                definition.setArgs(args);
                definition.setName(predicate.getName());
                return definition;
            }).collect(toList());
            routeDefinition.setPredicates(predicates);
        }
        if (CollectionUtil.isNotEmpty(rule.getFilters())) {
            List<FilterDefinition> filters = rule.getFilters().stream().map(filter -> {
                FilterDefinition definition = new FilterDefinition();
                final Map<String, String> args = filter.getArgs().stream()
                        .collect(toMap(RouteRule.Filter.FilterArg::getKey, RouteRule.Filter.FilterArg::getValue));
                definition.setArgs(args);
                definition.setName(filter.getName());
                return definition;
            }).collect(toList());
            routeDefinition.setFilters(filters);
        }
        if (services.contains(rule.getName())) {
            redisRouteDynamicGatewayService.saveOrUpdate(routeDefinition);
            rule.setStatus(true);
            stringRedisTemplate.opsForHash().put(KEY, rule.getId(), JSON.toJSONString(rule));
            return true;
        }
        return false;
    }

    /**
     * @param id     路由ID
     * @param status true = 上线 false = 下线
     */
    public void routeHandler(String id, Boolean status) {
        final Object object = stringRedisTemplate.opsForHash().get(KEY, id);
        if (object == null) {
            throw CheckedException.notFound("路由不存在");
        }
        if (status) {
            final boolean publish = publish(id);
            if (!publish) {
                throw CheckedException.badRequest("发布失败,服务未注册");
            }
        } else {
            RouteRule rule = JSON.parseObject(object.toString(), RouteRule.class);
            rule.setStatus(null);
            rule.setStatus(false);
            stringRedisTemplate.opsForHash().put(KEY, rule.getId(), JSON.toJSONString(rule));
            redisRouteDynamicGatewayService.delete(id);
        }
    }

    public List<RouteRule> query() {
        final CompositeRouteDefinitionLocator routeDefinitionLocator = SpringUtil.getBean(CompositeRouteDefinitionLocator.class);
        List<RouteDefinition> routeDefinitions = Lists.newArrayList();
        routeDefinitionLocator.getRouteDefinitions().subscribe(routeDefinitions::add);
        log.debug("所有路由 - {}", JSON.toJSONString(routeDefinitions));
        final List<String> services = discoveryClient.getServices();
        List<RouteRule> routeRules = stringRedisTemplate.opsForHash().keys(KEY).stream()
                .map(id -> {
                    Object object = stringRedisTemplate.opsForHash().get(KEY, id);
                    if (object == null) {
                        return null;
                    }
                    RouteRule rule = JSON.parseObject(object.toString(), RouteRule.class);
                    if (rule.getStatus() == null) {
                        rule.setStatus(false);
                    } else if (rule.getStatus()) {
                        rule.setStatus(services.contains(rule.getName()));
                    }
                    if (!rule.getStatus()) {
                        redisRouteDynamicGatewayService.delete(String.valueOf(id));
                    }
                    rule.setDynamic(true);
                    return rule;
                }).collect(toList());
        final List<String> idList = routeRules.stream().map(RouteRule::getId).collect(toList());
        for (RouteDefinition routeDefinition : routeDefinitions) {
            if (idList.contains(routeDefinition.getId())) {
                continue;
            }
            if (StringUtils.contains(routeDefinition.getId(), "CompositeDiscoveryClient_")) {
                continue;
            }
            RouteRule rule = new RouteRule();
            rule.setId(routeDefinition.getId());
            rule.setStatus(true);
            rule.setUri(routeDefinition.getUri().toString());
            rule.setOrder(routeDefinition.getOrder());
            rule.setName(StringUtils.defaultString(routeDefinition.getUri().getHost(), rule.getUri().replace("lb:ws://", "").replace("lb:wss://", "")));
            rule.setDynamic(false);
            final List<RouteRule.Filter> filters = routeDefinition.getFilters().stream().map(filterDefinition -> {
                List<RouteRule.Filter.FilterArg> args = filterDefinition.getArgs().entrySet().stream().map(entry -> RouteRule.Filter.FilterArg.builder()
                        .key(entry.getKey()).value(entry.getValue()).build()).collect(toList());
                return RouteRule.Filter.builder().args(args).name(filterDefinition.getName()).build();
            }).collect(toList());
            rule.setFilters(filters);
            routeRules.add(rule);
        }
        return routeRules;
    }

    public void delete(String id) {
        stringRedisTemplate.opsForHash().delete(KEY, id);
        redisRouteDynamicGatewayService.delete(id);
    }
}
