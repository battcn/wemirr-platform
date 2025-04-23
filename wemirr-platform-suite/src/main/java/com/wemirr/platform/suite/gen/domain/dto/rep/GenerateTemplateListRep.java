package com.wemirr.platform.suite.gen.domain.dto.rep;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * @author xiao1
 * @since 2024-12
 */
@Data
public class GenerateTemplateListRep {

    /*
        id
     */
    private Long id;


    /**
     * 模板名称
     */
    @Schema(description = "模板名称")
    private String name;


}
