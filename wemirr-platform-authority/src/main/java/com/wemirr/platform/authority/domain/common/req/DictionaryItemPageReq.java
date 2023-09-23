package com.wemirr.platform.authority.domain.common.req;

import com.wemirr.framework.db.mybatisplus.page.PageRequest;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * @author Levin
 */
@Data
@Schema(name = "DictionaryItemPageReq")
public class DictionaryItemPageReq extends PageRequest {

    @Schema(description = "标签")
    private String label;
    @Schema(description = "状态")
    private Boolean status;

}
