package com.wemirr.platform.authority.domain.baseinfo.entity;

import com.baomidou.mybatisplus.annotation.SqlCondition;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.wemirr.framework.commons.entity.SuperEntity;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import org.hibernate.validator.constraints.Length;

import static com.baomidou.mybatisplus.annotation.SqlCondition.LIKE;

/**
 * <p>
 * 实体类
 * 组织
 * </p>
 *
 * @author Levin
 * @since 2019-10-20
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@TableName("sys_org")
@Schema(description = "组织")
@SuperBuilder
public class Org extends SuperEntity<Long> {



    /**
     * 名称
     */
    @Schema(description = "名称")
    @NotEmpty(message = "名称不能为空")
    @Length(max = 255, message = "名称长度不能超过255")
    @TableField(value = "label", condition = SqlCondition.LIKE)
    protected String label;

    /**
     * 父ID
     */
    @Schema(description = "父ID")
    @TableField(value = "parent_id")
    protected Long parentId;


    /**
     * 排序
     */
    @Schema(description = "排序号")
    @TableField(value = "`sequence`")
    protected Integer sequence;


    private String tel;


    private Long tenantId;

    /**
     * 简称
     */
    @Schema(description = "简称")
    @Length(max = 255, message = "简称长度不能超过255")
    @TableField(value = "alias", condition = LIKE)
    private String alias;

    @TableField("`tree_path`")
    private String treePath;

    /**
     * 状态
     */
    @Schema(description = "状态")
    @TableField("status")
    private Boolean status;

    /**
     * 描述
     */
    @Schema(description = "描述")
    @Length(max = 255, message = "描述长度不能超过255")
    @TableField(value = "description", condition = LIKE)
    private String description;

}
