package com.wemirr.platform.wms.matedata.domain.req;

import com.wemirr.framework.db.mybatisplus.page.PageRequest;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 供应商分页查询请求数据
 * @author ddCat
 * @since 2024-07-01
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Schema(name = "SupplierPageReq", description = "供应商分页查询请求数据")
public class SupplierPageReq extends PageRequest {

    @Schema(description = "供应商编码")
    private String code;

    @Schema(description = "供应商名称")
    private String name;

    @Schema(description = "联系人姓名")
    private String contactName;

    @Schema(description = "统一信用代码")
    private String creditCode;
}
