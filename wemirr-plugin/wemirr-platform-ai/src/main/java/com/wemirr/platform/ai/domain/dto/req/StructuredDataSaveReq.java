package com.wemirr.platform.ai.domain.dto.req;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.util.Map;

/**
 * 结构化数据保存请求
 *
 * @author xJh
 * @since 2025/10/20
 **/
@Data
@Schema(description = "结构化数据保存请求")
public class StructuredDataSaveReq {

    @NotBlank(message = "知识库ID不能为空")
    @Schema(description = "所属知识库ID")
    private Long kbId;
    
    @Schema(description = "租户ID")
    private String tenantId;

    @NotBlank(message = "数据类型不能为空")
    @Schema(description = "数据类型：product_param, policy, rule等")
    private String dataType;

    @NotBlank(message = "数据标题不能为空")
    @Schema(description = "数据标题/名称")
    private String title;

    @NotNull(message = "结构化数据内容不能为空")
    @Schema(description = "结构化数据内容（JSON格式）")
    private Map<String, Object> content;

    @Schema(description = "元数据")
    private Map<String, Object> metadata;
}