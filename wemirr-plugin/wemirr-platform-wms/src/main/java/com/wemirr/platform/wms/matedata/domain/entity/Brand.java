package com.wemirr.platform.wms.matedata.domain.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.wemirr.framework.commons.entity.SuperEntity;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

/**
 * <p>
 * 品牌表
 * </p>
 *
 * @author ddCat
 * @since 2024-06-18
 */
@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@TableName("wms_brand")
@Schema(name = "Brand", description = "品牌")
@EqualsAndHashCode(callSuper = true)
public class Brand extends SuperEntity<Long> {

    @Schema(description = "品牌编码")
    private String code;

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
