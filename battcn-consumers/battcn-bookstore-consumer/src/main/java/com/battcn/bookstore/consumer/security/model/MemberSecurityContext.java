package com.battcn.bookstore.consumer.security.model;

import lombok.Data;

/**
 * @author Levin
 * @since 2017-05-25
 */
@Data
public class MemberSecurityContext {
    private String username;
    private String password;
}
