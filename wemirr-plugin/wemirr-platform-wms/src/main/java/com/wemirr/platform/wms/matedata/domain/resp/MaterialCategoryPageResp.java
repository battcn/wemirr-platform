package com.wemirr.platform.wms.matedata.domain.resp;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.time.Instant;

/**
 * 物料类型分页查询响应数据
 *
 * @author ddCat
 * @since 2024-06-18
 */
@Data
@Schema(name = "MaterialCategoryPageResp", description = "物料类型分页查询响应数据")
public class MaterialCategoryPageResp {

    @Schema(description = "ID")
    private Long id;

    @Schema(description = "物料类型编号")
    private String code;

    @Schema(description = "物料类型名称")
    private String name;

    @Schema(description = "父类型ID")
    private Long parentId;

    @Schema(description = "是否启用")
    private String status;

    @Schema(description = "备注")
    private String remark;

    @Schema(description = "创建人名称")
    private String createName;

    @Schema(description = "创建时间")
    private Instant createTime;

}
