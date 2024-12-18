package com.wemirr.platform.wms.basic.domain.req;

import com.wemirr.platform.wms.inbound.domain.enums.WeightMode;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

/**
 * @author Levin
 */
@Data
public class WorkbenchSaveReq {

    @NotBlank(message = "编号不能为空")
    @Schema(description = "编号")
    private String code;

    @NotBlank(message = "名称不能为空")
    @Schema(description = "名称")
    private String name;

    @NotBlank(message = "状态不能为空")
    @Schema(description = "状态")
    private String status;

    @NotBlank(message = "类型不能为空")
    @Schema(description = "类型")
    private String type;

    @Schema(description = "类别")
    private String category;

    @Schema(description = "复核模式")
    private String reviewMode;

    @Schema(description = "任务类型")
    private String taskType;

    @NotNull(message = "称重模式不能为")
    @Schema(description = "称重模式")
    private WeightMode weightMode;

    @Schema(description = "MAC地址")
    private String macAddress;

}
