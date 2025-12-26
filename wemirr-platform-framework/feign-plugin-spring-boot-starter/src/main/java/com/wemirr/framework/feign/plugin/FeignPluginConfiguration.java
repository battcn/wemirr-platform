/*
 * Copyright (c) 2023 WEMIRR-PLATFORM Authors. All Rights Reserved.
 *
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.wemirr.framework.feign.plugin;

import com.wemirr.framework.feign.plugin.decoder.FeignResponseDecoder;
import com.wemirr.framework.feign.plugin.mock.FeignPluginInterceptor;
import com.wemirr.framework.feign.plugin.mock.MockLoadBalancerFeignClient;
import com.wemirr.framework.feign.plugin.mock.MockProperties;
import feign.Client;
import feign.Logger;
import feign.codec.Decoder;
import feign.codec.ErrorDecoder;
import feign.optionals.OptionalDecoder;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.ObjectFactory;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.autoconfigure.http.HttpMessageConverters;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.loadbalancer.*;
import org.springframework.cloud.loadbalancer.support.LoadBalancerClientFactory;
import org.springframework.cloud.openfeign.loadbalancer.LoadBalancerFeignRequestTransformer;
import org.springframework.cloud.openfeign.support.HttpMessageConverterCustomizer;
import org.springframework.cloud.openfeign.support.ResponseEntityDecoder;
import org.springframework.cloud.openfeign.support.SpringDecoder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.annotation.Order;
import org.springframework.web.client.RestTemplate;

import java.util.List;

/**
 * @author Levin
 */
@Slf4j
@Configuration
@EnableConfigurationProperties(value = {FeignPluginProperties.class, MockProperties.class})
public class FeignPluginConfiguration {

    @Bean
    public Logger.Level feignLoggerLevel(FeignPluginProperties properties) {
        log.info("=============================== Feign Full Logger =============================== ");
        return properties.getLevel() != null ? properties.getLevel() : Logger.Level.BASIC;
    }

    @Bean
    public LoadBalancerLifecycle<Object, Object, ServiceInstance> logIpWhenError() {
        return new LoadBalancerLifecycle<>() {

            @Override
            public void onStart(Request<Object> request) {
                // 请求开始前，暂时不需要做啥
                log.debug("request => {}", request);
            }

            @Override
            public void onStartRequest(Request<Object> request, Response<ServiceInstance> lbResponse) {
                // 选完 IP，发起请求前。如果想看这次选了谁，也可以在这里打日志
                ServiceInstance instance = lbResponse.getServer();
                log.debug("instance => {}", instance);
            }

            @Override
            public void onComplete(CompletionContext<Object, ServiceInstance, Object> completionContext) {
                ServiceInstance instance = completionContext.getLoadBalancerResponse().getServer();
                if (instance == null) {
                    return;
                }
                if (completionContext.status() == CompletionContext.Status.FAILED) {
                    Throwable error = completionContext.getThrowable();
                    log.error("LoadBalancer调用失败 - 目标服务: {} , 地址: {}:{} - 异常信息: {}", instance.getServiceId(), instance.getHost(), instance.getPort(),
                            error != null ? error.getMessage() : "未知错误", error);
                } else {
                    log.debug("LoadBalancer调用成功 - 目标服务: {} , 地址: {}:{}", instance.getServiceId(), instance.getHost(), instance.getPort());
                }
            }
        };
    }

    @Bean
    @Primary
    @LoadBalanced
    public RestTemplate lbRestTemplate() {
        return new RestTemplate();
    }

    @Bean
    public Decoder feignDecoder(ObjectFactory<HttpMessageConverters> messageConverters,
                                ObjectProvider<HttpMessageConverterCustomizer> customizers) {
        return new OptionalDecoder((new ResponseEntityDecoder(new FeignResponseDecoder(new SpringDecoder(messageConverters, customizers)))));
    }

    @Bean
    public ErrorDecoder errorDecoder() {
        return (s, response) -> {
            log.warn("response status is:{}", response.status());
            return new ErrorDecoder.Default().decode(s, response);
        };
    }

    @Bean
    @Primary
    @ConditionalOnProperty(prefix = MockProperties.MOCK_PREFIX, name = "enabled", havingValue = "true")
    public Client feignClient(LoadBalancerClient loadBalancerClient, LoadBalancerClientFactory loadBalancerClientFactory,
                              List<LoadBalancerFeignRequestTransformer> transformers,
                              MockProperties mockProperties) {
        return new MockLoadBalancerFeignClient(new Client.Default(null, null),
                loadBalancerClient, loadBalancerClientFactory, transformers, mockProperties);
    }

    @Bean
    @Order(-999999)
    public FeignPluginInterceptor feignPluginInterceptor(FeignPluginProperties properties) {
        return new FeignPluginInterceptor(properties);
    }
}
