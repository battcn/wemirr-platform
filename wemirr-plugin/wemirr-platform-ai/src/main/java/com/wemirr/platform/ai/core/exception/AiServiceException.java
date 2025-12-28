package com.wemirr.platform.ai.core.exception;

import com.wemirr.framework.ai.core.exception.AiException;
import lombok.Getter;

import java.io.Serial;

/**
 * AI 服务异常（业务层）
 * <p>
 * 继承自 ai-spring-boot-starter 的 AiException，支持错误码枚举
 * </p>
 *
 * @author Levin
 * @since 2025/12/27
 */
@Getter
public class AiServiceException extends AiException {

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * 错误码枚举
     */
    private AiErrorCode errorCode;

    /**
     * 详细信息
     */
    private String detail;

    public AiServiceException(String message) {
        super(message);
    }

    public AiServiceException(String message, Throwable cause) {
        super(message, cause);
    }

    public AiServiceException(int code, String message) {
        super(code, message);
    }

    public AiServiceException(AiErrorCode errorCode) {
        super(errorCode.getCode(), errorCode.getMessage());
        this.errorCode = errorCode;
    }

    public AiServiceException(AiErrorCode errorCode, String detail) {
        super(errorCode.getCode(), errorCode.getMessage() + ": " + detail);
        this.errorCode = errorCode;
        this.detail = detail;
    }

    public AiServiceException(AiErrorCode errorCode, Throwable cause) {
        super(errorCode.getMessage(), cause);
        this.errorCode = errorCode;
        this.detail = cause.getMessage();
    }

    public AiServiceException(AiErrorCode errorCode, String detail, Throwable cause) {
        super(errorCode.getMessage() + ": " + detail, cause);
        this.errorCode = errorCode;
        this.detail = detail;
    }

    /**
     * 获取完整错误码
     */
    public String getFullCode() {
        return errorCode != null ? errorCode.getFullCode() : "AI_UNKNOWN";
    }

    // ==================== 快捷创建方法 ====================

    public static AiServiceException of(AiErrorCode errorCode) {
        return new AiServiceException(errorCode);
    }

    public static AiServiceException of(AiErrorCode errorCode, String detail) {
        return new AiServiceException(errorCode, detail);
    }

    public static AiServiceException of(AiErrorCode errorCode, Throwable cause) {
        return new AiServiceException(errorCode, cause);
    }

    public static AiServiceException modelNotFound(Long modelId) {
        return new AiServiceException(AiErrorCode.MODEL_NOT_FOUND, "modelId=" + modelId);
    }

    public static AiServiceException knowledgeBaseNotFound(Long kbId) {
        return new AiServiceException(AiErrorCode.KNOWLEDGE_BASE_NOT_FOUND, "kbId=" + kbId);
    }

    public static AiServiceException agentNotFound(Long agentId) {
        return new AiServiceException(AiErrorCode.AGENT_NOT_FOUND, "agentId=" + agentId);
    }

    public static AiServiceException vectorizationFailed(String reason) {
        return new AiServiceException(AiErrorCode.VECTORIZATION_FAILED, reason);
    }

    public static AiServiceException retrievalFailed(String reason) {
        return new AiServiceException(AiErrorCode.RETRIEVAL_FAILED, reason);
    }
}
