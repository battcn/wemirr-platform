package com.wemirr.platform.authority.domain.common.resp;

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
public class DictResp {

    @Schema(description = "字典值")
    private Long value;
    @Schema(description = "字典名")
    private String label;

}
