package com.wemirr.platform.ai.domain.dto.req;

import com.wemirr.framework.db.mybatisplus.page.PageRequest;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * @author xJh
 * @since 2025/11/4
 **/
@Data
@Schema(description = "智能体分页查询请求")
public class ChatAgentPageReq extends PageRequest {

    @Schema(description = "智能体ID（修改时使用）")
    private Long id;

    @Schema(description = "智能体名称")
    private String name;

    @Schema(description = "用户ID")
    private Long userId;
}
