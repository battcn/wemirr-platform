package com.wemirr.platform.tools.domain.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.wemirr.framework.commons.entity.SuperEntity;
import lombok.*;

/**
 * @author Levin
 */
@EqualsAndHashCode(callSuper = true)
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@TableName("gen_dynamic_release_column")
public class DynamicReleaseColumn extends SuperEntity<Long> {

    private Long gridId;
    @TableField("`title`")
    private String title;
    @TableField("`key`")
    private String key;
    @TableField("`type`")
    private String type;
    @TableField("`disabled`")
    private Boolean disabled;
    @TableField("`form`")
    private String form;
    @TableField("`search`")
    private String search;
    @TableField("`dict`")
    private String dict;
    @TableField("`width`")
    private Integer width;
    private Boolean showOverflowTooltip;
    @TableField("`fixed`")
    private Boolean fixed;
    @TableField("`simple`")
    private Boolean simple;
    private String columnJson;


}
