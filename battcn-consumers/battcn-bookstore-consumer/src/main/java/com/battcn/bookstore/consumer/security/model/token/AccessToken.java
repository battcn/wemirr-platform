package com.battcn.bookstore.consumer.security.model.token;

import io.jsonwebtoken.Claims;

/**
 * 验证Token
 *
 * @author Levin
 */
public final class AccessToken implements Token {

    private final String rawToken;
    private Claims claims;

    AccessToken(final String token, Claims claims) {
        this.rawToken = token;
        this.claims = claims;
    }

    public String getToken() {
        return this.rawToken;
    }

    public Claims getClaims() {
        return claims;
    }
}
