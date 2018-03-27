package com.battcn.framework.security.model.token;


import com.battcn.framework.security.Authentication;
import com.battcn.framework.security.SecurityTokenProperties;
import com.battcn.framework.security.model.Scopes;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Collections;
import java.util.Date;
import java.util.Optional;
import java.util.UUID;

import static java.util.stream.Collectors.toList;

/**
 * Token创建工厂类 {@link Token}.
 *
 * @author Levin
 * @since 2017-05-25
 */
@Component
public class TokenFactory {

    private final SecurityTokenProperties securityTokenProperties;

    @Autowired
    public TokenFactory(SecurityTokenProperties securityTokenProperties) {
        this.securityTokenProperties = securityTokenProperties;
    }

    /**
     * 利用JJWT 生成 Token
     *
     * @param authentication 上下文
     * @return 授权的Token
     */
    public AccessToken createAccessToken(Authentication authentication) {
        Optional.ofNullable(authentication.getPrincipal()).orElseThrow(() -> new IllegalArgumentException("Cannot create Token without username"));
        Optional.ofNullable(authentication.getAuthorities()).orElseThrow(() -> new IllegalArgumentException("User doesn't have any privileges"));
        Claims claims = Jwts.claims().setSubject(authentication.getPrincipal());
        claims.put("scopes", authentication.getAuthorities().stream().map(Object::toString).collect(toList()));
        LocalDateTime currentTime = LocalDateTime.now();
        String token = Jwts.builder()
                .setClaims(claims)
                .setIssuer(securityTokenProperties.getIssuer())
                .setIssuedAt(Date.from(currentTime.atZone(ZoneId.systemDefault()).toInstant()))
                .setExpiration(Date.from(currentTime
                        .plusMinutes(securityTokenProperties.getExpirationTime())
                        .atZone(ZoneId.systemDefault()).toInstant()))
                .signWith(SignatureAlgorithm.HS512, securityTokenProperties.getSigningKey())
                .compact();
        return new AccessToken(token, claims);
    }

    /**
     * 生成 刷新 RefreshToken
     *
     * @param authentication 上下文
     * @return Token
     */
    public Token createRefreshToken(Authentication authentication) {
        if (StringUtils.isBlank(authentication.getPrincipal())) {
            throw new IllegalArgumentException("Cannot create Token without username");
        }
        LocalDateTime currentTime = LocalDateTime.now();
        Claims claims = Jwts.claims().setSubject(authentication.getPrincipal());
        claims.put("scopes", Collections.singletonList(Scopes.REFRESH_TOKEN.authority()));
        String token = Jwts.builder()
                .setClaims(claims)
                .setIssuer(securityTokenProperties.getIssuer())
                .setId(UUID.randomUUID().toString())
                .setIssuedAt(Date.from(currentTime.atZone(ZoneId.systemDefault()).toInstant()))
                .setExpiration(Date.from(currentTime
                        .plusMinutes(securityTokenProperties.getRefreshExpTime())
                        .atZone(ZoneId.systemDefault()).toInstant()))
                .signWith(SignatureAlgorithm.HS512, securityTokenProperties.getSigningKey())
                .compact();
        return new AccessToken(token, claims);
    }
}
