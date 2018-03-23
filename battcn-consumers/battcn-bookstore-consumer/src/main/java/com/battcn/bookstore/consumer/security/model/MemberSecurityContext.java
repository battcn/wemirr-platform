package com.battcn.bookstore.consumer.security.model;

import org.apache.commons.lang3.StringUtils;
import org.springframework.security.core.GrantedAuthority;

import java.util.List;

/**
 * @author Levin
 * @since 2017-05-25
 */
public class MemberSecurityContext {

    private final String accountName;
    private final List<GrantedAuthority> authorities;

    private MemberSecurityContext(String accountName, List<GrantedAuthority> authorities) {
        this.accountName = accountName;
        this.authorities = authorities;
    }

    public static MemberSecurityContext create(String accountName, List<GrantedAuthority> authorities) {
        if (StringUtils.isBlank(accountName)) {
            throw new IllegalArgumentException("Username is blank: " + accountName);
        }
        return new MemberSecurityContext(accountName, authorities);
    }

    public String getAccountName() {
        return accountName;
    }

    public List<GrantedAuthority> getAuthorities() {
        return authorities;
    }
}
