package com.wemirr.platform.ai.core.provider.vectorStore;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;
import java.util.Map;

/**
 * @author xJh
 * @since 2025/10/13
 **/
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Document {

    private String id;
    private String title;
    private String content;
    private String source;
    private Map<String, Object> metadata;
    private Instant createAt;
}
