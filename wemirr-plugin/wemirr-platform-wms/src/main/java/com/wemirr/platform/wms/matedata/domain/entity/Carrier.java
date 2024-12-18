package com.wemirr.platform.wms.matedata.domain.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.wemirr.framework.commons.entity.SuperEntity;
import com.wemirr.platform.wms.inbound.domain.enums.CarrierType;
import com.wemirr.platform.wms.inbound.domain.enums.ServiceType;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.time.Instant;

import static com.baomidou.mybatisplus.annotation.FieldStrategy.ALWAYS;


/**
 * <p>
 * 承运商
 * </p>
 *
 * @author ddCat
 * @since 2024-07-12
 */
@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@TableName("wms_carrier")
@Schema(name = "Carrier", description = "承运商")
@EqualsAndHashCode(callSuper = true)
public class Carrier extends SuperEntity<Long> {

    @Schema(description = "承运商编码")
    private String code;

    @Schema(description = "承运商名称")
    private String name;

    @Schema(description = "承运商类型：外贸、内贸")
    private CarrierType type;

    @Schema(description = "服务类型：陆运、海运、空运")
    private ServiceType serviceType;

    @Schema(description = "生效日期")
    private Instant effectiveDate;

    @Schema(description = "失效日期")
    private Instant expirationDate;

    @Schema(description = "付款条款")
    private String paymentTerm;

    @Schema(description = "结算类型")
    private String settlementType;

    @Schema(description = "邮编")
    private String postcode;

    @Schema(description = "联系人")
    private String contactName;

    @Schema(description = "手机号")
    private String mobile;

    @Schema(description = "电子邮箱")
    private String email;

    @Schema(description = "传真")
    private String fax;

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

    @Schema(description = "详细地址")
    private String address;

    @Schema(description = "备注")
    private String description;

    @Schema(description = "状态")
    private String status;

    @Schema(description = "附件")
    private String attachment;

}
