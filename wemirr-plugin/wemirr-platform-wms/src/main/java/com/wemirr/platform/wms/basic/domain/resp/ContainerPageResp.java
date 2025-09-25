package com.wemirr.platform.wms.basic.domain.resp;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.time.Instant;

/**
 * 容器分页查询响应数据
 *
 * @author ddCat
 * @since 2024-06-17
 */

@Data
@Schema(name = "ContainerPageResp", description = "容器分页查询响应数据")
public class ContainerPageResp {

    @Schema(description = "ID")
    private Long id;

    @Schema(description = "容器编码")
    private String code;

    @Schema(description = "容器名称")
    private String name;

    @Schema(description = "容器规格ID")
    private Long specId;

    @Schema(description = "容器用途")
    private String remark;

    @Schema(description = "创建人名称")
    private String createName;

    @Schema(description = "创建时间")
    private Instant createTime;
}
