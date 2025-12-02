package com.wemirr.platform.ai.core.enums;

import com.baomidou.mybatisplus.annotation.EnumValue;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonValue;
import com.wemirr.framework.commons.entity.DictEnum;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * @author xJh
 * @since 2025/10/11
 **/
@Getter
@AllArgsConstructor
@NoArgsConstructor
@JsonFormat
@Schema(description = "ConversationType")
public enum ConversationType implements DictEnum<Integer> {

    /**
     * 对话类型[1-普通对话,2-通用智能体对话,3-平台智能体,4-知识库对话,5-图片生成]
     */
    NORMAL_TEXT(1, "普通文本对话"),
    GENERAL_AGENT(2, "通用智能体对话"),
    PLATFORM_AGENT(3, "平台智能体"),
    KNOWLEDGE_BASE(4, "知识库对话"),
    IMAGE_GENERATION(5, "图片生成"),
    ;

    @EnumValue
    @JsonValue
    private Integer type;

    @Schema(description = "描述")
    private String desc;

    @JsonCreator
    public static ConversationType of(Integer type) {
        if (type == null) {
            return null;
        }
        for (ConversationType info : values()) {
            if (info.type.equals(type)) {
                return info;
            }
        }
        return null;
    }


    @Override
    public Integer getValue() {
        return this.type;
    }

    @Override
    public String toString() {
        return String.valueOf(type);
    }

    @Override
    public String getLabel() {
        return "";
    }
}
