package com.wemirr.platform.flow.domain.dto.req;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

/**
 * @author levin
 */
@Data
public class ProcessTaskAssigneeReq {


    @NotNull(message = "用户ID不能为空")
    @Schema(description = "用户ID")
    private String userId;

    @Schema(description = "备注")
    private String remark;

    @Schema(description = "审核附件（暂时没有）")
    private String attachments;

}
