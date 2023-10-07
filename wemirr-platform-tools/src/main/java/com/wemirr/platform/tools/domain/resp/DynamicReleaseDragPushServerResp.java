package com.wemirr.platform.tools.domain.resp;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;
import java.util.List;

/**
 * @author Levin
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DynamicReleaseDragPushServerResp {

    private Long id;
    private String method;
    private String url;
    private String secret;
    private List<String> opt;
    private Boolean locked;
    @Schema(description = "创建时间")
    private Instant createdTime;


}
