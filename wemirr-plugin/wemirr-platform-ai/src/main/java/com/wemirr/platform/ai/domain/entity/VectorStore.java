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
 * @author xJh
 * @since 2025/10/17
 **/
@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@TableName("ai_kb_vector_store")
@EqualsAndHashCode(callSuper = true)
public class VectorStore extends SuperEntity<Long> {

    /**
     * 所属知识库ID
     */
    @TableField("kb_id")
    private String kbId;

    /**
     * 文本内容
     */
    @TableField("text")
    private String text;

    /**
     * 向量数据
     */
    @TableField("vector")
    private String vector;

    /**
     * 向量维度
     */
    @TableField("dimension")
    private Integer dimension;

    /**
     * 向量存储类型
     */
    @TableField("store_type")
    private String storeType;

    /**
     * 元数据
     */
    @TableField(value = "metadata", typeHandler = JacksonTypeHandler.class)
    private Map<String, Object> metadata;

    @Schema(description = "租户ID")
    private String tenantId;
}