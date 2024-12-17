package com.wemirr.platform.wms.matedata.domain.req;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * 品牌保存请求数据
 * @author ddCat
 * @since 2024-06-18
 */
@Data
@Schema(name = "BrandSaveReq", description = "品牌保存请求数据")
public class BrandSaveReq {

    @Schema(description = "品牌名称")
    private String name;

    @Schema(description = "品牌描述")
    private String description;

    @Schema(description = "品牌所有者")
    private String owner;

    @Schema(description = "品牌联系人")
    private String contactPerson;

    @Schema(description = "品牌公司")
    private String company;

    @Schema(description = "品牌联系人联系方式")
    private String contactPhone;

    @Schema(description = "品牌邮箱")
    private String email;

    @Schema(description = "品牌logo")
    private String logo;

    @Schema(description = "品牌状态")
    private String status;

    @Schema(description = "品牌类型")
    private String type;

    @Schema(description = "品牌网址")
    private String website;

    @Schema(description = "品牌备注")
    private String remark;

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
}
