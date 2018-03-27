package com.battcn.framework.security.model.token;

import com.battcn.framework.security.exceptions.ExpiredTokenException;
import com.battcn.framework.security.exceptions.InvalidTokenException;
import io.jsonwebtoken.*;
import lombok.extern.slf4j.Slf4j;

/**
 * 原授权Token
 *
 * @author Levin
 * @since 2018/03/23
 */
@Slf4j
public class RawAccessToken implements Token {


    private String token;

    public RawAccessToken(String token) {
        this.token = token;
    }

    /**
     * 分析并且验证Token是否有效
     *
     * @throws InvalidTokenException 如果验证请求被拒绝，则因为凭据无效 <br> 对于要抛出的异常，它意味着该帐户既不锁定也不禁用。 <br>
     * @throws ExpiredTokenException 过期的Token
     */
    Jws<Claims> parseClaims(String signingKey) {
        try {
            return Jwts.parser().setSigningKey(signingKey).parseClaimsJws(this.token);
        } catch (UnsupportedJwtException | MalformedJwtException | IllegalArgumentException | SignatureException ex) {
            log.error("Invalid Token", ex);
            throw new InvalidTokenException("Invalid token: ", ex);
        } catch (ExpiredJwtException expiredEx) {
            log.info("Token is expired", expiredEx);
            throw new ExpiredTokenException(this, "Token expired", expiredEx);
        }
    }

    @Override
    public String getToken() {
        return token;
    }
}
