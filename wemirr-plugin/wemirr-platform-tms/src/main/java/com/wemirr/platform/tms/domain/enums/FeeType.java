package com.wemirr.platform.tms.domain.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author Levin
 */
@AllArgsConstructor
@Getter
public enum FeeType {

    /**
     * 运输费
     */
    TRANSPORT(1, "运输费"),
    LOAD(2, "装货费"),
    UNLOAD(3, "卸货费"),
    WAIT(4, "等时费"),
    RELEASE(5, "空放费"),
    FINE(6, "违章罚款费"),
    FILE_EXPRESS(7, "文件快递费"),
    GOODS_EXPRESS(8, "货物快递费"),
    UPSTAIRS(9, "上楼费"),
    OTHERS(99, "其他费用"),
    ;

    private final Integer id;
    private final String name;


}
