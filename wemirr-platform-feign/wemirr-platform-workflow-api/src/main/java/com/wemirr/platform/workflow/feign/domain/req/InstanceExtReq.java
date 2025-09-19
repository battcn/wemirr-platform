package com.wemirr.platform.workflow.feign.domain.req;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 流程实例扩展数据
 *
 * @author battcn
 * @since 2025/5/29
 **/
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class InstanceExtReq {



    @Schema(description = "变量参数")
    private String variable;

    @Schema(description = "关键字")
    private String keyword;
}
