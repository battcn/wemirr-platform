package com.battcn.bookstore.consumer.security.auth.login;

import com.alibaba.fastjson.JSONObject;
import com.battcn.bookstore.consumer.security.model.MemberSecurityContext;
import com.battcn.bookstore.consumer.security.model.token.AccessToken;
import com.battcn.bookstore.consumer.security.model.token.Token;
import com.battcn.bookstore.consumer.security.model.token.TokenFactory;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.WebAttributes;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;


/**
 * 认证成功处理程序
 *
 * @author Levin
 * @since 2017-05-25
 */
@Component
public class AwareAuthenticationSuccessHandler implements AuthenticationSuccessHandler {

    private final ObjectMapper objectMapper;
    private final TokenFactory tokenFactory;

    @Autowired
    public AwareAuthenticationSuccessHandler(final ObjectMapper objectMapper, final TokenFactory tokenFactory) {
        this.objectMapper = objectMapper;
        this.tokenFactory = tokenFactory;
    }

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
                                        Authentication authentication) throws IOException, ServletException {
        MemberSecurityContext memberSecurityContext = (MemberSecurityContext) authentication.getPrincipal();

        AccessToken accessToken = tokenFactory.createAccessToken(memberSecurityContext);
        Token refreshToken = tokenFactory.createRefreshToken(memberSecurityContext);

        JSONObject tokenMap = new JSONObject();
        tokenMap.put("claims", accessToken.getClaims());
        tokenMap.put("token", accessToken.getToken());
        tokenMap.put("refreshToken", refreshToken.getToken());

        response.setStatus(HttpStatus.OK.value());
        response.setContentType(MediaType.APPLICATION_JSON_VALUE);
        objectMapper.writeValue(response.getWriter(), tokenMap);

        clearAuthenticationAttributes(request);
    }

    /**
     * 删除 session 中临时验证身份的相关数据
     */
    private void clearAuthenticationAttributes(HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        if (session == null) {
            return;
        }
        session.removeAttribute(WebAttributes.AUTHENTICATION_EXCEPTION);
    }
}
