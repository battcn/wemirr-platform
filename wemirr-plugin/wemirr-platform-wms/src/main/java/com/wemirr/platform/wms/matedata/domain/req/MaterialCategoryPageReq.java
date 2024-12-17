package com.wemirr.platform.wms.matedata.domain.req;

import com.wemirr.framework.db.mybatisplus.page.PageRequest;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 物料类型分页查询请求数据
 *
 * @author ddCat
 * @since 2024-06-18
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Schema(name = "MaterialTypePageReq", description = "物料类型分页查询请求数据")
public class MaterialCategoryPageReq extends PageRequest {

    @Schema(description = "物料类型编号")
    private String code;

    @Schema(description = "物料类型名称")
    private String name;

    @Schema(description = "父类型ID")
    private Long parentId;

    @Schema(description = "是否启用")
    private String status;
}
