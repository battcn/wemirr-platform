package com.wemirr.platform.tools.domain.entity;

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
@EqualsAndHashCode(callSuper = true)
@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@TableName("gen_dynamic_release_drag")
public class DynamicReleaseDrag extends SuperEntity<Long> {

    @TableField("`model`")
    private String model;
    @TableField("`label`")
    private String label;
    private Long tenantId;
    private Boolean locked;
    private String description;
    @TableField("`setting`")
    private String setting;
    @TableField("`table_name`")
    private String tableName;


}
