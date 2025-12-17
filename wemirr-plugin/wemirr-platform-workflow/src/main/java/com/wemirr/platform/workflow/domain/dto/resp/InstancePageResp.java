package com.wemirr.platform.workflow.domain.dto.resp;

import com.wemirr.platform.workflow.domain.enums.InsActivityStatus;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;

/**
 * 流程实例相关返回体
 * @author battcn
 * @since 2025/6/04
 **/
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Schema(description = "流程实例相关返回体")
public class InstancePageResp {

    @Schema(description = "流程实例Id")
    private Long id;

    @Schema(description = "流程实例Id")
    private Long instanceId;

    @Schema(description = "业务Id")
    private String businessId;

    @Schema(description = "流程节点类型")
    private Integer nodeType;

    @Schema(description = "流程节点编码")
    private String nodeCode;

    @Schema(description = "流程节点名称")
    private String nodeName;

    @Schema(description = "流程状态")
    private String flowStatus;

    @Schema(description = "流程状态")
    private String flowStatusName;

    @Schema(description = "创建人")
    private String createBy;

    @Schema(description = "流程编码")
    private String flowCode;

    @Schema(description = "流程名称")
    private String flowName;

    @Schema(description = "创建时间")
    private Instant createTime;

    @Schema(description = "修改时间")
    private Instant updateTime;

    @Schema(description = "创建人名称")
    private String createName;

    @Schema(description = "备注")
    private String remark;

    @Schema(description = "标题")
    private String title;

    @Schema(description = "流程定义Id")
    private Long definitionId;

    @Schema(description = "流程版本")
    private String version;

    @Schema(description = "附件")
    private String attachment;

    @Schema(description = "业务类型")
    private String businessType;

    @Schema(description = "业务编码")
    private String businessCode;

    @Schema(description = "关键字")
    private String keyword;

    @Schema(description = "流程节点状态")
    private InsActivityStatus activityStatus;

    @Schema(description = "流程节点状态")
    private String activityStatusName;

    @Schema(description = "流程分类名称")
    private String categoryName;

    @Schema(description = "流程分类ID")
    private String categoryId;

    public Long getInstanceId() {
        return id;
    }

    public String getActivityStatusName(){
        return activityStatus == null ? null :activityStatus.getLabel();
    }

}
