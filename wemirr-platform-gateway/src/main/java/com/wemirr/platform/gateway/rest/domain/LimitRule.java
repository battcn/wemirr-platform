package com.wemirr.platform.gateway.rest.domain;

import lombok.Data;

import java.time.LocalDateTime;

/**
 * @author Levin
 */
@Data
public class LimitRule {

    private String id;
    private String path;
    private String total;
    private String description;
    private boolean block;
    private LocalDateTime createdTime;

}
