package com.wemirr.platform.authority.domain.baseinfo.entity;

import com.baomidou.mybatisplus.annotation.SqlCondition;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.wemirr.framework.commons.entity.SuperEntity;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

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


    @Schema(description = "电话")
    private String tel;

    @Schema(description = "租户ID")
    private Long tenantId;

    /**
     * 简称
     */
    @Schema(description = "简称")
    @TableField(value = "alias", condition = LIKE)
    private String alias;

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
    @TableField(value = "description", condition = LIKE)
    private String description;

}
