package com.wemirr.framework.feign.plugin.mock;


import feign.Client;
import feign.Request;
import feign.Response;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.IOUtils;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.cloud.client.loadbalancer.LoadBalancerProperties;
import org.springframework.cloud.loadbalancer.support.LoadBalancerClientFactory;
import org.springframework.cloud.openfeign.loadbalancer.FeignBlockingLoadBalancerClient;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.util.CollectionUtils;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.net.URLDecoder;
import java.util.*;

/**
 * @author Levin
 */
@Slf4j
public class MockLoadBalancerFeignClient extends FeignBlockingLoadBalancerClient {

    private final MockProperties mockProperties;

    public MockLoadBalancerFeignClient(Client.Default delegate, LoadBalancerClient loadBalancerClient,
                                       LoadBalancerProperties properties, LoadBalancerClientFactory loadBalancerClientFactory, MockProperties mockProperties) {

        super(delegate, loadBalancerClient, properties, loadBalancerClientFactory);
        this.mockProperties = mockProperties;
        log.info("mock feign 负载均衡器初始化");
    }

    /**
     * 2. 请求的服务在mock服务列表中,则请求走mock服务器
     */
    @Override
    public Response execute(Request request, Request.Options options) throws IOException {
        String url = request.url();
        URI uri = URI.create(url);
        final String host = uri.getHost();
        final Map<String, MockProperties.Server> servicesMap = mockProperties.getServerMap();
        if (servicesMap.containsKey(host)) {
            final MockProperties.Server server = servicesMap.get(host);
            final Map<String, Collection<String>> feignHeaders = new LinkedHashMap<>(request.headers());
            final Collection<String> requestOriginList = feignHeaders.get(REQUEST_ORIGIN_KEY);
            if (!CollectionUtils.isEmpty(requestOriginList) && requestOriginList.contains(REQUEST_ORIGIN_VALUE)) {
                feignHeaders.remove("content-type");
                feignHeaders.put(HttpHeaders.CONTENT_TYPE, Collections.singleton("application/json"));
                log.debug("doc.html origin,remove content-type");
            }
            Request newRequest = Request.create(request.httpMethod(), url.replace(host, server.getServerUrl()), feignHeaders,
                    request.body(), request.charset(), null);
            return getResponse(newRequest, server);
        }
        return super.execute(request, options);
    }

    private static final String REQUEST_ORIGIN_KEY = "request-origion";
    private static final String REQUEST_ORIGIN_VALUE = "SwaggerBootstrapUi";

    /**
     * 请求响应
     */
    private Response getResponse(Request request, MockProperties.Server server) throws IOException {
        HttpHeaders headers = new HttpHeaders();
        headers.put(HttpHeaders.CONTENT_TYPE, Collections.singletonList("application/json"));
        final Map<String, Collection<String>> requestHeaders = request.headers();
        for (Map.Entry<String, Collection<String>> entry : requestHeaders.entrySet()) {
            headers.put(entry.getKey(), new ArrayList<>(entry.getValue()));
        }
        RestTemplate template = new RestTemplate();
        HttpEntity<byte[]> entity = new HttpEntity<>(request.body(), headers);
        final HttpMethod httpMethod = Objects.requireNonNull(HttpMethod.resolve(request.httpMethod().name()));
        final ResponseEntity<String> exchange = template.exchange(URLDecoder.decode(request.url(), "UTF-8"), httpMethod, entity, String.class);
        final String body = exchange.getBody();
        InputStream inputStream = null;
        if (Objects.nonNull(body)) {
            log.debug("响应结果 - {}", body);
            inputStream = IOUtils.toInputStream(body);
        }
        return Response.builder().request(request)
                .body(inputStream == null ? new byte[0] : IOUtils.toByteArray(Objects.requireNonNull(inputStream)))
                .headers(request.headers())
                .status(exchange.getStatusCode().value()).build();
    }

}