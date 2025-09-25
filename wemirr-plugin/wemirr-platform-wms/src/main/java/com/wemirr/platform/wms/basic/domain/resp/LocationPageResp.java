package com.wemirr.platform.wms.basic.domain.resp;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.time.Instant;

/**
 * 储位(库位)分页查询响应数据
 *
 * @author ddCat
 * @since 2024-06-17
 */
@Data
@Schema(name = "LocationPageResp", description = "储位(库位)分页查询响应数据")
public class LocationPageResp {

    @Schema(description = "ID")
    private Long id;

    @Schema(description = "储位编号")
    private String code;

    @Schema(description = "储位名称")
    private String name;

    @Schema(description = "仓库id")
    private Long warehouseId;

    @Schema(description = "所属库区")
    private Long storageAreaId;

    @Schema(description = "所属巷道")
    private Long aisleId;

    @Schema(description = "储位规格")
    private Long specId;

    @Schema(description = "储位类型（高储位;低储位）")
    private String type;

    @Schema(description = "位于货架第几层")
    private Integer shelfLayer;

    @Schema(description = "拣选储位销售等级")
    private String pickingLevel;

    @Schema(description = "创建人名称")
    private String createName;

    @Schema(description = "创建时间")
    private Instant createTime;
}
