package com.wemirr.platform.bpm.domain.resp;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;
import java.util.List;

/**
 * 查看流程功能 展示流程图需要的参数
 *
 * @author Levin
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Schema(name = "ProcessInstanceImageResp")
public class ProcessInstanceDetailResp {

    @Schema(description = "模型二进制数据")
    private String diagramData;
    @Schema(description = "模型名称")
    private String diagramName;
    @Schema(description = "模型key")
    private String processDefinitionKey;
    @Schema(description = "模型ID")
    private String processDefinitionId;
    @Schema(description = "实例ID")
    private String processInstanceId;
    @Schema(description = "流程实例名称")
    private String processInstanceName;
    @Schema(description = "版本")
    private Integer version;
    @Schema(description = "流程开始时间")
    private Instant processStartTime;
    @Schema(description = "流程结束时间")
    private Instant processEndTime;
    @Schema(description = "流程实例节点信息")
    private List<ProcessInstanceActivityNode> nodeList;

    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class ProcessInstanceActivityNode {

        @Schema(description = "节点ID")
        private String id;

        @Schema(description = "节点类型",example = "node | line")
        private String type;


    }
}