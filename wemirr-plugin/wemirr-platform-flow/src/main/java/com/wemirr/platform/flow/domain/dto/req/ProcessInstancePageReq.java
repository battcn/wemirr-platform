package com.wemirr.platform.flow.domain.dto.req;

import com.wemirr.framework.db.mybatisplus.page.PageRequest;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 流程实例查询参数
 * @author Levin
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Schema(name = "ProcessInstancePageReq")
public class ProcessInstancePageReq extends PageRequest {

    @Schema(description = "流程定义key")
    private String procDefKey;

    @Schema(description = "流程类别ID")
    private Long procInstCategoryId;

    @Schema(description = "流程定义名称")
    private String procDefName;

    @Schema(description = "流程实例名称")
    private String procInstName;

    @Schema(description = "流程实例状态")
    private Boolean procInstActivate;

}
