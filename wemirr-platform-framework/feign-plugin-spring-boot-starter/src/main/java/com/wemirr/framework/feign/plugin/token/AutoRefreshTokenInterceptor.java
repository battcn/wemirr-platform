package com.wemirr.framework.feign.plugin.token;

import cn.hutool.core.util.StrUtil;
import cn.hutool.extra.spring.SpringUtil;
import cn.hutool.http.HttpUtil;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.google.common.cache.Cache;
import feign.RequestInterceptor;
import feign.RequestTemplate;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.nio.charset.Charset;
import java.util.*;

/**
 * @author Levin
 */
@Slf4j
@RequiredArgsConstructor
public class AutoRefreshTokenInterceptor implements RequestInterceptor {


    private final AutoRefreshTokenProperties properties;
    private final Cache<String, String> tokenCache;

    @SneakyThrows
    @Override
    public void apply(RequestTemplate template) {
        final Map<String, Collection<String>> headers = template.headers();
        if (!headers.containsKey(properties.getIncludeTokenHeader())) {
            return;
        }
        final String tokenHeader = properties.getServerTokenHeader();
        if (template.headers().containsKey(tokenHeader)) {
            log.debug("当前上下文中已包含token,跳过自动获取流程...");
            return;
        }
        template.header(tokenHeader, tokenCache.get(tokenHeader, this::loadCache));
    }

    @SneakyThrows
    private String loadCache() {
        final HttpMethod method = Optional.of(HttpMethod.valueOf(properties.getMethod())).orElse(HttpMethod.GET);
        final AutoRefreshTokenProperties.OAuth auth = properties.getOAuth();
        //设置访问参数
        Map<String, Object> params = new LinkedHashMap<>();
        params.put("client_id", auth.getClientId());
        params.put("client_secret", auth.getClientSecret());
        params.put("username", auth.getUsername());
        params.put("password", auth.getPassword());
        params.put("tenant_code", auth.getTenantCode());
        params.put("grant_type", auth.getGrantType());
        params.put("scope", auth.getScope());
        RestTemplate restTemplate = new RestTemplate();
        HttpEntity<?> entity = new HttpEntity<>(params, new LinkedMultiValueMap<>());
        String url = properties.getUri();
        if (properties.isLoadBalance()) {
            // 没找到好方案,只能用这种笨办法了
            final DiscoveryClient client = SpringUtil.getBean(DiscoveryClient.class);
            final URI uri = new URI(properties.getUri());
            final String serviceId = uri.getHost();
            final ServiceInstance instance = client.getInstances(serviceId).get(0);
            final String hostAndPort = instance.getHost() + ":" + instance.getPort();
            url = StrUtil.replace(url, serviceId, hostAndPort);
        }
        final String exchangeUrl = HttpUtil.urlWithForm(url, params, Charset.defaultCharset(), true);
        final ResponseEntity<String> exchange = restTemplate.exchange(exchangeUrl, method, entity, String.class);
        final JSONObject object = JSON.parseObject(exchange.getBody());
        if (Objects.isNull(object)) {
            return null;
        }
        log.info("自动获取Token响应结果 - {}", object.toJSONString());
        final String accessToken = object.getString("access_token");
        if (StrUtil.isBlank(accessToken)) {
            return null;
        }
        return "bearer " + accessToken;
    }
}
