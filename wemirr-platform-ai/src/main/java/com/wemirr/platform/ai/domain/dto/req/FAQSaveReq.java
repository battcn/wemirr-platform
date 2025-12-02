package com.wemirr.platform.ai.domain.dto.req;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

import java.util.Map;

/**
 * FAQ保存请求
 *
 * @author xJh
 * @since 2025/10/20
 **/
@Data
@Schema(description = "FAQ保存请求")
public class FAQSaveReq {

    @NotBlank(message = "知识库ID不能为空")
    @Schema(description = "所属知识库ID")
    private Long kbId;
    
    @Schema(description = "租户ID")
    private String tenantId;

    @NotBlank(message = "问题不能为空")
    @Schema(description = "问题")
    private String question;

    @NotBlank(message = "答案不能为空")
    @Schema(description = "答案")
    private String answer;

    @Schema(description = "元数据")
    private Map<String, Object> metadata;
}