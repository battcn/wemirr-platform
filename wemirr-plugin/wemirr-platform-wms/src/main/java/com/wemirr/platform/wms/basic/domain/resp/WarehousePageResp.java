package com.wemirr.platform.wms.basic.domain.resp;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.time.Instant;

/**
 * 仓库分页查询响应数据
 *
 * @author ddCat
 */
@Data
@Schema(name = "WarehousePageResp", description = "仓库分页查询响应数据")
public class WarehousePageResp {

    @Schema(description = "ID")
    private Long id;

    @Schema(description = "仓库编号")
    private String code;

    @Schema(description = "仓库名称")
    private String name;

    @Schema(description = "仓库状态")
    private String status;

    @Schema(description = "时区")
    private String timezone;

    @Schema(description = "属性")
    private String attribute;

    @Schema(description = "计量单位")
    private String unit;

    @Schema(description = "联系人")
    private String contactPerson;

    @Schema(description = "邮箱")
    private String contactEmail;

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

    @Schema(description = "详细地址")
    private String address;

    @Schema(description = "仓库备注")
    private String remark;

    @Schema(description = "创建人名称")
    private String createName;

    @Schema(description = "创建时间")
    private Instant createTime;

    @Schema(description = "最后修改时间")
    private Instant lastModifiedTime;

    @Schema(description = "最后修改人名称")
    private String lastModifiedName;

}
