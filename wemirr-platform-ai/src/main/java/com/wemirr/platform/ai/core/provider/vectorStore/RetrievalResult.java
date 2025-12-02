package com.wemirr.platform.ai.core.provider.vectorStore;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Map;

/**
 * @author xJh
 * @date 2025/10/13
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
    private String source; // "semantic" 或 "keyword"
    private Map<String, Object> metadata;
    private Double normalizedScore;

    public String getFormattedContent() {
        return String.format("标题: %s\n内容: %s", title, content);
    }
}
