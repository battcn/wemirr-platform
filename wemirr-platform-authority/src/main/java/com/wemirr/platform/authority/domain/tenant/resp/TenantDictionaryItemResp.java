package com.wemirr.platform.authority.domain.tenant.resp;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author lijiale
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TenantDictionaryItemResp {

    @Schema(description = "字典名")
    private String label;

    @Schema(description = "字典值")
    private String value;

}
