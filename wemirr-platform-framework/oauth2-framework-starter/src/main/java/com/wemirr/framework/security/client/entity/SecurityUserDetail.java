package com.wemirr.framework.security.client.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;
import java.util.List;

/**
 * @author Levin
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class SecurityUserDetail {

    private Long userId;
    private String userNo;
    private String principal;
    private String name;
    private Long tenantId;
    private String mobile;
    private List<SecurityDictionary<Long>> departments;
    private String deptName;
    private String nickName;
    private String realName;
    private String companyName;
    private Integer userType;
    private String image;
    private Collection<? extends GrantedAuthority> authorities;

}
