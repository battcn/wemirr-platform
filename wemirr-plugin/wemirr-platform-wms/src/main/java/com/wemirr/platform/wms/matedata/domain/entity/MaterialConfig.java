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
 * 物料配置表
 * </p>
 *
 * @author ddCat
 * @since 2024-06-18
 */
@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@TableName("wms_material_config")
@Schema(name = "MaterialConfig", description = "物料配置")
@EqualsAndHashCode(callSuper = true)
public class MaterialConfig extends SuperEntity<Long> {

    @Schema(description = "物料ID")
    private Long materialId;

    @Schema(description = "批次号管理")
    private Boolean batch;

    @Schema(description = "序列号管理")
    private Boolean serial;

    @Schema(description = "有效期管理")
    private Boolean validityPeriod;

    @Schema(description = "最小库存天数")
    private Integer minStockDay;

}
