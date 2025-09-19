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
 * 流程分类
 *
 * @author battcn
 * @since 2025/5/28
 **/
@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@TableName("flow_category")
public class FlowCategory extends SuperEntity<Long> {

    @Schema(description = "ICON")
    private String icon;

    @Schema(description = "分类名称")
    private String name;

    @Schema(description = "分类状态")
    private Boolean status;

    @Schema(description = "分类描述")
    private String description;


}
