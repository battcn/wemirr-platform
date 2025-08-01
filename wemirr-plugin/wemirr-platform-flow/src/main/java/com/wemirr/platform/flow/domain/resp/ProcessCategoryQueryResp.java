
package com.wemirr.platform.flow.domain.resp;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * 流程类别查询Response
 *
 * @author Levin
 */
@Data
@Schema(name = "ProcessCategoryQueryResp")
public class ProcessCategoryQueryResp {


    @Schema(description = "ID")
    private String id;

    @Schema(description = "类别编码(唯一)")
    private String code;

    @Schema(description = "类别名称")
    private String name;

    @Schema(description = "类别状态:0-禁用,1-启用。默认0")
    private Integer statue;

}
