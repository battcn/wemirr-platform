package com.wemirr.platform.wms.matedata.domain.entity;


import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.wemirr.framework.commons.entity.SuperEntity;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.math.BigDecimal;
import java.time.LocalDate;

import static com.baomidou.mybatisplus.annotation.FieldStrategy.ALWAYS;

/**
 * <p>
 * 供应商表
 * </p>
 *
 * @author ddCat
 * @since 2024-06-18
 */
@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@TableName("wms_supplier")
@Schema(name = "Supplier", description = "供应商")
@EqualsAndHashCode(callSuper = true)
public class Supplier extends SuperEntity<Long> {

    @Schema(description = "供应商编码")
    private String code;

    @Schema(description = "供应商名称")
    private String name;

    @Schema(description = "联系人姓名")
    private String contactName;

    @Schema(description = "联系电话")
    private String contactPhone;

    @Schema(description = "电子邮件")
    private String email;

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
    @TableField(updateStrategy = ALWAYS)
    private Long provinceId;
    @Schema(description = "省")
    @TableField(updateStrategy = ALWAYS)
    private String provinceName;
    @Schema(description = "市")
    @TableField(updateStrategy = ALWAYS)
    private Long cityId;
    @Schema(description = "市")
    @TableField(updateStrategy = ALWAYS)
    private String cityName;
    @Schema(description = "区")
    @TableField(updateStrategy = ALWAYS)
    private Long districtId;
    @Schema(description = "区")
    @TableField(updateStrategy = ALWAYS)
    private String districtName;

    @Schema(description = "登记机关")
    private String registrationAuthority;

    @Schema(description = "注册地址")
    private String registrationAddress;

    @Schema(description = "经营范围")
    private String businessScope;

    @Schema(description = "企业类型")
    private String enterpriseType;

    @Schema(description = "法定代表人")
    private String legalPerson;

    @Schema(description = "LOGO（存储路径或URL）")
    private String logo;

    @Schema(description = "统一信用代码")
    private String creditCode;

    @Schema(description = "信用限额")
    private BigDecimal creditLimit;

    @Schema(description = "描述/备注")
    private String description;
}
