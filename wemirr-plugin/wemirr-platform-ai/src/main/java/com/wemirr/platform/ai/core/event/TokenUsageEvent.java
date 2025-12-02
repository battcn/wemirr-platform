package com.wemirr.platform.ai.core.event;
import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * @author xJh
 * @since 2025/9/29
 **/
@Data
@AllArgsConstructor
public class TokenUsageEvent {

    private int promptTokens;
    private int completionTokens;
    private int totalTokens;

}
