package com.wemirr.platform.wms.inbound.domain.enums;


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
 * 收货计划状态
 *
 * @author ddCat
 * @since 2024-06-24
 */
@Getter
@AllArgsConstructor
@NoArgsConstructor
@JsonFormat
@Schema(description = "ReceivingStatus")
public enum ReceivingStatus implements IEnum<Integer> {

    DRAFT(0, "草稿"),
    WAIT(10, "待执行"),
    IN_EXECUTION(20, "执行中"),
    COMPLETED(30, "已完成"),
    CANCEL(-10, "已取消"),
    // 部分收货后将收货计划关闭
    CLOSE(-20, "关闭"),
    ;

    @EnumValue
    @JsonValue
    private Integer status;

    @Schema(description = "描述")
    private String desc;

    @JsonCreator
    public static ReceivingStatus of(Integer status) {
        if (status == null) {
            return null;
        }
        for (ReceivingStatus info : values()) {
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
