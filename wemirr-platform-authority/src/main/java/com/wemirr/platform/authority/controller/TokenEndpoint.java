package com.wemirr.platform.authority.controller;

import com.wemirr.framework.commons.exception.CheckedException;
import com.wemirr.framework.db.TenantEnvironment;
import com.wemirr.framework.security.domain.UserInfoDetails;
import com.wemirr.platform.authority.domain.req.ChangePasswordReq;
import com.wemirr.platform.authority.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.server.authorization.OAuth2Authorization;
import org.springframework.security.oauth2.server.authorization.OAuth2AuthorizationService;
import org.springframework.security.oauth2.server.authorization.OAuth2TokenType;
import org.springframework.security.oauth2.server.resource.authentication.BearerTokenAuthentication;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.Objects;

/**
 * @author Levin
 */
@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping
public class TokenEndpoint {

    private final TenantEnvironment tenantEnvironment;
    private final UserService userService;
    private final OAuth2AuthorizationService oAuth2AuthorizationService;

    @GetMapping("/oauth2/check_token")
    public Authentication checkToken() {
        return SecurityContextHolder.getContext().getAuthentication();
    }

    @GetMapping("/oauth2/userinfo")
    public Object userinfo(Principal principal) {
        // 账号密码模式登陆
        if (principal instanceof UsernamePasswordAuthenticationToken token) {
            if (token.getPrincipal() instanceof UserInfoDetails user) {
                return user;
            }
        }
        tenantEnvironment.tenantId();
        if (principal instanceof BearerTokenAuthentication token) {
            if (token.getPrincipal() instanceof UserInfoDetails user) {
                return user;
            }
        }
        if (principal instanceof JwtAuthenticationToken token) {
            final String tokenValue = token.getToken().getTokenValue();
            final OAuth2Authorization oAuth2Authorization = oAuth2AuthorizationService.findByToken(tokenValue, OAuth2TokenType.ACCESS_TOKEN);
            final UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = (UsernamePasswordAuthenticationToken) Objects.requireNonNull(oAuth2Authorization).getAttributes().get(Principal.class.getName());
            return usernamePasswordAuthenticationToken.getPrincipal();
        }
        return principal;
    }

    @PutMapping("/change_password")
    @Operation(summary = "修改密码")
    public void changePassword(@Validated @RequestBody ChangePasswordReq dto) {
        if (!StringUtils.equals(dto.getPassword(), dto.getConfirmPassword())) {
            throw CheckedException.badRequest("新密码与确认密码不一致");
        }
        final Long userId = tenantEnvironment.userId();
        this.userService.changePassword(userId, dto.getOriginalPassword(), dto.getPassword());
    }

    @DeleteMapping("/oauth2/logout")
    @Operation(summary = "退出登录")
    public void logout(Principal principal) {
        if (principal instanceof JwtAuthenticationToken token) {
            final String tokenValue = token.getToken().getTokenValue();
            oAuth2AuthorizationService.remove(oAuth2AuthorizationService.findByToken(tokenValue, OAuth2TokenType.ACCESS_TOKEN));
        }
    }


}
