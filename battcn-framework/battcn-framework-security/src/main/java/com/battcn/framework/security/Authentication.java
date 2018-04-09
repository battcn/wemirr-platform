package com.battcn.framework.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.commons.lang3.StringUtils;

import java.util.List;

/**
 * @author Levin
 * @version 3.0.4
 * @since 2018-01-20
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Authentication implements java.io.Serializable {

    private static final long serialVersionUID = 5940220889405231039L;

    private String authId;
    private String principal;
    private List<String> authorities;

    public static Authentication create(String authId, String principal, List<String> authorities) {
        if (StringUtils.isBlank(authId)) {
            throw new IllegalArgumentException("authId is blank: " + authId);
        }
        if (StringUtils.isBlank(principal)) {
            throw new IllegalArgumentException("Username is blank: " + principal);
        }
        return new Authentication(authId, principal, authorities);
    }

    public static Authentication create(Jws<Claims> jwsClaims) {
        final Claims claims = jwsClaims.getBody();
        String principal = claims.getSubject();
        if (StringUtils.isBlank(principal)) {
            throw new IllegalArgumentException("Username is blank: " + principal);
        }
        String authId = claims.get("authId", String.class);
        if (StringUtils.isBlank(authId)) {
            throw new IllegalArgumentException("authId is blank: " + authId);
        }
        @SuppressWarnings("unchecked")
        List<String> scopes = claims.get("scopes", List.class);
        return new Authentication(authId, principal, scopes);
    }


}
