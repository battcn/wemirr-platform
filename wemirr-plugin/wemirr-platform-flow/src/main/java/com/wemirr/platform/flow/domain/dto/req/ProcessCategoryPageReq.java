
package com.wemirr.platform.flow.domain.dto.req;


import com.wemirr.framework.db.mybatisplus.page.PageRequest;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

/**
 * 流程类别分页查询Request
 *
 * @author Levin
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@Schema(name = "ProcessCategoryPageReq")
public class ProcessCategoryPageReq extends PageRequest {

    @Schema(description = "类别名称")
    private String name;
    @Schema(description = "类别编码")
    private String code;
    @Schema(description = "类别状态:0-禁用,1-启用")
    private Integer status;

}
