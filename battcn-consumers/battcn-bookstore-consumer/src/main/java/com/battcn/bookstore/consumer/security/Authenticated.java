package com.battcn.bookstore.consumer.security;

import lombok.Data;

/**
 * @author Levin
 * @version 3.0.4
 * @since 2018-01-20
 */
@Data
public class Authenticated implements java.io.Serializable {

    private static final long serialVersionUID = 5940220889405231039L;

    private Long authId;
    private String name;
    private String mobile;
    private String token;
    private Integer gender;

}
