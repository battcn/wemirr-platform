package com.wemirr.platform.flow.domain.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.wemirr.framework.commons.entity.SuperEntity;
import com.wemirr.platform.flow.domain.enums.ProcInstStatus;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.time.Instant;

/**
 * 流程实例扩展信息表
 *
 * @author Levin
 */
@Data
@EqualsAndHashCode(callSuper = true)
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@TableName("wp_process_instance_ext")
public class ProcessInstanceExt extends SuperEntity<Long> {

    @Schema(description = "模型数据")
    private String diagramData;

    @Schema(description = "模型名称")
    private String diagramName;

    @Schema(description = "流程定义ID")
    private String procDefId;

    @Schema(description = "流程定义名称")
    private String procDefName;

    @Schema(description = "流程定义KEY")
    private String procDefKey;

    @Schema(description = "流程实例ID")
    private String procInstId;

    @Schema(description = "流程实例名称")
    private String procInstName;

    @Schema(description = "流程实例版本")
    private Integer procInstVersion;

    @Schema(description = "流程类别ID")
    private Long procInstCategoryId;

    @Schema(description = "流程类别Code")
    private String procInstCategoryCode;

    @Schema(description = "流程类别名称")
    private String procInstCategoryName;

    @Schema(description = "业务KEY（如：ID或者编号）")
    private String businessKey;

    @Schema(description = "业务组（如：表名）")
    private String businessGroup;

    @Schema(description = "激活状态")
    private Boolean procInstActivate;

    @Schema(description = "流程实例状态 处理中，已完成，已作废")
    private ProcInstStatus procInstStatus;

    @Schema(description = "流程实例开始时间")
    private Instant procInstStartTime;

    @Schema(description = "流程实例结束时间")
    private Instant procInstEndTime;

    @Schema(defaultValue = "脚本")
    private String formScript;

    @Schema(description = "表单配置")
    private String formSchemas;

    @Schema(description = "表单数据")
    private String formData;

}
