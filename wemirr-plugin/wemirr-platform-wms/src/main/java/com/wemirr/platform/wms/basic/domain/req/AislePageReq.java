package com.wemirr.platform.wms.basic.domain.req;

import com.wemirr.framework.db.mybatisplus.page.PageRequest;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 巷道分页查询请求数据
 *
 * @author ddCat
 * @since 2024-06-17
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Schema(name = "AislePageReq", description = "巷道分页查询请求数据")
public class AislePageReq extends PageRequest {

    @Schema(description = "巷道编号")
    private String code;

    @Schema(description = "巷道名称")
    private String name;

    @Schema(description = "是否启用")
    private String status;
}
