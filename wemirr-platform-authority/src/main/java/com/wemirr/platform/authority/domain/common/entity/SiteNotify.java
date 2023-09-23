package com.wemirr.platform.authority.domain.common.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.wemirr.framework.commons.entity.SuperEntity;
import com.wemirr.platform.authority.domain.baseinfo.enums.ReceiverType;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

/**
 * @author Levin
 */
@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
@TableName(value = "sys_site_notify")
public class SiteNotify extends SuperEntity<Long> {

    @TableField("`title`")
    @Schema(description = "标题")
    private String title;

    @TableField("`level`")
    @Schema(description = "等级")
    private String level;

    @TableField("`status`")
    @Schema(description = "状态")
    private Boolean status;

    @TableField("`type`")
    @Schema(description = "类型")
    private ReceiverType type;

    @TableField("`receiver`")
    @Schema(description = "接收人")
    private String receiver;

    @TableField("`description`")
    @Schema(description = "描述")
    private String description;

    @TableField("`content`")
    @Schema(description = "内容")
    private String content;

}
