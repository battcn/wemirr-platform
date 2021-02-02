package com.wemirr.platform.gateway.config;

import com.alibaba.fastjson.JSON;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cloud.gateway.config.GatewayProperties;
import org.springframework.cloud.gateway.route.RouteDefinition;
import org.springframework.cloud.gateway.route.RouteDefinitionRepository;
import org.springframework.cloud.gateway.support.NameUtils;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;
import springfox.documentation.swagger.web.SwaggerResource;
import springfox.documentation.swagger.web.SwaggerResourcesProvider;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Levin
 * 聚合接口文档注册，和 gateway 实现类似
 */
@Slf4j
@Primary
@Component
@EnableConfigurationProperties(IgnoreProperties.class)
@AllArgsConstructor
public class SwaggerProvider implements SwaggerResourcesProvider {


    private static final String API_URI = "/v2/api-docs";


    @Resource
    private RouteDefinitionRepository redisRouteDefinitionRepository;
    private final IgnoreProperties ignoreProperties;
    private final GatewayProperties gatewayProperties;

    @Override
    public List<SwaggerResource> get() {
        List<RouteDefinition> routes = gatewayProperties.getRoutes();
        List<SwaggerResource> resources = new ArrayList<>();
        redisRouteDefinitionRepository.getRouteDefinitions().subscribe(routes::add);
        routes.forEach(routeDefinition -> routeDefinition.getPredicates().stream()
                .filter(predicateDefinition -> "Path".equalsIgnoreCase(predicateDefinition.getName()))
                .filter(predicateDefinition -> !ignoreProperties.getSwaggerProviders().contains(routeDefinition.getId()))
                .forEach(predicateDefinition -> resources.add(swaggerResource(routeDefinition.getId(),
                        predicateDefinition.getArgs().get(NameUtils.GENERATED_NAME_PREFIX + "0").replace("/**", API_URI)))));
        List<SwaggerResource> swaggerResources = resources.stream().sorted(Comparator.comparing(SwaggerResource::getName))
                .collect(Collectors.toList());
        log.debug("[SwaggerResource] - [{}]", JSON.toJSONString(swaggerResources));
        return swaggerResources;
    }

    private SwaggerResource swaggerResource(String name, String location) {
        SwaggerResource swaggerResource = new SwaggerResource();
        swaggerResource.setName(name);
        swaggerResource.setLocation(location);
        swaggerResource.setSwaggerVersion("2.0");
        return swaggerResource;
    }
}
