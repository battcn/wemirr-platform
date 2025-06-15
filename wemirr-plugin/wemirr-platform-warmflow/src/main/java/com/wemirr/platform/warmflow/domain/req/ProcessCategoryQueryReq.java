
package com.wemirr.platform.warmflow.domain.req;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * 流程类别条件查询Request
 *
 * @author Levin
 */
@Data
@Schema(name = "ProcessCategoryQueryReq")
public class ProcessCategoryQueryReq {


    @Schema(description = "类别编码")
    private String code;

    @Schema(description = "类别名称")
    private String name;

    @Schema(description = "类别状态:0-禁用,1-启用")
    private Integer status;
}
