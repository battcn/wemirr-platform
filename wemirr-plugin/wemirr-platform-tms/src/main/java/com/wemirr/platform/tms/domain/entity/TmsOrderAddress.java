package com.wemirr.platform.tms.domain.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.wemirr.framework.commons.entity.SuperEntity;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.math.BigDecimal;

/**
 * tms_order_sender
 *
 * @author Levin
 */
@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@SuperBuilder(toBuilder = true)
@TableName("tms_order_address")
public class TmsOrderAddress extends SuperEntity<Long> {

    @Schema(description = "订单ID")
    private Long orderId;

    @Schema(description = "地址类型(0=发货;1=收货)")
    private Integer type;

    @Schema(description = "公司/商店/站点名称")
    private String company;

    @Schema(description = "联系人姓名")
    private String name;

    @Schema(description = "固定电话")
    private String phone;

    @Schema(description = "手机")
    private String mobile;

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

    @Schema(description = "地址")
    private String address;

    @Schema(description = "幢/码头/库位")
    private String warehouse;

    @Schema(description = "幢号/码头号/库位号")
    private String position;

    @Schema(description = "地图解析的编码")
    private String sysCode;

    @Schema(description = "地图解析的省份")
    private String sysProvince;

    @Schema(description = "地图解析的城市")
    private String sysCity;

    @Schema(description = "地图解析的区县")
    private String sysDistrict;

    @Schema(description = "地理经度")
    private BigDecimal longitude;

    @Schema(description = "地理纬度")
    private BigDecimal latitude;



}