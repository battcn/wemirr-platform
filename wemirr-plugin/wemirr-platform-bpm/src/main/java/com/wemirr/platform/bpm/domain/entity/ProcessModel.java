package com.wemirr.platform.bpm.domain.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.wemirr.framework.commons.entity.SuperEntity;
import com.wemirr.platform.bpm.feign.domain.enums.ProcessModelStatus;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.time.Instant;


/**
 * 流程模型管理
 *
 * @author Levin
 */
@Data
@EqualsAndHashCode(callSuper = true)
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@TableName("wp_process_model")
public class ProcessModel extends SuperEntity<Long> {

    @Schema(description = "模型编号")
    private String code;

    @Schema(description = "bpmn模型")
    private String diagramData;

    @Schema(description = "ICON")
    private String diagramIcon;

    @Schema(description = "租户ID")
    private Long tenantId;

    @Schema(description = "流程定义key,多个逗号隔开")
    private String definitionKey;

    @Schema(description = "流程定义ids,多个逗号隔开")
    private String definitionId;

    @Schema(description = "模型名称")
    private String diagramName;

    @Schema(description = "类别ID")
    private Long categoryId;

    @Schema(description = "类别编码")
    private String categoryCode;

    @Schema(description = "类别名字")
    private String categoryName;

    /**
     * 模型状态:0-未部署,1-已经部署,2-新版本待部署,参考常量字段:ProcessModelStatus
     */
    private ProcessModelStatus status;

    @Schema(description = "是否pool模型,0-不是,1-是", example = "0")
    private Integer havePool;

    @Schema(description = "部署名称")
    private String deployName;

    @Schema(description = "部署ID")
    private String deployId;


    @Schema(description = "资源ID,多个逗号分割")
    private String resourceIds;

    @Schema(description = "资源名称")
    private String resourceNames;

    @Schema(description = "部署时间")
    private Instant deployTime;

    @Schema(description = "当前模型版本")
    private Integer version;

    @Schema(description = "备注")
    private String remark;

    @Schema(description = "外置表单Key")
    private String formKey;

}
