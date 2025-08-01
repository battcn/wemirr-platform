package com.wemirr.platform.flow.domain.resp;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;
import java.util.List;

/**
 * 任务
 *
 * @author Levin
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ProcessTaskCommentResp {

    @Schema(description = "任务ID")
    private String taskId;

    @Schema(description = "任务名称")
    private String taskName;

    @Schema(description = "任务定义Key")
    private String taskDefinitionKey;

    @Schema(description = "审批人")
    private String approverName;

    @Schema(description = "审批人时间")
    private Instant approverTime;

    @Schema(description = "审核内容")
    private String remark;

    @Schema(description = "审核附件（暂时没有）")
    private List<String> attachments;

    @Schema(description = "执行人头像")
    private String avatar;

    @Schema(description = "评论内容")
    private List<TaskComment> comments;

    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class TaskComment {

        @Schema(description = "评论内容")
        private String message;

        @Schema(description = "评论时间")
        private Instant time;
    }

}
