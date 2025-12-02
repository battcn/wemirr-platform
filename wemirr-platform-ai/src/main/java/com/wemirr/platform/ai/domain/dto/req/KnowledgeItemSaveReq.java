package com.wemirr.platform.ai.domain.dto.req;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.util.Map;

/**
 * 知识条目保存请求
 *
 * @author xJh
 * @since 2025/10/20
 **/
@Data
@Schema(description = "知识条目保存请求")
public class KnowledgeItemSaveReq {

    @NotBlank(message = "知识库ID不能为空")
    @Schema(description = "所属知识库ID")
    private Long kbId;
    
    @Schema(description = "租户ID")
    private String tenantId;
    
    @NotBlank(message = "知识条目类型不能为空")
    @Schema(description = "知识条目类型：DOCUMENT, QA_PAIR, STRUCTURED, TEXT_SNIPPET")
    private String type;
    
    @Schema(description = "来源ID")
    private String sourceId;
    
    @NotNull(message = "结构化内容不能为空")
    @Schema(description = "结构化内容")
    private Map<String, Object> content;
    
    @Schema(description = "元数据")
    private Map<String, Object> metadata;
}