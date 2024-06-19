package com.wemirr.framework.log.diff.domain.req;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author Levin
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DiffLogInfoQueryReq {

    @Schema(description = "业务组，比如：订单类型、商品类型")
    protected String businessGroup;

    @Schema(description = "日志标签 比如 编辑用户")
    protected String businessTag;

    @Schema(description = "业务ID")
    protected String businessKey;

}
