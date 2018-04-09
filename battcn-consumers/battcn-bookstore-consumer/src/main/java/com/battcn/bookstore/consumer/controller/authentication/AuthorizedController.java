package com.battcn.bookstore.consumer.controller.authentication;


import com.alibaba.dubbo.config.annotation.Reference;
import com.alibaba.fastjson.JSONObject;
import com.battcn.bookstore.consumer.enums.AuthorizedEnum;
import com.battcn.framework.exception.CustomException;
import com.battcn.framework.redis.sequence.SequenceGenerator;
import com.battcn.framework.redis.sequence.SequenceType;
import com.battcn.framework.security.Authentication;
import com.battcn.framework.security.Environments;
import com.battcn.framework.security.annotation.IgnoreAuthenticate;
import com.battcn.framework.security.model.MemberSecurityContext;
import com.battcn.framework.security.model.token.AccessToken;
import com.battcn.framework.security.model.token.Token;
import com.battcn.framework.security.model.token.TokenFactory;
import com.battcn.member.facade.MemberService;
import com.battcn.member.pojo.po.Member;
import com.google.common.collect.Lists;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.http.MediaType;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.io.Serializable;
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


    @Reference(version = "1.0.0",
            application = "${dubbo.application.id}",
            url = "dubbo://localhost:20880", timeout = 10000)
    private MemberService memberService;

    private final SequenceGenerator sequenceGenerator;
    private final RedisTemplate<String, Serializable> redisCacheTemplate;
    private final TokenFactory tokenFactory;

    @Autowired
    public AuthorizedController(SequenceGenerator sequenceGenerator, TokenFactory tokenFactory, RedisTemplate<String, Serializable> redisCacheTemplate) {
        this.sequenceGenerator = sequenceGenerator;
        this.tokenFactory = tokenFactory;
        this.redisCacheTemplate = redisCacheTemplate;
    }

    @IgnoreAuthenticate
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
        authentication.setPrincipal(member.getUsername());
        AccessToken accessToken = tokenFactory.createAccessToken(authentication);
        Token refreshToken = tokenFactory.createRefreshToken(authentication);
        JSONObject result = new JSONObject();
        result.put("claims", accessToken.getClaims());
        result.put("token", accessToken.getToken());
        result.put("refreshToken", refreshToken.getToken());
        return result;
    }

    @IgnoreAuthenticate
    @PostMapping(value = "/register/{client_id}/{code}", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ApiOperation(value = "注册")
    public JSONObject registerAuthorized(@PathVariable("client_id") String clientId, @PathVariable("code") String code, @Validated @RequestBody MemberSecurityContext context) {
        final HashOperations<String, String, String> opsForHash = redisCacheTemplate.opsForHash();
        final String cacheCode = opsForHash.get(AuthorizedEnum.REDIS_CAPTCHA_HASH.getKey(), clientId);
        if (!StringUtils.equals(code, cacheCode)) {
            throw CustomException.badRequest("验证码错误");
        }
        final String memberNo = sequenceGenerator.generateSequence(SequenceType.MR);
        Member member = new Member();
        member.setMemberNo(memberNo);
        member.setUsername(context.getUsername());
        member.setPassword(context.getPassword());
        member.setRoleName("MEMBER");
        this.memberService.insertSelective(member);
        // 验证成功删除临时Token
        opsForHash.delete(AuthorizedEnum.REDIS_CAPTCHA_HASH.getKey(), clientId);
        // 模拟登陆
        return loginAuthorized(context);
    }

    @PutMapping("/refresh")
    @ApiOperation(value = "刷新Token")
    public Token refreshToken() {
        Member member = Optional.ofNullable(memberService.selectById(Environments.getAuthId())).orElseThrow(() -> CustomException.badRequest("用户未找到"));
        Authentication authentication = Authentication.create(member.getMemberNo(), member.getUsername(), Lists.newArrayList(member.getRoleName()));
        return tokenFactory.createAccessToken(authentication);
    }

}
