package com.wemirr.platform.ai.domain.dto.req;

import com.wemirr.platform.ai.core.enums.ModelType;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.util.Map;

/**
 * @author xJh
 * @since 2025/10/31
 **/
@Data
public class ModelConfigSaveReq {

    @Schema(description = "模型提供商")
    @NotBlank(message = "模型提供商不能为空")
    private String provider;

    @Schema(description = "模型类型")
    @NotNull(message = "模型类型不能为空")
    private ModelType modelType;

    @Schema(description = "模型名称")
    @NotBlank(message = "模型名称不能为空")
    private String modelName;

    @Schema(description = "API密钥")
    @NotBlank(message = "API密钥不能为空")
    private String apiKey;

    @Schema(description = "基础URL")
    private String baseUrl;

    @Schema(description = "模型配置属性")
    private Map<String, Object> variables;
}


