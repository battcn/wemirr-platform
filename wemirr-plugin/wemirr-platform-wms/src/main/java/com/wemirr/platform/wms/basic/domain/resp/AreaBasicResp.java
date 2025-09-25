package com.wemirr.platform.wms.basic.domain.resp;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.math.BigDecimal;
import java.time.Instant;

/**
 * 省份/城市/区域信息
 * @author ddCat
 */
@Data
@Schema(name = "AreaBasicResp", description = "省份/城市/区域信息")
public class AreaBasicResp {

    @Schema(description = "名称")
    private String name;

    @Schema(description = "经度")
    private BigDecimal longitude;

    @Schema(description = "纬度")
    private BigDecimal latitude;

    @Schema(description = "创建人名称")
    private String createName;

    @Schema(description = "创建时间")
    private Instant createTime;
}
