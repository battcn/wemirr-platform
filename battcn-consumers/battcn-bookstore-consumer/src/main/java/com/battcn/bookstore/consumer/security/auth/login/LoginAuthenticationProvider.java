package com.battcn.bookstore.consumer.security.auth.login;

import com.alibaba.dubbo.config.annotation.Reference;
import com.alibaba.fastjson.JSON;
import com.battcn.bookstore.consumer.security.model.MemberSecurityContext;
import com.battcn.member.facade.MemberService;
import com.battcn.member.pojo.po.Member;
import com.google.common.collect.Lists;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;

import java.util.Optional;


/**
 * @author Levin
 * @since 2017-05-25
 */
@Slf4j
@Component
public class LoginAuthenticationProvider implements AuthenticationProvider {

    @Reference(version = "1.0.0",
            application = "${dubbo.application.id}",
            url = "dubbo://localhost:20880", timeout = 10000)
    private MemberService memberService;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        Assert.notNull(authentication, "No authentication data provided");
        log.debug("[authentication info] - [{}]", JSON.toJSONString(authentication));
        String username = (String) authentication.getPrincipal();
        String password = (String) authentication.getCredentials();
        Member member = Optional.ofNullable(memberService.findByName(username)).orElseThrow(() -> new BadCredentialsException("账号不存在"));
        if (!StringUtils.equals(password, member.getPassword())) {
            throw new BadCredentialsException("密码错误");
        }
        final String roleName = member.getRoleName();
        MemberSecurityContext memberSecurityContext = MemberSecurityContext.create(member.getAccountName(), Lists.newArrayList(new SimpleGrantedAuthority(roleName)));
        return new UsernamePasswordAuthenticationToken(memberSecurityContext, null, memberSecurityContext.getAuthorities());
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return (UsernamePasswordAuthenticationToken.class.isAssignableFrom(authentication));
    }
}
