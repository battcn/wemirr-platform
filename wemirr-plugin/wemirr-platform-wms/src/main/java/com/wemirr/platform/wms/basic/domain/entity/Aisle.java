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
 * 巷道表
 * </p>
 *
 * @author ddCat
 * @since 2024-06-17
 */
@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@TableName("wms_aisle")
@Schema(name = "Aisle", description = "巷道")
@EqualsAndHashCode(callSuper = true)
public class Aisle extends SuperEntity<Long> {

    @Schema(description = "巷道编号")
    private String code;

    @Schema(description = "巷道名称")
    private String name;

    @Schema(description = "是否启用")
    private String status;

    @Schema(description = "长度")
    private BigDecimal length;

    @Schema(description = "宽度")
    private BigDecimal width;

    @Schema(description = "高度")
    private BigDecimal height;
}
