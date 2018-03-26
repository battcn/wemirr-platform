package com.battcn.bookstore.consumer.security.model.token;

import com.battcn.bookstore.consumer.security.config.TokenProperties;
import com.battcn.bookstore.consumer.security.model.MemberSecurityContext;
import com.battcn.bookstore.consumer.security.model.Scopes;
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

    private final TokenProperties properties;

    @Autowired
    public TokenFactory(TokenProperties properties) {
        this.properties = properties;
    }

    /**
     * 利用JJWT 生成 Token
     *
     * @param memberSecurityContext 上下文
     * @return 授权的Token
     */
    public AccessToken createAccessToken(MemberSecurityContext memberSecurityContext) {
        Optional.ofNullable(memberSecurityContext.getAccountName()).orElseThrow(() -> new IllegalArgumentException("Cannot create Token without username"));
        Optional.ofNullable(memberSecurityContext.getAuthorities()).orElseThrow(() -> new IllegalArgumentException("User doesn't have any privileges"));
        Claims claims = Jwts.claims().setSubject(memberSecurityContext.getAccountName());
        claims.put("scopes", memberSecurityContext.getAuthorities().stream().map(Object::toString).collect(toList()));
        LocalDateTime currentTime = LocalDateTime.now();
        String token = Jwts.builder()
                .setClaims(claims)
                .setIssuer(properties.getIssuer())
                .setIssuedAt(Date.from(currentTime.atZone(ZoneId.systemDefault()).toInstant()))
                .setExpiration(Date.from(currentTime
                        .plusMinutes(properties.getExpirationTime())
                        .atZone(ZoneId.systemDefault()).toInstant()))
                .signWith(SignatureAlgorithm.HS512, properties.getSigningKey())
                .compact();
        return new AccessToken(token, claims);
    }

    /**
     * 生成 刷新 RefreshToken
     *
     * @param memberSecurityContext 上下文
     * @return Token
     */
    public Token createRefreshToken(MemberSecurityContext memberSecurityContext) {
        if (StringUtils.isBlank(memberSecurityContext.getAccountName())) {
            throw new IllegalArgumentException("Cannot create Token without username");
        }
        LocalDateTime currentTime = LocalDateTime.now();
        Claims claims = Jwts.claims().setSubject(memberSecurityContext.getAccountName());
        claims.put("scopes", Collections.singletonList(Scopes.REFRESH_TOKEN.authority()));
        String token = Jwts.builder()
                .setClaims(claims)
                .setIssuer(properties.getIssuer())
                .setId(UUID.randomUUID().toString())
                .setIssuedAt(Date.from(currentTime.atZone(ZoneId.systemDefault()).toInstant()))
                .setExpiration(Date.from(currentTime
                        .plusMinutes(properties.getRefreshExpTime())
                        .atZone(ZoneId.systemDefault()).toInstant()))
                .signWith(SignatureAlgorithm.HS512, properties.getSigningKey())
                .compact();

        return new AccessToken(token, claims);
    }
}
