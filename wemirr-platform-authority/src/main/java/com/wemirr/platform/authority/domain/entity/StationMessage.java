package com.wemirr.platform.authority.domain.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.wemirr.framework.commons.entity.SuperEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author Levin
 */
@EqualsAndHashCode(callSuper = true)
@Data
@TableName(value = "sys_station_message")
public class StationMessage extends SuperEntity<Long> {

    @TableField("`title`")
    private String title;

    @TableField("`code`")
    private String code;

    @TableField("`description`")
    private String description;

    @TableField("`content`")
    private String content;

    private Boolean mark;
    /**
     * 接收用户的Id
     */
    @TableField("`receive_id`")
    private Long receiveId;


}
