package com.wemirr.platform.iam.feign.domain.req;

import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;

/**
 * @author Levin
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserFeignPageReq {

    @Schema(description = "用户名")
    private String username;

    @Schema(description = "昵称")
    private String nickName;

    @Schema(description = "邮箱")
    private String email;

    private Instant startTime;

    private Instant endTime;

    @Schema(description = "当前页码", example = "1")
    @Parameter(description = "当前页码", example = "1")
    private long current = 1;

    @Schema(description = "分页大小", example = "20")
    @Parameter(description = "分页大小", example = "20")
    private long size = 20;


}
