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
 * 容器规格表
 * </p>
 *
 * @author ddCat
 * @since 2024-06-17
 */
@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@TableName("wms_container_spec")
@Schema(name = "ContainerSpec", description = "容器规格")
@EqualsAndHashCode(callSuper = true)
public class ContainerSpec extends SuperEntity<Long> {


    @Schema(description = "容器规格名称")
    private String name;

    @Schema(description = "状态")
    private String status;

    @Schema(description = "长度")
    private BigDecimal length;

    @Schema(description = "宽度")
    private BigDecimal width;

    @Schema(description = "高度")
    private BigDecimal height;

    @Schema(description = "容器自重(单位KG)")
    private BigDecimal selfWeight;

    @Schema(description = "可承载长度")
    private BigDecimal loadLength;

    @Schema(description = "可承载宽度")
    private BigDecimal loadWidth;

    @Schema(description = "可承载高度")
    private BigDecimal loadHeight;

    @Schema(description = "可承载重量(单位KG)")
    private BigDecimal loadWeight;

}
