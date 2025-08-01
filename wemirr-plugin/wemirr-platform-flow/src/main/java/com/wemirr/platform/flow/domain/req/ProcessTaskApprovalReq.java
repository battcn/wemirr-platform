package com.wemirr.platform.flow.domain.req;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

import java.util.List;

/**
 * 完成任务请求参数
 *
 * @author Levin
 */
@Data
public class ProcessTaskApprovalReq {


    @Schema(title = "附件")
    private List<String> attachments;

    @Schema(title = "审批意见")
    @NotBlank(message = "审批意见不能为空")
    @Length(max = 1000, message = "审批意见长度不能超过{max}")
    private String remark;

}