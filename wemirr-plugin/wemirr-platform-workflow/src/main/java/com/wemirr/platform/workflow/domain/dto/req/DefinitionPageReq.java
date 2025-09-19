package com.wemirr.platform.workflow.domain.dto.req;

import com.wemirr.framework.db.mybatisplus.page.PageRequest;
import com.wemirr.platform.workflow.domain.enums.FlowPublishStatus;
import com.wemirr.platform.workflow.domain.enums.ModelValue;
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


    @Schema(description = "流程定义编码")
    private String flowCode;

    @Schema(description = "流程分类id")
    private String categoryId;

    @Schema(description = "发布（0未开启 1开启 9）")
    private FlowPublishStatus publishStatus;

    @Schema(description = "设计器模型（CLASSICS经典模型 MIMIC仿钉钉模型）")
    private ModelValue modelValue;
}
