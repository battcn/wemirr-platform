package com.battcn.bookstore.consumer.pojo.dto;

import lombok.Data;

import javax.persistence.Id;

/**
 * 会员详细资料
 *
 * @author Levin
 * @since 2018/4/9 0009
 */
@Data
public class MemberDetailDto {

    @Id
    private String memberNo;
    private String username;
    private String nickName;
    private String roleName;
    private Byte level;
    private Integer point;
    private Integer exp;


}
