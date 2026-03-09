package com.wemirr.platform.ai.domain.dto.req;

import com.wemirr.platform.ai.core.enums.ChunkType;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Map;

/**
 * 文本分片创建请求
 *
 * @author xJh
 * @since 2025/12/28
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "文本分片创建请求")
public class TextChunkCreateReq {

    @NotNull(message = "知识库ID不能为空")
    @Schema(description = "知识库ID")
    private Long kbId;

    @NotNull(message = "知识条目ID不能为空")
    @Schema(description = "知识条目ID")
    private Long itemId;

    @NotBlank(message = "文本内容不能为空")
    @Schema(description = "文本内容")
    private String text;

    @NotNull(message = "分片类型不能为空")
    @Schema(description = "分片类型")
    private ChunkType chunkType;

    @Schema(description = "元数据")
    private Map<String, Object> metadata;
}
