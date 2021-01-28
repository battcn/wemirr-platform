package com.wemirr.platform.authority.configuration.provider;

import com.alibaba.fastjson.JSON;
import com.google.common.collect.Lists;
import com.google.common.collect.Sets;
import com.wemirr.framework.security.client.entity.UserInfoDetails;
import com.wemirr.platform.authority.configuration.integration.IntegrationAuthentication;
import com.wemirr.platform.authority.configuration.integration.IntegrationAuthenticationContext;
import com.wemirr.platform.authority.configuration.integration.IntegrationAuthenticator;
import com.wemirr.platform.authority.service.LoginLogService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * @author Levin
 */
@Slf4j
@Service
public class JdbcUserDetailsServiceImpl implements UserDetailsService {

    @Resource
    private List<IntegrationAuthenticator> authenticators;
    @Resource
    private LoginLogService loginLogService;


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        IntegrationAuthentication integrationAuthentication = IntegrationAuthenticationContext.get();
        //判断是否是集成登录
        if (integrationAuthentication == null) {
            integrationAuthentication = new IntegrationAuthentication();
        }
        integrationAuthentication.setUsername(username);
        UserInfoDetails userInfoDetails = this.authenticate(integrationAuthentication);
        if (userInfoDetails == null) {
            throw new UsernameNotFoundException("用户名或密码错误");
        }
        this.setAuthorize(userInfoDetails, integrationAuthentication);
        log.debug("[用户信息] - [{}]", JSON.toJSONString(userInfoDetails));
        try {
            // 写个登录日志
            this.loginLogService.saveLoginLog(userInfoDetails.getUserId(), username, userInfoDetails.getRealName());
        } catch (Exception e) {
            log.error("[登录日志记录错误] - [{}]", e.getMessage());
        }
        return userInfoDetails;
    }

    /**
     * 设置授权信息
     *
     * @param user user
     */
    private void setAuthorize(UserInfoDetails user, IntegrationAuthentication integrationAuthentication) {
        final ArrayList<String> roles = Lists.newArrayList("ADMIN", "USER");
        final ArrayList<String> permissions = Lists.newArrayList("role:view", "user:view");
        // 验证角色和登录系统
        Set<String> authorize = Sets.newHashSet();
        authorize.addAll(roles);
        authorize.addAll(permissions);
        user.setRoles(roles);
        user.setPermissions(permissions);
        user.setAuthorities(authorize.stream().filter(StringUtils::isNotBlank).map(SimpleGrantedAuthority::new).collect(Collectors.toSet()));
    }

    private UserInfoDetails authenticate(IntegrationAuthentication integrationAuthentication) {
        if (this.authenticators == null) {
            return null;
        }
        for (IntegrationAuthenticator authenticator : authenticators) {
            if (authenticator.support(integrationAuthentication)) {
                return authenticator.authenticate(integrationAuthentication);
            }
        }
        return null;
    }
}
