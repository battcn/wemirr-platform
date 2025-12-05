package com.wemirr.platform.ai.core.sse;

import com.wemirr.platform.ai.core.event.SseEventName;
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
 * @author xJh
 */
@Slf4j
@Component
public class SseChatHelper {

    private static final Duration DEFAULT_TIMEOUT = Duration.ofMinutes(5);

    // traceId -> emitter 映射（用于日志和调试）
    private final Map<String, SseEmitter> activeEmitters = new ConcurrentHashMap<>();

//    private final ScheduledExecutorService cleanupScheduler = Executors.newSingleThreadScheduledExecutor(r -> {
//        Thread t = new Thread(r, "sse-emitter-cleanup");
//        t.setDaemon(true);
//        return t;
//    });
//
//    public SseChatHelper() {
//        // 定期清理（可选，主要依赖 onCompletion）
//        cleanupScheduler.scheduleAtFixedRate(activeEmitters::clear, 10, 10, TimeUnit.MINUTES);
//    }

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

        sendEvent(emitter, SseEventName.START, Map.of("traceId", traceId));
        return emitter;
    }

    /**
     * 将聊天流转换为 SSE 响应
     */
    public void chatStreamToSse(AskReq askReq, SseEmitter emitter, TokenStream tokenStream,
                                Consumer<Map<String, Object>> onComplete) {
        StringBuilder responseBuilder = new StringBuilder();

        tokenStream.onPartialResponse(token -> {
            try {
                // 转义处理：将换行符转换为可见字符或特殊标记
                // 方转义为\n字符
                String escapedToken = token.replace("\n", "\\n");
                responseBuilder.append(token);  // 原始数据保留换行符
                emitter.send(escapedToken);     // 发送转义后的数据
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
                // 发布token使用事件
//                tokenUsagePublisher.publishTokenUsage(askReq.getConversationId(), userId, i, o); // TODO: Calculate actual token usage
                // 调用完成回调
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
            }
        }).onError(error -> {
            try {
                emitter.send(error);
                emitter.complete();
            } catch (IOException e) {
                log.error("Error sending error to SSE", e);
            }
        }).start();
    }

    // --- 通用消息发送 ---

    public void sendThinking(SseEmitter emitter) {
        sendEvent(emitter, SseEventName.THINKING, "思考中...");
    }

    public void sendError(SseEmitter emitter, String message) {
        sendEvent(emitter, SseEventName.ERROR, message);
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
                // ignore
            }
        }
    }

    // --- 工具方法 ---

    public int getActiveCount() {
        return activeEmitters.size();
    }

    public boolean isActive(String traceId) {
        return activeEmitters.containsKey(traceId);
    }

    // --- 生命周期 ---

//    @jakarta.annotation.PreDestroy
//    public void destroy() {
//        log.info("[SSE] Shutting down, closing {} connections", activeEmitters.size());
//        activeEmitters.values().forEach(this::safeComplete);
//        activeEmitters.clear();
//        cleanupScheduler.shutdown();
//    }
}