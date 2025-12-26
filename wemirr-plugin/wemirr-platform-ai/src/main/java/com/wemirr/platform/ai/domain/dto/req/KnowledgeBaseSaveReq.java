package com.wemirr.platform.ai.domain.dto.req;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.extension.handlers.JacksonTypeHandler;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

import java.util.Map;

/**
 * @author xJh
 * @since 2025/10/20
 **/
@Data
@Schema(description = "知识库保存请求")
public class KnowledgeBaseSaveReq {

    @NotBlank(message = "知识库名称不能为空")
    @Schema(description = "知识库名称")
    private String name;

    @Schema(description = "知识库描述")
    private String description;

    @Schema(description = "相似结果数量")
    private Integer topK;

    @Schema(description = "相似度分数阈值，仅返回分数高于此值的结果。[-1, 1]，一般 >0.5 表示有一定相关性")
    private Double minScore;

    @Schema(title = "文档切割时重叠数量(根据token计算)")
    @TableField("ingest_max_overlap")
    private Integer ingestMaxOverlap;

    @Schema(title = "文档切割时最大长度(根据token计算)")
    @TableField("ingest_max_length")
    private Integer ingestMaxLength;

    @Schema(title = "文档召回最大数量")
    @TableField("retrieve_max_results")
    private Integer retrieveMaxResults;

    @Schema(description = "聊天模型ID")
    private Long chatModelId;

    @Schema(description = "向量模型ID")
    private Long embeddingModelId;

    @Schema(description = "元数据")
    @TableField(typeHandler = JacksonTypeHandler.class)
    private Map<String, Object> metadata;

}
