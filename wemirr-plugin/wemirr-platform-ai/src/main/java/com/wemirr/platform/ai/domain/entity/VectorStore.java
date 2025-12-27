package com.wemirr.platform.ai.domain.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.handlers.JacksonTypeHandler;
import com.wemirr.framework.commons.entity.SuperEntity;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.util.Map;

/**
 * 向量存储
 *
 * @author xJh
 * @since 2025/10/17
 */
@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@TableName("ai_vector_store")
@EqualsAndHashCode(callSuper = true)
@Schema(description = "向量存储")
public class VectorStore extends SuperEntity<Long> {

    @Schema(description = "所属知识库ID")
    private String kbId;

    @Schema(description = "文本内容")
    private String textContent;

    @Schema(description = "向量数据")
    private String vectorData;

    @Schema(description = "向量维度")
    @TableField("dimension")
    private Integer dimension;

    @Schema(description = "向量存储类型")
    @TableField("store_type")
    private String storeType;

    @Schema(description = "元数据")
    @TableField(value = "metadata", typeHandler = JacksonTypeHandler.class)
    private Map<String, Object> metadata;

    @Schema(description = "租户ID")
    private String tenantId;
}