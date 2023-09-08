package com.wemirr.platform.authority.domain.entity.baseinfo;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.wemirr.framework.commons.entity.TreeEntity;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import lombok.experimental.Accessors;
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
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName("sys_org")
@Schema(description = "组织")
public class Org extends TreeEntity<Org, Long> {

    

    protected String label;

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
