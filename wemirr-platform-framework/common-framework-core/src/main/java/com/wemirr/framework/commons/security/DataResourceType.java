package com.wemirr.framework.commons.security;

import com.baomidou.mybatisplus.annotation.EnumValue;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonValue;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * <p>
 * 数据权限范围，值越大，权限越大
 * </p>
 *
 * @author Levin
 */
@Getter
@JsonFormat
@AllArgsConstructor
public enum DataResourceType {


    /**
     * 用户（比如你可以根据 地区、机构、公司、网点等维度控制权限，只需要指定数据资源类型即可）
     */
    USER("user", "用户维度"),
    TENANT("tenant", "租户维度"),
    COMPANY("company", "公司维护"),
    AREA("area", "地区维度"),
    ;

    @EnumValue
    @JsonValue
    private final String resource;
    private final String desc;

    @JsonCreator
    public static DataResourceType of(String resource) {
        if (resource == null) {
            return null;
        }
        for (DataResourceType info : values()) {
            if (info.resource.equals(resource)) {
                return info;
            }
        }
        return null;
    }
}
