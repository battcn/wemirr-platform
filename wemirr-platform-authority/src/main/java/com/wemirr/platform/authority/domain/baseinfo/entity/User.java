package com.wemirr.platform.authority.domain.baseinfo.entity;


import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.wemirr.framework.commons.entity.SuperEntity;
import com.wemirr.platform.authority.domain.baseinfo.enums.Sex;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.Accessors;

import java.time.LocalDate;

import static com.baomidou.mybatisplus.annotation.SqlCondition.LIKE;

/**
 * <p>
 * 实体类
 * 用户
 * </p>
 *
 * @author Levin
 * @since 2020-02-14
 */
@Data
@NoArgsConstructor
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName("t_user")
@Schema(name = "User", description = "用户")
public class User extends SuperEntity<Long> {

    

    /**
     * 账号
     */
    @TableField(value = "username")
    private String username;
    private Long tenantId;
    /**
     * 密码
     */
    @TableField(value = "password")
    private String password;
    /**
     * 昵称
     */
    @TableField(value = "nick_name", condition = LIKE)
    private String nickName;

    private String description;

    private String idCard;

    /**
     * 邮箱
     */
    @TableField(value = "email", condition = LIKE)
    private String email;

    /**
     * 手机
     */
    @TableField(value = "mobile", condition = LIKE)
    private String mobile;

    /**
     * 性别
     * #Sex{W:女;M:男;N:未知}
     */
    @TableField(value = "sex")
    private Sex sex;

    /**
     * 头像
     */
    @TableField(value = "avatar")
    private String avatar;

    private Boolean readonly;


    private Boolean status;


    /**
     * 名族
     */
    private String nation;

    /**
     * 学历
     */
    private String education;


    /**
     * 生日
     */
    private LocalDate birthday;
    private Long orgId;
    private Long stationId;
    private String positionStatus;

}
