package com.wemirr.platform.tms.domain.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author Levin
 */
@AllArgsConstructor
@Getter
public enum EventType {

    /**
     * 101 = 提货
     */
    PICK(101, "提货"),
    DISPATCH(102, "调度"),
    ERR_PICK(199, "提货异常"),

    ARRIVE_DEPARTURE(201, "到达始发站"),
    LEAVE_DEPARTURE(209, "离开始发站"),

    ARRIVE_TRANSFER(401, "到达中转站"),
    ON_WAY(405, "在途运输"),
    LEAVE_TRANSFER(409, "离开中转站"),
    ERR_ON_WAY(499, "在途异常"),

    ARRIVE_DESTINATION(601, "到达目的站"),
    LEAVE_DESTINATION(609, "离开目的站"),

    POD(801, "签收"),
    ERR_POD(899, "签收异常"),

    ;

    private final Integer id;
    private final String name;

}
