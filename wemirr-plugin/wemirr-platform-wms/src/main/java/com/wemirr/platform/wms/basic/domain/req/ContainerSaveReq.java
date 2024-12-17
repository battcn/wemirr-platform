package com.wemirr.platform.wms.basic.domain.req;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

/**
 * 容器保存请求数据
 *
 * @author ddCat
 * @since 2024-06-17
 */

@Data
@Schema(name = "ContainerSaveReq", description = "容器保存请求数据")
public class ContainerSaveReq {

    @NotBlank(message = "容器编码不能为空")
    @Schema(description = "容器编码")
    private String code;

    @NotBlank(message = "容器名称不能为空")
    @Schema(description = "容器名称")
    private String name;

    @NotNull(message = "容器规格不能为空")
    @Schema(description = "容器规格ID")
    private Long specId;

    @NotBlank(message = "容器用途能为空")
    @Schema(description = "容器用途")
    private String remark;

}
