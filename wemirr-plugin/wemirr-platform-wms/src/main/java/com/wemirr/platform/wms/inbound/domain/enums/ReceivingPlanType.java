package com.wemirr.platform.wms.inbound.domain.enums;

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
 * 入库计划类型
 *
 * @author ddCat
 */
@Getter
@AllArgsConstructor
@NoArgsConstructor
@JsonFormat
@Schema(description = "ReceivingPlanType")
public enum ReceivingPlanType implements DictEnum<String> {

    PURCHASE_INVENTORY("PURCHASE_INVENTORY", "标准采购订单"),
    RETURN_INVENTORY("RETURN_INVENTORY", "退货入库"),
    OUTSOURCING_INVENTORY("OUTSOURCING_INVENTORY", "委外加工入库"),
    REPAIR_INVENTORY("REPAIR_INVENTORY", "维修入库"),
    ;

    @EnumValue
    @JsonValue
    private String value;

    @Schema(description = "描述")
    private String label;

    @JsonCreator
    public static ReceivingPlanType of(String type) {
        if (type == null) {
            return null;
        }
        for (ReceivingPlanType info : values()) {
            if (info.value.equals(type)) {
                return info;
            }
        }
        return null;
    }
}
