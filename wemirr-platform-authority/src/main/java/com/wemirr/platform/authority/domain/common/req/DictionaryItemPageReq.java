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
@Schema(name = "DictionaryItemPageReq")
public class DictionaryItemPageReq extends PageRequest {

    @Schema(description = "标签")
    private String label;

    @Schema(description = "状态")
    private Boolean status;

}
