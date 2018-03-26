package com.battcn.bookstore.consumer.security.model.token;

import com.battcn.bookstore.consumer.security.model.Scopes;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.util.CollectionUtils;

import java.util.List;
import java.util.Optional;

/**
 * Token刷新工具类
 *
 * @author Levin
 * @since 2017-08-15
 */
public class RefreshToken implements Token {

    private Jws<Claims> claims;

    private RefreshToken(Jws<Claims> claims) {
        this.claims = claims;
    }

    /**
     * 创建和验证刷新令牌
     *
     * @param token      Token
     * @param signingKey 签名
     * @return 刷新
     * @throws BadCredentialsException BadCredentialsException
     */
    public static Optional<RefreshToken> create(RawAccessToken token, String signingKey) {
        Jws<Claims> claims = token.parseClaims(signingKey);
        @SuppressWarnings("unchecked")
        List<String> scopes = claims.getBody().get("scopes", List.class);
        if (CollectionUtils.isEmpty(scopes) || scopes.stream().noneMatch(scope -> Scopes.REFRESH_TOKEN.authority().equals(scope))) {
            return Optional.empty();
        }
        return Optional.of(new RefreshToken(claims));
    }

    @Override
    public String getToken() {
        return null;
    }

    public Jws<Claims> getClaims() {
        return claims;
    }

    public String getJti() {
        return claims.getBody().getId();
    }

    public String getAccountName() {
        return claims.getBody().getSubject();
    }
}
