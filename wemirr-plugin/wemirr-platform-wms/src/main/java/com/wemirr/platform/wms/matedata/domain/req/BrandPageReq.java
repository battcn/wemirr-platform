package com.wemirr.platform.wms.matedata.domain.req;

import com.wemirr.framework.db.mybatisplus.page.PageRequest;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 品牌分页查询请求数据
 *
 * @author ddCat
 * @since 2024-06-18
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Schema(name = "BrandPageReq", description = "品牌分页查询请求数据")
public class BrandPageReq extends PageRequest {

    @Schema(description = "品牌编码")
    private String code;

    @Schema(description = "品牌名称")
    private String name;

    @Schema(description = "状态")
    private String status;
}
