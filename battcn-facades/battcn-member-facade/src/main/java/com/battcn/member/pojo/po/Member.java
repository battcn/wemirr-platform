package com.battcn.member.pojo.po;

import com.battcn.framework.mybatis.pojo.RecordEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @author Levin
 * @since 2018/3/23 0023
 */
@EqualsAndHashCode(callSuper = true)
@Data
@Table(name = "t_member")
public class Member extends RecordEntity {

    private static final long serialVersionUID = 7611703644690711358L;

    @Id
    private String memberNo;
    private String accountName;
    private String password;
    private String roleName;


}
