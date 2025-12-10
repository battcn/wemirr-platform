package com.wemirr.platform.iam.tenant.domain.dto.resp;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.time.Instant;
import java.util.List;

/**
 * @author Levin
 */
@Data
public class PlanDefinitionDetailResp {

    @Schema(description = "ID")
    private Long id;

    @Schema(description = "套餐编码")
    private String code;

    @Schema(description = "套餐名称")
    private String name;

    @Schema(description = "套餐Logo链接")
    private String logo;

    @Schema(description = "套餐详情")
    private String description;

    @Schema(description = "启用状态")
    private Boolean status;

    @Schema(description = "创建人")
    private String createName;

    @Schema(description = "创建时间")
    private Instant createTime;

    @Schema(description = "套餐关联资源ID")
    private List<Long> itemIdList;
}
