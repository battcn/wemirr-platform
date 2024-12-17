package com.wemirr.platform.tms.domain.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author Levin
 */
@AllArgsConstructor
@Getter
public enum QtyUnit {

    PIECE(1, "件"),
    CASE(2, "箱"),
    TRAY(3, "托"),
    BUCKET(4, "桶"),
    PACKAGE(5, "包"),
    ROLL(6, "卷"),
    BOX(7, "盒"),
    CHEST(8, "柜"),
    PIECE_ONE(9, "个"),
    OTHERS(99, "其他"),
    ;

    private final Integer id;
    private final String name;

    public static String getNameById(Integer id) {
        for (QtyUnit qtyUnit : QtyUnit.values()) {
            if (qtyUnit.getId().equals(id)) {
                return qtyUnit.getName();
            }
        }
        return null;
    }

    public static Integer getIdByName(String name) {
        for (QtyUnit qtyUnit : QtyUnit.values()) {
            if (qtyUnit.getName().equals(name)) {
                return qtyUnit.getId();
            }
        }
        return null;
    }

}
