package com.wemirr.platform.gateway.config;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

/**
 * @author Levin
 * 聚合接口文档注册，和 gateway 实现类似
 */
@Slf4j
@Primary
@Configuration
@AllArgsConstructor
@EnableConfigurationProperties(IgnoreProperties.class)
public class SwaggerProvider {

    private static final String API_URI = "/v3/api-docs";

    private final IgnoreProperties ignoreProperties;

//    @Bean
//    public RouterFunction<ServerResponse> routerFunction() {
//        return RouterFunctions.route()
//                .GET("/v3/api-docs/wemirr-platform-{serviceId}", request -> {
//                    final String serviceId = request.pathVariable("serviceId");
//                    return ServerResponse.temporaryRedirect(URI.create("http://localhost:9000/" + serviceId + API_URI)).build();
//                }).build();
//    }
//
//    @Bean
//    @Lazy(false)
//    public List<GroupedOpenApi> groupedOpenApis(SwaggerUiConfigParameters swaggerUiConfigParameters, RouteDefinitionLocator locator) {
//        List<GroupedOpenApi> groups = new ArrayList<>();
//        List<RouteDefinition> definitions = locator.getRouteDefinitions().collectList().block();
//        if (CollectionUtil.isEmpty(definitions)) {
//            return groups;
//        }
//        final List<RouteDefinition> routeDefinitions = definitions.stream()
//                .filter(definition -> !StringUtils.contains(definition.getId(), "websocket"))
//                .filter(definition -> !ignoreProperties.getSwaggerProviders().contains(definition.getId()))
//                .filter(definition -> definition.getPredicates().stream()
//                        .anyMatch(predicateDefinition -> "Path".equalsIgnoreCase(predicateDefinition.getName()))).collect(Collectors.toList());
//        for (RouteDefinition routeDefinition : routeDefinitions) {
//            for (PredicateDefinition predicateDefinition : routeDefinition.getPredicates()) {
//                String pathsToMatch = predicateDefinition.getArgs().get(NameUtils.GENERATED_NAME_PREFIX + "0").replace("/**", API_URI);
//                groups.add(GroupedOpenApi.builder().group(routeDefinition.getId()).pathsToMatch(pathsToMatch).build());
//                // 上面的group 不生效，具体情况不清楚（如果能）
//                swaggerUiConfigParameters.addGroup(routeDefinition.getId());
//            }
//        }
//        return groups;
//    }
}
