package com.wemirr.platform.workflow.domain.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.wemirr.framework.commons.entity.SuperEntity;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

/**
 * 流程实例扩展信息
 *
 * @author battcn
 * @since 2025/5/28
 **/
@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@TableName("flow_instance_ext")
public class InstanceExt extends SuperEntity<Long> {

    @Schema(description = "流程实例ID")
    private Long instanceId;

    @Schema(description = "标题")
    private String title;

    @Schema(description = "备注")
    private String remark;

    @Schema(description = "来源类型 例如新增 或者更新等")
    private String operationType;

    @Schema(description = "来源类型  比如网点")
    private String businessType;

    @Schema(description = "附件")
    private String attachment;

    @Schema(description = "变量")
    private String variable;

    @Schema(description = "业务id")
    private String businessId;

    @Schema(description = "业务编号")
    private String businessCode;

    @Schema(description = "关键字")
    private String keyword;

    private String formData;
    private String formSchemas;
    private String formScript;


}
