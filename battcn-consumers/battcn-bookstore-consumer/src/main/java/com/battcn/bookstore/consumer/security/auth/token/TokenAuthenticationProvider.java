package com.battcn.bookstore.consumer.security.auth.token;

import com.battcn.bookstore.consumer.security.auth.AuthenticationToken;
import com.battcn.bookstore.consumer.security.config.TokenProperties;
import com.battcn.bookstore.consumer.security.model.MemberSecurityContext;
import com.battcn.bookstore.consumer.security.model.token.RawAccessToken;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Component;

import java.util.List;

import static java.util.stream.Collectors.toList;

/**
 * 使用 {@link AuthenticationProvider} 的接口提供实现 {@link com.battcn.bookstore.consumer.security.model.token.Token} 身份验证的实例
 *
 * @author Levin
 * @since 2017-05-25
 */
@Slf4j
@Component
public class TokenAuthenticationProvider implements AuthenticationProvider {

    private final TokenProperties tokenProperties;

    @Autowired
    public TokenAuthenticationProvider(TokenProperties tokenProperties) {
        this.tokenProperties = tokenProperties;
    }

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        RawAccessToken rawAccessToken = (RawAccessToken) authentication.getCredentials();
        long startTime = System.currentTimeMillis();
        Jws<Claims> jwsClaims = rawAccessToken.parseClaims(tokenProperties.getSigningKey());
        log.debug("[验证Token消耗时间] - [{}]", (System.currentTimeMillis() - startTime));
        String subject = jwsClaims.getBody().getSubject();
        @SuppressWarnings("unchecked")
        List<String> scopes = jwsClaims.getBody().get("scopes", List.class);
        List<GrantedAuthority> authorities = scopes.stream().map(SimpleGrantedAuthority::new).collect(toList());
        MemberSecurityContext context = MemberSecurityContext.create(subject, authorities);
        return new AuthenticationToken(context, context.getAuthorities());
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return (AuthenticationToken.class.isAssignableFrom(authentication));
    }
}
