package com.wemirr.platform.authority.service.impl;

import cn.hutool.core.collection.CollUtil;
import com.wemirr.framework.db.mybatis.SuperServiceImpl;
import com.wemirr.platform.authority.domain.entity.baseinfo.RegisteredClientRef;
import com.wemirr.platform.authority.domain.req.RegisteredClientRefReq;
import com.wemirr.platform.authority.repository.RegisteredClientRefMapper;
import com.wemirr.platform.authority.service.RegisteredClientRefService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.oauth2.core.AuthorizationGrantType;
import org.springframework.security.oauth2.server.authorization.client.RegisteredClient;
import org.springframework.security.oauth2.server.authorization.client.RegisteredClientRepository;
import org.springframework.security.oauth2.server.authorization.settings.ClientSettings;
import org.springframework.security.oauth2.server.authorization.settings.TokenSettings;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.util.List;

/**
 * @author Levin
 */
@Service
@RequiredArgsConstructor
public class RegisteredClientRefServiceImpl extends SuperServiceImpl<RegisteredClientRefMapper, RegisteredClientRef> implements RegisteredClientRefService {

    private final RegisteredClientRepository registeredClientRepository;

    @Override
    public void registeredClient(RegisteredClientRefReq req) {
        final RegisteredClientRefReq.TokenSettingsReq token = req.getTokenSettings();
        final RegisteredClient registeredClient = RegisteredClient.withId(req.getClientId()).clientName(req.getClientName())
                .clientId(req.getClientId()).clientIdIssuedAt(req.getClientIdIssuedAt())
                .clientSecret(req.getClientSecret()).clientSecretExpiresAt(req.getClientSecretExpiresAt())
                .authorizationGrantTypes((grantTypes) -> {
                    if (CollUtil.isNotEmpty(req.getAuthorizationGrantTypes())) {
                        final List<AuthorizationGrantType> list = req.getAuthorizationGrantTypes().stream().map(AuthorizationGrantType::new).toList();
                        grantTypes.addAll(list);
                    }
                })
                .redirectUri(req.getRedirectUris())
                .postLogoutRedirectUri(req.getRedirectUris())
                .scopes((x) -> x.addAll(req.getScopes()))
                .tokenSettings(TokenSettings.builder()
                        .accessTokenTimeToLive(Duration.ofMinutes(token.getAccessTokenTimeToLive()))
                        .refreshTokenTimeToLive(Duration.ofMinutes(token.getRefreshTokenTimeToLive()))
//                        .authorizationCodeTimeToLive(Duration.ofMinutes(ObjUtil.defaultIfNull(token.getAuthorizationCodeTimeToLive(), 0L)))
//                        .deviceCodeTimeToLive(Duration.ofMinutes(ObjUtil.defaultIfNull(token.getDeviceCodeTimeToLive(), 0L)))
                        .build())
                .clientSettings(ClientSettings.builder().requireAuthorizationConsent(true).build())
                .build();
        registeredClientRepository.save(registeredClient);

    }

    @Override
    public void deleteById(String id) {
        this.baseMapper.removeById(id);
    }
}
