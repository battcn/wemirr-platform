package com.wemirr.platform.workflow.feign.domain.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 流程定义id 与tag的映射
 * @author battcn
 * @since 2025/5/28
 **/
@Getter
@AllArgsConstructor
public enum DefId2Tag {
    /**
     * 网点
     */
    SITE("SITE", "site", "site", "网点信息", ""),
    DEMAND("DEMAND", "demand", "demand", "需求", "lock:demand:work-flow"),
    ;
    /**
     * 流程定义编码
     */
    private final String flowCode;

    /**
     * 流程定义对应的tag信息
     */
    private final String tag;

    /**
     * 流程定义对应的业务类型
     */
    private final String businessType;

    /**
     * 业务类型描述
     */
    private final String desc;

    /**
     * redis key
     */
    private final String redisKey;

    /**
     * 根据流程定义获取tag信息
     * @param code code
     * @return Tag
     */
    public static String ofTag(String code) {
        for (DefId2Tag value : values()) {
            if (value.flowCode.equals(code)) {
                return value.tag;
            }
        }
        return null;
    }

    /**
     * 根据流程定义获取业务类型信息
     * @param code code
     * @return BusinessType
     */
    public static String ofBusinessType(String code) {
        for (DefId2Tag value : values()) {
            if (value.flowCode.equals(code)) {
                return value.businessType;
            }
        }
        return null;
    }
}
