package com.wemirr.platform.authority.domain.entity.common;


import com.baomidou.mybatisplus.annotation.*;
import io.swagger.v3.oas.annotations.Parameter;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import lombok.experimental.Accessors;

import java.math.BigDecimal;
import java.time.Instant;

/**
 * <p>
 * 实体类
 * 字典类型
 * </p>
 *
 * @author Levin
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString(callSuper = true)
@Accessors(chain = true)
@TableName("common_area")
public class AreaEntity {

    @TableId(value = "id", type = IdType.INPUT)
    @NotNull(message = "id不能为空")
    protected Long id;
    /**
     * 名称
     */
    protected String name;

    @TableField("`level`")
    private Integer level;

    /**
     * 父ID
     */
    protected Long parentId;

    /**
     * 经度
     */
    private BigDecimal longitude;

    @TableField("`sequence`")
    private Integer sequence;

    @TableField("`source`")
    private String source;
    /**
     * 纬度
     */
    private BigDecimal latitude;


    /**
     * 创建人ID
     */
    @TableField(value = "created_by", fill = FieldFill.INSERT)
    @Parameter(description = "创建人ID")
    protected Long createdBy;

    /**
     * 创建人名称
     */
    @TableField(value = "created_name", fill = FieldFill.INSERT)
    @Parameter(description = "创建人名称")
    protected String createdName;

    /**
     * 创建时间（依托数据库功能）
     */
    @TableField(value = "created_time")
    @Parameter(description = "创建时间")
    protected Instant createdTime;


    @Parameter(description = "最后修改时间")
    @TableField(value = "last_modified_time")
    protected Instant lastModifiedTime;

    @Parameter(description = "最后修改人ID")
    @TableField(value = "last_modified_by", fill = FieldFill.INSERT_UPDATE)
    protected Long lastModifiedBy;


    @Parameter(description = "最后修改人名称")
    @TableField(value = "last_modified_name", fill = FieldFill.INSERT_UPDATE)
    protected String lastModifiedName;

}
