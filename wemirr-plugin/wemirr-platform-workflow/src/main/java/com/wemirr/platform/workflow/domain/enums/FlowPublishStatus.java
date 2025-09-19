package com.wemirr.platform.workflow.domain.enums;

import com.baomidou.mybatisplus.annotation.EnumValue;
import com.fasterxml.jackson.annotation.JsonValue;
import com.wemirr.framework.commons.entity.DictEnum;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

/**
 * 流程定义发布状态( 0未发布 1已发布 9失效 ）
 * @author battcn
 * @since 2025/8/15
 **/
@Getter
@RequiredArgsConstructor
public enum FlowPublishStatus implements DictEnum<Integer> {

    UNPUBLISHED(0, "未发布"),
    PUBLISHED(1, "已发布"),
    INVALID(9, "失效"),

    ;

    @EnumValue
    @JsonValue
    private final Integer value;

    private final String label;
}