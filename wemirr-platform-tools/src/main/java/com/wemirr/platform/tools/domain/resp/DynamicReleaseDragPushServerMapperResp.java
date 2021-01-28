package com.wemirr.platform.tools.domain.resp;

import lombok.Data;

/**
 * @author Levin
 */
@Data
public class DynamicReleaseDragPushServerMapperResp {

    private String model;
    private String method;
    private Long pushId;
    private Long dragId;
    private String url;

}
