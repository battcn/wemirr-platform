package com.wemirr.platform.authority.configuration.provider;

import cn.hutool.core.util.StrUtil;
import com.google.common.collect.Sets;
import com.wemirr.framework.security.client.exception.Auth2Exception;
import com.wemirr.platform.authority.domain.entity.OAuthClientDetails;
import com.wemirr.platform.authority.mapper.OAuthClientDetailsMapper;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.apache.commons.lang3.StringUtils;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.context.annotation.Primary;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.provider.ClientDetails;
import org.springframework.security.oauth2.provider.ClientDetailsService;
import org.springframework.security.oauth2.provider.client.BaseClientDetails;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * 自定义 redis 缓存实现的 ClientDetailsService
 *
 * @author Levin
 */
@Service
@Primary
@RequiredArgsConstructor
public class CacheClientDetailsServiceImpl implements ClientDetailsService {

    private final PasswordEncoder passwordEncoder;
    private final OAuthClientDetailsMapper oAuthClientDetailsMapper;

    @SneakyThrows
    @Override
    @Cacheable(value = "clients", key = "#clientId", unless = "#result == null")
    public ClientDetails loadClientByClientId(String clientId) {
        final OAuthClientDetails details = this.oAuthClientDetailsMapper.selectById(clientId);
        if (Objects.isNull(details)) {
            throw new Auth2Exception("client_id 或 client_secret 错误", HttpStatus.BAD_REQUEST.value());
        }
        BaseClientDetails clientDetails = new BaseClientDetails();
        clientDetails.setClientId(details.getClientId());
        clientDetails.setClientSecret(passwordEncoder.encode(details.getClientSecret()));
        if (StringUtils.isNotBlank(details.getResourceIds())) {
            clientDetails.setResourceIds(StrUtil.split(details.getResourceIds(), ','));
        }
        if (StringUtils.isNotBlank(details.getAuthorizedGrantTypes())) {
            clientDetails.setAuthorizedGrantTypes(StrUtil.split(details.getAuthorizedGrantTypes(), ','));
        }
        if (StringUtils.isNotBlank(details.getScope())) {
            clientDetails.setScope(StrUtil.split(details.getScope(), ','));
        }
        if (StringUtils.isNotBlank(details.getAutoApprove())) {
            clientDetails.setAutoApproveScopes(StrUtil.split(details.getAutoApprove(), ','));
        }
        if (StringUtils.isNotBlank(details.getWebServerRedirectUri())) {
            final HashSet<String> registeredRedirectUris = Sets.newHashSet(StrUtil.split(details.getWebServerRedirectUri(), ','));
            clientDetails.setRegisteredRedirectUri(registeredRedirectUris);
        }
        if (StringUtils.isNotBlank(details.getAuthorities())) {
            List<SimpleGrantedAuthority> authorities = StrUtil.split(details.getAuthorities(), ',')
                    .stream().distinct().map(SimpleGrantedAuthority::new)
                    .collect(Collectors.toList());
            clientDetails.setAuthorities(authorities);
        }
        return clientDetails;
    }

}
