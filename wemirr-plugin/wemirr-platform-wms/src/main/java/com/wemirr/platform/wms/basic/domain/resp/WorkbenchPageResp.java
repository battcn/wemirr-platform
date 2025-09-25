package com.wemirr.platform.wms.basic.domain.resp;

import com.wemirr.platform.wms.inbound.domain.enums.WeightMode;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.time.Instant;

/**
 * @author Levin
 */
@Data
public class WorkbenchPageResp {

    @Schema(description = "ID")
    private Long id;

    @Schema(description = "编号")
    private String code;

    @Schema(description = "名称")
    private String name;

    @Schema(description = "状态")
    private String status;

    @Schema(description = "类型")
    private String type;

    @Schema(description = "类别")
    private String category;

    @Schema(description = "复核模式")
    private String reviewMode;

    @Schema(description = "任务类型")
    private String taskType;

    @Schema(description = "称重模式")
    private WeightMode weightMode;

    @Schema(description = "MAC地址")
    private String macAddress;

    @Schema(description = "创建人名称")
    private String createName;

    @Schema(description = "创建时间")
    private Instant createTime;

    @Schema(description = "最后修改时间")
    private Instant lastModifiedTime;

    @Schema(description = "最后修改人名称")
    private String lastModifiedName;

}
