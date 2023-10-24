package com.wemirr.platform.authority.domain.baseinfo.req;

import com.wemirr.framework.db.mybatisplus.page.PageRequest;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 实体类
 * 岗位
 * </p>
 *
 * @author Levin
 * @since 2019-07-28
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Schema(name = "StationPageReq", description = "岗位")
public class StationPageReq extends PageRequest {

    @Schema(description = "名称")
    private String name;
    @Schema(description = "组织ID")
    private Long orgId;
    @Schema(description = "类型")
    private Integer type;
    @Schema(description = "状态")
    private Boolean status;

}
