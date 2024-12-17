package com.wemirr.platform.wms.matedata.domain.req;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * 物料类型保存请求数据
 *
 * @author ddCat
 * @since 2024-06-18
 */
@Data
@Schema(name = "MaterialCategorySaveReq", description = "物料类型保存请求数据")
public class MaterialCategorySaveReq {

    @Schema(description = "物料类型名称")
    private String name;

    @Schema(description = "父类型ID")
    private Long parentId;

    @Schema(description = "是否启用")
    private String status;

    @Schema(description = "备注")
    private String remark;
}
