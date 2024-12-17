package com.wemirr.platform.wms.matedata.domain.entity;

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
 * 物料类别
 * </p>
 *
 * @author ddCat
 * @since 2024-06-18
 */
@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@TableName("wms_material_category")
@Schema(name = "MaterialCategory", description = "物料类别")
@EqualsAndHashCode(callSuper = true)
public class MaterialCategory extends SuperEntity<Long> {

    @Schema(description = "物料类型编号")
    private String code;

    @Schema(description = "物料类型名称")
    private String name;

    @Schema(description = "父类型ID")
    private Long parentId;

    @Schema(description = "是否启用")
    private String status;

    @Schema(description = "备注")
    private String remark;

}
