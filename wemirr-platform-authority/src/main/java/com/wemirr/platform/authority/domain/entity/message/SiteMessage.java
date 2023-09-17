package com.wemirr.platform.authority.domain.entity.message;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.wemirr.framework.commons.entity.SuperEntity;
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
    private String title;

    @TableField("`level`")
    private String level;

    @TableField("`description`")
    private String description;

    @TableField("`content`")
    private String content;

    @TableField("`mark`")
    private Boolean mark;
    /**
     * 接收用户的Id
     */
    @TableField("`receive_id`")
    private Long receiveId;


}
