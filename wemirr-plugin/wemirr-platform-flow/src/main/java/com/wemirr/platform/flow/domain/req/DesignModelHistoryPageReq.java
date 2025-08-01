
package com.wemirr.platform.flow.domain.req;

import com.wemirr.framework.db.mybatisplus.page.PageRequest;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.EqualsAndHashCode;


/**
 * 流程模型历史分页查询Request
 *
 * @author Levin
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Schema(name = "DesignModelHistoryPageReq")
public class DesignModelHistoryPageReq extends PageRequest {

    @Schema(description = "模型id")
    @NotBlank(message = "模型id不能为空")
    private String modelId;

    @Schema(description = "模型或类型名称或部署名称")
    private String keyword;
}
