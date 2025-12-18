package com.wemirr.platform.workflow.domain.dto.req;

import com.wemirr.framework.db.mybatisplus.page.PageRequest;
import com.wemirr.platform.workflow.domain.enums.FlowPublishStatus;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author battcn
 * @since 2025/5/24
 **/
@Data
@EqualsAndHashCode(callSuper = true)
@Schema(name = "DefinitionPageReq", description = "流程定义分页请求对象")
public class DefinitionPageReq extends PageRequest {

    @Schema(description = "流程定义名称")
    private String flowName;

    @Schema(description = "流程分类ID")
    private String categoryId;

    @Schema(description = "流程激活状态（0=挂起 1=激活）")
    private Integer activityStatus;

    @Schema(description = "发布（ 0=未发布 1=已发布 9=已失效 ）")
    private FlowPublishStatus isPublish;
}
