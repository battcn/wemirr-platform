package com.wemirr.platform.wms.matedata.domain.req;

import com.wemirr.framework.db.mybatisplus.page.PageRequest;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 物料分页查询请求数据
 *
 * @author ddCat
 * @since 2024-06-18
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Schema(name = "MaterialPageResp", description = "物料分页查询请求数据")
public class MaterialPageReq extends PageRequest {

    @Schema(description = "物料编号")
    private String code;

    @Schema(description = "物料编号")
    private String name;

    @Schema(description = "类型ID")
    private Long typeId;

    @Schema(description = "品牌ID")
    private Long brandId;

    @Schema(description = "基本计量单位(库内单位)")
    private String unit;

    @Schema(description = "规格")
    private String spec;

    @Schema(description = "型号")
    private String model;

    @Schema(description = "条码")
    private String barCode;
}
