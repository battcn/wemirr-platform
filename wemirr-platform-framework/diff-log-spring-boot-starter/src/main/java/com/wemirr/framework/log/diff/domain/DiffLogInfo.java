package com.wemirr.framework.log.diff.domain;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;
import java.util.List;
import java.util.Map;

/**
 * 差异日志信息实体，记录操作日志的完整信息
 *
 * @author Levin
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "审计日志")
public class DiffLogInfo {

    @Schema(description = "服务名（比如 WMS 服务/TMS 服务）")
    protected String serviceName;

    @Schema(description = "业务组，比如：订单类型、商品类型")
    protected String businessGroup;

    @Schema(description = "日志标签 比如 编辑用户")
    protected String businessTag;

    @Schema(description = "业务ID")
    protected String businessKey;

    @Schema(description = "日志内容")
    protected String description;

    @Schema(description = "日志状态")
    protected Boolean status;

    @Schema(description = "租户ID")
    protected Long tenantId;

    @Schema(description = "操作人ID")
    protected Long createBy;

    @Schema(description = "操作人名字")
    protected String createName;

    @Schema(description = "日志的创建时间")
    protected Instant createTime;

    @Schema(description = "请求上下文信息（IP、浏览器、URI等）")
    protected Map<String, Object> extra;

    @Schema(description = "字段变更记录列表")
    protected List<FieldChange> variables;
}