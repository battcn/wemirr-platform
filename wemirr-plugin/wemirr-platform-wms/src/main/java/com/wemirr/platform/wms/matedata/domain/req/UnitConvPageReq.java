package com.wemirr.platform.wms.matedata.domain.req;

import com.wemirr.framework.db.mybatisplus.page.PageRequest;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 基本计量单位转换分页查询请求数据
 * @author ddCat
 * @since 2024-06-24
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Schema(name = "UnitConvPageReq", description = "基本计量单位转换分页查询请求数据")
public class UnitConvPageReq extends PageRequest {


    @Schema(description = "被转换的计量单位ID")
    private Long unitId;


    @Schema(description = "被转换的计量单位ID")
    private Long convUnitId;

}
