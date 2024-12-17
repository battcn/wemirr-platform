package com.wemirr.platform.tms.domain.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.wemirr.framework.commons.entity.SuperEntity;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.time.LocalDate;

/**
 * @author Levin
 */
@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder(toBuilder = true)
@TableName(value = "tms_driver")
public class Driver extends SuperEntity<Long> {

    @Schema(description = "身份证号")
    private String idCardNo;

    @Schema(description = "身份证有效期开始日期")
    private LocalDate idCardStartDate;

    @Schema(description = "身份证有效期结束日期")
    private LocalDate idCardEndDate;

    @Schema(description = "驾驶证号")
    private String licenseCode;

    @Schema(description = "驾驶证类型")
    private String licenseType;

    @Schema(description = "驾驶证有效期开始日期")
    private LocalDate driverLicenseStart;

    @Schema(description = "驾驶证有效期结束日期")
    private LocalDate driverLicenseEnd;

    @Schema(description = "驾驶证发放机关")
    private String driverLicenseIssueOrg;

    @Schema(description = "证书编号")
    private String certificateCode;

    @Schema(description = "证书有效期开始日期")
    private LocalDate certificateStart;

    @Schema(description = "证书有效期结束日期")
    private LocalDate certificateEnd;

    @Schema(description = "备注")
    private String remark;

    @Schema(description = "租户ID")
    private Integer tenantId;

    @Schema(description = "启用状态")
    private Boolean enabled;

    @Schema(description = "人像照片ID")
    private String filePortraitId;

    @Schema(description = "身份证正面照片ID")
    private String fileIdCardFront;

    @Schema(description = "身份证背面照片ID")
    private String fileIdCardBack;

    @Schema(description = "驾驶证照片ID")
    private String fileDriverLicense;

    @Schema(description = "证书照片ID")
    private String fileCertificate;

    @Schema(description = "其他照片ID")
    private String fileOther;

    @Schema(description = "真实姓名")
    private String realName;

    @Schema(description = "手机号")
    private String mobile;

    @Schema(description = "从业资格证件号")
    private String adaptIdNo;


    @Schema(description = "驾驶员类型")
    private String driverType;

    @Schema(description = "默认车辆ID")
    private String defaultTruckId;

    @Schema(description = "从业资格证件发放日期")
    private LocalDate adaptIdIssueDate;

    @Schema(description = "从业资格证件有效期至")
    private LocalDate adaptIdDueDate;

    @Schema(description = "从业资格证件照片ID")
    private String fileAdaptId;

    @Schema(description = "审批状态")
    private Boolean approvalStatus;

    @Schema(description = "审批用户ID")
    private Long approvalId;

    @Schema(description = "审批用户名")
    private String approvalName;

    @Schema(description = "审批描述")
    private String approvalDesc;

}
