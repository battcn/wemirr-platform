package com.wemirr.platform.authority.domain.common.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.wemirr.framework.commons.entity.SuperEntity;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

/**
 * @author Levin
 */
@Data
@SuperBuilder(toBuilder = true)
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@TableName(value = "sys_site_message")
public class SiteMessage extends SuperEntity<Long> {

    @TableField("`title`")
    @Schema(description = "标题")
    private String title;

    @TableField("`level`")
    @Schema(description = "等级")
    private String level;


    @TableField("`description`")
    @Schema(description = "描述")
    private String description;


    @TableField("`content`")
    @Schema(description = "内容")
    private String content;

    @TableField("`mark`")
    @Schema(description = "标记")
    private Boolean mark;


    @TableField("`receive_id`")
    @Schema(description = "接收人")
    private Long receiveId;


}
