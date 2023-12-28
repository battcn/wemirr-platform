package com.wemirr.framework.commons.entity;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author Levin
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class KeyVal {

    @Schema(description = "字典名")
    private Object label;

    @Schema(description = "字典值")
    private Object value;


}
