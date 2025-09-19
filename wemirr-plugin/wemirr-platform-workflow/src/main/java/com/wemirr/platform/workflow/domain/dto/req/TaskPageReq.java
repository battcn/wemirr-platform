package com.wemirr.platform.workflow.domain.dto.req;

import com.wemirr.framework.db.mybatisplus.page.PageRequest;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

/**
 * 流程任务查询实体
 *
 * @author battcn
 * @since 2025/5/22
 **/
@Data
@EqualsAndHashCode(callSuper = true)
@Schema(description = "流程任务分页请求对象")
public class TaskPageReq extends PageRequest {

    @Schema(description = "流程实例id")
    private Long instanceId;

    @Schema(description = "权限范围")
    private List<String> permissionList;

    @Schema(description = "节点编码")
    private String nodeCode;

    @Schema(description = "节点名称")
    private String nodeName;

    @Schema(description = "审批人")
    private String approver;

    @Schema(description = "业务编码")
    private String businessCode;

    @Schema(description = "关键字")
    private String keyword;

    @Schema(description = "业务类型")
    private String businessType;
}
