package com.wemirr.platform.authority.domain.req;

import com.wemirr.framework.db.page.PageRequest;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author Levin
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class TenantPageReq extends PageRequest {

    private String name;
    private String code;
    private Integer type;
    private Integer status;
    private Integer industry;

    private Long provinceId;
    private Long cityId;
    private Long districtId;
}
