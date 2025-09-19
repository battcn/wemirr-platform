package com.wemirr.platform.workflow.feign.domain.enums;

import com.baomidou.mybatisplus.annotation.EnumValue;
import com.fasterxml.jackson.annotation.JsonValue;
import com.wemirr.framework.commons.entity.DictEnum;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 工作流动作枚举，表示不同的操作类型。
 * @author battcn
 */
@Getter
@AllArgsConstructor
public enum FlowActionType implements DictEnum<String> {
    /**
     * 创建动作
     */
    CREATE("create", "创建动作"),
    /**
     * 更新动作
     */
    MODIFY("modify", "更新动作"),
    /**
     * 删除动作
     */
    DELETE("delete", "删除动作"),
    /**
     * 启用禁用
     */
    ENABLE("enable", "启用禁用"),
    /**
     * 用于创建流程的时候,回写流程实例id
     */
    INIT("init", "初始化流程");

    @EnumValue
    @JsonValue
    private final String value;

    @Schema(description = "描述")
    private final String label;

}