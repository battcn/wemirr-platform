package com.wemirr.platform.ai.domain.dto.req;

import com.wemirr.framework.db.mybatisplus.page.PageRequest;
import com.wemirr.platform.ai.core.enums.ModelType;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author xJh
 * @since 2025/10/31
 **/
@Data
@EqualsAndHashCode(callSuper = true)
@Schema(name = "ModelConfigPageReq")
public class ModelConfigPageReq extends PageRequest {

    @Schema(description = "模型提供商")
    private String provider;

    @Schema(description = "模型类型")
    private ModelType modelType;

    @Schema(description = "模型名称")
    private String modelName;
}


