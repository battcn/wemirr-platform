package com.wemirr.platform.wms.outbound.domain.enums;


import com.baomidou.mybatisplus.annotation.EnumValue;
import com.baomidou.mybatisplus.annotation.IEnum;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonValue;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * 出库单状态
 *
 * @author ddCat
 * @since 2024-08-06
 */
@Getter
@AllArgsConstructor
@NoArgsConstructor
@JsonFormat
@Schema(description = "OutboundStatus")
public enum OutboundStatus implements IEnum<Integer> {

    DRAFT(10, "草稿"),
    PACKING(20, "配货中"),
    COMPLETE(30, "完成"),
    CANCEL(-10, "取消"),
    CLOSE(-20, "关闭"),
    ;

    @EnumValue
    @JsonValue
    private Integer status;

    @Schema(description = "描述")
    private String desc;

    @JsonCreator
    public static OutboundStatus of(Integer status) {
        if (status == null) {
            return null;
        }
        for (OutboundStatus info : values()) {
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
