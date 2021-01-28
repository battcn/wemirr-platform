package com.wemirr.platform.tools.domain.req;

import lombok.Data;

/**
 * @author Levin
 */
@Data
public class DynamicReleaseDragReq implements java.io.Serializable {

    private String model;
    private String label;
    private Boolean locked;
    private String description;

}
