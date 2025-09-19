package com.wemirr.platform.workflow.domain.dto.req;

import com.wemirr.framework.db.mybatisplus.page.PageRequest;
import com.wemirr.platform.workflow.domain.enums.InsActivityStatus;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 流程实例请求体
 *
 * @author battcn
 * @since 2025/6/04
 **/
@Data
@EqualsAndHashCode(callSuper = true)
@Schema(name = "InstancePageReq", description = "流程实例请求体")
public class InstancePageReq extends PageRequest {

    @Schema(description = "流程实例id")
    private Long instanceId;

    @Schema(description = "节点编码")
    private String nodeCode;

    @Schema(description = "节点名称")
    private String nodeName;

    @Schema(description = "业务编码")
    private String businessCode;

    @Schema(description = "关键字")
    private String keyword;

    @Schema(description = "业务类型")
    private String businessType;

    @Schema(description = "流程活动状态")
    private InsActivityStatus activityStatus;

    @Schema(description = "流程状态")
    private String approvalStatus;

    @Schema(description = "用户id")
    private String userId;

    @Schema(description = "流程分类id")
    private String categoryId;

}
