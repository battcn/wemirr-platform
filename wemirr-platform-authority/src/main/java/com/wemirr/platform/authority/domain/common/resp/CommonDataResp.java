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
public class CommonDataResp {

    @Schema(description = "ID")
    private Long id;
    @Schema(description = "名称")
    private String name;
}
