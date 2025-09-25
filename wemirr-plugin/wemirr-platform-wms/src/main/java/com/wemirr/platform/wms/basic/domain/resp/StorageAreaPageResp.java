package com.wemirr.platform.wms.basic.domain.resp;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.time.Instant;

/**
 * 库区分页查询响应数据
 *
 * @author ddCat
 */
@Data
@Schema(name = "StorageAreaPageResp", description = "库区分页查询响应数据")
public class StorageAreaPageResp {

    @Schema(description = "ID")
    private Long id;

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

    @Schema(description = "创建人名称")
    private String createName;

    @Schema(description = "创建时间")
    private Instant createTime;

    @Schema(description = "最后修改时间")
    private Instant lastModifiedTime;

    @Schema(description = "最后修改人名称")
    private String lastModifiedName;
}
