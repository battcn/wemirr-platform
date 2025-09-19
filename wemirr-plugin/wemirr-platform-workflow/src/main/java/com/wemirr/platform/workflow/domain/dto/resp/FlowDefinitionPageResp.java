
package com.wemirr.platform.workflow.domain.dto.resp;

import com.wemirr.platform.workflow.domain.enums.FlowPublishStatus;
import com.wemirr.platform.workflow.domain.enums.ModelValue;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;

/**
 * 流程定义返回体
 *
 * @author battcn
 * @since 2025/6/04
 **/
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Schema(description = "流程定义返回体")
public class FlowDefinitionPageResp {

    @Schema(description = "主键ID")
    private Long id;

    @Schema(description = "编码")
    private String flowCode;

    @Schema(description = "名称")
    private String flowName;

    @Schema(description = "流程类别名称")
    private String categoryName;

    @Schema(description = "设计器模型（CLASSICS经典模型 MIMIC仿钉钉模型）")
    private ModelValue modelValue;

    @Schema(description = "设计器模型名称")
    private String modelValueName;

    @Schema(description = "流程版本")
    private String version;

    @Schema(description = "发布（ 0=未发布 1=已发布 9=已失效 ）")
    private FlowPublishStatus isPublish;

    @Schema(description = "发布名称")
    private String publishName;

    @Schema(description = "流程激活状态（0=挂起 1=激活）")
    private Integer activityStatus;

    @Schema(description = "扩展字段，预留给业务系统使用")
    private String ext;

    @Schema(description = "创建时间")
    private Instant createTime;

    public String getModelValueName() {
        return modelValue == null ? null :modelValue.getLabel();
    }
    public String getPublishName() {
        return isPublish == null ? null :isPublish.getLabel();
    }
}
