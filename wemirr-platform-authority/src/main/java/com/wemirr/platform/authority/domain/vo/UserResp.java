package com.wemirr.platform.authority.domain.vo;

import com.wemirr.platform.authority.domain.enums.Sex;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * @author Levin
 */
@Data
public class UserResp {

    private Long id;
    /**
     * 账号
     */
    private String username;
    /**
     * 昵称
     */
    private String nickName;

    private String description;

    private String idCard;

    /**
     * 邮箱
     */
    private String email;

    /**
     * 手机
     */
    private String mobile;

    /**
     * 性别
     * #Sex{W:女;M:男;N:未知}
     */
    private Sex sex;

    /**
     * 头像
     */
    private String avatar;

    private Boolean readonly;


    private Boolean status;

    private String positionStatus;

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

    /**
     * 组织ID
     * #c_core_org
     */
    private Long orgId;

    /**
     * 岗位ID
     */
    private Long stationId;

    private LocalDateTime createdTime;

}
