
package com.wemirr.platform.bpm.domain.req;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 删除流程模型
 *
 * @author Levin
 * @since JDK11
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Schema(name = "DeleteHistoryDesignModelReq")
public class HistoryDesignModelDeleteReq {

    @Schema(title = "历史模型id")
    @NotBlank(message = "历史模型id不能为空")
    private String historyModelId;

    @Schema(title = "是否级联，默认false")
    @Builder.Default
    private Boolean cascade = false;

    @Schema(title = "是否跳过自定义监听器，默认false")
    @Builder.Default
    private Boolean skipCustomListeners = false;

    @Schema(title = "是否跳过io映射，默认false")
    @Builder.Default
    private Boolean skipIoMappings = false;
}
