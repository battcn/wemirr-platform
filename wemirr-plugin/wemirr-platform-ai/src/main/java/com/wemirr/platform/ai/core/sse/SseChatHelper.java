package com.wemirr.platform.ai.core.sse;

import com.wemirr.framework.ai.core.constant.AiConstants;
import com.wemirr.platform.ai.domain.dto.req.AskReq;
import dev.langchain4j.service.TokenStream;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import java.io.IOException;
import java.time.Duration;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Consumer;

/**
 * SSE 聊天辅助服务
 *
 * @author xJh
 */
@Slf4j
@Component
public class SseChatHelper {

    private static final Duration DEFAULT_TIMEOUT = Duration.ofMinutes(5);

    private final Map<String, SseEmitter> activeEmitters = new ConcurrentHashMap<>();

    /**
     * 正在处理中的会话（用于防止重复请求）
     * key: conversationId, value: 请求时间戳
     */
    private final Map<String, Long> processingConversations = new ConcurrentHashMap<>();

    /**
     * 防重复请求的时间窗口（毫秒）
     */
    private static final long DUPLICATE_REQUEST_WINDOW_MS = 1000;

    /**
     * 检查是否为重复请求
     *
     * @param conversationId 会话ID
     * @return true 如果是重复请求
     */
    public boolean isDuplicateRequest(String conversationId) {
        long now = System.currentTimeMillis();
        Long lastRequestTime = processingConversations.get(conversationId);

        if (lastRequestTime != null && (now - lastRequestTime) < DUPLICATE_REQUEST_WINDOW_MS) {
            log.warn("[SSE] 检测到重复请求，已忽略: conversationId={}, interval={}ms",
                    conversationId, now - lastRequestTime);
            return true;
        }

        processingConversations.put(conversationId, now);
        return false;
    }

    /**
     * 标记会话处理完成
     */
    public void markConversationComplete(String conversationId) {
        processingConversations.remove(conversationId);
    }

    /**
     * 创建 SSE 连接
     */
    public SseEmitter createEmitter(String traceId) {
        SseEmitter emitter = new SseEmitter(DEFAULT_TIMEOUT.toMillis());
        activeEmitters.put(traceId, emitter);

        emitter.onCompletion(() -> {
            log.info("[SSE] Completed. traceId={}", traceId);
            activeEmitters.remove(traceId);
        });
        emitter.onTimeout(() -> {
            log.warn("[SSE] Timeout. traceId={}", traceId);
            activeEmitters.remove(traceId);
            safeComplete(emitter);
        });
        emitter.onError(throwable -> {
            log.error("[SSE] Error. traceId={}", traceId, throwable);
            activeEmitters.remove(traceId);
            safeComplete(emitter);
        });

        sendEvent(emitter, AiConstants.SSE_EVENT_START, Map.of("traceId", traceId));
        return emitter;
    }

    /**
     * 将聊天流转换为 SSE 响应
     */
    public void chatStreamToSse(AskReq askReq, SseEmitter emitter, TokenStream tokenStream,
                                Consumer<Map<String, Object>> onComplete) {
        StringBuilder responseBuilder = new StringBuilder();
        String conversationId = String.valueOf(askReq.getConversationId());

        tokenStream.onPartialResponse(token -> {
            try {
                String escapedToken = token.replace("\n", "\\n");
                responseBuilder.append(token);
                emitter.send(escapedToken);
                log.debug("Sending token to SSE: {}", escapedToken);
            } catch (IOException e) {
                log.error("Error sending token to SSE", e);
            }
        }).onCompleteResponse(response -> {
            try {
                log.info("Chat response received: {}", response);
                emitter.complete();
                Integer i = response.tokenUsage().inputTokenCount();
                Integer o = response.tokenUsage().outputTokenCount();
                Map<String, Object> result = Map.of(
                        "content", responseBuilder.toString(),
                        "inputTokens", i,
                        "outputTokens", o
                );
                if (onComplete != null) {
                    onComplete.accept(result);
                }
            } catch (Exception e) {
                log.error("Error completing SSE", e);
            } finally {
                // 标记会话处理完成，允许下一次请求
                markConversationComplete(conversationId);
            }
        }).onError(error -> {
            try {
                emitter.send(error);
                emitter.complete();
            } catch (IOException e) {
                log.error("Error sending error to SSE", e);
            } finally {
                // 出错时也要标记完成
                markConversationComplete(conversationId);
            }
        }).start();
    }

    public void sendThinking(SseEmitter emitter) {
        sendEvent(emitter, AiConstants.SSE_EVENT_THINKING, "思考中...");
    }

    public void sendError(SseEmitter emitter, String message) {
        sendEvent(emitter, AiConstants.SSE_EVENT_ERROR, message);
        safeComplete(emitter);
    }

    private void sendEvent(SseEmitter emitter, String eventName, Object data) {
        if (emitter == null) {
            return;
        }
        try {
            emitter.send(SseEmitter.event().name(eventName).data(data));
        } catch (IOException e) {
            log.warn("Failed to send SSE event: {}", eventName, e);
            safeComplete(emitter);
        }
    }

    private void safeComplete(SseEmitter emitter) {
        if (emitter != null) {
            try {
                emitter.complete();
            } catch (Exception ignored) {
            }
        }
    }

    public int getActiveCount() {
        return activeEmitters.size();
    }

    public boolean isActive(String traceId) {
        return activeEmitters.containsKey(traceId);
    }
}