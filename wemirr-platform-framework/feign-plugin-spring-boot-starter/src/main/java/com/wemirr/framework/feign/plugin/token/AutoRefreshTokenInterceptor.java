package com.wemirr.framework.feign.plugin.token;

import cn.hutool.core.util.StrUtil;
import cn.hutool.http.HttpUtil;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.google.common.cache.Cache;
import feign.RequestInterceptor;
import feign.RequestTemplate;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import java.nio.charset.Charset;
import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Optional;

/**
 * @author Levin
 */
@Slf4j
@RequiredArgsConstructor
public class AutoRefreshTokenInterceptor implements RequestInterceptor {


    private final AutoRefreshTokenProperties properties;
    private final RestTemplate lbRestTemplate;
    private final Cache<String, String> tokenCache;

    @SneakyThrows
    @Override
    public void apply(RequestTemplate template) {
        final Map<String, Collection<String>> headers = template.headers();
        if (headers.containsKey(properties.getIncludeTokenHeader())) {
            return;
        }
        final String tokenHeader = properties.getServerTokenHeader();
        template.header(tokenHeader, tokenCache.get(tokenHeader, this::loadCache));
    }

    private String loadCache() {
        final HttpMethod method = Optional.ofNullable(HttpMethod.resolve(properties.getMethod())).orElse(HttpMethod.GET);
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
        if (properties.isLoadBalance()) {
            restTemplate = lbRestTemplate;
        }
        HttpEntity<MultiValueMap<String, Object>> entity = new HttpEntity<>(new LinkedMultiValueMap<>());
        final String url = HttpUtil.urlWithForm(properties.getUri(), params, Charset.defaultCharset(), true);
        final ResponseEntity<String> exchange = restTemplate.exchange(url, method, entity, String.class);
        JSONObject object = JSON.parseObject(exchange.getBody());
        log.info("自动获取Token响应结果 - {}", object.toJSONString());
        final String accessToken = object.getString("access_token");
        if (StrUtil.isBlank(accessToken)) {
            return null;
        }
        return "bearer " + accessToken;
    }
}
