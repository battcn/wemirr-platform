package com.wemirr.platform.authority.domain.converts;

import cn.hutool.core.util.StrUtil;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.Module;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.wemirr.framework.db.page.BasePageConverts;
import com.wemirr.platform.authority.domain.entity.baseinfo.RegisteredClientRef;
import com.wemirr.platform.authority.domain.resp.RegisteredClientRefResp;
import lombok.SneakyThrows;
import org.springframework.security.jackson2.SecurityJackson2Modules;
import org.springframework.security.oauth2.server.authorization.client.JdbcRegisteredClientRepository;
import org.springframework.security.oauth2.server.authorization.jackson2.OAuth2AuthorizationServerJackson2Module;
import org.springframework.security.oauth2.server.authorization.settings.TokenSettings;

import java.util.List;
import java.util.Map;

/**
 * @author Levin
 */
public class RegisteredClientRefConverts {

    public static final RegisteredClientRefConverts.RegisteredClientRef2RespConverts REGISTERED_CLIENT_REF_2_RESP_CONVERTS = new RegisteredClientRefConverts.RegisteredClientRef2RespConverts();

    public static class RegisteredClientRef2RespConverts implements BasePageConverts<RegisteredClientRef, RegisteredClientRefResp> {

        @SneakyThrows
        @Override
        public RegisteredClientRefResp convert(RegisteredClientRef source) {
            if (source == null) {
                return null;
            }
            RegisteredClientRefResp target = new RegisteredClientRefResp();
            target.setId(source.getId());
            target.setClientName(source.getClientName());
            target.setClientId(source.getClientId());
            target.setClientIdIssuedAt(source.getClientIdIssuedAt());
            target.setClientName(source.getClientName());
            target.setClientSecretExpiresAt(source.getClientSecretExpiresAt());
            target.setAuthorizationGrantTypes(StrUtil.split(source.getAuthorizationGrantTypes(), ','));
            target.setRedirectUris(source.getRedirectUris());
            target.setPostLogoutRedirectUris(source.getPostLogoutRedirectUris());
            target.setScopes(StrUtil.split(source.getScopes(), ','));
            final String tokenSettings = source.getTokenSettings();
            if (StrUtil.isNotBlank(tokenSettings)) {
                ObjectMapper objectMapper = new ObjectMapper();
                ClassLoader classLoader = JdbcRegisteredClientRepository.class.getClassLoader();
                List<Module> securityModules = SecurityJackson2Modules.getModules(classLoader);
                objectMapper.registerModules(securityModules);
                objectMapper.registerModule(new OAuth2AuthorizationServerJackson2Module());
                final Map<String, Object> readValue = objectMapper.readValue(tokenSettings, new TypeReference<>() {
                });
                final TokenSettings settings = TokenSettings.withSettings(readValue).build();
                target.setAccessTokenTimeToLive(settings.getAccessTokenTimeToLive().toMinutes());
                target.setRefreshTokenTimeToLive(settings.getRefreshTokenTimeToLive().toMinutes());
                target.setAuthorizationCodeTimeToLive(settings.getAuthorizationCodeTimeToLive().toMinutes());
                target.setDeviceCodeTimeToLive(settings.getDeviceCodeTimeToLive().toMinutes());
            }
            return target;
        }
    }

}
