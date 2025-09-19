package com.wemirr.platform.workflow.domain.enums;

import com.baomidou.mybatisplus.annotation.EnumValue;
import com.fasterxml.jackson.annotation.JsonValue;
import com.wemirr.framework.commons.entity.DictEnum;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

/**
 * @author battcn
 * @since 2025/8/15
 **/
@Getter
@RequiredArgsConstructor
public enum ModelValue implements DictEnum<String> {

    CLASSICS("CLASSICS", "经典模型"),

    MIMIC("MIMIC", "仿钉钉模型");

    @EnumValue
    @JsonValue
    private final String value;

    private final String label;
}
