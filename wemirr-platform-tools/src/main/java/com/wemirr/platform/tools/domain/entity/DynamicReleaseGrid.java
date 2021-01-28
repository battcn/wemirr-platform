package com.wemirr.platform.tools.domain.entity;

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
@TableName("gen_dynamic_release_grid")
public class DynamicReleaseGrid extends SuperEntity<Long> {

    private Long datasourceId;

    @TableField("`model`")
    private String model;

    /**
     * 表名
     */
    private String tableName;
    private Long templateId;
    private Boolean locked;
    private String description;


}
