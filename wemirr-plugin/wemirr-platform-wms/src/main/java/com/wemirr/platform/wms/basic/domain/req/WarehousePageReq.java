package com.wemirr.platform.wms.basic.domain.req;

import com.wemirr.framework.db.mybatisplus.page.PageRequest;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 仓库分页查询请求数据
 *
 * @author ddCat
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Schema(name = "WarehousePageReq", description = "仓库分页查询请求数据")
public class WarehousePageReq extends PageRequest {

    @Schema(description = "仓库编号")
    private String code;

    @Schema(description = "仓库名称")
    private String name;

    @Schema(description = "仓库状态")
    private String status;

}
