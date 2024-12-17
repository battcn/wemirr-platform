package com.wemirr.platform.wms.matedata.domain.req;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;

/**
 * 供应商保存请求数据
 * @author ddCat
 * @since 2024-07-01
 */
@Data
@Schema(name = "SupplierSaveReq", description = "供应商保存请求数据")
public class SupplierSaveReq {

    @NotBlank(message = "供应商名称不能为空")
    @Schema(description = "供应商名称")
    private String name;

    @NotBlank(message = "联系人不能为空")
    @Schema(description = "联系人姓名")
    private String contactName;

    @NotBlank(message = "联系电话不能为空")
    @Schema(description = "联系电话")
    private String contactPhone;

    @Schema(description = "电子邮件")
    private String email;

    @NotBlank(message = "地址不能为空")
    @Schema(description = "地址")
    private String address;

    @Schema(description = "公司电话")
    private String tel;

    @Schema(description = "成立日期")
    private LocalDate establishmentDate;

    @Schema(description = "营业期限开始时间")
    private LocalDate businessStartDate;

    @Schema(description = "营业期限截止时间")
    private LocalDate businessEndDate;

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

    @Schema(description = "登记机关")
    private String registrationAuthority;

    @Schema(description = "注册地址")
    private String registrationAddress;

    @Schema(description = "经营范围")
    private String businessScope;

    @Schema(description = "企业类型")
    private String enterpriseType;

    @NotBlank(message = "法人不能为空")
    @Schema(description = "法定代表人")
    private String legalPerson;

    @Schema(description = "LOGO（存储路径或URL）")
    private String logo;

    @NotBlank(message = "统一信用代码不能为空")
    @Schema(description = "统一信用代码")
    private String creditCode;

    @Schema(description = "信用限额")
    private BigDecimal creditLimit;

    @Schema(description = "描述/备注")
    private String description;
}
