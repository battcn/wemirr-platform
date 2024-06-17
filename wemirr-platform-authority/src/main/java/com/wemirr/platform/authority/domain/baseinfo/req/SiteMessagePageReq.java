package com.wemirr.platform.authority.domain.baseinfo.req;

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
    private String level;
    @Schema(description = "标记")
    private Boolean mark;
}
