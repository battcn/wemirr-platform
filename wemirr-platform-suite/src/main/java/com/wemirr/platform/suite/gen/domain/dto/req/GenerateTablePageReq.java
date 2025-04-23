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
@Schema(name = "GenerateTableReq")
public class GenerateTablePageReq extends PageRequest {

    @Schema(name = "name", description = "表名")
    private String name;
}
