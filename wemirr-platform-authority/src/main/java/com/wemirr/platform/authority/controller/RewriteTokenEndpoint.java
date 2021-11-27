package com.wemirr.platform.authority.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.wemirr.framework.commons.StringUtils;
import com.wemirr.framework.commons.entity.Result;
import com.wemirr.framework.commons.exception.CheckedException;
import com.wemirr.framework.db.TenantEnvironment;
import com.wemirr.framework.security.client.utils.SecurityUtils;
import com.wemirr.platform.authority.domain.dto.ChangePasswordDTO;
import com.wemirr.platform.authority.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.EqualsAndHashCode;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.IOUtils;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.common.OAuth2RefreshToken;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.endpoint.TokenEndpoint;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;

import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.security.Principal;
import java.util.Map;

/**
 * 重写响应端点。主要是为了封装响应结果
 *
 * @author Levin
 */
@Slf4j
@Controller
@Tag(name = "认证管理", description = "认证管理")
@RequestMapping("/oauth")
@RequiredArgsConstructor
public class RewriteTokenEndpoint {

    private final TenantEnvironment tenantEnvironment;
    private final TokenEndpoint tokenEndpoint;
    private final UserService userService;
    private final TokenStore tokenStore;

    @ResponseBody
    @RequestMapping(value = "/token", method = {RequestMethod.GET, RequestMethod.POST})
    public SwaggerEnhancer<OAuth2AccessToken> postAccessToken(Principal principal, @RequestParam Map<String, String> parameters, HttpServletRequest request) throws HttpRequestMethodNotSupportedException {
        final Result<OAuth2AccessToken> success = Result.success(tokenEndpoint.postAccessToken(principal, parameters).getBody());
        SwaggerEnhancer<OAuth2AccessToken> r = new SwaggerEnhancer<>(success.getCode(), success.getData(), success.getMessage());
        r.setTimestamp(System.currentTimeMillis());
        // 说明是swagger 来的请求，那么增强实现
        r.setAccessToken(success.getData().getValue());
        return r;
    }


    /**
     * @author battcn
     */
    @EqualsAndHashCode(callSuper = true)
    public static class SwaggerEnhancer<T> extends Result<T> {
        @JsonInclude(JsonInclude.Include.NON_NULL)
        @JsonProperty(value = "access_token")
        private String accessToken;

        public SwaggerEnhancer(int code, T data, String message) {
            super(code, data, message);
        }

        public String getAccessToken() {
            return accessToken;
        }

        public void setAccessToken(String accessToken) {
            this.accessToken = accessToken;
        }
    }

    @DeleteMapping("/logout")
    @ResponseBody
    public void removeToken() {
        if (SecurityUtils.anonymous()) {
            return;
        }
        final OAuth2AccessToken accessToken = tokenStore.getAccessToken(SecurityUtils.getAuthentication());
        if (accessToken == null) {
            return;
        }
        tokenStore.removeAccessToken(accessToken);
        final OAuth2RefreshToken refreshToken = tokenStore.readRefreshToken(accessToken.getValue());
        if (refreshToken != null) {
            tokenStore.removeRefreshToken(refreshToken);
        }
    }

    /**
     * 支持 POST 流传输方式
     *
     * @param request request
     * @return 重定向到 OAUTH 端点
     */
    @PostMapping("/access_token")
    public RedirectView login(HttpServletRequest request) throws IOException {
        final ServletInputStream inputStream = request.getInputStream();
        final String string = IOUtils.toString(inputStream, StandardCharsets.UTF_8.name());
        JSONObject object = JSON.parseObject(string);
        RedirectView view = new RedirectView("/oauth/token");
        view.addStaticAttribute("grant_type", object.getString("grantType"));
        view.addStaticAttribute("scope", object.getString("scope"));
        view.addStaticAttribute("username", object.getString("username"));
        view.addStaticAttribute("password", object.getString("password"));
        view.addStaticAttribute("client_id", object.getString("clientId"));
        view.addStaticAttribute("client_secret", object.getString("clientSecret"));
        return view;
    }

    @ResponseBody
    @GetMapping("/info")
    @Operation(summary = "获取当前用户信息")
    public Result<Object> userInfo(Principal principal) {
        OAuth2Authentication oAuth2Authentication = (OAuth2Authentication) principal;
        return Result.success(oAuth2Authentication.getPrincipal());
    }

    @ResponseBody
    @GetMapping("/users")
    @Operation(summary = "获取当前用户信息")
    public Principal users(Principal principal) {
        return principal;
    }

    @ResponseBody
    @PutMapping("/change_password")
    @Operation(summary = "修改密码")
    public void changePassword(@Validated @RequestBody ChangePasswordDTO dto) {
        if (!StringUtils.equals(dto.getPassword(), dto.getConfirmPassword())) {
            throw CheckedException.badRequest("新密码与确认密码不一致");
        }
        final Long userId = tenantEnvironment.userId();
        this.userService.changePassword(userId, dto.getOriginalPassword(), dto.getPassword());
    }
}
