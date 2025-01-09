package com.wemirr.platform.suite.online.domain.req;

import com.wemirr.framework.db.mybatisplus.page.PageRequest;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author levin
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Schema(name = "OnlineFormDesignerPageReq")
public class OnlineFormDesignerPageReq extends PageRequest {

    @Schema(description = "定义KEY")
    private String definitionKey;

    @Schema(description = "标题")
    private String title;


}
