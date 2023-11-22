package com.wemirr.platform.authority.domain.baseinfo.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.wemirr.framework.commons.entity.SuperEntity;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import lombok.experimental.Accessors;
import lombok.experimental.SuperBuilder;

import static com.baomidou.mybatisplus.annotation.SqlCondition.LIKE;

/**
 * <p>
 * 实体类
 * 岗位
 * </p>
 *
 * @author Levin
 * @since 2019-10-20
 */
@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName("sys_station")
@Schema(name = "Station", description = "岗位")
public class Station extends SuperEntity<Long> {


    /**
     * 名称
     */
    @Schema(description = "名称")
    @TableField(value = "`name`", condition = LIKE)
    private String name;

    @Schema(description = "岗位编码")
    private String code;

    @Schema(description = "排序")
    @TableField(value = "`sequence`")
    private Integer sequence;


    @Schema(description = "组织ID")
    @TableField("org_id")
    private Long orgId;


    /**
     * 状态
     */
    @Schema(description = "状态")
    @TableField("`status`")
    private Boolean status;

    /**
     * 描述
     */
    @Schema(description = "描述")
    private String description;


}
