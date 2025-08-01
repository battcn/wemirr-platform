package com.wemirr.platform.flow.domain.dto.resp;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

/**
 * 流程信息
 *
 * @author Levin
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Deprecated
public class ProcessInstanceNodeResp {

    @Schema(name = "id", description = "主键ID")
    private String id;
    @Schema(name = "activityId", description = "活动ID")
    private String activityId;
    @Schema(name = "executionId", description = "执行ID")
    private String executionId;
    @Schema(name = "activityName", description = "活动名称")
    private String activityName;
    @Schema(name = "activityType", description = "活动类型")
    private String activityType;
    @Schema(name = "userTask", description = "是否是用户任务")
    private boolean userTask;
    @Schema(name = "multi", description = "是否是多实例任务")
    private boolean multi;
    @Schema(name = "multiCompleteInfo", description = "多实例完成信息")
    private String multiCompleteInfo;
    @Schema(name = "multiInfos", description = "多实例信息")
    private String multiInfos;
    @Schema(name = "activityInstanceId", description = "活动实例ID")
    private String activityInstanceId;
    @Schema(name = "activityInstanceState", description = "活动实例状态")
    private int activityInstanceState;
    @Schema(name = "parentActivityInstanceId", description = "活动实例父ID")
    private String parentActivityInstanceId;
    @Schema(name = "taskId", description = "任务ID")
    private String taskId;
    @Schema(name = "taskAssignee", description = "执行人")
    private String taskAssignee;
    @Schema(name = "taskAssigneeInfo", description = "执行人信息")
    private Object taskAssigneeInfo;
    @Schema(name = "candidateUsers", description = "候选用户")
    private List<String> candidateUsers;
    @Schema(name = "candidateGroups", description = "候选组")
    private List<String> candidateGroups;
    @Schema(name = "durationInMillis", description = "持续时间格式")
    private Long durationInMillis;
    @Schema(name = "startTime", description = "开始时间")
    private Date startTime;
    @Schema(name = "endTime", description = "结束时间")
    private Date endTime;
    @Schema(name = "dueDate", description = "耗时")
    private String dueDate;
    @Schema(name = "followUpDate", description = "跟进时间")
    private String followUpDate;
    @Schema(name = "dueDateStr", description = "耗时-字符串")
    private String dueDateStr;
    @Schema(name = "dueFormatDate", description = "耗时格式")
    private String dueFormatDate;
    @Schema(name = "followUpDateStr", description = "跟进时间-字符串")
    private String followUpDateStr;
    @Schema(name = "followUpFormatDate", description = "跟进时间格式")
    private String followUpFormatDate;
    @Schema(name = "sequenceCounter", description = "计数器")
    private int sequenceCounter;
    @Schema(name = "taskTitle", description = "任务标题")
    private String taskTitle;
    @Schema(name = "handleType", description = "handle类型")
    private String handleType;
    @Schema(name = "handleTypeDescribe", description = "handle类型描述")
    private String handleTypeDescribe;
    @Schema(name = "propertyInfo", description = "参数信息")
    private String propertyInfo;
    @Schema(name = "formModel", description = "表单模型")
    private String formModel;
    @Schema(name = "approvalStatus", description = "审批状态")
    private Integer approvalStatus;
    @Schema(name = "optionInfos", description = "审批信息")
    private String optionInfos;
    @Schema(name = "owner", description = "代理人")
    private String owner;
    @Schema(name = "ownerUserInfo", description = "代理人信息")
    private String ownerUserInfo;

}