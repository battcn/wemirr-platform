package com.wemirr.platform.gateway.route;

import com.alibaba.fastjson2.JSON;
import com.alibaba.fastjson2.JSONObject;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.gateway.route.RouteDefinition;
import org.springframework.cloud.gateway.route.RouteDefinitionRepository;
import org.springframework.cloud.gateway.support.NotFoundException;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;

import static java.util.stream.Collectors.toList;

/**
 * @author Levin
 */
@Slf4j
@Component
@RequiredArgsConstructor
public class RedisRouteDefinitionRepository implements RouteDefinitionRepository {

    /**
     * 存储的的key
     */
    private final static String KEY = "gateway_dynamic_route";
    private final StringRedisTemplate redisTemplate;

    public RouteDefinition find(String routeId) {
        final Object object = redisTemplate.opsForHash().get(KEY, routeId);
        if (object == null) {
            return null;
        }
        log.debug("[动态路由信息] - [{}]", object);
        return JSON.parseObject(object.toString(), RouteDefinition.class);
    }


    /**
     * 获取路由信息
     *
     * @return Flux
     */
    @Override
    public Flux<RouteDefinition> getRouteDefinitions() {
        final List<RouteDefinition> gatewayRouteEntityList = redisTemplate.opsForHash().keys(KEY).stream()
                .map(routeId -> find(String.valueOf(routeId))).collect(toList());
        return Flux.fromIterable(gatewayRouteEntityList);
    }

    /**
     * 新增
     *
     * @param route route
     * @return Mono
     */
    @Override
    public Mono<Void> save(Mono<RouteDefinition> route) {
        return route.flatMap(routeDefinition -> {
            redisTemplate.opsForHash().put(KEY, routeDefinition.getId(), JSONObject.toJSONString(routeDefinition));
            return Mono.empty();
        });
    }

    /**
     * 删除
     *
     * @param routeId routeId
     * @return Mono
     */
    @Override
    public Mono<Void> delete(Mono<String> routeId) {
        return routeId.flatMap(id -> {
            if (redisTemplate.opsForHash().hasKey(KEY, id)) {
                redisTemplate.opsForHash().delete(KEY, id);
                return Mono.empty();
            }
            return Mono.defer(() -> Mono.error(new NotFoundException("route definition is not found, routeId:" + routeId)));
        });
    }
}
