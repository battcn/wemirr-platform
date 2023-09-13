package com.wemirr.framework.security.domain;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.Data;
import org.springframework.security.core.GrantedAuthority;

/**
 * 自定义权限类
 *
 * @author Levin
 */
@Data
@JsonSerialize
public class CustomGrantedAuthority implements GrantedAuthority {

    private final String authority;
    private final boolean role;
    private static final String ROLE_ = "ROLE_";

    public CustomGrantedAuthority(String authority) {
        this.authority = authority;
        this.role = false;
    }

    public CustomGrantedAuthority(String authority, boolean role) {
        this.role = role;
        if (role) {
            // 拼接 ROLE_ 以便于支持 @PreAuthorize("hasRole('ROLE_ADMIN')")
            this.authority = authority.contains(ROLE_) ? authority : ROLE_ + authority;
        } else {
            this.authority = authority;
        }

    }

    @Override
    public String getAuthority() {
        return this.authority;
    }
}
