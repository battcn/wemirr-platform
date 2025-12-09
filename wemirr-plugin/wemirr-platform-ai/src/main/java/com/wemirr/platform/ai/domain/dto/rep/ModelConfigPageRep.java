package com.wemirr.platform.ai.domain.dto.rep;

import com.wemirr.framework.boot.sensitive.Sensitive;
import com.wemirr.platform.ai.core.enums.ModelType;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.time.Instant;

/**
 * @author xJh
 * @since 2025/10/31
 **/
@Data
public class ModelConfigPageRep {

    @Schema(description = "ID")
    private Long id;

    @Schema(description = "模型提供商")
    private String provider;

    @Schema(description = "模型类型")
    private ModelType modelType;

    @Schema(description = "模型名称")
    private String modelName;

    @Schema(description = "基础URL")
    private String baseUrl;

    @Sensitive(type = Sensitive.Type.PASSWORD)
    @Schema(description = "API密钥")
    private String apiKey;

    @Schema(description = "创建人")
    private String createName;

    @Schema(description = "创建时间")
    private Instant createTime;
}


