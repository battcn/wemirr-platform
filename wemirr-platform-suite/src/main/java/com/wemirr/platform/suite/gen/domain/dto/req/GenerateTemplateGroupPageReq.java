package com.wemirr.platform.suite.gen.domain.dto.req;

import com.wemirr.framework.db.mybatisplus.page.PageRequest;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author xiao1
 * @since 2024-12
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Schema(name = "GenerateTemplateGroupPageReq")
public class GenerateTemplateGroupPageReq extends PageRequest {

    @Schema(name = "name", description = "分组名称")
    private String name;
}
