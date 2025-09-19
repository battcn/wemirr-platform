package com.wemirr.platform.workflow.domain.enums;

import com.baomidou.mybatisplus.annotation.EnumValue;
import com.fasterxml.jackson.annotation.JsonValue;
import com.wemirr.framework.commons.entity.DictEnum;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 流程实例活动状态枚举
 * @author battcn
 * @since 2025/8/14
 **/
@Getter
@AllArgsConstructor
public enum InsActivityStatus implements DictEnum<Integer> {

    SUSPENDED(0,"挂起"),

    ACTIVITY(1,"激活");

    /**
     * 状态码
     */
    @EnumValue
    @JsonValue
    private final Integer value;

    /**
     * 描述
     */
    private final String label;
}
