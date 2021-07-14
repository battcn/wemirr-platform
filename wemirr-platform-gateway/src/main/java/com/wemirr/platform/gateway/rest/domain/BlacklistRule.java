package com.wemirr.platform.gateway.rest.domain;

import lombok.Data;

import java.time.LocalDateTime;

/**
 * @author Levin
 */
@Data
public class BlacklistRule {

    private String id;
    private String ip;
    private Long visits;
    private String method;
    private String path;
    private Boolean status;
    private LocalDateTime startTime;
    private LocalDateTime endTime;
    private String description;
    private LocalDateTime createdTime;
}
