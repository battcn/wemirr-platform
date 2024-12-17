package com.wemirr.platform.wms.basic.domain.req;

import com.wemirr.framework.db.mybatisplus.page.PageRequest;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 储位(库位)分页查询请求参数
 *
 * @author ddCat
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Schema(name = "LocationPageReq", description = "储位(库位)分页查询请求参数")
public class LocationPageReq extends PageRequest {

    @Schema(description = "储位编号")
    private String code;

    @Schema(description = "储位名称")
    private String name;

    @Schema(description = "所属库区ID")
    private Long storageAreaId;

    @Schema(description = "所属巷道ID")
    private Long aisleId;

    @Schema(description = "储位规格ID")
    private Long specId;

    @Schema(description = "仓库ID")
    private Long warehouseId;

    @Schema(description = "是否高储位")
    private Boolean high;
}
