package com.battcn.bookstore.consumer.controller.authentication;


import com.alibaba.dubbo.config.annotation.Reference;
import com.battcn.bookstore.consumer.security.auth.token.extractor.TokenExtractor;
import com.battcn.bookstore.consumer.security.config.TokenProperties;
import com.battcn.bookstore.consumer.security.config.WebSecurityConfig;
import com.battcn.bookstore.consumer.security.exceptions.InvalidTokenException;
import com.battcn.bookstore.consumer.security.model.MemberSecurityContext;
import com.battcn.bookstore.consumer.security.model.token.RawAccessToken;
import com.battcn.bookstore.consumer.security.model.token.RefreshToken;
import com.battcn.bookstore.consumer.security.model.token.Token;
import com.battcn.bookstore.consumer.security.model.token.TokenFactory;
import com.battcn.member.facade.MemberService;
import com.battcn.member.pojo.po.Member;
import com.google.common.collect.Lists;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.Optional;

/**
 * 认证
 *
 * @author Levin
 * @since 2018/3/21 0021
 */
@RestController
@RequestMapping(value = "/auth", produces = MediaType.APPLICATION_JSON_VALUE)
@Api(value = "授权管理", description = "授权管理", tags = "1.0")
public class AuthenticationController {

    private final TokenProperties tokenProperties;
    private final TokenFactory tokenFactory;
    private final TokenExtractor tokenExtractor;

    @Reference(version = "1.0.0",
            application = "${dubbo.application.id}",
            url = "dubbo://localhost:20880", timeout = 10000)
    private MemberService memberService;

    @Autowired
    public AuthenticationController(TokenProperties tokenProperties, TokenFactory tokenFactory, TokenExtractor tokenExtractor) {
        this.tokenProperties = tokenProperties;
        this.tokenFactory = tokenFactory;
        this.tokenExtractor = tokenExtractor;
    }

    @PostMapping("/refresh")
    public Token refreshToken(HttpServletRequest request) {
        String tokenPayload = tokenExtractor.extract(request.getHeader(WebSecurityConfig.TOKEN_HEADER_PARAM));
        RawAccessToken rawToken = new RawAccessToken(tokenPayload);
        RefreshToken refreshToken = RefreshToken.create(rawToken, tokenProperties.getSigningKey()).orElseThrow(() -> new InvalidTokenException("Token验证失败"));
        String accountName = refreshToken.getAccountName();
        Member member = Optional.ofNullable(memberService.findByName(accountName)).orElseThrow(() -> new UsernameNotFoundException("用户未找到: " + accountName));
        MemberSecurityContext memberSecurityContext = MemberSecurityContext.create(accountName, Lists.newArrayList(new SimpleGrantedAuthority(member.getRoleName())));
        return tokenFactory.createAccessToken(memberSecurityContext);
    }


}
