//package com.wemirr.platform.gateway.strategy;
//
//import com.nepxion.discovery.plugin.strategy.adapter.DefaultDiscoveryEnabledStrategy;
//import com.netflix.loadbalancer.Server;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.context.annotation.Primary;
//import org.springframework.stereotype.Component;
//
///**
// * 实现了组合策略，版本路由策略+区域路由策略+IP地址和端口路由策略+自定义策略
// *
// * @author Levin
// */
//@Slf4j
//@Primary
//@Component
//public class DynamicDiscoveryEnabledStrategy extends DefaultDiscoveryEnabledStrategy {
//
//    @Override
//    public boolean apply(Server server) {
//        String serviceId = pluginAdapter.getServerServiceId(server);
//        String version = pluginAdapter.getServerVersion(server);
//        String region = pluginAdapter.getServerRegion(server);
//        String environment = pluginAdapter.getServerEnvironment(server);
//        String address = server.getHost() + ":" + server.getPort();
//        String mobile = strategyContextHolder.getHeader("mobile");
//        log.info("负载均衡用户定制触发：mobile={}, serviceId={}, version={}, region={}, env={}, address={}", mobile, serviceId, version, region, environment, address);
//        return true;
//    }
//}
