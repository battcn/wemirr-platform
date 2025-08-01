package com.wemirr.platform.flow.domain.dto.bo;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.wemirr.framework.commons.entity.SuperEntity;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

/**
 * 请假业务对象 test_leave
 *
 * @author battcn
 * @date 2023-07-21
 */
@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
public class TestLeaveBo extends SuperEntity<Long> {

    /**
     * 主键
     */
    @NotNull(message = "主键不能为空", groups = {SuperEntity.Update.class})
    private Long id;

    /**
     * 请假类型
     */
    @NotBlank(message = "请假类型不能为空", groups = {SuperEntity.Save.class, SuperEntity.Update.class})
    private String leaveType;

    /**
     * 开始时间
     */
    @NotNull(message = "开始时间不能为空", groups = {SuperEntity.Save.class, SuperEntity.Update.class})
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date startDate;

    /**
     * 结束时间
     */
    @NotNull(message = "结束时间不能为空", groups = {SuperEntity.Save.class, SuperEntity.Update.class})
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date endDate;

    /**
     * 请假天数
     */
    private Integer leaveDays;

    /**
     * 开始时间
     */
    private Integer startLeaveDays;

    /**
     * 结束时间
     */
    private Integer endLeaveDays;

    /**
     * 请假原因
     */
    private String remark;

    /**
     * 状态
     */
    private String status;


}
