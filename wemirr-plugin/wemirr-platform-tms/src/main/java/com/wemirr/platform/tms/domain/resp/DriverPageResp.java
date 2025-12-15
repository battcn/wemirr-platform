package com.wemirr.platform.tms.domain.resp;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.time.Instant;
import java.time.LocalDate;

/**
 * @author Levin
 */
@Data
@Schema(name = "TmsDriverPageResp")
public class DriverPageResp {

    @Schema(description = "ID")
    private Long id;

    @Schema(description = "身份证号")
    private String idCardNo;

    @Schema(description = "真实姓名")
    private String realName;

    @Schema(description = "手机号")
    private String mobile;

    @Schema(description = "默认车辆ID")
    private Long defaultTruckId;

    @Schema(description = "身份证有效期开始日期")
    private Instant idCardStartDate;

    @Schema(description = "身份证有效期结束日期")
    private Instant idCardEndDate;

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

    @Schema(description = "驾驶员类型")
    private String driverType;


    @Schema(description = "启用状态")
    private Boolean enabled;


    @Schema(description = "证书编号")
    private String certificateCode;

    @Schema(description = "证书有效期开始日期")
    private LocalDate certificateStart;

    @Schema(description = "证书有效期结束日期")
    private LocalDate certificateEnd;


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

    @Schema(description = "从业资格证件号")
    private String adaptIdNo;

    @Schema(description = "从业资格证件发放日期")
    private LocalDate adaptIdIssueDate;

    @Schema(description = "从业资格证件有效期至")
    private LocalDate adaptIdDueDate;

    @Schema(description = "从业资格证件照片ID")
    private String fileAdaptId;

    @Schema(description = "备注")
    private String remark;

    @Schema(description = "创建人")
    private String createName;

    @Schema(description = "创建时间")
    private Instant createTime;

}
