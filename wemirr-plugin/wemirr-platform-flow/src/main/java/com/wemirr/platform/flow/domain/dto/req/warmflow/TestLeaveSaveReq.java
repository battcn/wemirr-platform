package com.wemirr.platform.flow.domain.dto.req.warmflow;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.util.Date;

@Data
public class TestLeaveSaveReq {
    /**
     * 主键ID
     */
    private Long id;
    /**
     * 请假类型
     */
    private String leaveType;

    /**
     * 开始时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date startDate;

    /**
     * 结束时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date endDate;

    /**
     * 请假天数
     */
    private Integer leaveDays;

    /**
     * 备注
     */
    private String remark;

    /**
     * 状态
     */
    private String status;
}
