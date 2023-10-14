package com.wemirr.framework.security.domain;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.util.ObjectUtil;
import com.google.common.collect.Lists;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.CredentialsContainer;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.oauth2.core.OAuth2AuthenticatedPrincipal;

import java.time.LocalDate;
import java.util.*;

/**
 * @author Levin
 * @since 2019-04-03
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserInfoDetails implements UserDetails, OAuth2AuthenticatedPrincipal, CredentialsContainer, java.io.Serializable {

    private Long userId;
    private String email;
    private Integer sex;
    private String mobile;
    private Long tenantId;
    private String tenantCode;
    private String tenantName;
    private String nickName;
    private String realName;
    private String username;
    private String password;
    private String avatar;
    private Boolean enabled;
    private String description;
    private LocalDate birthday;
    private Long orgId;
    private Collection<String> permissions = new ArrayList<>();
    private Collection<String> roles = new ArrayList<>();
    private Collection<GrantedAuthority> authorities;
    private Map<String, Object> attributes = new HashMap<>();


    @Override
    public void eraseCredentials() {
        this.password = null;
    }

    @Override
    public Map<String, Object> getAttributes() {
        return ObjectUtil.defaultIfNull(attributes, Map.of());
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        if (CollUtil.isNotEmpty(authorities)) {
            return this.authorities;
        }
        final List<GrantedAuthority> authorities = Lists.newArrayList();
        if (CollUtil.isNotEmpty(roles)) {
            authorities.addAll(roles.stream().map(x -> new CustomGrantedAuthority(x, true)).toList());
        }
        if (CollUtil.isNotEmpty(permissions)) {
            authorities.addAll(permissions.stream().map(CustomGrantedAuthority::new).toList());
        }
        return authorities;
    }

    @Override
    public String getPassword() {
        return this.password;
    }

    @Override
    public String getUsername() {
        return this.username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return this.enabled;
    }

    @Override
    public String getName() {
        return this.username;
    }
}
