package com.wemirr.platform.wms.basic.domain.entity;

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
 * 储位(库位)表
 * </p>
 *
 * @author ddCat
 * @since 2024-06-17
 */
@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@TableName("wms_location")
@Schema(name = "Location", description = "储位(库位)")
@EqualsAndHashCode(callSuper = true)
public class Location extends SuperEntity<Long> {

    @Schema(description = "储位编号")
    private String code;

    @Schema(description = "储位名称")
    private String name;

    @Schema(description = "仓库id")
    private Long warehouseId;

    @Schema(description = "所属库区")
    private Long storageAreaId;

    @Schema(description = "所属巷道")
    private Long aisleId;

    @Schema(description = "储位规格")
    private Long specId;

    @Schema(description = "是否高储位")
    private String type;

    @Schema(description = "货架层")
    private Integer shelfLayer;

    @Schema(description = "拣选等级")
    private String pickingLevel;
}
