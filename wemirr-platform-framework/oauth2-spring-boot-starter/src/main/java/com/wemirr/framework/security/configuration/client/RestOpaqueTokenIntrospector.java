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
package com.wemirr.framework.security.configuration.client;

import com.wemirr.framework.security.configuration.SecurityExtProperties;
import com.wemirr.framework.security.domain.UserInfoDetails;
import com.wemirr.framework.security.exception.OAuth2InvalidException;
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
    private final SecurityExtProperties properties;
    private static final String ERROR_MESSAGE = "No instances available for";
    
    @Override
    public OAuth2AuthenticatedPrincipal introspect(String token) {
        HttpHeaders headers = new HttpHeaders();
        headers.setBearerAuth(token);
        headers.set("rewrite", "0");
        final SecurityExtProperties.OpaqueToken opaqueToken = properties.getClient().getOpaqueToken();
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
