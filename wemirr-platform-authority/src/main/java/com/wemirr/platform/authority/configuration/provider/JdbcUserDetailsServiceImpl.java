package com.wemirr.platform.authority.configuration.provider;

import com.google.common.collect.Lists;
import com.google.common.collect.Sets;
import com.wemirr.framework.security.client.entity.UserInfoDetails;
import com.wemirr.platform.authority.configuration.integration.IntegrationAuthentication;
import com.wemirr.platform.authority.configuration.integration.IntegrationAuthenticationContext;
import com.wemirr.platform.authority.configuration.integration.IntegrationAuthenticator;
import com.wemirr.platform.authority.domain.entity.baseinfo.Role;
import com.wemirr.platform.authority.repository.ResourceMapper;
import com.wemirr.platform.authority.repository.RoleMapper;
import com.wemirr.platform.authority.service.LoginLogService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.toList;

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
    @Resource
    private RoleMapper roleMapper;
    @Resource
    private ResourceMapper resourceMapper;

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
        final List<String> roles = Optional.ofNullable(this.roleMapper.findRoleByUserId(user.getUserId())).orElseGet(Lists::newArrayList)
                .stream().map(Role::getCode).collect(toList());
        final List<String> permissions = Optional.ofNullable(this.resourceMapper.queryPermissionByUserId(user.getUserId())).orElseGet(Lists::newArrayList);
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
