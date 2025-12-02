package com.wemirr.platform.ai.domain.dto.req;

import com.wemirr.framework.db.mybatisplus.page.PageRequest;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author xJh
 * @since 2025/10/20
 **/
@Data
@EqualsAndHashCode(callSuper = true)
@Schema(description = "知识库分页查询请求")
public class KnowledgeBasePageReq extends PageRequest {

    @Schema(description = "知识库名称")
    private String name;

    @Schema(description = "租户ID")
    private String tenantId;
}
