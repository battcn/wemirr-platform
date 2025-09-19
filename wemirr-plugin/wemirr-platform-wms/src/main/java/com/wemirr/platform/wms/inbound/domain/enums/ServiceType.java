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
 * 服务类型：陆运、海运、空运
 *
 * @author ddCat
 */
@Getter
@AllArgsConstructor
@NoArgsConstructor
@JsonFormat
@Schema(description = "ServiceType")
public enum ServiceType implements DictEnum<String> {

    LAND("LAND", "陆运"),
    SEA("SEA", "海运"),
    AIR("AIR", "空运");

    @EnumValue
    @JsonValue
    private String value;

    @Schema(description = "描述")
    private String label;

    @JsonCreator
    public static ServiceType of(String type) {
        if (type == null) {
            return null;
        }
        for (ServiceType info : values()) {
            if (info.value.equals(type)) {
                return info;
            }
        }
        return null;
    }

}
