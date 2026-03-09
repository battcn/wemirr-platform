package com.wemirr.platform.ai.core.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;
import java.util.Map;

/**
 * 文档模型
 *
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
