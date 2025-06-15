
package com.wemirr.platform.warmflow.domain.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.wemirr.framework.commons.entity.SuperEntity;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.time.Instant;

/**
 * 流程模型历史(DesignModelHistory)Entity
 *
 * @author Levin
 */
@Data
@EqualsAndHashCode(callSuper = true)
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@TableName("wp_process_deploy_history")
public class ProcessDeployHistory extends SuperEntity<Long> {


    @Schema(description = "租户ID")
    private Long tenantId;
    @Schema(description = "租户编码")
    private String tenantCode;
    @Schema(description = "租户名字")
    private String tenantName;

    @Schema(description = "模型ID")
    private Long modelId;

    @Schema(description = "流程定义key,多个逗号隔开")
    private String processDefinitionKey;

    @Schema(description = "流程定义ids,多个逗号隔开")
    private String processDefinitionId;

    @Schema(description = "bpmn模型(XML存储)")
    private String diagramData;

    @Schema(description = "部署名称")
    private String deploymentName;

    @Schema(description = "模型名称")
    private String diagramNames;

    @Schema(description = "部署ID")
    private String deploymentId;

    @Schema(description = "资源名称")
    private String resourceNames;

    @Schema(description = "资源IDS")
    private String resourceIds;

    @Schema(description = "是否pool模型,0-不是,1-是;默认0")
    private Integer havePool;

    @Schema(description = "部署时间")
    private Instant deploymentTime;

    @Schema(description = "模型类别ID")
    private Long processCategoryId;

    @Schema(description = "模型类别编码")
    private String processCategoryCode;

    @Schema(description = "模型类别名称")
    private String processCategoryName;

    @Schema(description = "当前版本")
    private Integer version;

    @Schema(description = "备注")
    private String remark;
}
