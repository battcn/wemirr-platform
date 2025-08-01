package com.wemirr.platform.flow.domain.enums;

import com.baomidou.mybatisplus.annotation.EnumValue;
import com.baomidou.mybatisplus.annotation.IEnum;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonValue;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Objects;

/**
 * @author Levin
 */
@Getter
@Schema
@JsonFormat
@NoArgsConstructor
@AllArgsConstructor
public enum TaskCommentType implements IEnum<Integer> {

    /**
     * 0=评论;1=审批
     */
    COMMENT(0, "评论"),
    APPROVAL(1, "审批"),
    TRANSFER(2, "转办"),
    DELEGATE(3, "委派");;

    @EnumValue
    @JsonValue
    private Integer type;

    @Schema(description = "描述")
    private String desc;

    @JsonCreator
    public static TaskCommentType of(Integer type) {
        if (type == null) {
            return null;
        }
        for (TaskCommentType info : values()) {
            if (Objects.equals(info.type, type)) {
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
}
