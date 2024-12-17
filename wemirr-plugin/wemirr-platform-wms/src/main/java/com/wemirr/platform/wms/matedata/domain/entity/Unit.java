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
 * 基本计量单位表
 * </p>
 *
 * @author ddCat
 * @since 2024-06-24
 */
@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@TableName("wms_unit")
@Schema(name = "Unit", description = "基本计量单位")
@EqualsAndHashCode(callSuper = true)
public class Unit extends SuperEntity<Long> {
    /**
     * 计量单位中文名称：计量单位的全称，例如 千克、米
     * 计量单位英文名称：计量单位的简称，例如 kilogram、meter
     * 计量单位符号：计量单位的简写或符号，例如 kg、m
     * 计量单位类型：计量单位类型，例如 重量、长度、体积、面积、体积
     * 计量单位状态：计量单位状态，例如 正常、停用
     * 描述：关于该计量单位的任何额外的信息或说明
     */
    @Schema(description = "计量单位中文名称")
    private String name;

    @Schema(description = "计量单位英文名称")
    private String nameEn;

    @Schema(description = "计量单位符号")
    private String symbol;

    @Schema(description = "计量单位类型")
    private String type;

    @Schema(description = "计量单位状态")
    private String status;

    @Schema(description = "描述")
    private String description;

}
