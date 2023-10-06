package com.wemirr.platform.authority.domain.tenant.resp;

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

    private String label;

    private String value;

}
