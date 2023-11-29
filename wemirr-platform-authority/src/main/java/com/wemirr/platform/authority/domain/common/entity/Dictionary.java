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
 * <p>
 * 实体类
 * 字典类型
 * </p>
 *
 * @author Levin
 * @since 2020-01-03
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@TableName("common_dictionary")
@SuperBuilder
@Schema(name = "Dictionary", description = "字典类型")
public class Dictionary extends SuperEntity<Long> {

    @Schema(description = "编码")
    @TableField(value = "`code`")
    private String code;


    @Schema(description = "名称")
    @TableField(value = "name")
    private String name;

    @Schema(description = "描述")
    @TableField(value = "description")
    private String description;


    @Schema(description = "状态")
    private Boolean status;

    @TableField(value = "`readonly`")
    @Schema(description = "只读")
    private Boolean readonly;

}
