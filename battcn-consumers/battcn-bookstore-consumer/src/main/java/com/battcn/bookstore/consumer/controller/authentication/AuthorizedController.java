package com.battcn.bookstore.consumer.controller.authentication;


import com.alibaba.dubbo.config.annotation.Reference;
import com.alibaba.fastjson.JSONObject;

import com.battcn.framework.exception.CustomException;
import com.battcn.framework.security.Authentication;
import com.battcn.framework.security.TokenProperties;
import com.battcn.framework.security.exceptions.InvalidTokenException;
import com.battcn.framework.security.extractor.TokenExtractor;
import com.battcn.framework.security.model.MemberSecurityContext;
import com.battcn.framework.security.model.token.*;
import com.battcn.member.facade.MemberService;
import com.battcn.member.pojo.po.Member;
import com.google.common.collect.Lists;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

/**
 * 认证
 *
 * @author Levin
 * @since 2018/3/21 0021
 */
@RestController
@RequestMapping(value = "/authorized", produces = MediaType.APPLICATION_JSON_VALUE)
@Api(value = "授权管理", description = "授权管理", tags = "1.0")
public class AuthorizedController {

    /**
     * Token存放的请求头
     */
    private static final String TOKEN_HEADER_PARAM = "X-Authorization";

    @Reference(version = "1.0.0",
            application = "${dubbo.application.id}",
            url = "dubbo://localhost:20880", timeout = 10000)
    private MemberService memberService;


    private final TokenProperties tokenProperties;
    private final TokenFactory tokenFactory;
    private final TokenExtractor tokenExtractor;

    @Autowired
    public AuthorizedController(TokenProperties tokenProperties, TokenFactory tokenFactory, TokenExtractor tokenExtractor) {
        this.tokenProperties = tokenProperties;
        this.tokenFactory = tokenFactory;
        this.tokenExtractor = tokenExtractor;
    }

    @PostMapping(value = "/token", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ApiOperation(value = "登陆")
    public JSONObject loginAuthorized(@RequestBody MemberSecurityContext context) {
        final String username = context.getUsername();
        final String password = context.getPassword();
        Member member = Optional.ofNullable(memberService.findByName(username)).orElseThrow(() -> CustomException.badRequest("用户未找到: " + username));
        if (!StringUtils.equals(password, member.getPassword())) {
            throw CustomException.forbidden();
        }
        Authentication authentication = new Authentication();
        authentication.setAuthId(member.getMemberNo());
        authentication.setAuthorities(Lists.newArrayList(member.getRoleName()));
        authentication.setPrincipal(member.getAccountName());
        AccessToken accessToken = tokenFactory.createAccessToken(authentication);
        Token refreshToken = tokenFactory.createRefreshToken(authentication);
        JSONObject result = new JSONObject();
        result.put("claims", accessToken.getClaims());
        result.put("token", accessToken.getToken());
        result.put("refreshToken", refreshToken.getToken());
        return result;
    }

    @PutMapping("/refresh")
    @ApiOperation(value = "刷新Token")
    public Token refreshToken(@RequestHeader(TOKEN_HEADER_PARAM) String payload) {
        String tokenPayload = tokenExtractor.extract(payload);
        RawAccessToken rawToken = new RawAccessToken(tokenPayload);
        RefreshToken refreshToken = RefreshToken.create(rawToken, tokenProperties.getSigningKey()).orElseThrow(() -> new InvalidTokenException("Token验证失败"));
        String accountName = refreshToken.getAccountName();
        Member member = Optional.ofNullable(memberService.findByName(accountName)).orElseThrow(() -> CustomException.badRequest("用户未找到: " + accountName));
        Authentication authentication = Authentication.create(accountName, Lists.newArrayList(member.getRoleName()));
        return tokenFactory.createAccessToken(authentication);
    }

}
