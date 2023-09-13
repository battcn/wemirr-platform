package com.wemirr.framework.security.service;

import com.wemirr.framework.security.configuration.server.support.IntegrationAuthentication;
import com.wemirr.framework.security.configuration.server.support.IntegrationAuthenticator;
import com.wemirr.framework.security.domain.UserInfoDetails;
import com.wemirr.framework.security.exception.OAuth2InvalidException;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import java.util.List;
import java.util.Optional;

/**
 * <p>
 * 基础用户信息表 服务实现类
 * </p>
 *
 * @author Levin
 */
@RequiredArgsConstructor
public class IntegrationUserDetailsServiceImpl implements UserDetailsService {

    private final List<IntegrationAuthenticator> authenticators;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        // 获取当前request
        HttpServletRequest request = Optional.ofNullable(RequestContextHolder.getRequestAttributes()).map(req -> ((ServletRequestAttributes) req).getRequest()).orElseThrow(() -> new OAuth2InvalidException("Failed to get the current request."));
        final IntegrationAuthentication authentication = new IntegrationAuthentication(request);
        return Optional.ofNullable(this.authenticate(authentication)).orElseThrow(() -> new UsernameNotFoundException("用户名或密码错误"));
    }

    private UserInfoDetails authenticate(IntegrationAuthentication integrationAuthentication) {
        if (this.authenticators == null) {
            return null;
        }
        for (IntegrationAuthenticator authenticator : authenticators) {
            if (authenticator.support(integrationAuthentication)) {
                return authenticator.authenticate(integrationAuthentication);
            }
        }
        return null;
    }
}
