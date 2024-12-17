package com.wemirr.platform.wms.basic.domain.req;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

/**
 * 仓库保存请求数据
 *
 * @author ddCat
 */
@Data
@Schema(name = "WarehouseSaveReq", description = "仓库保存请求数据")
public class WarehouseSaveReq {

    @NotBlank(message = "仓库编号不能为空")
    @Schema(description = "仓库编号")
    private String code;

    @NotBlank(message = "仓库名称不能为空")
    @Schema(description = "仓库名称")
    private String name;

    @Schema(description = "仓库状态")
    private String status;

    @NotBlank(message = "时区不能为空")
    @Schema(description = "时区")
    private String timezone;

    @Schema(description = "属性")
    private String attribute;

    @Schema(description = "计量单位")
    private String unit;

    @NotBlank(message = "联系人不能为空")
    @Schema(description = "联系人")
    private String contactPerson;

    @Schema(description = "邮箱")
    private String contactEmail;

    @NotBlank(message = "联系电话不能为空")
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

    @NotBlank(message = "详细地址不能为空")
    @Schema(description = "详细地址")
    private String address;

    @Schema(description = "备注")
    private String remark;

}
