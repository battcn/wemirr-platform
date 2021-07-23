package com.wemirr.platform.gateway.route;

import lombok.RequiredArgsConstructor;
import org.springframework.cloud.gateway.event.RefreshRoutesEvent;
import org.springframework.cloud.gateway.route.RouteDefinition;
import org.springframework.cloud.gateway.support.NotFoundException;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.http.ResponseEntity;
import reactor.core.publisher.Mono;

/**
 * @author Levin
 */
@RequiredArgsConstructor
public class RedisRouteDynamicGatewayService {

    private final ApplicationEventPublisher applicationEventPublisher;
    private final RedisRouteDefinitionRepository redisRouteDefinitionRepository;

    public void saveOrUpdate(RouteDefinition routeDefinition) {
        final String id = routeDefinition.getId();
        final RouteDefinition definition = redisRouteDefinitionRepository.find(id);
        if (definition != null) {
            redisRouteDefinitionRepository.delete(Mono.just(id));
        }
        redisRouteDefinitionRepository.save(Mono.just(routeDefinition)).subscribe();
        applicationEventPublisher.publishEvent(new RefreshRoutesEvent(this));

    }

    public void create(RouteDefinition routeDefinition) {
        redisRouteDefinitionRepository.save(Mono.just(routeDefinition)).subscribe();
        applicationEventPublisher.publishEvent(new RefreshRoutesEvent(this));
    }

    public void update(RouteDefinition routeDefinition) {
        redisRouteDefinitionRepository.delete(Mono.just(routeDefinition.getId()));
        redisRouteDefinitionRepository.save(Mono.just(routeDefinition)).subscribe();
        applicationEventPublisher.publishEvent(new RefreshRoutesEvent(this));
    }

    public void delete(String id) {
        redisRouteDefinitionRepository.delete(Mono.just(id)).then(Mono.defer(() -> Mono.just(ResponseEntity.ok().build())))
                .onErrorResume(t -> t instanceof NotFoundException, t -> Mono.just(ResponseEntity.notFound().build())).subscribe();
        applicationEventPublisher.publishEvent(new RefreshRoutesEvent(this));
    }

}
