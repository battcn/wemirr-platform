package com.wemirr.platform.ai.domain.dto.req;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * @author xJh
 * @since 2025/10/28
 **/
@Data
@Schema(description = "文档分块更新请求")
public class ChunkSaveReq {

    @Schema(description = "分块ID")
    private Long id;

    @Schema(description = "分块内容")
    private String content;

}
