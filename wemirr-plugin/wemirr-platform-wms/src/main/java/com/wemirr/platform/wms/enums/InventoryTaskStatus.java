package com.wemirr.platform.wms.enums;

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
 * InventoryTaskStatus
 *
 * @author Levin
 */
@Getter
@AllArgsConstructor
@NoArgsConstructor
@JsonFormat
@Schema(description = "InventoryTaskStatus")
public enum InventoryTaskStatus implements DictEnum<Integer> {

    CREATED(0, "创建"),
    IN_PROGRESS(1, "进行中"),
    FINISH(10, "已完成"),
    CANCEL(-1, "取消");
    @EnumValue
    @JsonValue
    private Integer status;

    @Schema(description = "描述")
    private String desc;

    @JsonCreator
    public static InventoryTaskStatus of(Integer status) {
        if (status == null) {
            return null;
        }
        for (InventoryTaskStatus info : values()) {
            if (info.status.equals(status)) {
                return info;
            }
        }
        return null;
    }


    @Override
    public Integer getValue() {
        return this.status;
    }

    @Override
    public String toString() {
        return String.valueOf(status);
    }
}
