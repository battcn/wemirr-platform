package com.wemirr.platform.wms.basic.domain.req;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

/**
 * 库区保存请求数据
 * @author ddCat
 */
@Data
@Schema(name = "StorageAreaSaveReq", description = "库区保存请求数据")
public class StorageAreaSaveReq {

    @NotBlank(message = "库区编码不能为空")
    @Schema(description = "库区编号")
    private String code;

    @NotBlank(message = "库区名称不能为空")
    @Schema(description = "库区名称")
    private String name;

    @NotBlank(message = "库区类型不能为空")
    @Schema(description = "库区类型")
    private String type;

    @NotBlank(message = "库区状态不能为空")
    @Schema(description = "库区状态")
    private String status;

    @NotNull(message = "所属仓库不能为空")
    @Schema(description = "所属仓库ID")
    private Long warehouseId;

}
