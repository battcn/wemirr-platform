package com.wemirr.platform.wms.basic.domain.req;

import com.wemirr.framework.db.mybatisplus.page.PageRequest;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 库区分页查询请求参数
 *
 * @author ddCat
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Schema(name = "StorageAreaPageReq", description = "库区分页查询请求参数")
public class StorageAreaPageReq extends PageRequest {

    @Schema(description = "库区编号")
    private String code;

    @Schema(description = "库区名称")
    private String name;

    @Schema(description = "库区类型")
    private String type;

    @Schema(description = "库区状态")
    private String status;

    @Schema(description = "仓库id")
    private Long warehouseId;
}
