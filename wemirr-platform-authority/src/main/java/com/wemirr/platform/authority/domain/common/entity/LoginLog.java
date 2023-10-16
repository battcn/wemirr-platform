package com.wemirr.platform.authority.domain.common.entity;


import com.baomidou.mybatisplus.annotation.*;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;

import static com.wemirr.framework.commons.entity.Entity.CREATE_TIME_COLUMN;

/**
 * <p>
 * 实体类
 * 登录日志
 * </p>
 *
 * @author Levin
 * @since 2019-11-01
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@TableName("common_login_log")
public class LoginLog {


    @TableId(value = "id", type = IdType.AUTO)
    @Schema(description = "主键")
    private Long id;
    /**
     * 登录IP
     */
    @Schema(description = "登录IP")
    private String ip;

    /**
     * 登录人ID
     */
    @Schema(description = "登录人ID")
    @TableField("user_id")
    private Long userId;

    @Schema(description = "租户ID")
    private Long tenantId;

    /**
     * 登录人姓名
     */
    @Schema(description = "登录人姓名")
    private String name;

    /**
     * 登录人账号
     */
    @Schema(description = "登录人账号")
    private String principal;


    @Schema(description = "平台类型")
    private String platform;


    @Schema(description = "引擎类型")
    private String engine;

    /**
     * 浏览器名称
     */
    @Schema(description = "浏览器名称")
    @TableField(value = "browser")
    private String browser;

    /**
     * 操作系统
     */
    @Schema(description = "操作系统")
    @TableField(value = "os")
    private String os;

    /**
     * 登录地点
     */
    @Schema(description = "登录地点")
    @TableField(value = "location")
    private String location;


    private String clientId;

    @TableField(value = CREATE_TIME_COLUMN, fill = FieldFill.INSERT)
    @Schema(description = "创建时间")
    protected Instant createdTime;

}
