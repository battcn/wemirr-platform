package com.wemirr.platform.ai.domain.dto.rep;

import com.wemirr.platform.ai.core.enums.ModelType;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.util.Map;

/**
 * @author xJh
 * @since 2025/10/31
 **/
@Data
public class ModelDetailRep {

    private Long id;

    @Schema(description = "模型提供商")
    private String provider;

    @Schema(description = "模型类型")
    private ModelType type;

    @Schema(description = "模型名称")
    private String name;

    @Schema(description = "API密钥")
    private String apiKey;

    @Schema(description = "基础URL")
    private String baseUrl;

    @Schema(description = "模型配置属性")
    private Map<String, Object> variables;

}


