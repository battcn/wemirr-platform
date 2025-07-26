package com.wemirr.platform.warmflow.domain.resp.warmflow;

import com.alibaba.excel.annotation.ExcelProperty;
import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;

public class TestLeaveResp {
    /**
     * 主键
     */
    @ExcelProperty(value = "主键")
    private Long id;

    /**
     * 请假类型
     */
    @ExcelProperty(value = "请假类型")
    private String leaveType;

    /**
     * 开始时间
     */
    @ExcelProperty(value = "开始时间")
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date startDate;

    /**
     * 结束时间
     */
    @ExcelProperty(value = "结束时间")
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date endDate;

    /**
     * 请假天数
     */
    @ExcelProperty(value = "请假天数")
    private Integer leaveDays;

    /**
     * 备注
     */
    @ExcelProperty(value = "请假原因")
    private String remark;

    /**
     * 状态
     */
    @ExcelProperty(value = "状态")
    private String status;
}
