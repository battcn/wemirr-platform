package com.wemirr.platform.authority.domain.common.req;

import com.wemirr.framework.db.mybatisplus.page.PageRequest;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author Levin
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Schema(name = "SiteMessagePageReq")
public class SiteMessagePageReq extends PageRequest {

    @Schema(description = "标题")
    private String title;
    @Schema(description = "级别")
    private Integer level;
    @Schema(description = "类型")
    private Integer type;


}
