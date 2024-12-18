package com.wemirr.platform.wms.matedata.domain.req;

import com.baomidou.mybatisplus.annotation.TableField;
import com.wemirr.platform.wms.inbound.domain.enums.CarrierType;
import com.wemirr.platform.wms.inbound.domain.enums.ServiceType;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.time.Instant;

import static com.baomidou.mybatisplus.annotation.FieldStrategy.ALWAYS;

/**
 * 承运商保存请求数据
 *
 * @author ddCat
 * @since 2024-07-14
 */
@Data
@Schema(name = "CarrierSaveReq", description = "承运商保存请求数据")
public class CarrierSaveReq {

    @NotBlank(message = "承运商名称不能为空")
    @Schema(description = "承运商名称")
    private String name;

    @NotNull(message = "承运商类型不能为空")
    @Schema(description = "承运商类型：外贸、内贸")
    private CarrierType type;

    @NotNull(message = "服务类型不能为空")
    @Schema(description = "服务类型：陆运、海运、空运")
    private ServiceType serviceType;


    @NotNull(message = "生效日期不能为空")
    @Schema(description = "生效日期")
    private Instant effectiveDate;

    @NotNull(message = "失效日期不能为空")
    @Schema(description = "失效日期")
    private Instant expirationDate;

    @Schema(description = "付款条款")
    private String paymentTerm;

    @Schema(description = "结算类型")
    private String settlementType;

    @Schema(description = "邮编")
    private String postcode;

    @NotBlank(message = "联系人不能为空")
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

    @NotBlank(message = "详细地址不能为空")
    @Schema(description = "详细地址")
    private String address;

    @Schema(description = "备注")
    private String description;

    @Schema(description = "状态")
    private String status;

    @Schema(description = "附件")
    private String attachment;
}
