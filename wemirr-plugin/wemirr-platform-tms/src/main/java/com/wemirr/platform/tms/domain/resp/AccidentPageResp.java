package com.wemirr.platform.tms.domain.resp;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.math.BigDecimal;
import java.time.Instant;
import java.time.LocalDate;

/**
 * @author Levin
 */
@Data
public class AccidentPageResp {

    @Schema(description = "ID")
    private Long id;

    @Schema(description = "事故编号")
    private String accidentNo;

    @Schema(description = "卡车ID")
    private Long truckId;

    @Schema(description = "车牌号")
    private String plateNo;

    @Schema(description = "驾驶员ID")
    private Long driverId;

    @Schema(description = "驾驶员姓名")
    private String driverName;

    @Schema(description = "事故地点")
    private String address;

    @Schema(description = "责任方")
    private String responsibleParty;

    @Schema(description = "事故金额")
    private BigDecimal accidentAmount;

    @Schema(description = "事故日期")
    private LocalDate accidentDate;

    @Schema(description = "经办人姓名")
    private String agentName;

    @Schema(description = "备注")
    private String remark;

    @Schema(description = "创建人")
    private String createName;

    @Schema(description = "创建时间")
    private Instant createTime;




}
