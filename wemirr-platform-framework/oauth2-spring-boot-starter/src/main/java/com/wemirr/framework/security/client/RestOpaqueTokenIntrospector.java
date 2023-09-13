package com.wemirr.framework.security.client;

import com.wemirr.framework.security.entity.UserInfoDetails;
import com.wemirr.framework.security.exception.OAuth2InvalidException;
import com.wemirr.framework.security.properties.CustomSecurityProperties;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.*;
import org.springframework.security.oauth2.core.OAuth2AuthenticatedPrincipal;
import org.springframework.security.oauth2.server.resource.introspection.OpaqueTokenIntrospector;
import org.springframework.web.client.RestTemplate;

/**
 * @author Levin
 */
@Slf4j
@RequiredArgsConstructor
public class RestOpaqueTokenIntrospector implements OpaqueTokenIntrospector {

    private final RestTemplate restTemplate;
    private final CustomSecurityProperties properties;
    private static final String ERROR_MESSAGE = "No instances available for";

    @Override
    public OAuth2AuthenticatedPrincipal introspect(String token) {
        HttpHeaders headers = new HttpHeaders();
        headers.setBearerAuth(token);
        headers.set("rewrite", "0");
        final CustomSecurityProperties.OpaqueToken opaqueToken = properties.getClient().getOpaqueToken();
        HttpEntity<String> httpEntity = new HttpEntity<>(headers);
        try {
            final ResponseEntity<UserInfoDetails> response = restTemplate.exchange(opaqueToken.getUserinfo(), HttpMethod.GET, httpEntity, UserInfoDetails.class);
            if (response.getStatusCode() != HttpStatus.OK || response.getBody() == null) {
                log.error("请求失败 - {}", response.getBody());
                throw new OAuth2InvalidException("Token 异常,访问受限");
            }
            return response.getBody();
        } catch (Exception ex) {
            log.error("服务访问失败 - {}", ex.getLocalizedMessage());
            if (ex.getLocalizedMessage().contains(ERROR_MESSAGE)) {
                throw new OAuth2InvalidException("鉴权服务异常,请稍后再试");
            }
            throw new OAuth2InvalidException("Token 异常,访问受限");
        }
    }
}
