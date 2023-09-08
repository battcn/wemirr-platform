package com.wemirr.framework.security.server.handler;

import com.alibaba.fastjson.JSONObject;
import com.google.common.collect.Maps;
import com.wemirr.framework.commons.entity.Result;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.core.OAuth2AccessToken;
import org.springframework.security.oauth2.server.authorization.authentication.OAuth2AccessTokenAuthenticationToken;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.time.temporal.ChronoUnit;
import java.util.Map;
import java.util.Objects;

/**
 * 登录成功处理类
 *
 * @author Levin
 */
@Slf4j
public class LoginSuccessHandler implements AuthenticationSuccessHandler {

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException {
        log.info("登录成功 - {}", JSONObject.toJSONString(authentication));
        Map<String, Object> tokenInfo = Maps.newLinkedHashMap();
        if (authentication instanceof OAuth2AccessTokenAuthenticationToken toke) {
            final OAuth2AccessToken accessToken = toke.getAccessToken();
            tokenInfo.put("access_token", accessToken.getTokenValue());
            tokenInfo.put("token_type", accessToken.getTokenType().getValue());
            if (Objects.nonNull(toke.getRefreshToken())) {
                tokenInfo.put("refresh_token", toke.getRefreshToken().getTokenValue());
            }
            tokenInfo.put("scope", accessToken.getScopes());
            long between = ChronoUnit.SECONDS.between(Objects.requireNonNull(accessToken.getIssuedAt()), accessToken.getExpiresAt());
            tokenInfo.put("expires_in", between);
        }
        final Result<Map<String, Object>> success = Result.success(tokenInfo);
        response.setCharacterEncoding(StandardCharsets.UTF_8.name());
        response.setContentType(MediaType.APPLICATION_JSON_VALUE);
        response.getWriter().write(success.toString());
        response.getWriter().flush();
    }

}
