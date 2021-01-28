package com.wemirr.platform.authority.domain.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * @author Levin
 */
@Data
public class UserVO {

    private Long id;

    /**
     * 姓名
     */
    @Schema(description="昵称")
    private String nickName;

    /**
     * 姓名
     */
    @Schema(description="真实名称")
    private String realName;


    /**
     * 邮箱
     */
    @Schema(description="邮箱")
    private String email;

    /**
     * 手机
     */
    @Schema(description="手机号")
    private String mobile;


    @Schema(description="用户名")
    private String username;

    /**
     * 生日
     */
    @Schema(description="生日")
    private LocalDate birthday;


    /**
     * 擅长
     */
    @Schema(description="擅长")
    private String goodAt;
    /**
     * 职业
     */
    @Schema(description="职业")
    private String occupation;


    /**
     * 创建时间（依托数据库功能）
     */
    @Schema(description="创建时间")
    protected LocalDateTime createdTime;


}
