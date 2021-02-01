package com.wemirr.platform.gateway.rest.domain;

import lombok.Data;

import java.time.LocalDateTime;

/**
 * @author Levin
 */
@Data
public class BlacklistRule {

    private String ip;
    private String description;
    private LocalDateTime createdTime;
}
