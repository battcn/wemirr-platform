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
 * 仓库表
 * </p>
 *
 * @author ddCat
 * @since 2024-06-17
 */
@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@TableName("wms_warehouse")
@Schema(name = "Warehouse", description = "仓库")
@EqualsAndHashCode(callSuper = true)
public class Warehouse extends SuperEntity<Long> {

    @Schema(description = "仓库编号")
    private String code;

    @Schema(description = "仓库名称")
    private String name;

    @Schema(description = "仓库状态")
    private String status;

    @Schema(description = "时区")
    private String timezone;

    @Schema(description = "属性")
    private String attribute;

    @Schema(description = "计量单位")
    private String unit;

    @Schema(description = "联系人")
    private String contactPerson;

    @Schema(description = "邮箱")
    private String contactEmail;

    @Schema(description = "联系电话")
    private String contactPhone;

    @Schema(description = "邮编")
    private String postcode;

    @Schema(description = "省")
    private Long provinceId;

    @Schema(description = "省")
    private String provinceName;

    @Schema(description = "市")
    private Long cityId;

    @Schema(description = "市")
    private String cityName;

    @Schema(description = "区")
    private Long districtId;

    @Schema(description = "区")
    private String districtName;

    @Schema(description = "详细地址")
    private String address;

    @Schema(description = "备注")
    private String remark;

}
