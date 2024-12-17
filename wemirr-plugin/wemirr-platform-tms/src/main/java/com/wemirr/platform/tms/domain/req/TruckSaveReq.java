package com.wemirr.platform.tms.domain.req;

import cn.hutool.core.lang.RegexPool;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;

/**
 * @author Levin
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Schema(name = "TruckSaveReq")
public class TruckSaveReq {


    @NotBlank(message = "车牌号不能为空")
    @Pattern(regexp = RegexPool.PLATE_NUMBER, message = "车牌号格式错误")
    @Schema(description = "车牌号")
    private String plateNo;

    @Schema(description = "是否外协")
    private Boolean isExternal;

    @Schema(description = "卡车型号字典")
    private String truckModel;

    @Schema(description = "卡车长度字典")
    private String truckLength;

    @Schema(description = "卡车宽度字典")
    private String truckWidth;

    @Schema(description = "能源类型（租户字典 TMS_ENERGY_TYPE）")
    private String energyType;

    @Schema(description = "轮胎数量字典")
    private Integer tiresCount;

    @Schema(description = "轴数字典")
    private Integer axlesCount;

    @Schema(description = "档案编号")
    private String fileNo;

    @Schema(description = "车牌颜色（租户字典 TMS_PLATE_COLOR）")
    private String plateColor;

    @Schema(description = "使用性质")
    private String usageType;

    @Schema(description = "品牌")
    private String brand;

    @Schema(description = "车辆识别代码")
    private String truckCode;

    @Schema(description = "发动机号码")
    private String engineCode;

    @Schema(description = "核定载客人数")
    private Integer approvedPassenger;

    @Schema(description = "总质量")
    private BigDecimal totalWeight;

    @Schema(description = "整备质量")
    private BigDecimal curbWeight;

    @Schema(description = "核定载重")
    private BigDecimal approvedWeight;

    @Schema(description = "牵引质量")
    private BigDecimal towWeight;

    @Schema(description = "报废日期")
    private LocalDate scrapDate;

    @Schema(description = "道路运输证号")
    private String roadCertificateCode;

    @Schema(description = "所有人")
    private String ownerName;

    @Schema(description = "所有人证件号")
    private String ownerIdCardNo;

    @Schema(description = "是否隶属")
    private Boolean isAffiliation;

    @Schema(description = "挂靠单位")
    private String subordination;

    @Schema(description = "备注")
    private String remark;

    @Schema(description = "默认司机ID")
    private String defaultDriverId;

    @Schema(description = "车队ID")
    private String fleetId;

    @Schema(description = "挂车ID")
    private String trailerId;

    @Schema(description = "人车合照")
    private String fileDriverAndVehicle;

    @Schema(description = "道路运输证")
    private String fileRoadCertificate;

    @Schema(description = "其它文件")
    private String fileOther;


}
