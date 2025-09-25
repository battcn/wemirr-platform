package com.wemirr.platform.wms.matedata.domain.resp;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.time.Instant;
import java.util.List;

/**
 * 物料类型列表查询响应数据
 *
 * @author ddCat
 * @since 2024-06-23
 */
@Data
@Schema(name = "MaterialCategoryListResp", description = "物料类型列表查询响应数据")
public class MaterialCategoryListResp {

    @Schema(description = "ID")
    private Long id;

    @Schema(description = "物料类型编号")
    private String code;

    @Schema(description = "物料类型名称")
    private String label;

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

    @Schema(description = "是否含有子节点")
    private boolean hasChildren;

    @Schema(description = "是否已加载")
    private boolean loaded;

    @Schema(description = "子节点")
    private List<MaterialCategoryListResp> children;

    public String getLabel() {
        return label == null ? name : label;
    }
}
