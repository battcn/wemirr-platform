package com.wemirr.platform.authority.domain.common.req;

import com.wemirr.framework.db.mybatisplus.page.PageRequest;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author Levin
 */
@EqualsAndHashCode(callSuper = true)
@Data
@Schema(name = "DictionaryPageReq")
public class DictionaryPageReq extends PageRequest {

    private String name;
    private String code;
    private Boolean status;


}
