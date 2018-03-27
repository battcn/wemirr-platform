package com.battcn.bookstore.consumer.security;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.commons.lang3.StringUtils;

import java.util.List;

/**
 * @author Levin
 * @version 3.0.4
 * @since 2018-01-20
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Authentication implements java.io.Serializable {

    private static final long serialVersionUID = 5940220889405231039L;

    private String authId;
    private String principal;
    private List<String> authorities;

    public static Authentication create(String principal, List<String> authorities) {
        if (StringUtils.isBlank(principal)) {
            throw new IllegalArgumentException("Username is blank: " + principal);
        }
        return new Authentication(null, principal, authorities);
    }


}
