package com.wemirr.platform.ai.core.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Map;

/**
 * 检索结果模型
 *
 * @author xJh
 * @since 2025/10/13
 **/
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RetrievalResult {

    private String id;
    private String title;
    private String content;
    private Double score;
    /**
     * 检索来源: "semantic" 或 "keyword"
     */
    private String source;
    private Map<String, Object> metadata;
    private Double normalizedScore;

    public String getFormattedContent() {
        return String.format("标题: %s\n内容: %s", title, content);
    }
}
