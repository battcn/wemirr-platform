package com.wemirr.platform.gateway.rest;

import com.wemirr.platform.gateway.route.RedisRouteDynamicGatewayService;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.gateway.route.RouteDefinition;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

import javax.annotation.Resource;


/**
 * @author Levin
 */
@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/routes")
@Tag(name = "路由管理", description = "动态路由")
public class GatewayDynamicRouteController {

    @Resource
    private StringRedisTemplate stringRedisTemplate;
    @Resource
    private RedisRouteDynamicGatewayService redisRouteDynamicGatewayService;

    @ApiOperation("路由创建 - [DONE]")
    @PostMapping
    public ResponseEntity<Void> create(@RequestBody RouteDefinition model) {
        redisRouteDynamicGatewayService.create(model);
        return ResponseEntity.ok().build();
    }

    @ApiOperation("路由变更 - [DONE]")
    @PutMapping
    public ResponseEntity<Void> update(@RequestBody RouteDefinition model) {
        redisRouteDynamicGatewayService.update(model);
        return ResponseEntity.ok().build();
    }

    @ApiOperation("路由状态变更 - [DONE]")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "路由ID", example = "gateway", paramType = "path"),
            @ApiImplicitParam(name = "status", value = "状态(0 正常;1 删除)", allowableValues = "0,1", paramType = "path"),
    })
    @PatchMapping("/{id}/{status}")
    public Mono<ResponseEntity<RouteDefinition>> status(@PathVariable String id, @PathVariable Integer status) {

        return null;
    }

    @ApiOperation("路由详情 - [DONE]")
    @GetMapping("/{id}")
    public Mono<ResponseEntity<Object>> route(@PathVariable String id) {
        final Object gatewayDynamicRoute = this.stringRedisTemplate.opsForHash().get("gateway_dynamic_route", id);
        return Mono.just(ResponseEntity.ok(gatewayDynamicRoute));
    }


    @ApiOperation("路由刷新 - [DONE]")
    @PostMapping("/refresh")
    public Mono<ResponseEntity<RouteDefinition>> refresh() {
        return null;
    }

    @ApiOperation("删除路由 - [DONE]")
    @DeleteMapping("/{id}")
    public Mono<ResponseEntity<Object>> delete(@PathVariable String id) {
        return this.redisRouteDynamicGatewayService.delete(id);
    }


}
