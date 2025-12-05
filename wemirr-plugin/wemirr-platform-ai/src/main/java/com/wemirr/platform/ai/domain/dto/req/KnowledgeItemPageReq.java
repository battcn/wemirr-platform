package com.wemirr.platform.ai.domain.dto.req;

import com.wemirr.framework.db.mybatisplus.page.PageRequest;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 知识条目分页查询请求
 *
 * @author xJh
 * @since 2025/10/20
 **/
@Data
@EqualsAndHashCode(callSuper = true)
@Schema(description = "知识条目分页查询请求")
public class KnowledgeItemPageReq extends PageRequest {

    @Schema(description = "知识库ID")
    private String kbId;

    @Schema(description = "知识条目类型：DOCUMENT, QA_PAIR, STRUCTURED, TEXT_SNIPPET")
    private String type;
    
    @Schema(description = "状态：PENDING, PROCESSING, PROCESSED, FAILED")
    private String status;
}