package com.wemirr.platform.wms.basic.domain.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.wemirr.framework.commons.entity.SuperEntity;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.math.BigDecimal;

/**
 * <p>
 * 储位规格表
 * </p>
 *
 * @author ddCat
 * @since 2024-06-17
 */
@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@TableName("wms_location_spec")
@Schema(name = "LocationSpec", description = "储位规格")
@EqualsAndHashCode(callSuper = true)
public class LocationSpec extends SuperEntity<Long> {

    @Schema(description = "储位规格编号")
    private String code;

    @Schema(description = "储位规格名称")
    private String name;

    @Schema(description = "状态")
    private String status;

    @Schema(description = "长度")
    private BigDecimal length;

    @Schema(description = "宽度")
    private BigDecimal width;

    @Schema(description = "高度")
    private BigDecimal height;

    @Schema(description = "承重(KG)")
    private BigDecimal loadCapacity;
}
