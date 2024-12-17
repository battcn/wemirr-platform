package com.wemirr.platform.tms.domain.enums;

import com.baomidou.mybatisplus.annotation.EnumValue;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonValue;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 订单状态
 *
 * @author Levin
 */
@Getter
@AllArgsConstructor
@Schema
@JsonFormat
public enum OrderStatus {

    /**
     * 订单状态:-10取消,0新建,10提货,20到达始发站,30离开始发站,40到达目的站,50离开目的站,60签收,70回单,80完成
     */
    CANCELLED(-10, "取消"),
    NEW(0, "新建"),
    PICKED(10, "提货"),
    ARRIVED_ORIGIN(20, "到达始发站"),
    LEAVE_ORIGIN(30, "离开始发站"),
    ARRIVED_DEST(40, "到达目的站"),
    LEAVE_DEST(50, "离开目的站"),
    PODED(60, "签收"),
    HAS_EPODED(70, "回单"),
    FINISHED(80, "完成"),


    ;
    @EnumValue
    @JsonValue
    public final Integer status;

    @Schema(description = "名称")
    public final String name;

    @JsonCreator
    public static OrderStatus of(Integer status) {
        if (status == null) {
            return null;
        }
        for (OrderStatus info : values()) {
            if (info.status.equals(status)) {
                return info;
            }
        }
        return null;
    }

}
