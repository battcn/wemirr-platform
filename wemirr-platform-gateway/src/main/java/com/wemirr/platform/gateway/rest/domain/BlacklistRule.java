package com.wemirr.platform.gateway.rest.domain;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.time.Instant;

/**
 * @author Levin
 */
@Data
public class BlacklistRule {

    @Schema(description = "ID")
    private String id;
    @Schema(description = "IP")
    private String ip;
    @Schema(description = "访问量")
    private Long visits;
    @Schema(description = "请求方式")
    private String method;
    @Schema(description = "请求路径")
    private String path;
    @Schema(description = "状态")
    private Boolean status;
    @Schema(description = "开始时间")
    private Instant startTime;
    @Schema(description = "结束时间")
    private Instant endTime;
    @Schema(description = "描述")
    private String description;
    @Schema(description = "创建时间")
    private Instant createdTime;
}
