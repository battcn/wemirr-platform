package com.wemirr.platform.wms.matedata.domain.req;

import com.wemirr.framework.db.mybatisplus.page.PageRequest;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 基本计量单位分页查询请求数据
 * @author ddCat
 * @since 2024-06-24
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Schema(name = "UnitPageReq", description = "基本计量单位分页查询请求数据")
public class UnitPageReq extends PageRequest {

    @Schema(description = "计量单位中文名称")
    private String name;

    @Schema(description = "计量单位英文名称")
    private String nameEn;

    @Schema(description = "计量单位符号")
    private String symbol;

    @Schema(description = "计量单位类型")
    private String type;

    @Schema(description = "计量单位状态")
    private String status;
}
