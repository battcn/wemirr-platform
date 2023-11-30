package com.wemirr.platform.authority.domain.tenant.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.wemirr.framework.commons.entity.SuperEntity;
import io.swagger.v3.oas.annotations.media.Schema;
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
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@SuperBuilder(toBuilder = true)
@TableName("t_tenant_dictionary")
@Schema(name = "TenantDictionary", description = "字典类型")
public class TenantDictionary extends SuperEntity<Long> {

    /**
     * 编码
     */
    @Schema(description = "编码")
    @TableField(value = "`code`")
    private String code;

    /**
     * 名称
     */
    @Schema(description = "名称")
    @TableField(value = "name")
    private String name;

    /**
     * 名称
     */
    @Schema(description = "租户ID")
    @TableField(value = "tenant_id")
    private Long tenantId;

    /**
     * 描述
     */
    @Schema(description = "描述")
    @TableField(value = "description")
    private String description;

    /**
     * 状态
     */
    @Schema(description = "状态")
    private Boolean status;

    @TableField(value = "`readonly`")
    @Schema(description = "是否只读")
    private Boolean readonly;

}
