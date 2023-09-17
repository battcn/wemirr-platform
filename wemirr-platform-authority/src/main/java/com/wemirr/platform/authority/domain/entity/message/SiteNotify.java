package com.wemirr.platform.authority.domain.entity.message;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.wemirr.framework.commons.entity.SuperEntity;
import com.wemirr.platform.authority.domain.enums.ReceiverType;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author Levin
 */
@EqualsAndHashCode(callSuper = true)
@Data
@TableName(value = "sys_site_notify")
public class SiteNotify extends SuperEntity<Long> {

    @TableField("`title`")
    private String title;

    @TableField("`level`")
    private String level;

    @TableField("`status`")
    private Boolean status;

    @TableField("`type`")
    private ReceiverType type;

    @TableField("`receiver`")
    private String receiver;

    @TableField("`description`")
    private String description;

    @TableField("`content`")
    private String content;

}
